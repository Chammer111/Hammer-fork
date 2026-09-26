import java.util.*;
import java.io.*;

public class FCFS implements Algorithm{

    private List<Task> queue;

    // constructor 
    public FCFS (List<Task> queue){
        this.queue = queue;
    }

    // implementing the functions from Algorithm 

    @Override
    public void schedule(){
        // Put the implementation code here
    }

    @Override
    public Task pickNextTask(){
        // dummy return value remove when implementation is done
        return queue.get(0);
    }
    
}
