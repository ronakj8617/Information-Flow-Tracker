# 🛡️ Information Flow Tracker (IFT)

A prototype system that implements **information flow tracking** for Java-based component systems using **Spring Boot** and **MongoDB**. This project aligns with ongoing research in **unified def-site and use-site security policies** for component-based software systems.

---

## 📚 Research Motivation

Modern software systems are built by composing components from diverse sources—some trusted, some not. These systems often face security risks from **untrusted third-party code** and lack mechanisms to control **how data flows** across components.

This prototype explores how we can **track and control information flow** between software components to prevent **unauthorized propagation of sensitive data**, laying the groundwork for enforcing **unified security policies** at both:

- **Def-site (Component Developer)**: Low-level variable tracking
- **Use-site (Application Integrator)**: High-level data usage constraints

---

## 🎯 Objectives

- Track flow of **sensitive or tainted data** across backend services.
- Log and visualize data movement between **defining components (def-site)** and **using components (use-site)**.
- Lay the foundation for implementing **static/dynamic enforcement** of information flow policies.

---

## ⚙️ Features

- 🔄 **Dynamic Tracking** of sensitive data at runtime via hooks and interceptors.
- 🏷️ **Taint Labels** to classify and trace critical data (e.g., `PERSONAL`, `PUBLIC`, `INTERNAL`,`CONFIDENTIAL`).
- 📈 **Flow Logging** to MongoDB for analyzing how and where sensitive data moves.
- 🧠 **Modular Architecture** based on Spring Boot microservices.
- 🌐 **RESTful APIs** to simulate data access between components.
- 🧩 **Policy Enforcement Points** (in progress): where flows can be restricted based on security rules.
- 📊 **Flow Graph Visualization** (planned): UI to visualize data provenance and policy violations.

---

## 🧪 Example Use Case

Imagine a microservice handling a user's SSN (Social Security Number):

1. It is tagged as `CONFIDENTIAL` at ingestion.
2. As it flows to downstream modules, each transfer is tracked.
3. If untrusted components access or leak it, the system logs the violation.

This simulates the research use-case of protecting **application-level data** against **component-level threats**.

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- MongoDB Atlas
- REST APIs
- Maven
- HTML
- JavaScript
- CSS

---
## 🚀 Getting Started

1. Clone the repo:

   ```bash
   git clone https://github.com/ronakj8617/Information-Flow-Tracker.git

   ```

2. Navigate and build:

```bash
    cd Information-Flow-Tracker
    mvn clean install
```

3. Run the spring app:

```bash
mvn spring-boot:run
```

4. Access API at: ``` http://localhost:8080```

---

## 📜 License

Mozilla Public License Version 2.0

---

## 🧑‍💻 Author

**Ronak Parmar**  
[GitHub](https://github.com/ronakj8617) | [LinkedIn](https://www.linkedin.com/in/ronakj8617/)
