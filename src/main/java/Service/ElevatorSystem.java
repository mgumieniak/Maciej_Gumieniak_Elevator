package Service;

import Data.DataBase;
import Domains.ElevatorInterface;
import Domains.Message;
import java.util.Collection;
import java.util.concurrent.*;

/**
 * ElevatorSystem class implements ElevatorSystemInterface. Each object contain four private fields:
 * processors - nb running processors in elevator system., executorService - executor service to
 * handle threads, dataBase - store data about elevator instances and queue - BlockingQueue instance which
 * store Message produced by producers.
 */
public class ElevatorSystem implements ElevatorSystemInterface{

    private int processors;
    private ExecutorService executorService;
    private DataBase dataBase;
    private BlockingQueue<Message> queue;

    /**
     * Construct the class instance.
     *
     * @param processors - nb running processors in elevator system.
     * @param data - data base connected with elevator system.
     * @param queue - BlockingQueue instance which store Message produced by producers.
     */
    public ElevatorSystem(int processors, DataBase data,BlockingQueue<Message> queue) {
        this.processors = processors;
        this.executorService = Executors.newFixedThreadPool(processors);
        this.dataBase = data;
        this.queue = queue;
    }

    /**
     * Pick up elevator with id, on floor report in specific direction. The method will be run
     * in separately executing thread using Producer class. Method imitate customer action.
     *
     * @param idElevator - id elevator.
     * @param flourReport - selected floor.
     * @param direction - direction.
     */
    @Override
    public void pickUpElevator(int idElevator, int flourReport, int direction) {
        Producer producer = new Producer(getQueue(),idElevator, flourReport,direction,false);
        getExecutorService().submit(producer.getRunnableProducer());
    }

    /**
     * Select destination floor in elevator. The method will be run
     * in separately executing thread using Producer class. Method imitate customer action.
     *
     * @param idElevator - id elevator.
     * @param destinationFlour - selected floor.
     */
    @Override
    public void selectFloorInsideElevator(int idElevator, int destinationFlour) {
        Producer producer = new Producer(getQueue(),idElevator, destinationFlour,0,true);
        getExecutorService().submit(producer.getRunnableProducer());
    }

    /**
     * Take numberOfSubmit Message from queue by Consumer. If numberOfSubmit will be bigger then actual nb of
     * messages in queue, the thread will be waiting for data.
     *
     * @param numberOfSubmit - number of taken message from queue
     */
    @Override
    public void receiveData(int numberOfSubmit) {
        Consumer consumer = new Consumer(getQueue(),getData());
        for(int i=0; i<numberOfSubmit; i++){
            getExecutorService().submit(consumer.getRunnableConsumer());
        }
    }

    /**
     * @return  collection interface represent all elevator in connected with system data base.
     */
    @Override
    public Collection<ElevatorInterface> showStatusAllElevator() {
        return dataBase.showStatusAllElevator();
    }

    /**
     * Simulate number of submit movement elevator with specific id.
     *
     * @param idElevator - id simulated elevator.
     * @param numberOfSubmit - nb of simulations.
     */
    @Override
    public void updateAndSimulationStep(int idElevator, int numberOfSubmit) {
        for(int i=0; i<numberOfSubmit; i++){
            dataBase.getMap().computeIfPresent(idElevator,(id,elevator)->elevator.updateAndSimulationStep());
        }
    }

    /**
     * Close executor service and wait for ending task by run processed for specific time. If any thread do not
     * finish their task, after timeout, the exception will be thrown and catch inside this method.
     *
     * @param timeout - time durations.
     * @param unit - unit specify in parameter timeout.
     */
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

    /**
     * @return executor instance.
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * @return data base instance.
     */
    public DataBase getData() {
        return dataBase;
    }

    /**
     * @return queue instance.
     */
    public BlockingQueue<Message> getQueue() {
        return queue;
    }
}
