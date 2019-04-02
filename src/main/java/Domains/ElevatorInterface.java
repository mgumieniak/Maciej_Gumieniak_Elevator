package Domains;

import java.util.LinkedList;

public interface ElevatorInterface {
    void pickUpElevator(int flourReport, int destination);
    void selectFlourInsideElevator(int destinationFlour);
    void update();
    void simulationStep();
    void status();
    void updateAndStatusAndSimulationStep();
    int  getCurrentFlour();
    int getDestinationFlour();
    LinkedList<Integer> getFlourQue();
}
