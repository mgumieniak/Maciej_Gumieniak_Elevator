package Domains;


import java.util.List;

public interface ElevatorInterface {
    ElevatorInterface pickUpElevator(int flourReport, int destination);
    ElevatorInterface selectFlourInsideElevator(int destinationFlour);
    ElevatorInterface update();
    ElevatorInterface simulationStep();
    void status();
    ElevatorInterface updateAndSimulationStep();
    int  getCurrentFlour();
    int getDestinationFlour();
    List<Integer> getFlourQue();
}
