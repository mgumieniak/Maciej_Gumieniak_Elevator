package Domains;

import java.util.Objects;

/**
 * Immutable class which instances are produced by producer ( people selecting destination floor inside elevator or
 * pick up). Instances are putted into BockingQueue. The Consumer take Messages objects from BlockingQueue and
 * produced from it Elevator objects, therefor the instance of Message class should contain all information to create
 * Elevator instance.
 */
public class Message {
    private final int idElevator;
    private final int destinationFloor;
    private final int direction;
    private final boolean selectInsideElevator;

    /**
     * Private class constructor.
     *
     * @param idElevator - unique int which specific object
     * @param destinationFloor - current destination floor.
     * @param direction - direction which we pick up elevator, otherwise if you want to use selectFloorInsideElevator put 0.
     * @param selectInsideElevator - true if you want to use selectFloorInsideElevator.
     */
    private Message(int idElevator, int destinationFloor, int direction, boolean selectInsideElevator) {
        this.idElevator = idElevator;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.selectInsideElevator = selectInsideElevator;
    }

    /**
     * Static factory method, which produce the objects.
     *
     * @param idElevator - unique int which specific object
     * @param destinationFloor - current destination floor.
     * @param direction - direction which we pick up elevator, otherwise if you want to use selectFloorInsideElevator put 0.
     * @param selectInsideElevator - true if you want to use selectFloorInsideElevator.
     * @return created object.
     */
    public static Message createMessage(int idElevator, int destinationFloor, int direction, boolean selectInsideElevator){
        return new Message(idElevator, destinationFloor, direction, selectInsideElevator);
    }

    /**
     * @return Elevator id.
     */
    public int getIdElevator() {
        return idElevator;
    }

    /**
     *
     * @return Elevator destination floor.
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     *
     * @return Elevator direction.
     */
    public int getDirection() {
        return direction;
    }

    /**
     *
     * @return true if you want to use selectFloorInsideElevator
     */
    public boolean isSelectInsideElevator() {
        return selectInsideElevator;
    }

    @Override
    public String toString() {
        return "Message{ idElevator=" + idElevator +
                ", destinationFloor=" + destinationFloor +
                ", direction=" + direction +
                ", selectInsideElevator=" + selectInsideElevator +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getIdElevator() == message.getIdElevator() &&
                getDestinationFloor() == message.getDestinationFloor() &&
                getDirection() == message.getDirection() &&
                isSelectInsideElevator() == message.isSelectInsideElevator();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdElevator());
    }
}
