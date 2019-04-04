package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *  DataBase class implements DataBaseInterface. The Class handle data Elevator class due to
 *  unique id. Id have to be the value <1,16>.
 *
 *  Data are stored in ConcurrentHashMap. It helps to deal with data using multi thread,
 *  without destroy them. To add new object into ConcurrentHashMap, method addElevator(..)
 *  use putIfAbsent. It ensure that we do not override existing object. To update existing
 *  value use compute method instead of put method, which is not thread safety. In package
 *  Service in Consumer class is the example of using compute method.
 *  To get object from ConcurrentHashMap use get method.
 */
public class DataBase implements DataBaseInterface<ElevatorInterface>{

    /**
     *  NUMBERSSELEVATORS - maximum nb of elevator which can be stored in data base.
     */
    public enum numberElevator{
        NUMBERSSELEVATORS(16);

        int value;
        numberElevator(int value) {
            this.value=value;
        }

        public int getValue() {
            return value;
        }
    }

    private ConcurrentHashMap<Integer, ElevatorInterface> map;

    /**
     *  Construct the instance of a class, which contain ConcurrentHashMap.
     */
    public DataBase() {
        this.map = new ConcurrentHashMap<>();
    }

    /**
     * Add new object in data base, which unique id.
     *
     * @param id - unique int value enable separated objects in data base.
     * @throws IllegalArgumentException if id is not between <1,16>
     */
    @Override
    public void add(int id) {
        if (id <= 0 || id > numberElevator.NUMBERSSELEVATORS.getValue()) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        map.putIfAbsent(id, Elevator.createElevator(id,0,0, List.of()));
    }

    /**
     * Return element from data base but don't remove.
     *
     * @param id - unique int value enable separated objects in
     *           data base.
     * @throws IllegalArgumentException if id is not between <1,16>
     * @return the object with specific id.
     */
    @Override
    public ElevatorInterface findObjectById(int id) {
        ElevatorInterface elevator = map.get(id);
        if(elevator == null){
            throw new IllegalArgumentException("Dont find elevator with id = "+id);
        }
        return elevator;
    }

    /**
     * @return Collection with all objects which was added in data base
     */
    @Override
    public Collection<ElevatorInterface> showAll() {
        return this.getMap().values();
    }

    /**
     * @return ConcurrentHashMap which contains Elevator objects.
     */
    @Override
    public ConcurrentHashMap<Integer, ElevatorInterface> getMap() {
        return map;
    }

}
