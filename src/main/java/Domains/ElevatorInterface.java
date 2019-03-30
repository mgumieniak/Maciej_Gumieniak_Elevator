package Domains;

public interface ElevatorInterface {
    void pickUpElevator(int flourReport, int destination);
    void selectFlourInsideElevator(int destinationFlour);
    void update();
    void simulationStep();
    void status();
}
