package Domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test Message class.
 */
class MessageTest {

    @Test
    void createMessageTest(){
        Message msg = Message.createMessage(1,0,0,false);
        assertAll(
                ()->assertEquals(1,msg.getIdElevator()),
                ()->assertEquals(0,msg.getDestinationFloor()),
                ()->assertEquals(0,msg.getDirection()),
                ()->assertEquals(false,msg.isSelectInsideElevator())
        );
    }

}