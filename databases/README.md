# Enhancement Three: Databases

[Back to Home Page](../README.md)

---

## Artifact Description
The artifact enhanced for this milestone is the Mobile Inventory Tracking Application (`com.example.aqbahinventoryapp`). This enhancement focuses on data persistence layer optimization, security hardening, and schema design.

---

## Justification for Inclusion & Enhancement Details
I selected this artifact to demonstrate database engineering principles. In its original state, the database layer relied on unindexed tables, lacked foreign key relationships, performed write queries outside transactional boundaries, and lacked database-level constraint validations.

Enhancing this artifact allowed me to showcase my ability to design relational database schemas, enforce database security, and optimize persistence performance. Specific enhancements made include:

* **Relational Integrity & Schema Design:** Refactored `DatabaseHelper.java` to introduce relational integrity by linking inventory records directly to the creating user via foreign keys (`FOREIGN KEY(created_by) REFERENCES users(username)`).
* **Database Field Constraints:** Implemented SQL-level check constraints (`CHECK (quantity >= 0)`, `CHECK (threshold >= 0)`) to enforce validation directly at the database tier.
* **Database Performance Indexing:** Created explicit database indexes (`CREATE INDEX idx_inventory_name ON inventory(name)`) to accelerate query execution on high-cardinality fields.
* **ACID-Compliant Transactions:** Wrapped insert, update, and delete calls inside atomic database transactions (`beginTransaction()`, `setTransactionSuccessful()`, `endTransaction()`).
* **SQL Injection Prevention:** Ensured all queries execute using parameterized arguments (`?`) via Android's `SQLiteDatabase.query()` and `ContentValues` abstractions.

---

## Course Outcomes Alignment
* **Outcome 4 (Innovative Skills and Tools in Database Management):** Implemented relational schemas, foreign keys, check constraints, indexes, and transactional write handling.
* **Outcome 5 (Security Mindset & Vulnerability Mitigation):** Enforced parameterized SQL queries and database-level data validation constraints to neutralize SQL injection risks.

---

## Source Code Files
* [`DatabaseHelper.java`](com/example/aqbahinventoryapp/DatabaseHelper.java)
* [`InventoryRepository.java`](com/example/aqbahinventoryapp/InventoryRepository.java)
