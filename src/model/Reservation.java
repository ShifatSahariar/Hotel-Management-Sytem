package model;

import java.util.Date;
/**
 * Reservation class hold the information about customer reservation
 *
 * @author shifatsahariar
 */
public class Reservation {
    private Customer customer;
    private IRoom room;

    private Date checkInDate;
    private Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation : \n"
                + customer +"\n"
                + " room no: " + room.getRoomNumber() +"\n"
                +" checkIn date " + checkInDate +"\n"
                + " checkOut date " + checkOutDate;
    }
}
