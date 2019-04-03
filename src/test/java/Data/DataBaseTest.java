package Data;
import Domains.Elevator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 15})
    void addElevatorAppropriateInput(int args) {
        DataBase dataBase = new DataBase();
        dataBase.addElevator(args);
        assertAll(
              //  ()->assertEquals(1,dataBase.getMap().size()),
               // ()->assertEquals(new Elevator(args,0,0),dataBase.getMap().get(args))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { -2, -1, 16})
    void addElevatorShouldTrowExceptionForNegativeAndExceedNUMBERSELEVATORSId(int args) {
        DataBase dataBase = new DataBase();
        assertThrows(IllegalArgumentException.class,()->dataBase.addElevator(args));

    }

    @Test
    void addElevatorShouldTrowExceptionForRepeatId() {
        DataBase dataBase = new DataBase();
        dataBase.addElevator(1);
        assertThrows(IllegalArgumentException.class,()->dataBase.addElevator(1));
    }

    @Test
    void findElevatorById() {
        DataBase dataBase = new DataBase();
        dataBase.addElevator(1);
        assertAll(
             //   ()->assertEquals(new Elevator(1,0,0),dataBase.findElevatorUpById(1))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 1,2})
    void findElevatorByIdShouldTrowExceptionForWrongId(int args) {
        DataBase dataBase = new DataBase();
        dataBase.addElevator(args);
        assertThrows(IllegalArgumentException.class,()->dataBase.findElevatorUpById(3));
    }

    @Test
    void showStatusAllElevatorTest(){
        DataBase dataBase = new DataBase();
        dataBase.addElevator(1);
        dataBase.addElevator(2);
        assertAll(
                ()->assertEquals(2,dataBase.getMap().size()),
                ()->assertTrue(dataBase.showStatusAllElevator().containsAll(dataBase.getMap().values()))
        );
    }

}