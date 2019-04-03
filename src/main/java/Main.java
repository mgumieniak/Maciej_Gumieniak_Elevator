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

        ElevatorSystem elevatorSystem = new ElevatorSystem(Runtime.getRuntime().availableProcessors(),
                data, new PriorityBlockingQueue<>(20, (first,second)->second.getIdElevator()-first.getIdElevator()));


        elevatorSystem.selectFlourInsideElevator(1,3);
        elevatorSystem.pickUpElevator(1,2,-1);
        elevatorSystem.selectFlourInsideElevator(2,5);

        elevatorSystem.receiveData(3);


        elevatorSystem.systemShutDown(10,TimeUnit.SECONDS);

        elevatorSystem.updateAndSimulationStep(1,4);
        elevatorSystem.updateAndSimulationStep(2,4);
        System.out.println("Data status: " + elevatorSystem.showStatusAllElevator());
    }

    public static DataBase init(){
        DataBase data = new DataBase();
        data.addElevator(1);
        data.addElevator(2);
        return data;
    }
}
