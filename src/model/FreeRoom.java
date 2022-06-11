package model;

/**
 * This class is a child class of room
 *
 * @author shifatsahariar
 */
public class FreeRoom extends Room{

 //private Double freeRoomPrice = 0.0 ;
    public FreeRoom(String roomNumber,RoomType enumeration) {
        super(roomNumber,0.0,enumeration);

    }

    @Override
    public String toString() {
        return "Free room number is " + getRoomNumber() + " and room Type " + getRoomType();
    }
}

