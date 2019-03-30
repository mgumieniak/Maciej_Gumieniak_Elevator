package Data;

import Domains.ElevatorInterface;

public interface DataBaseInterface {
    ElevatorInterface pickElevatorUpById(int id, int direct);
    void updateElevatorById(int id, int currentFlour, int directFlour);
    Iterable<ElevatorInterface> showStatusAllElevator();
    Iterable<ElevatorInterface> showElevatorById(int id);
    void addElevator(int id);
}
