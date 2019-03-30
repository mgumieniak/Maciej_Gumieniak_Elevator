package Service;

public interface ElevatorSystemInterface {
    void pickUpAcction(int id, int direct);
    void step();
    void showElevatorStatusById(int id);
    void showAllElevatorStatus();

}
