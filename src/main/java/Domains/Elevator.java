package Domains;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Immutable Elevator class implements ElevatorInterface. Each object contain four
 * private fields: id - unique int which specific object, currentFloor - current floor,
 * destinationFloor - current destination floor and FloorQue - list of flours, which
 * clients pick up elevator or select inside.
 *
 * Elevator class is immutable, so each changes lead to create new class instance
 * which modified fields.
 *
 * The purpose of the class is to ensure property handle incoming orders for elevator
 * ( in specific order). For example: If sb inside elevator select 7 floor and sb pick
 * up elevator on floor 4 to go up. The data will be put in list in order: [4,7]. If
 * sb pick up elevator on floor 4 to go down then the order in list will be: [7,4].
 *
 * Otherwise class contains method to simulate elevator movement (increasing/decreasing
 * actual floor) with selecting current floor from FloorQue.
 */
public class Elevator implements ElevatorInterface{

    private final int id;
    private final int currentFloor;
    private final int destinationFloor;
    private final List<Integer> FloorQue;

    /**
     * Private class constructor.
     *
     * @param id - unique int which specific object.
     * @param currentFloor - current floor.
     * @param destinationFloor - current destination floor.
     * @param list - list of flours, which clients pick up elevator or select inside.
     */
    private Elevator(int id, int currentFloor, int destinationFloor, List<Integer> list) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.FloorQue = Collections.unmodifiableList(new LinkedList<>(list));
    }

    /**
     * Static factory method, which produce the objects.
     *
     * @param id - unique int which specific object.
     * @param currentFloor - current floor.
     * @param destinationFloor - current destination floor.
     * @param list - list of flours, which clients pick up elevator or select inside.
     * @return the produced class object.
     */
    public static Elevator createElevator(int id, int currentFloor, int destinationFloor, List<Integer> list){
        return new Elevator(id, currentFloor, destinationFloor, list);
    }

    /**
     * Help method handle ordering incoming task when destination is negative.
     * The position new task in FloorQue depends from the actual destination elevator
     * and destination of pick up. Example:
     * When elevator go up to floor nb 5 and sb pick up 2 to go down, the FloorQue looks
     * like [5,2], but sb immediately pick up 3 to go down. The FloorQue should looks like
     * [5,3,2]. That is why we have to use for loop to specific appropriate FloorQue.
     *
     *
     * @param floorReport - floor on which sb pick up elevator.
     * @return modified class instance.
     */
    private Elevator helpForDestNegative(int floorReport){
        LinkedList<Integer> list= new LinkedList<>(this.getFloorQue());
        for(int i=0; i<this.getFloorQue().size(); i++){
            if(this.getFloorQue().get(i)<floorReport && (this.currentFloor - floorReport)>0){
                list.add(i,floorReport);
                break;
            }else if(i == this.getFloorQue().size()-1){
                list.addLast(floorReport);
                break;
            }
        }
        return createElevator(getId(),getcurrentFloor(),getdestinationFloor(),list);
    }

    /**
     * Handle ordering incoming task when destination is positive. The causes creating are
     * the same like in method helpForDestNegative.
     *
     * @param floorReport - floor on which sb pick up elevator.
     * @return modified class instance.
     */
    private Elevator helpForDestPositive(int floorReport){
        LinkedList<Integer> list= new LinkedList<>(this.getFloorQue());
        for(int i=0; i<this.getFloorQue().size(); i++){
            if(this.getFloorQue().get(i)>floorReport && (this.currentFloor - floorReport)<0){
                list.add(i,floorReport);
                break;
            }else if(i == this.getFloorQue().size()-1){
                list.addLast(floorReport);
                break;
            }
        }
        return createElevator(getId(),getcurrentFloor(),getdestinationFloor(),list);
    }

    /**
     * Method handle pick up the elevator from specific floor and direction. Beside add
     * appropriate new task (floor) in FloorQue using helpForDestPositive and helpForDestNegative.
     *
     * @param floorReport - the floor from sb call the elevator.
     * @param direction - direction in which sb want to go by elevator. The positive value
     *                  means up direction and negative value means down direction.
     * @return modified class instance.
     */
    @Override
    public Elevator pickUpElevator(int floorReport, int direction){
        LinkedList<Integer> list= new LinkedList<>(this.getFloorQue());

        if(this.getFloorQue().isEmpty()){
            list.add(floorReport);
        }else if(direction <0){
            return helpForDestNegative(floorReport);
        }else if(direction >0){
            return helpForDestPositive(floorReport);
        }else{ // direction ==0
            if(this.getcurrentFloor()>floorReport){
               return helpForDestNegative(floorReport);
            }else return helpForDestPositive(floorReport);
        }
        return createElevator(getId(),getcurrentFloor(),getdestinationFloor(),list);
    }

    /**
     * Method handle select floor inside elevator using pickUpElevator with direction = 0.
     * 
     * @param destinationFloor - destination floor, selected inside elevator.
     * @return modified class instance.
     */
    @Override
    public Elevator selectFlourInsideElevator(int destinationFloor){
        if(!this.getFloorQue().contains(destinationFloor)){
            return pickUpElevator(destinationFloor,0);
        }else return this;
    }

    /**
     * Update queue value. When current floor is equal actual floor take first
     * element form floorQueue (return and remove).
     *
     * @return modified class instance.
     */
    @Override
    public Elevator update(){
        if(this.getcurrentFloor() == this.getdestinationFloor() && !(this.getFloorQue().isEmpty())){
            LinkedList<Integer> list= new LinkedList<>(this.getFloorQue());
            int destinationFloor = list.removeFirst();
            return createElevator(getId(),getcurrentFloor(),destinationFloor,list);
        }else return this;
    }

    /**
     * Simulate elevator movement increasing or decreasing current floor by 1.
     *
     * @return modified class instance.
     */
    @Override
    public Elevator simulationStep(){
        if(this.getdestinationFloor() == this.getcurrentFloor()){return  this;}
        else if(this.getdestinationFloor() > this.getcurrentFloor()){
            int currentFloor = this.getcurrentFloor()+1;
            return createElevator(getId(),currentFloor,getdestinationFloor(),getFloorQue());
        }else {
            int currentFloor = this.getcurrentFloor()-1;
            return createElevator(getId(),currentFloor,getdestinationFloor(),getFloorQue());
        }
    }

    /**
     * Show all information about instance fields.
     */
    @Override
    public void status(){
        System.out.println(this.toString());
    }

    /**
     * Help method to call method update() and simulationStep().
     *
     * @return modified class instance.
     */
    @Override
    public Elevator updateAndSimulationStep(){
        Elevator elevator = this.update();
        elevator = elevator.simulationStep();
        return elevator;
    }

    /**
     *
     * @return object id.
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return object current floor.
     */
    public int getcurrentFloor() {
        return currentFloor;
    }

    /**
     *
     * @return object destination floor.
     */
    public int getdestinationFloor() {
        return destinationFloor;
    }

    /**
     *
     * @return object floor que.
     */
    public List<Integer> getFloorQue() {
        return FloorQue;
    }

    @Override
    public String toString() {
        return "Elevator{ id=" + id +
                ", currentFloor=" + currentFloor +
                ", destinationFloor=" + destinationFloor +
                "} "+this.getFloorQue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return getId() == elevator.getId() &&
                getcurrentFloor() == elevator.getcurrentFloor() &&
                getdestinationFloor() == elevator.getdestinationFloor() &&
                Objects.equals(getFloorQue(), elevator.getFloorQue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getcurrentFloor(), getdestinationFloor(), getFloorQue());
    }
}
