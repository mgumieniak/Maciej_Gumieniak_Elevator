package Service;

import Domains.ElevatorInterface;

import java.util.concurrent.BlockingQueue;

public class AddTask {
    private BlockingQueue<ElevatorInterface> queue;
    private int idElevator;

    public AddTask(BlockingQueue<ElevatorInterface> queue, int idElevator) {
        this.queue = queue;
        this.idElevator=idElevator;
    }

    Runnable runnableAddTask =()->{
        queue.put();
    };


}
