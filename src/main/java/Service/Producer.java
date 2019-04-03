package Service;

import Domains.Message;
import java.util.concurrent.BlockingQueue;

public class Producer {
    private BlockingQueue<Message> queue;
    private int idElevator;
    private int destinationFloor;
    private int direction;
    private  boolean selectInsideElevator;

    public Producer(BlockingQueue<Message> queue, int idElevator, int destinationFloor, int direction,boolean selectInsideElevator) {
        this.queue = queue;
        this.idElevator = idElevator;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.selectInsideElevator = selectInsideElevator;
    }

    Runnable runnableProducer =()->{
        Message msg = Message.createMessage(getIdElevator(),getDestinationFloor(),getDirection(),isSelectInsideElevator());
        try {
            Thread.sleep(1000);
            queue.put(msg);
            System.out.println(Thread.currentThread().getName()+": Produced " + msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    };

    public int getIdElevator() {
        return idElevator;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getDirection() { return direction; }

    public boolean isSelectInsideElevator() {
        return selectInsideElevator;
    }

    public Runnable getRunnableProducer() {
        return runnableProducer;
    }
}
