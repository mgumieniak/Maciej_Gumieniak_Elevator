package Domains;
import java.util.LinkedList;

public class Elevator implements ElevatorInterface{

    private final int id;
    private int currentFlour;
    private int destinationFlour;
    private LinkedList<Integer> flourQue;


    public Elevator(int id, int currentFlour, int destinationFlour) {
        this.id = id;
        this.currentFlour = currentFlour;
        this.destinationFlour = destinationFlour;
        this.flourQue = new LinkedList<>();
    }

    void helpForDestNegative(int flourReport){
        for(int i=0; i<this.getFlourQue().size(); i++){
            if(this.getFlourQue().get(i)<flourReport && (this.currentFlour - flourReport)>0){
                this.getFlourQue().add(i,flourReport);
                break;
            }else if(i == this.getFlourQue().size()-1){
                this.getFlourQue().addLast(flourReport);
                break;
            }
        }
    }

    void helpForDestPositive(int flourReport){
        for(int i=0; i<this.getFlourQue().size(); i++){
            if(this.getFlourQue().get(i)>flourReport && (this.currentFlour - flourReport)<0){
                this.getFlourQue().add(i,flourReport);
                break;
            }else if(i == this.getFlourQue().size()-1){
                this.getFlourQue().addLast(flourReport);
                break;
            }
        }
    }

    @Override
    public void pickUpElevator(int flourReport, int destination){

        if (destination != 0) {
            System.out.println("pickUp on floor: "+flourReport+" direct: "+destination);
        }

        if(this.getFlourQue().isEmpty()){
            this.getFlourQue().add(flourReport);
        }else if(destination <0){
            helpForDestNegative(flourReport);
        }else if(destination >0){
            helpForDestPositive(flourReport);
        }else{ // desttination ==0
            if(this.getCurrentFlour()>flourReport){
                helpForDestNegative(flourReport);
            }else helpForDestPositive(flourReport);
        }
    }

    @Override
    public void selectFlourInsideElevator(int destinationFlour){
        System.out.println("Select inside elevator floor: "+destinationFlour);
        if(!this.getFlourQue().contains(destinationFlour)){
            this.pickUpElevator(destinationFlour,0);
        }
    }

    @Override
    public void update(){
        if(this.getCurrentFlour() == this.getDestinationFlour()){
            System.out.println("Floor: "+this.getCurrentFlour());
            if(!(this.getFlourQue().isEmpty())){
                this.setDestinationFlour(this.getFlourQue().removeFirst());
            }
        }
    }

    @Override
    public void simulationStep(){
        if(this.getDestinationFlour() == this.getCurrentFlour()){}
        else if(this.getDestinationFlour() > this.getCurrentFlour()){
            this.setCurrentFlour(this.getCurrentFlour()+1);
        }else this.setCurrentFlour(this.getCurrentFlour()-1);
    }

    @Override
    public void status(){
        System.out.println(this.toString());
    }


    public void updateAndStatusAndSimulationStep(){
        this.status();
        this.update();
        this.simulationStep();
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


    public void setCurrentFlour(int currentFlour) {
        this.currentFlour = currentFlour;
    }

    public void setDestinationFlour(int destinationFlour) {
        this.destinationFlour = destinationFlour;
    }


    public LinkedList<Integer> getFlourQue() {
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
