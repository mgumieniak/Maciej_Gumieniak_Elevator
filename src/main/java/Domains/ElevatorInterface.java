package Domains;

/**
 * This interface contain method to operate with elevator.
 */
public interface ElevatorInterface {
    /**
     * Method handle pick up the elevator from specific floor and direction.
     *
     * @param flourReport - the floor from sb call the elevator.
     * @param direction - direction in which sb want to go by elevator. The positive value
     *                  means up direction and negative value means down direction.
     * @return object with modify value.
     */
    ElevatorInterface pickUpElevator(int flourReport, int direction);

    /**
     * Method handle select floor inside elevator.
     *
     * @param destinationFlour - destinationFlour
     * @return object with modify value
     */
    ElevatorInterface selectFlourInsideElevator(int destinationFlour);

    /**
     * Update queue value.
     *
     * @return object with modify value
     */
    ElevatorInterface update();

    /**
     *
     * @return object with modify value
     */
    ElevatorInterface simulationStep();
    void status();
    ElevatorInterface updateAndSimulationStep();
}
