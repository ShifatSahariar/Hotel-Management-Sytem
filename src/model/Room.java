package model;

public class Room implements IRoom {
    private String roomNumber;
    private Double price;
    private RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public Double getRoomPrice() {
        return this.price;
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        if (this.price == null || this.price == 0.0) {
            return true;
        }
        else return false;
    }



    @Override
    public String toString() {
        return "Room number is " + getRoomNumber() + "Room Type " + getRoomType() + " and Price is "+getRoomPrice();
    }
}
