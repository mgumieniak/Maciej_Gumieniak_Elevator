import Data.DataBase;
import Data.DataBaseInterface;
import Domains.Elevator;
import Domains.ElevatorInterface;
import Domains.Message;
import Service.Consumer;
import Service.ElevatorSystem;
import Service.Producer;

import java.util.Comparator;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args){
        DataBase data = Main.init();

        ElevatorSystem elevatorSystem = new ElevatorSystem(Runtime.getRuntime().availableProcessors(), data,
                new PriorityBlockingQueue<>(20, (first,second)->second.getIdElevator()-first.getIdElevator()));


        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.pickUpElevator(1,2,-1);
        elevatorSystem.selectFloorInsideElevator(3,5);
        elevatorSystem.selectFloorInsideElevator(4,2);

        try {
            //Waits for put all data into queue.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        elevatorSystem.receiveData(4);

        elevatorSystem.systemShutDown(10,TimeUnit.SECONDS);

        elevatorSystem.updateAndSimulationStep(1,4);
        elevatorSystem.updateAndSimulationStep(2,4);
        System.out.println("Data status: " + elevatorSystem.showStatusAllElevator());
    }

    public static DataBase init(){
        DataBase data = new DataBase();
        data.add(1);
        data.add(2);
        data.add(3);
        data.add(4);
        return data;
    }
}
