# Enhancement One: Software Design and Engineering

[Back to Home Page](../README.md)

---

## Artifact Description
The artifact enhanced for this category is the Mobile Inventory Tracking Application (`com.example.aqbahinventoryapp`) originally created in CS-360. The application manages inventory records, tracks threshold levels, triggers automated SMS notifications, and persists data locally using SQLite.

---

## Justification for Inclusion & Enhancement Details
I selected this artifact to demonstrate software engineering and architectural design principles. In its original state, the application followed a monolithic structure where UI components directly managed database transactions and raw string data.

Enhancing this artifact allowed me to showcase my ability to apply industry-standard design patterns, strict encapsulation, and defensive programming techniques. Specific enhancements implemented include:

* **MVVM Architecture Refactoring:** Introduced an `InventoryRepository` class to decouple UI activities from database operations, creating a clean separation of concerns.
* **Encapsulation & Domain Modeling:** Updated `InventoryItem.java` to enforce strict object-oriented encapsulation using private instance variables and public getters/setters.
* **Defensive Error Handling:** Integrated `try-catch` blocks around all user inputs in `AddItemActivity.java` to gracefully catch `NumberFormatException` errors and prevent application crashes.

---

## Course Outcomes Alignment
* **Outcome 4 (Innovative Skills & Tools in Software Engineering):** Refactored legacy monolithic applications into modern MVVM architecture using repository patterns and strict encapsulation.
* **Outcome 5 (Security Mindset & Defensive Design):** Built defensive input validation and edge-case numerical error handling into controller layers.

---

## Source Code Files
* [`InventoryRepository.java`](com/example/aqbahinventoryapp/InventoryRepository.java)
* [`InventoryItem.java`](com/example/aqbahinventoryapp/InventoryItem.java)
* [`AddItemActivity.java`](com/example/aqbahinventoryapp/AddItemActivity.java)
