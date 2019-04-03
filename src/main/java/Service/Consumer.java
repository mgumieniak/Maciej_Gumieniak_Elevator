package Service;

import Data.DataBase;
import Domains.Message;

import java.util.concurrent.BlockingQueue;

public class Consumer {
    private BlockingQueue<Message> queue;
    private DataBase dataBase;

    public Consumer(BlockingQueue<Message> queue, DataBase dataBase) {
        this.queue = queue;
        this.dataBase = dataBase;
    }

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

    public Runnable getRunnableConsumer() {
        return runnableConsumer;
    }
}
