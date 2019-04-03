package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;

import java.util.Collection;
import java.util.List;
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

    private ConcurrentHashMap<Integer, ElevatorInterface> map;

    public DataBase() {
        this.map = new ConcurrentHashMap<>();
    }

    @Override
    public void addElevator(int id) {
        if (id < 0 || id >= numberElevator.NUMBERSSELEVATORS.getValue()) {
            throw new IllegalArgumentException("Id cannot be negative");
        }
        map.putIfAbsent(id, Elevator.createElevator(id,0,0, List.of()));
    }

    @Override
    public ElevatorInterface findElevatorUpById(int id) {
        ElevatorInterface elevator = map.get(id);
        if(elevator == null){
            throw new IllegalArgumentException("Dont find elevator with id = "+id);
        }
        return elevator;
    }

    @Override
    public Collection<ElevatorInterface> showStatusAllElevator() {
        return this.getMap().values();
    }

    public ConcurrentHashMap<Integer, ElevatorInterface> getMap() {
        return map;
    }

}
