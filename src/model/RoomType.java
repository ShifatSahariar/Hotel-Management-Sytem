package model;
/**
 * Enumaration to set room types
 * @author shifatsahariar
 */
public enum RoomType {
    SINGLE(1),
    DOUBLE(2);

    public final int roomType;

    RoomType(int roomType){
        this.roomType = roomType;
    }
}
