package Service;

import Data.DataBase;
import Data.DataBaseInterface;
import Domains.ElevatorInterface;
import java.util.Collection;
import java.util.concurrent.*;

public class ElevatorSystem implements ElevatorSystemInterface{

    private int processors;
    private ExecutorService executorService;
    private DataBase data;

    public ElevatorSystem(int processors, DataBase data) { ;
        this.processors = processors;
        this.executorService = Executors.newFixedThreadPool(processors);
        this.data = data;
    }

    @Override
    public void submitProducer(Producer producer) {
        getExecutorService().submit(producer.getRunnableProducer());
    }

    @Override
    public void submitConsumer(Consumer consumer, int numberOfSubmit) {
        for(int i=0; i<numberOfSubmit; i++){
            getExecutorService().submit(consumer.getRunnableConsumer());
        }
    }

    @Override
    public Collection<ElevatorInterface> showStatusAllElevator() {
        return data.showStatusAllElevator();
    }

    @Override
    public void updateAndSimulationStep(int idElevator, int numberOfSubmit) {
        for(int i=0; i<numberOfSubmit; i++){
            data.getMap().computeIfPresent(idElevator,(id,elevator)->elevator.updateAndSimulationStep());
        }
    }

    @Override
    public void systemShutDown(long timeout, TimeUnit unit) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(timeout, unit)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public DataBase getData() {
        return data;
    }
}
