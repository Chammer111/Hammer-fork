import java.util.*;

public class FCFS implements Algorithm{

    private List<Task> queue;
    private int totalTasks;

    // constructor 
    public FCFS (List<Task> queue){
        this.queue = new ArrayList<>(queue);
        this.totalTasks = queue.size();
    }

    // implementing the functions from Algorithm 

    @Override
    public void schedule(){
        // Put the implementation code here
        System.out.println("Starting FCFS Scheduling\n");

        int currentTime = 0;
        double totalTurnaroundTime = 0;
        double totalWaitingTime = 0;
        double totalResponseTime = 0;
        while (!queue.isEmpty()) {

            Task currentTask = pickNextTask();

            // In FCFS, a task waits until the current time to get the CPU.
            int responseTime = currentTime;
            int waitingTime = currentTime; 
            
            CPU.run(currentTask, currentTask.getBurst());
            
            // Advance the clock by the task's burst time
            currentTime += currentTask.getBurst();
            
            // Turnaround time is the time it finishes (since arrival is 0)
            int turnaroundTime = currentTime;
            
            totalResponseTime += responseTime;
            totalWaitingTime += waitingTime;
            totalTurnaroundTime += turnaroundTime;
            
            System.out.println("Task " + currentTask.getName() + " finished.");
        }

        if (totalTasks > 0) {
            System.out.println("\n--- FCFS Performance Metrics ---");
            System.out.printf("Average Turnaround Time: %.2f ms\n", (totalTurnaroundTime / totalTasks));
            System.out.printf("Average Waiting Time: %.2f ms\n", (totalWaitingTime / totalTasks));
            System.out.printf("Average Response Time: %.2f ms\n", (totalResponseTime / totalTasks));
            System.out.println("--------------------------------\n");
        }
    }

    @Override
    public Task pickNextTask(){
        if (!queue.isEmpty()){
            return queue.remove(0);
        }
        return null;
    }
    
}
