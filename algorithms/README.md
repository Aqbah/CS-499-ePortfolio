# Enhancement Two: Algorithms and Data Structures

[Back to Home Page](../README.md)

---

## Artifact Description
The artifact enhanced for this milestone is the Mobile Inventory Tracking Application (`com.example.aqbahinventoryapp`). Interactive mobile applications rely heavily on optimal memory management and fast search/sort response times.

---

## Justification for Inclusion & Enhancement Details
I selected this artifact to demonstrate algorithmic principles and data structure efficiency. In its prior version, data retrieval relied entirely on repeated $O(n)$ full-table database scans and linear iterative searches across unindexed lists.

Enhancing this artifact allowed me to showcase my ability to design computing solutions using algorithmic principles, evaluate trade-offs in time/space complexity, and implement in-memory data structures. Specific improvements made include:

* **Algorithmic Sorting Implementation (QuickSort Algorithm):** Developed a custom `InventorySorter.java` class featuring an in-memory QuickSort implementation to sort inventory lists dynamically by numerical quantities ($O(n \log n)$ average time complexity).
* **$O(\log n)$ Binary Search Integration:** Implemented a binary search algorithm (`InventorySorter.binarySearchByName`) for searching inventory items by name in logarithmic time.
* **$O(1)$ Hash Map Data Caching:** Introduced an in-memory `HashMap<Long, InventoryItem>` cache layer inside `InventoryRepository.java` for constant-time lookups.

---

## Course Outcomes Alignment
* **Outcome 3 (Data Structures & Algorithms):** Designed custom QuickSort and Binary Search algorithms and implemented an in-memory HashMap caching strategy to manage complexity trade-offs and maximize performance.

---

## Source Code Files
* [`InventorySorter.java`](com/example/aqbahinventoryapp/InventorySorter.java)
* [`InventoryRepository.java`](com/example/aqbahinventoryapp/InventoryRepository.java)
