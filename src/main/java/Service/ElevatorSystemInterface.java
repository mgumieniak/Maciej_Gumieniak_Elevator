package Service;

import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Interface provides method to handle with maximum 16 elevators. 
 * 
 * The interface support multi thread. Using executor service helps to create and handle with thread.
 *
 */
public interface ElevatorSystemInterface {
    
    /**
     * Close executor service and wait for ending task by run processed for specific time. If any thread do not
     * finish their task, after timeout, the exception will be thrown and catch inside this method.
     * 
     * @param timeout - time durations.
     * @param unit - unit specify in parameter timeout.
     */
    void systemShutDown(long timeout, TimeUnit unit);

    /**
     * Pick up elevator with id, on floor report in specific direction. The method will be run
     * in separately executing thread using Producer class. Method imitate customer action.
     * 
     * @param idElevator - id elevator.
     * @param floorReport - selected floor.
     * @param direction - direction.
     */
    void pickUpElevator(int idElevator, int floorReport, int direction);

    /**
     * Select destination floor in elevator. The method will be run
     * in separately executing thread using Producer class. Method imitate customer action.
     *
     * @param idElevator - id elevator.
     * @param destinationFloor - selected floor.
     */
    void selectFloorInsideElevator(int idElevator, int destinationFloor);

    /**
     * Take numberOfSubmit Message from queue by Consumer. If numberOfSubmit will be bigger then actual nb of
     * messages in queue, the thread will be waiting for data.
     *
     * @param numberOfSubmit - number of taken message from queue
     */
    void receiveData(int numberOfSubmit);

    /**
     * @return collection interface represent all elevator in connected with system data base.
     */
    Collection<ElevatorInterface> showStatusAllElevator();

    /**
     * Simulate number of submit movement elevator with specific id.
     *
     * @param idElevator - id simulated elevator.
     * @param numberOfSubmit - nb of simulations.
     */
    void updateAndSimulationStep(int idElevator, int numberOfSubmit);
}
