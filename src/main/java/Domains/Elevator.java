package Domains;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class Elevator implements ElevatorInterface{

    private final int id;
    private final int currentFlour;
    private final int destinationFlour;
    private final List<Integer> flourQue;


    private Elevator(int id, int currentFlour, int destinationFlour, List<Integer> list) {
        this.id = id;
        this.currentFlour = currentFlour;
        this.destinationFlour = destinationFlour;
        this.flourQue = Collections.unmodifiableList(new LinkedList<>(list));
    }

    public static Elevator createElevator(int id, int currentFlour, int destinationFlour, List<Integer> list){
        return new Elevator(id, currentFlour, destinationFlour, list);
    }

    ElevatorInterface helpForDestNegative(int flourReport){
        LinkedList<Integer> list= new LinkedList<>(this.getFlourQue());
        for(int i=0; i<this.getFlourQue().size(); i++){
            if(this.getFlourQue().get(i)<flourReport && (this.currentFlour - flourReport)>0){
                list.add(i,flourReport);
                break;
            }else if(i == this.getFlourQue().size()-1){
                list.addLast(flourReport);
                break;
            }
        }
        return createElevator(getId(),getCurrentFlour(),getDestinationFlour(),list);
    }

    ElevatorInterface helpForDestPositive(int flourReport){
        LinkedList<Integer> list= new LinkedList<>(this.getFlourQue());
        for(int i=0; i<this.getFlourQue().size(); i++){
            if(this.getFlourQue().get(i)>flourReport && (this.currentFlour - flourReport)<0){
                //this.getFlourQue().add(i,flourReport);
                //break;
                list.add(i,flourReport);
                break;
            }else if(i == this.getFlourQue().size()-1){
                //this.getFlourQue().addLast(flourReport);
               // break;
                list.addLast(flourReport);
                break;
            }
        }
        return createElevator(getId(),getCurrentFlour(),getDestinationFlour(),list);
    }

    @Override
    public ElevatorInterface pickUpElevator(int flourReport, int destination){
        LinkedList<Integer> list= new LinkedList<>(this.getFlourQue());
        /*if (destination != 0) {
            System.out.println("pickUp on floor: "+flourReport+" direct: "+destination);
        }*/

        if(this.getFlourQue().isEmpty()){
            list.add(flourReport);
        }else if(destination <0){
            return helpForDestNegative(flourReport);
        }else if(destination >0){
            return helpForDestPositive(flourReport);
        }else{ // desttination ==0
            if(this.getCurrentFlour()>flourReport){
               return helpForDestNegative(flourReport);
            }else return helpForDestPositive(flourReport);
        }
        return createElevator(getId(),getCurrentFlour(),getDestinationFlour(),list);
    }

    @Override
    public ElevatorInterface selectFlourInsideElevator(int destinationFlour){
        //System.out.println("Select inside elevator floor: "+destinationFlour);
        if(!this.getFlourQue().contains(destinationFlour)){
            return pickUpElevator(destinationFlour,0);
        }else return this;
    }

    @Override
    public ElevatorInterface update(){
        if(this.getCurrentFlour() == this.getDestinationFlour() && !(this.getFlourQue().isEmpty())){
            LinkedList<Integer> list= new LinkedList<>(this.getFlourQue());
            int destinationFlour = list.removeFirst();
            return createElevator(getId(),getCurrentFlour(),destinationFlour,list);
        }else return this;
    }

    @Override
    public ElevatorInterface simulationStep(){
        if(this.getDestinationFlour() == this.getCurrentFlour()){return  this;}
        else if(this.getDestinationFlour() > this.getCurrentFlour()){
            int currentFlour = this.getCurrentFlour()+1;
            return createElevator(getId(),currentFlour,getDestinationFlour(),getFlourQue());
        }else {
            int currentFlour = this.getCurrentFlour()-1;
            return createElevator(getId(),currentFlour,getDestinationFlour(),getFlourQue());
        }
    }

    @Override
    public void status(){
        System.out.println(this.toString());
    }

    @Override
    public ElevatorInterface updateAndSimulationStep(){
        ElevatorInterface elevator = this.update();
        elevator = elevator.simulationStep();
        return elevator;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFlour() {
        return currentFlour;
    }

    public int getDestinationFlour() {
        return destinationFlour;
    }


    public List<Integer> getFlourQue() {
        return flourQue;
    }

    @Override
    public String toString() {
        return "Elevator{ id=" + id +
                ", currentFlour=" + currentFlour +
                ", destinationFlour=" + destinationFlour +
                "} "+this.getFlourQue();
    }
}
