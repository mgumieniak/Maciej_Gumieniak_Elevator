package Service;

import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface ElevatorSystemInterface {
    void systemShutDown(long timeout, TimeUnit unit);
    void pickUpElevator(int idElevator, int flourReport, int direction);
    void selectFlourInsideElevator(int idElevator, int destinationFlour);
    void receiveData(int numberOfSubmit);
    Collection<ElevatorInterface> showStatusAllElevator();
    void updateAndSimulationStep(int idElevator, int numberOfSubmit);
}
