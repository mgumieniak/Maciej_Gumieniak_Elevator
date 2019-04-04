package Service;

import Domains.Message;
import java.util.concurrent.BlockingQueue;

/**
 * The Producer create Message class objects and put them in BlockingQueue, therefore Producer class have to contain all
 * fields to create Message instance.
 *
 * Class simulate action realized by elevator clients.
 */
public class Producer {
    private BlockingQueue<Message> queue;
    private int idElevator;
    private int destinationFloor;
    private int direction;
    private  boolean selectInsideElevator;

    /**
     * Construct the class instance.
     *
     * @param queue - queue, which contain Message putted by this class.
     * @param idElevator - created message id.
     * @param destinationFloor - created message destination floor.
     * @param direction - created message direction.
     * @param selectInsideElevator - created message bool if sb select floor inside elevator.
     */
    public Producer(BlockingQueue<Message> queue, int idElevator, int destinationFloor, int direction,boolean selectInsideElevator) {
        this.queue = queue;
        this.idElevator = idElevator;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.selectInsideElevator = selectInsideElevator;
    }

    /**
     * Override run method in Runnable. Starting the thread causes the object's run method to be called
     * in that separately executing thread. The method create Message object and put it in queue.
     *
     * The method Thread.sleep() is called to sleep the current object to see slowly result on the screen.
     */
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

    /**
     *
     * @return Id message object, which will be created.
     */
    public int getIdElevator() {
        return idElevator;
    }

    /**
     * @return Destination floor message object, which will be created.
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     * @return direction message, which will be created.
     */
    public int getDirection() { return direction; }

    /**
     * @return Bool, if sb select floor inside elevator, message object, which will be created.
     */
    public boolean isSelectInsideElevator() {
        return selectInsideElevator;
    }

    /**
     * @return Runnable instance.
     */
    public Runnable getRunnableProducer() {
        return runnableProducer;
    }
}
