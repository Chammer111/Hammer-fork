# Academic Integrity & Generative AI Attribution Statement

## Course & Project Information
* **Student who used AI for this portion**:  Christopher Hammer 
* **Course**: CS3600-001 Operating Systems
* **Project**: Project 1 — CPU Process Scheduling Algorithms
* **Term**: Fall 2026

---

## 1. Statement of Academic Integrity & Authorship
In accordance with university academic integrity policies and principles of ethical AI usage in academia, this document provides full disclosure of generative AI and automated coding assistants utilized during the completion of this project.

The student affirms:
1. **Conceptual Understanding**: I fully understand every algorithm, data structure, and line of code submitted in this project, and I am capable of explaining, defending, and modifying the implementation in an oral examination or code defense.
2. **Role of AI as a Pedagogical Tutor & Pair Programmer**: AI tools were utilized strictly for conceptual explanation, mathematical verification, peer review, and syntax assistance, rather than as a substitute for learning or unexamined copy-pasting.
3. **No Plagiarism**: All algorithmic logic reflects the required curriculum from *Operating System Concepts* (10th Edition, Silberschatz et al., Chapter 5) and the instructor's provided specifications.

---

## 2. Student Methodology: How AI Was Used to Learn and Author Code

Throughout this project, AI was utilized as an **interactive pedagogical workbench**, emphasizing active learning over passive code generation:

1. **Mathematical & Conceptual Modeling**:
   - I used Google Antigravity (Gemini) as a Socratic tutor to review CPU scheduling principles from *Operating System Concepts* (Silberschatz Chapter 5).
   - Before writing code, we performed hand calculations on `schedule.txt` to establish exact analytical benchmarks:
     - Total Burst: $170\text{ ms}$, Average Burst: $\overline{B} = 21.25\text{ ms}$
     - Non-preemptive waiting/response equivalence: $T_{\text{wait}} \equiv T_{\text{response}} = t_{\text{start}}$
     - Average Waiting & Response Times: $75.00\text{ ms}$
     - Average Turnaround Time: $96.25\text{ ms}$
     - Invariant verification: $\overline{T}_{\text{turnaround}} = \overline{T}_{\text{wait}} + \overline{B}$ ($75.00 + 21.25 = 96.25\text{ ms}$).

2. **Hands-On Coding & Debugging**:
   - Rather than having AI generate files, I typed the implementation directly into VS Code in `java/Priority.java`.
   - When encountering compiler diagnostics (such as bracket placement and variable scoping issues between `schedule()` and `pickNextTask()`), the AI assisted in explaining Java compiler error messages so I could diagnose and fix the method structure myself.

3. **Constraining In-Editor Tools**:
   - To prevent GitHub Copilot from hallucinating external libraries (such as Java threads, semaphores, or GUI frameworks), I created `.github/copilot-instructions.md` to enforce strict boundaries aligned with the project's single-threaded, discrete-event simulation model.

4. **Multi-Model Pre-Submission Audit**:
   - Before committing, I invoked Claude Code CLI to run an independent static analysis against `documents/given_README.md` to verify interface compliance, edge cases, and arithmetic invariants.

---

## 3. Tool Inventory & Scope of Use

### A. Google Antigravity CLI (Gemini)
* **Role**: Interactive Socratic tutor and project coordinator.
* **Scope of Usage**:
  * Provided step-by-step conceptual walkthroughs of Operating System scheduling mechanics (non-preemptive priority ordering, tie-breaking criteria, stable sort invariants).
  * Provided formal mathematical modeling and analytical hand traces for performance metrics (Turnaround Time, Waiting Time, Response Time).
  * Assisted in environment setup, Git repository synchronization with team branches, and project parameter documentation.

### B. GitHub Copilot (VS Code)
* **Role**: In-editor completion assistant.
* **Scope of Usage**:
  * Provided syntax assistance and boilerplate autocompletion within VS Code.
  * Constrained by `.github/copilot-instructions.md` to ensure suggestions complied strictly with project specifications without hallucinating external concurrency libraries or unsupported APIs.

### C. Anthropic Claude Code CLI
* **Role**: Static analysis and automated code review auditor.
* **Scope of Usage**:
  * Executed via terminal CLI to perform rigorous pre-submission verification of implemented algorithms against the instructor's `given_README.md`.
  * Checked interface compliance with `Algorithm.java`, verified edge cases (empty queues, identical priorities, single-task runs), and validated mathematical metric formulas.

---

## 4. Algorithm-by-Algorithm Attribution Log

| Algorithm | Primary Implementation & Authorship | AI Assistance Utilized | Verification Method |
| :--- | :--- | :--- | :--- |
| **FCFS** (`FCFS.java`) | Team member (Sharif Islam) | Starter scaffolding & initial metrics | Compiled & verified via `Driver.java` |
| **SJF** (`SJF.java`) | Team member (Sharif Islam) | Starter scaffolding & comparator pattern | Compiled & verified via `Driver.java` |
| **Priority** (`Priority.java`) | Student author with interactive pair programming | Socratic conceptual breakdown, comparator syntax, stable sort theory | Mathematical hand trace + JDK 17 compilation + Claude Code static review |
| **Round-Robin** (`RR.java`) | Student author with interactive pair programming | Time-slicing logic and queue re-insertion guidance | Step-by-step execution trace against `rr-schedule.txt` |
| **Priority with RR** (`PriorityRR.java`) | Student author with interactive pair programming | Tiered priority grouping and quantum dispatch logic | Test verification against multi-tier schedules |

---

## 5. Verification & Audit Trail
All commits, test executions, and verification logs are maintained in the local Git repository history, which will be packaged and submitted via the designated Git bundle format (`<team_name>.bundle`).
