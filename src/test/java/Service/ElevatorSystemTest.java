package Service;

import Data.DataBase;
import Domains.Elevator;
import Domains.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ElevatorSystem class test.
 *
 */
class ElevatorSystemTest {

    private ElevatorSystem elevatorSystem;
    private DataBase data;

    @BeforeEach
    void setUp() {
        data = initDataBase();

        elevatorSystem = new ElevatorSystem(Runtime.getRuntime().availableProcessors(),
                data, new PriorityBlockingQueue<>(20, (first, second)->first.getIdElevator()-second.getIdElevator()));
    }

    @Test
    void systemShutDownWaitForEndThreads() {
        elevatorSystem.pickUpElevator(1,3,1);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,3,1,false)))
        );
    }

    @Test
    void withoutSystemShutDownWaitThreadsDontManageToUpdateHashMapOnTime() {
        elevatorSystem.pickUpElevator(1,3,1);
        assertAll(
                ()->assertFalse(elevatorSystem.getQueue().contains(Message.createMessage(1,3,1,false)))
        );
    }

    @Test
    void pickUpElevatorWithTheSameIDUsingMultiThreadShouldFillQueueWithoutProblems() {
        elevatorSystem.pickUpElevator(1,3,1);
        elevatorSystem.pickUpElevator(1,4,-1);
        elevatorSystem.pickUpElevator(1,2,1);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,3,1,false))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,4,-1,false))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,2,1,false)))
        );
    }

    @Test
    void pickUpElevatorWithTheOderIDUsingMultiThreadShouldFillQueueWithoutProblems() {
        elevatorSystem.pickUpElevator(1,3,1);
        elevatorSystem.pickUpElevator(2,4,-1);
        elevatorSystem.pickUpElevator(1,2,1);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,3,1,false))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(2,4,-1,false))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,2,1,false)))
        );
    }

    @Test
    void selectFloorInsideElevatorWithTheSameIDUsingMultiThreadShouldFillQueueWithoutProblems() {
        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.selectFloorInsideElevator(1,4);
        elevatorSystem.selectFloorInsideElevator(1,2);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,3,0,true))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,4,0,true))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,2,0,true)))
        );
    }

    @Test
    void selectFloorInsideElevatorWithTheOderIDUsingMultiThreadShouldFillQueueWithoutProblems() {
        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.selectFloorInsideElevator(2,4);
        elevatorSystem.selectFloorInsideElevator(1,2);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,3,0,true))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(2,4,0,true))),
                ()->assertTrue(elevatorSystem.getQueue().contains(Message.createMessage(1,2,0,true)))
        );
    }

    @Test
    void messagesInTheQueueWillBeProcessedInOrderSpecifyInComparatorInterface() {
        elevatorSystem = new ElevatorSystem(Runtime.getRuntime().availableProcessors(),
                data, new PriorityBlockingQueue<>(20, (first, second)->second.getIdElevator()-first.getIdElevator()));

        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.selectFloorInsideElevator(2,4);
        elevatorSystem.selectFloorInsideElevator(3,2);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertEquals(Message.createMessage(3,2,0,true),elevatorSystem.getQueue().take()),
                ()->assertEquals(Message.createMessage(2,4,0,true),elevatorSystem.getQueue().take())
        );
    }

    @Test
    void receiveDataFromTheQueue() {
        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.selectFloorInsideElevator(2,4);
        elevatorSystem.selectFloorInsideElevator(3,2);
        elevatorSystem.receiveData(2);
        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertEquals(1, elevatorSystem.getQueue().size())
        );
    }


    @Test
    void updateAndSimulationStep() {
        elevatorSystem.selectFloorInsideElevator(1,3);
        elevatorSystem.receiveData(1);

        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        elevatorSystem.updateAndSimulationStep(1,2);
        assertAll(
                ()->assertEquals(Elevator.createElevator(1,2,3,List.of()), elevatorSystem.getData().findElevatorUpById(1))
        );
    }

    @Test
    void selectIdWhichIsNotInDatabaseDontComputeElevatorDataBase() {
        elevatorSystem.selectFloorInsideElevator(20,3);
        elevatorSystem.receiveData(1);

        elevatorSystem.systemShutDown(10, TimeUnit.SECONDS);
        assertAll(
                ()->assertThrows(IllegalArgumentException.class, ()->elevatorSystem.getData().findElevatorUpById(20))
        );
    }

    public static DataBase initDataBase(){
        DataBase data = new DataBase();
        data.addElevator(1);
        data.addElevator(2);
        return data;
    }
}