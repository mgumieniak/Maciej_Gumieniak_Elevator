package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DataBase implements DataBaseInterface{

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

    private ConcurrentMap<Integer, Elevator> map;

    public DataBase() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void addElevator(int id) {
        if (id < 0 || id >= numberElevator.NUMBERSSELEVATORS.getValue()) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        if(map.get(id) == null){
            map.putIfAbsent(id, new Elevator(id,0,0));
        }else throw new IllegalArgumentException("Id exist");

    }

    @Override
    public ElevatorInterface findElevatorUpById(int id) {
        ElevatorInterface elevator = map.get(id);
        if(elevator == null){
            throw new IllegalArgumentException("Dont find elevator with id = "+id);
        }
        return elevator;
    }

    public ConcurrentMap<Integer, Elevator> getMap() {
        return map;
    }

}
