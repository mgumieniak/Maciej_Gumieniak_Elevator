package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;
import java.util.Collection;


public interface DataBaseInterface {
    void addElevator(int id);
    ElevatorInterface findElevatorUpById(int id);
    //void updateElevatorById(int id, int currentFlour, int directFlour);
    Collection<Elevator> showStatusAllElevator();
}
