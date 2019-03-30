package Domains;

import java.util.Objects;

public class Elevator implements ElevatorInterface{
    private final int id;
    private final int currentFlour;
    private final int directFlour;

    private Elevator(int id, int currentFlour, int directFlour) {
        this.id = id;
        this.currentFlour = currentFlour;
        this.directFlour = directFlour;
    }

    public static Elevator update(int id, int currentFlour, int directFlour) {
        return new Elevator(id, currentFlour, directFlour);
    }

    public static Elevator update(Elevator elevator, int currentFlour, int directFlour) {
        return new Elevator(elevator.getId(), currentFlour, directFlour);
    }

    public static Elevator addElevator(int id) {
        return update(id,0,0);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFlour() {
        return currentFlour;
    }

    public int getDirectFlour() {
        return directFlour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Elevator elevator = (Elevator) o;
        return getId() == elevator.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Elevator{" +
                "id=" + id +
                ", currentFlour=" + currentFlour +
                ", directFlour=" + directFlour +
                '}';
    }
}
