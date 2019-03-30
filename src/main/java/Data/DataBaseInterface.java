package Data;

import Domains.ElevatorInterface;

public interface DataBaseInterface {
    void addElevator(int id);
    ElevatorInterface findElevatorUpById(int id);
    //void updateElevatorById(int id, int currentFlour, int directFlour);
    //Iterable<ElevatorInterface> showStatusAllElevator();
}
