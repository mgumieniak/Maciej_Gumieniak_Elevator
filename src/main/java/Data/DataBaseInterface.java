package Data;

import Domains.ElevatorInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *  Interface DataBaseInterface provides below method to
 *  handle with storing data about Elevator.
 *
 *  The elements in data base are separated by unique id.
 */
public interface DataBaseInterface {

    /**
     * Add new object in data base, which unique id.
     *
     * @param id - unique int value enable separated objects in
     *           data base.
     */
    void addElevator(int id);

    /**
     * @param id - unique int value enable separated objects in
     *           data base.
     * @return - object in data base connected with id
     */
    ElevatorInterface findElevatorUpById(int id);

    /**
     * @return Collection with all objects which was added in data base
     */
    Collection<ElevatorInterface> showStatusAllElevator();
}
