package Service;

import Data.DataBase;
import Domains.Message;

import java.util.concurrent.BlockingQueue;

/**
 * The Consumer take Messages objects from BlockingQueue and produced from it Elevator objects.
 */
public class Consumer {
    private BlockingQueue<Message> queue;
    private DataBase dataBase;

    /**
     * Construct the class instance.
     *
     * @param queue - queue, which contain Message putted by Producer.
     * @param dataBase - data base, which contain Elevator objects.
     */
    public Consumer(BlockingQueue<Message> queue, DataBase dataBase) {
        this.queue = queue;
        this.dataBase = dataBase;
    }

    /**
     * Override run method in Runnable. Starting the thread causes the object's run method to be called
     * in that separately executing thread. The method take Message object, create new Elevator object and replace
     * old object (using thread safety method computeIfPresent).
     *
     * The method Thread.sleep() is called to sleep the current object to see slowly result on the screen.
     *
     */
    Runnable runnableConsumer = ()->{
        Message msg;
        try {
            msg = queue.take();
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+": Consumed: "+msg);
            if(msg.isSelectInsideElevator()){
                dataBase.getMap().computeIfPresent(msg.getIdElevator(),(id,elevator)->elevator.selectFlourInsideElevator(msg.getDestinationFloor()));
            } else dataBase.getMap().computeIfPresent(msg.getIdElevator(),(id,elevator)->elevator.pickUpElevator(msg.getDestinationFloor(),msg.getDirection()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    /**
     * @return Runnable instance.
     */
    public Runnable getRunnableConsumer() {
        return runnableConsumer;
    }
}
