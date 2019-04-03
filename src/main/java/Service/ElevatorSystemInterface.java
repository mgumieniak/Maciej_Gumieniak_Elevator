package Service;

import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

public interface ElevatorSystemInterface {
    void systemShutDown(long timeout, TimeUnit unit);
    void submitProducer(Producer producer);
    void submitConsumer(Consumer consumer, int numberOfSubmit);
    Collection<ElevatorInterface> showStatusAllElevator();
    void updateAndSimulationStep(int idElevator, int numberOfSubmit);
}
