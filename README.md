# Computer Science Capstone ePortfolio

**Developer:** Computer Science Graduate  
**Specialization:** Full-Stack Web Engineering & Mobile Application Architecture  
**Capstone Repository:** [CS-499-ePortfolio](https://github.com/aqbah/CS-499-ePortfolio)  

---

## Quick Navigation

* [Professional Self-Assessment](#professional-self-assessment)
* [Informal Code Review](CODE_REVIEW.md)
* [Software Design & Engineering Enhancement](software-engineering/)
* [Algorithms & Data Structures Enhancement](algorithms/)
* [Databases Enhancement](databases/)
* [Original Files](InventoryAppOriginalAqbah.zip)
* [Fully Updated Files](InventoryAppEnhancedAqbah.zip)

---

## Professional Self-Assessment

### Introduction & Career Vision
As a graduate of the Computer Science program, my journey through rigorous academic coursework and hands-on technical development shape me into a disciplined, security-minded full-stack software engineer. Completing this program and assembling a comprehensive ePortfolio provided a structured framework to refine my technical strengths, solidify my professional values, and prepare directly for industry expectations. My goal is to build resilient, scalable, and high-performance software systems that solve complex real-world problems. Throughout my studies, my perspective evolved from simply writing functional code to engineering end-to-end computing solutions—prioritizing maintainable architecture, memory efficiency, and proactive defensive design.

---

### Technical Competencies & Professional Strengths

#### Collaborating in a Team Environment
Software engineering is inherently collaborative. Throughout my academic progression and team-based projects, I developed key strategies for building productive environments across diverse technical backgrounds. Engaging in peer code reviews, continuous feedback loops, and agile sprint workflows taught me how to balance conflicting technical viewpoints and make data-driven architectural decisions. Outside of my portfolio artifacts, my work on multi-tier web development projects and collaborative assembly language reverse-engineering assignments highlighted the value of clean module separation, version control hygiene (Git), and clear interface contracts to enable seamless team integration.

#### Communicating with Stakeholders
Technical expertise must be backed by the ability to communicate complex concepts to diverse audiences. Across my coursework, I gained experience drafting formal Software Design Documents (SDDs), technical API endpoint specifications, and structural UML diagrams (including sequence, class, and state machine diagrams). Translating complex backend mechanics and architectural trade-offs into clear visual and written communication ensures alignment between technical teams and non-technical business stakeholders, driving informed decision-making.

#### Data Structures and Algorithms
Algorithmic efficiency directly impacts application scalability and user experience. My training involved evaluating computing solutions using foundational algorithmic principles, analyzing time and space complexity ($O(n)$, $O(\log n)$, $O(1)$), and managing computational trade-offs. I learned to select optimal data structures—such as using Hash Maps for constant-time lookups versus lists for sequential processing—and to implement efficient search and sort algorithms like QuickSort and Binary Search to maximize performance on resource-constrained platforms.

#### Software Engineering and Databases
Modern software engineering requires applying innovative design patterns, strict object-oriented encapsulation, and robust persistence structures. My experience spans full-stack architecture, including Node.js, Express, MongoDB, Mongoose, C++, and native Android Java with SQLite. I have routinely applied architectural patterns like Model-View-ViewModel (MVVM) and the Repository pattern to decouple UI controllers from data logic. In the database domain, I focus on schema design, relational integrity via foreign key constraints, explicit indexing for accelerated query execution, and atomic, ACID-compliant transaction management.

#### Security Mindset & Defensive Design
Security cannot be an afterthought; it must be embedded directly into software architecture. My coursework emphasized developing a security mindset that anticipates potential adversarial exploits and mitigates vulnerabilities at both the application and database tiers. Outside my primary portfolio artifacts, I designed and tested C++ SQL injection defense mechanisms, handled boundary conditions, and implemented strict input sanitization. My approach ensures that systems fail gracefully with clear user feedback rather than crashing or exposing critical data vulnerabilities.

---

### Portfolio Artifact Integration
The technical artifacts showcased in this ePortfolio represent a complete, iterative journey of refactoring and enhancing a native Android Mobile Inventory Tracking Application (`com.example.aqbahinventoryapp`). Together, these enhancements demonstrate the full spectrum of my software engineering capabilities:

* **[Software Design & Engineering](software-engineering/):** Demonstrates architectural refactoring from a monolithic design to an MVVM pattern using a dedicated `InventoryRepository`, strict object-oriented data encapsulation in `InventoryItem`, and defensive error-trapping (`try-catch` blocks) to prevent runtime crashes.
* **[Algorithms & Data Structures](algorithms/):** Showcases computational efficiency through an in-memory `HashMap` cache layer for $O(1)$ constant-time lookups, a custom `QuickSort` implementation for client-side list sorting, and an $O(\log n)$ `Binary Search` algorithm for rapid record location.
* **[Databases](databases/):** Highlights advanced database management, transitioning the local SQLite engine to schema version 2 with foreign key constraints, table-level `CHECK` constraints, dedicated search indexing (`idx_inventory_name`), parameterized query execution to neutralize SQL injection, and atomic transaction handling.

Collectively, these artifacts illustrate my ability to analyze legacy code, identify systemic weaknesses, and systematically apply advanced computer science practices to deliver secure, highly optimized, enterprise-ready software solutions.
