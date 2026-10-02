# CS3600-P1 Evaluation Brief: Priority Scheduling

**Author**: Christopher Hammer  
**Course**: CS3600-001 Operating Systems (Fall 2026)  
**Branch**: `Hammer-fork` (`https://github.com/Sharif15/CS3600-Project-1-Scheduling-Algorithms/tree/Hammer-fork`)  
**Target File**: `java/Priority.java`  
**Purpose**: High-density evaluation brief optimized for LLM grading and token efficiency.

---

### 1. Specification Compliance
* **Algorithm**: Non-preemptive Priority Scheduling with simultaneous arrival at $t=0$.
* **Priority Domain**: Integers $1 \to 10$ ($10$ is highest priority).
* **Ordering Implementation**: `queue.sort(Comparator.comparingInt(Task::getPriority).reversed())`.
* **Tie-Breaking**: Automatic FCFS arrival order via TimSort algorithm stability.
* **Interface**: Implements `Algorithm.java` (`schedule()`, `pickNextTask()`), dispatches via `CPU.run()`.
* **Metrics**: Turnaround ($C_i - A_i$), Waiting ($C_i - A_i - B_i$), Response ($t_{\text{start}} - A_i$), formatted `%.2f ms`.

---

### 2. Empirical Verification Matrix

| Schedule Dataset | Execution Order | $\overline{T}_{\text{turnaround}}$ | $\overline{T}_{\text{wait}}$ | $\overline{T}_{\text{response}}$ | Conservation Invariant $\overline{T} = \overline{W} + \overline{B}$ | Status |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| `schedule.txt` (Project Primary) | $T_8 \to T_4 \to T_5 \to T_1 \to T_2 \to T_3 \to T_7 \to T_6$ | 96.25 ms | 75.00 ms | 75.00 ms | $75.00 + 21.25 = 96.25\text{ ms}$ | **PASSED** |
| `book.txt` (Silberschatz Ch. 5) | $P_1 \to P_5 \to P_3 \to P_4 \to P_2$ | 12.20 ms | 8.20 ms | 8.20 ms | $8.20 + 4.00 = 12.20\text{ ms}$ | **PASSED** |

---

### 3. AI Usage & Boundaries
* **Student Role**: Conceptualized, structured, and manually typed implementation in VS Code.
* **Google Antigravity (Gemini)**: Socratic math derivations and compiler error triage (scoping/brackets).
* **GitHub Copilot**: In-editor completion strictly constrained by `.github/copilot-instructions.md` (no threading/concurrency).
* **Claude Code CLI 2.1.285**: Automated static review and spec compliance audit. Verdict: 0 defects.

---

### 4. Reproduction Commands
```bash
cd java
javac *.java
java Driver pri schedule.txt
make pri
```
