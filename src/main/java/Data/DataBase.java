package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.Set;
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
    public Elevator findElevatorUpById(int id) {
        Elevator elevator = map.get(id);
        if(elevator == null){
            throw new IllegalArgumentException("Dont find elevator with id = "+id);
        }
        return elevator;
    }

    @Override
    public Collection<Elevator> showStatusAllElevator() {
        return this.getMap().values();
    }

    public ConcurrentMap<Integer, Elevator> getMap() {
        return map;
    }

}
