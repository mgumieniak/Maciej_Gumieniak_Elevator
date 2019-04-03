import Data.DataBase;
import Data.DataBaseInterface;
import Domains.Elevator;
import Domains.ElevatorInterface;
import Domains.Message;
import Service.Consumer;
import Service.ElevatorSystem;
import Service.Producer;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        DataBase data = Main.init();
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(20);


        ElevatorSystem elevatorSystem = new ElevatorSystem(Runtime.getRuntime().availableProcessors(),data);

        elevatorSystem.submitProducer(new Producer(queue,1,3,0,true));
        elevatorSystem.submitProducer(new Producer(queue,1,2,-1,false));
        elevatorSystem.submitConsumer(new Consumer(queue,data),2);

        elevatorSystem.systemShutDown(10,TimeUnit.SECONDS);

        elevatorSystem.updateAndSimulationStep(1,2);
        System.out.println("Data status: " + elevatorSystem.showStatusAllElevator());

    }

    public static DataBase init(){
        DataBase data = new DataBase();
        data.addElevator(1);
        data.addElevator(2);
        return data;
    }
}
