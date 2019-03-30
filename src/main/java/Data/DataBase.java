package Data;

import Domains.Elevator;
import Domains.ElevatorInterface;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class DataBase implements DataBaseInterface{

    private int numberElevator;
    private ConcurrentMap<Integer, Elevator> map;

    public DataBase(int numberElevator, ConcurrentMap<Integer, Elevator> map) {
        this.numberElevator = numberElevator;
        this.map = map;
    }

    @Override
    public ElevatorInterface pickElevatorUpById(int id, int direct) {
        ElevatorInterface elevator = map.get(id);
        if(elevator == null){
            map.putIfAbsent(id, Elevator.addElevator(id));
        }
    }

    @Override
    public void updateElevatorById(int id, int currentFlour, int directFlour) {
        if(id < 0 && currentFlour<0 && directFlour<0){
            throw new IllegalArgumentException("Wrong input argument");
        }else map.compute(id, (Integer i, Elevator l)-> Elevator.update(l.getId(), currentFlour, directFlour));
    }

    @Override
    public Iterable<ElevatorInterface> showStatusAllElevator() {
        map.elements()
    }

    @Override
    public Iterable<ElevatorInterface> showElevatorById(int id) {
        return null;
    }

    @Override
    public void addElevator(int id) {
        if(map.get(id) == null){
            map.putIfAbsent(id, Elevator.addElevator(id));
        }else throw new IllegalArgumentException("Id exist");
    }

    public ConcurrentMap<Integer, Elevator> getMap() {
        return map;
    }

}
