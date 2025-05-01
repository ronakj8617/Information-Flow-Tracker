// const graphData = [
//     { source: "UserService", target: "ReportService", sourceTaint: "Confidential", targetTaint: "Public" },
//     { source: "ReportService", target: "AnalyticsService", sourceTaint: "Public", targetTaint: "Public" },
//     { source: "PaymentService", target: "BankingService", sourceTaint: "Restricted", targetTaint: "Restricted" },
//     { source: "UserService", target: "PaymentService", sourceTaint: "Confidential", targetTaint: "Internal" }
// ];

const taintColors = {
    "CONFIDENTIAL": "red",
    "RESTRICTED": "orange",
    "INTERNAL": "yellow",
    "PUBLIC": "green"
};
fetch('/api/flows/graph')
    .then(response => response.json())
    .then(data => renderGraph(data))
    .catch(err => console.error("Failed to load data", err));

function renderGraph(data) {
    console.log(data)
    const nodesMap = new Map();
    const links = [];

    data.forEach(d => {
        if (!nodesMap.has(d.source)) nodesMap.set(d.source, { id: d.source, taint: d.sourceTaint });
        if (!nodesMap.has(d.target)) nodesMap.set(d.target, { id: d.target, taint: d.targetTaint });

        const violation = d.sourceTaint === "Confidential" && d.targetTaint !== "Confidential";
        links.push({ source: d.source, target: d.target, violation });
    });

    const nodes = Array.from(nodesMap.values());

    const width = window.innerWidth;
    const height = window.innerHeight;

    const svg = d3.select("#graph")
        .attr("width", width)
        .attr("height", height)
        .call(d3.zoom().on("zoom", (e) => {
            svg.select('g').attr("transform", e.transform);
        }))
        .append("g");

    const simulation = d3.forceSimulation(nodes)
        .force("link", d3.forceLink(links).id(d => d.id).distance(200))
        .force("charge", d3.forceManyBody().strength(-400))
        .force("center", d3.forceCenter(width / 2, height / 2));

    svg.append('defs').append('marker')
        .attr('id', 'arrowhead')
        .attr('viewBox', '-0 -5 10 10')
        .attr('refX', 23)
        .attr('refY', 0)
        .attr('orient', 'auto')
        .attr('markerWidth', 6)
        .attr('markerHeight', 6)
        .attr('xoverflow', 'visible')
        .append('svg:path')
        .attr('d', 'M 0,-5 L 10 ,0 L 0,5')
        .attr('fill', '#999')
        .style('stroke','none');

    const link = svg.append("g")
        .selectAll("line")
        .data(links)
        .enter().append("line")
        .attr("stroke", d => d.violation ? "red" : "#999")
        .attr("stroke-width", 2)
        .attr("marker-end", "url(#arrowhead)");

    const node = svg.append("g")
        .selectAll("circle")
        .data(nodes)
        .enter().append("circle")
        .attr("r", 18)
        .attr("fill", d => taintColors[d.taint])
        .attr("stroke", "#fff")
        .attr("stroke-width", 2)
        .call(d3.drag()
            .on("start", dragStart)
            .on("drag", dragging)
            .on("end", dragEnd));

    const tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")
        .style("opacity", 0);

    node.on("mouseover", (event, d) => {
        tooltip.transition().duration(200).style("opacity", .9);
        tooltip.html(`<strong>${d.id}</strong><br/>Taint: ${d.taint}`)
            .style("left", (event.pageX + 5) + "px")
            .style("top", (event.pageY - 28) + "px");
    }).on("mouseout", () => {
        tooltip.transition().duration(500).style("opacity", 0);
    });

    simulation.on("tick", () => {
        link
            .attr("x1", d => d.source.x)
            .attr("y1", d => d.source.y)
            .attr("x2", d => d.target.x)
            .attr("y2", d => d.target.y);

        node
            .attr("cx", d => d.x)
            .attr("cy", d => d.y);
    });

    function dragStart(event) {
        if (!event.active) simulation.alphaTarget(0.3).restart();
        event.subject.fx = event.subject.x;
        event.subject.fy = event.subject.y;
    }

    function dragging(event) {
        event.subject.fx = event.x;
        event.subject.fy = event.y;
    }

    function dragEnd(event) {
        if (!event.active) simulation.alphaTarget(0);
        event.subject.fx = null;
        event.subject.fy = null;
    }
}

// renderGraph(graphData);