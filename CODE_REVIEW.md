# Milestone One: Informal Code Review

[Back to Home Page](README.md)

---

## Video Walkthrough

[![Code Review Walkthrough Video](https://img.youtube.com/vi/npg_1vVYMnQ/0.jpg)](https://www.youtube.com/watch?v=npg_1vVYMnQ)

---

## Code Review Overview & Analysis

In Module Two, I performed a comprehensive code review of the baseline Mobile Inventory Tracking Application (`com.example.aqbahinventoryapp`) originally developed in CS-360. The review targeted areas for architectural improvement, algorithmic optimization, data integrity, and security hardening.

### 1. Existing Functionality Walkthrough
The baseline application provided essential Android user interface screens for local user authentication, displaying an inventory list, adding items, updating quantities, and triggering low-inventory SMS notifications. Data was written directly to an SQLite database without clear separation of concerns.

### 2. Targeted Code Analysis & Weaknesses
* **Software Architecture:** The original code mixed database queries directly inside Activity controllers, creating a monolithic structure with tight coupling and zero unit testability. Data fields lacked proper encapsulation.
* **Algorithmic Complexity:** Searching and sorting records relied entirely on executing repeated `SELECT` queries across disk storage or scanning unindexed lists in $O(n)$ linear time.
* **Database & Security:** Tables lacked relational integrity, foreign key linkage, and database-level constraints. User password handling and query execution lacked strict parameterized constraints.

### 3. Planned Enhancement Strategy
* **Software Design & Engineering:** Refactor the codebase to an MVVM pattern, create an `InventoryRepository` abstraction layer, encapsulate domain fields, and implement defensive input handling.
* **Algorithms & Data Structures:** Introduce an in-memory `HashMap` cache for $O(1)$ lookups, write a custom `QuickSort` algorithm for in-memory sorting, and implement $O(\log n)$ Binary Search routines.
* **Databases:** Upgrade SQLite schema to Version 2 with explicit Foreign Keys, `CHECK` constraints (`quantity >= 0`), index structures (`idx_inventory_name`), and atomic transaction blocks (`beginTransaction()`).
