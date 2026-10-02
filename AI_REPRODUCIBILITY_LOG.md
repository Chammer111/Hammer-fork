# AI Interaction & Reproducibility Log

**Course**: CS3600-001 Operating Systems  
**Project**: Project 1 — CPU Scheduling Algorithms (Priority Scheduling Section)  
**Student**: Chammer111  
**Repository**: [https://github.com/Sharif15/CS3600-Project-1-Scheduling-Algorithms/tree/Hammer-fork](https://github.com/Sharif15/CS3600-Project-1-Scheduling-Algorithms/tree/Hammer-fork)  
**Date**: Fall 2026  

---

## 1. Executive Summary & Purpose for Instructor

This document provides a transparent, step-by-step audit trail of how generative AI and automated developer tooling were utilized to learn, construct, debug, and audit the **Priority Scheduling** implementation (`java/Priority.java`).

The goal is full **scientific and educational reproducibility**: any instructor, TA, or evaluator can review the prompts given, the mathematical derivations performed, the compiler feedback loops navigated by the student, and the exact terminal commands required to reproduce the identical results.

---

## 2. Tooling & Environment Specifications

| Tool | Version / Engine | Role | Configuration / Constraint Files |
| :--- | :--- | :--- | :--- |
| **Google Antigravity CLI** | Gemini 2.5 Flash / Pro | Interactive Socratic Tutor & Math Modeler | `GEMINI.md` |
| **VS Code Copilot** | GitHub Copilot | In-Editor Completion Assistant | `.github/copilot-instructions.md` |
| **Anthropic Claude Code** | CLI 2.1.285 | Static Code Reviewer & Requirements Auditor | Headless non-interactive audit (`claude -p`) |
| **Java Development Kit** | OpenJDK 17.0.x | Compiler & Virtual Machine | Standard `javac` / `java` |

---

## 3. Step-by-Step Prompt & Interaction History

### Phase 1: Conceptual Modeling & Mathematical Proof
* **Student Invariant**: *"This is a school project for class, I need you to teach me how this works and why I am typing in the code I will be typing to make everything work."*
* **Theoretical Grounding**: *Operating System Concepts* (10th Edition, Silberschatz et al.), Chapter 5.
* **Student & AI Mathematical Trace of `schedule.txt`**:
  * Task Set: $T_1(4, 20), T_2(3, 25), T_3(3, 25), T_4(5, 15), T_5(5, 20), T_6(1, 10), T_7(3, 30), T_8(10, 25)$.
  * Non-preemptive ordering with FCFS tie-breaking:
    $$T_8 (p=10) \to T_4 (p=5) \to T_5 (p=5) \to T_1 (p=4) \to T_2 (p=3) \to T_3 (p=3) \to T_7 (p=3) \to T_6 (p=1)$$
  * Analytical Derivations:
    * Total Burst: $170\text{ ms}$, Average Burst: $\overline{B} = \frac{170}{8} = 21.25\text{ ms}$.
    * Total Wait / Response: $0 + 25 + 40 + 60 + 80 + 105 + 130 + 160 = 600\text{ ms} \implies \overline{T}_{\text{wait}} = 75.00\text{ ms}$.
    * Total Turnaround: $25 + 40 + 60 + 80 + 105 + 130 + 160 + 170 = 770\text{ ms} \implies \overline{T}_{\text{turnaround}} = 96.25\text{ ms}$.
    * Conservation Invariant Verified:
      $$\overline{T}_{\text{turnaround}} = \overline{T}_{\text{wait}} + \overline{B} = 75.00 + 21.25 = 96.25\text{ ms}$$

---

### Phase 2: Constraining AI Tools to Prevent Hallucination
* **Student Action**: Implemented `.github/copilot-instructions.md` to restrict AI behavior.
* **Why**: LLMs often guess by injecting multi-threading (`Thread`, `Semaphore`, `synchronized`), which violates this single-threaded discrete CPU burst simulation.
* **Enforced Parameters**:
  * Single-threaded simulation model.
  * Priority range: 1 to 10 (10 is highest priority).
  * FCFS tie-breaking guaranteed via stable TimSort.
  * Floating-point division using cached `totalTasks`.

---

### Phase 3: Active Implementation & Syntax Debugging
The student typed the implementation directly into VS Code. When compiler diagnostics occurred, the student used AI to interpret error output and fix them manually:

1. **Bug 1: Semicolon omitted in `pickNextTask()`**:
   * *Diagnostic*: `Priority.java:28: error: ';' expected`
   * *Student Fix*: Appended `;` to `return queue.remove(0);`.
2. **Bug 2: Method Scoping & Bracket Placement**:
   * *Diagnostic*: Floating statements outside method body triggered `error: illegal start of type` and cascaded into flagging `@Override public Task pickNextTask()` as invalid.
   * *Learning Outcome*: The student identified that an empty starter stub `public void schedule() { }` was prematurely closed, leaving the execution loop in the class scope. The student properly enclosed the loop, accumulators, and metric printing within `schedule()`.
3. **Bug 3: Variable Spelling Typo**:
   * *Diagnostic*: Variable declared as `totalWairtingTime` on line 23 could not resolve to `totalWaitingTime` on line 59.
   * *Student Fix*: Corrected variable identifier spelling.

---

### Phase 4: Independent External Audit via Claude Code CLI
To verify compliance before pushing, the student executed Claude Code CLI non-interactively to perform static analysis.

* **Audit Command Executed**:
  ```powershell
  $null | claude -p "Please audit the final implementation of java/Priority.java in this repository. Confirm that it satisfies all requirements from documents/given_README.md, has no syntax or logical errors, complies with Algorithm.java, matches FCFS.java and SJF.java in design, and verify whether the performance metric calculations are mathematically sound."
  ```
* **Audit Result**:
  * **Status**: **PASSED (100% compliant)**.
  * **Verified**:
    * Priority ordering: descending via `Comparator.comparingInt(Task::getPriority).reversed()`.
    * Tie-breaking: TimSort stability verified.
    * Invariants: Under non-preemptive simultaneous arrival ($t=0$), response time equals waiting time, and turnaround equals completion time.
    * No divide-by-zero vulnerabilities (`if (totalTasks > 0)` guard).

---

## 4. How the Instructor Can Reproduce Results

To independently verify the implementation and test runs, execute the following commands from the `java/` directory:

### Step 1: Clean and Compile
```bash
cd java
javac *.java
```

### Step 2: Run Primary Project Test (`schedule.txt`)
```bash
java Driver pri schedule.txt
```
**Expected Output**:
```text
Starting Priority Scheduling
Will run Name: T8
Tid: 7
Priority: 10
Burst: 25

Task T8 finished.
Will run Name: T4
Tid: 3
Priority: 5
Burst: 15

Task T4 finished.
Will run Name: T5
Tid: 4
Priority: 5
Burst: 20

Task T5 finished.
Will run Name: T1
Tid: 0
Priority: 4
Burst: 20

Task T1 finished.
Will run Name: T2
Tid: 1
Priority: 3
Burst: 25

Task T2 finished.
Will run Name: T3
Tid: 2
Priority: 3
Burst: 25

Task T3 finished.
Will run Name: T7
Tid: 6
Priority: 3
Burst: 30

Task T7 finished.
Will run Name: T6
Tid: 5
Priority: 1
Burst: 10

Task T6 finished.

--- Priority Performance Metrics ---
Average Turnaround Time: 96.25 ms
Average Waiting Time: 75.00 ms 
Average Response Time: 75.00 ms
------------------------------------
```

### Step 3: Run Textbook Benchmark (`book.txt`)
```bash
java Driver pri book.txt
```
**Expected Output**:
```text
--- Priority Performance Metrics ---
Average Turnaround Time: 12.20 ms
Average Waiting Time: 8.20 ms 
Average Response Time: 8.20 ms
------------------------------------
```

### Step 4: Run Makefile Integration Test
```bash
make pri
```
