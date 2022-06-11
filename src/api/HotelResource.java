package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

/**
 * API for HotelResources  ,which will be used in MainMenu Items
 * @author shifatsahariar
 */
public class HotelResource {
    // Declaring Static Reference
    private static HotelResource singletonHotelResource ;
    private HotelResource(){};
    public static HotelResource HotelResource(){
        if (singletonHotelResource == null){
            singletonHotelResource = new HotelResource();
        }
        return singletonHotelResource;
    }
    // <<Finishing of Declaring Static Reference
    ReservationService reservationService = ReservationService.getInstance();
    CustomerService customerService = CustomerService.getInstance();
    // Getting the customer details
    public Customer getCustomer(String email){
        return CustomerService.getInstance().getCustomer(email);
    }

    // Creating a New customer who has no account yet .
    public void createACustomer(String email , String firstName , String lastName){
        customerService.addCustomer(email, firstName, lastName);
    }
    // Searing a room by Room Number .
    public IRoom getRoom (String roomNumber){
        return reservationService.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room , Date checkInDate ,Date checkOutDate) {

        return reservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);

    }

    public Collection<Reservation> getCustomersReservations(String customerEmail){
        return reservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn , Date checkOut){
        return reservationService.findRooms(checkIn,checkOut);
    }



}


