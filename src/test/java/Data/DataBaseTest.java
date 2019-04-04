package Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseTest {

    @ParameterizedTest
    @ValueSource(ints = { 0, 1, 15})
    void addAppropriateInput(int args) {
        DataBase dataBase = new DataBase();
        dataBase.add(args);
        assertAll(
              //  ()->assertEquals(1,dataBase.getMap().size()),
               // ()->assertEquals(new Elevator(args,0,0),dataBase.getMap().get(args))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { -2, -1, 16})
    void addShouldTrowExceptionForNegativeAndExceedNUMBERSELEVATORSId(int args) {
        DataBase dataBase = new DataBase();
        assertThrows(IllegalArgumentException.class,()->dataBase.add(args));

    }

    @Test
    void addShouldTrowExceptionForRepeatId() {
        DataBase dataBase = new DataBase();
        dataBase.add(1);
        assertThrows(IllegalArgumentException.class,()->dataBase.add(1));
    }

    @Test
    void findElevatorById() {
        DataBase dataBase = new DataBase();
        dataBase.add(1);
        assertAll(
             //   ()->assertEquals(new Elevator(1,0,0),dataBase.findObjectById(1))
        );
    }

    @ParameterizedTest
    @ValueSource(ints = { 1,2})
    void findElevatorByIdShouldTrowExceptionForWrongId(int args) {
        DataBase dataBase = new DataBase();
        dataBase.add(args);
        assertThrows(IllegalArgumentException.class,()->dataBase.findObjectById(3));
    }

    @Test
    void showAllTest(){
        DataBase dataBase = new DataBase();
        dataBase.add(1);
        dataBase.add(2);
        assertAll(
                ()->assertEquals(2,dataBase.getMap().size()),
                ()->assertTrue(dataBase.showAll().containsAll(dataBase.getMap().values()))
        );
    }

}