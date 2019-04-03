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
     * @param destinationFlour - destination floor
     * @return object with modify value
     */
    ElevatorInterface selectFlourInsideElevator(int destinationFlour);

    /**
     * Update queue value. When current floor is equal actual floor take first
     * element form floorQueue (return and remove).
     *
     * @return object with modify value
     */
    ElevatorInterface update();

    /**
     * Simulate elevator movement increasing or decreasing current floor by 1.
     *
     * @return object with modify value.
     */
    ElevatorInterface simulationStep();

    /**
     * Show all information about instance fields.
     */
    void status();

    /**
     * Help method to call method update() and simulationStep().
     *
     * @return modify object.
     */
    ElevatorInterface updateAndSimulationStep();
}
