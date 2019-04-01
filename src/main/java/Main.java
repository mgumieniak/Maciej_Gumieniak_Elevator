import Data.DataBase;
import Data.DataBaseInterface;
import Domains.Elevator;
import Domains.ElevatorInterface;
import Service.Consumer;
import Service.Producer;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        DataBaseInterface data = Main.init();

        /*BlockingQueue<ElevatorInterface> queue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(queue,data);
        Consumer consumer = new Consumer(queue);

        int processors = Runtime.getRuntime().availableProcessors();


        ExecutorService executorService = Executors.newFixedThreadPool(processors);

        executorService.submit(producer.getRunnableProducer());
        executorService.submit(consumer.getRunnableConsumer());
        executorService.shutdown();*/
    }
    public static DataBaseInterface init(){
        DataBaseInterface data = new DataBase();
        data.addElevator(1);
        data.addElevator(2);
        return data;
    }
}
