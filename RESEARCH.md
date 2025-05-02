# Research Addendum – Information Flow Tracker

## What is Information Flow Control (IFC)?

Information Flow Control (IFC) is a security technique used to track and control how data moves through a system to ensure that it complies with confidentiality and integrity policies. Unlike traditional methods like access control or encryption, IFC focuses on *how* data flows rather than *who* can access it. It aims to prevent sensitive data from leaking or being improperly modified, especially when interacting with untrusted components.

## Problem Being Solved

Modern applications are often built from a collection of reusable components from various sources. Some of these components may not be trustworthy. Current IFC techniques either:

- Focus on **def-site policies**: Security labels or constraints defined by the component developer (e.g., "this variable is secret").
- Or focus on **use-site policies**: Constraints specified by application integrators or deployers (e.g., "this third-party component should not access secrets").

This disjoint model results in a mismatch. There is no unified framework for bridging developer intentions and application-level security constraints. My project explores this gap.

## Project Objective

The Information Flow Tracker prototype aims to:

- Track how user data flows through various layers (controller → service → repository).
- Tag and trace sensitive data using classification labels.
- Log and store potential taint violations.
- Provide a base for enforcing both def-site and use-site policies in microservice architectures.
