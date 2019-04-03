package Domains;

public class Message {
    private final int idElevator;
    private final int destinationFloor;
    private final int direction;
    private final boolean selectInsideElevator;

    private Message(int idElevator, int destinationFloor, int direction, boolean selectInsideElevator) {
        this.idElevator = idElevator;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.selectInsideElevator = selectInsideElevator;
    }

    public static Message createMessage(int idElevator, int destinationFloor, int direction, boolean selectInsideElevator){
        return new Message(idElevator, destinationFloor, direction, selectInsideElevator);
    }

    public int getIdElevator() {
        return idElevator;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public int getDirection() {
        return direction;
    }

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
}
