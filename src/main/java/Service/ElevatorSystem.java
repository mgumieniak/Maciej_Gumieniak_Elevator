package Service;

import Data.DataBase;
import Data.DataBaseInterface;
import Domains.ElevatorInterface;
import Domains.Message;

import java.util.Collection;
import java.util.concurrent.*;

public class ElevatorSystem implements ElevatorSystemInterface{

    private int processors;
    private ExecutorService executorService;
    private DataBase dataBase;
    BlockingQueue<Message> queue;

    public ElevatorSystem(int processors, DataBase data,BlockingQueue<Message> queue) {
        this.processors = processors;
        this.executorService = Executors.newFixedThreadPool(processors);
        this.dataBase = data;
        this.queue = queue;
    }

    @Override
    public void pickUpElevator(int idElevator, int flourReport, int direction) {
        Producer producer = new Producer(getQueue(),idElevator, flourReport,direction,false);
        getExecutorService().submit(producer.getRunnableProducer());
    }

    @Override
    public void selectFlourInsideElevator(int idElevator, int destinationFlour) {
        Producer producer = new Producer(getQueue(),idElevator, destinationFlour,0,true);
        getExecutorService().submit(producer.getRunnableProducer());
    }

    @Override
    public void receiveData(int numberOfSubmit) {
        Consumer consumer = new Consumer(getQueue(),getData());
        for(int i=0; i<numberOfSubmit; i++){
            getExecutorService().submit(consumer.getRunnableConsumer());
        }
    }

    @Override
    public Collection<ElevatorInterface> showStatusAllElevator() {
        return dataBase.showStatusAllElevator();
    }

    @Override
    public void updateAndSimulationStep(int idElevator, int numberOfSubmit) {
        for(int i=0; i<numberOfSubmit; i++){
            dataBase.getMap().computeIfPresent(idElevator,(id,elevator)->elevator.updateAndSimulationStep());
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
        return dataBase;
    }

    public BlockingQueue<Message> getQueue() {
        return queue;
    }
}
