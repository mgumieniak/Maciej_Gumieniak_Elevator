package Domains;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*Integration Elevator class test

P = pickUp elevator
U = destination up
D = destination down
I = select flour inside elevator

Example:
methodI7_P2D - inside elevator select 7 floor. Sb picks up on floor 2(direction up)
 */
class ElevatorTest {

    @Test
    void removeOrdersFromQueueWhenElevatorReachTheFloor(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(1);
        elevator = elevator.selectFlourInsideElevator(2);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(2,  finalElevator.getFloorQue().size())
        );

        elevator = elevator.updateAndSimulationStep();// 1 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(1, finalElevator1.getFloorQue().size())
        );

        elevator = elevator.updateAndSimulationStep(); // 2 F
        Elevator finalElevator2 = elevator;
        assertAll(
                ()->assertEquals(0, finalElevator2.getFloorQue().size())
        );
    }

    @Test
    void stopElevatorWhenReachFloorAndNothingWillBeInQueue(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator=elevator.selectFlourInsideElevator(1);
        elevator=elevator.updateAndSimulationStep();// 1 F
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(1, finalElevator.getCurrentFloor())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(1, finalElevator1.getCurrentFloor())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        Elevator finalElevator2 = elevator;
        assertAll(
                ()->assertEquals(1, finalElevator2.getCurrentFloor())
        );
    }

    @Test
    void methodI7_P2U(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator=elevator.selectFlourInsideElevator(7);
        elevator=elevator.pickUpElevator(2,1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,7)),
                        finalElevator.getFloorQue())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        elevator = elevator.updateAndSimulationStep();// 2 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(2, finalElevator1.getCurrentFloor())
        );
    }

    @Test
    void methodI7_P2D(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(7);
        elevator = elevator.pickUpElevator(2,-1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(7,2)),
                        finalElevator.getFloorQue())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        elevator = elevator.updateAndSimulationStep();// 2 F
        elevator = elevator.updateAndSimulationStep();// 3 F
        elevator = elevator.updateAndSimulationStep();// 4 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(4, finalElevator1.getCurrentFloor())
        );
    }

    @Test
    void methodI2_P7D(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.pickUpElevator(7,-1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,7)),
                        finalElevator.getFloorQue())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        elevator = elevator.updateAndSimulationStep();// 2 F
        elevator = elevator.updateAndSimulationStep();// 3 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(3, finalElevator1.getCurrentFloor()),
                ()->assertEquals(7, finalElevator1.getDestinationFloor())
        );
    }

    @Test
    void methodI2_P7U(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.pickUpElevator(7,1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,7)),
                        finalElevator.getFloorQue())
        );
        elevator = elevator.updateAndSimulationStep();// 1 F
        elevator = elevator.updateAndSimulationStep();// 2 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(2, finalElevator1.getCurrentFloor()),
                ()->assertEquals(2, finalElevator1.getDestinationFloor()),
                ()->assertEquals(new LinkedList<>(Arrays.asList(7)),
                        finalElevator1.getFloorQue())
        );
    }

    @Test
    void methodI2_I7_P1U(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.selectFlourInsideElevator(7);
        elevator = elevator.pickUpElevator(1,1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(1,2,7)),
                        finalElevator.getFloorQue())
        );
    }

    @Test
    void methodI2_I7_P1D(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.selectFlourInsideElevator(7);
        elevator = elevator.pickUpElevator(1,-1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,7,1)),
                        finalElevator.getFloorQue())
        );
    }

    @Test
    void methodI7_I2_P1U(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(7);
        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.pickUpElevator(1,1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(1,2,7)),
                        finalElevator.getFloorQue())
        );
    }

    @Test
    void methodI7_I2_P1D(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(7);
        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.pickUpElevator(1,-1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,7,1)),
                        finalElevator.getFloorQue())
        );
    }

    @Test
    void methodI1_I2_P1D(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(1);
        elevator = elevator.updateAndSimulationStep(); // 1 F
        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.pickUpElevator(1,-1);
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(new LinkedList<Integer>(Arrays.asList(2,1)),
                        finalElevator.getFloorQue())
        );

        elevator = elevator.updateAndSimulationStep(); // 1 F
        elevator = elevator.updateAndSimulationStep(); // 2 F
        elevator = elevator.updateAndSimulationStep(); // 1 F
        Elevator finalElevator1 = elevator;
        assertAll(
                ()->assertEquals(0, finalElevator1.getFloorQue().size()),
                ()->assertEquals(1, finalElevator1.getCurrentFloor()),
                ()->assertEquals(1, finalElevator1.getDestinationFloor())

        );
    }

    @Test
    void methodI2_Step_P5D_P4U_Step_P3U(){
        Elevator elevator = Elevator.createElevator(1,0,0, List.of());

        elevator = elevator.selectFlourInsideElevator(2);
        elevator = elevator.updateAndSimulationStep(); // 1 F
        elevator = elevator.pickUpElevator(5,-1);
        elevator = elevator.pickUpElevator(4,1);
        elevator = elevator.updateAndSimulationStep(); // 2 F
        elevator = elevator.pickUpElevator(3,1);

        elevator = elevator.updateAndSimulationStep(); // 3 F
        elevator = elevator.updateAndSimulationStep(); // 4 F
        elevator = elevator.updateAndSimulationStep(); // 5 F
        Elevator finalElevator = elevator;
        assertAll(
                ()->assertEquals(0, finalElevator.getFloorQue().size())
        );
    }
}