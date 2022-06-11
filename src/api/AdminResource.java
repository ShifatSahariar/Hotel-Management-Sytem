package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;
import java.util.Collection;
import java.util.List;

/**
 * API for Admin panel
 * @author shifatsahariar
 */
public class AdminResource {
    // Declaring Static Reference
    private static AdminResource singletonAdminResource ;
    private AdminResource(){};
    public static AdminResource adminResource(){
        if (singletonAdminResource == null){
            singletonAdminResource = new AdminResource();
        }
        return singletonAdminResource;
    }
    // <<Finishing of Declaring Static Reference

    CustomerService customerService =CustomerService.getInstance();
    ReservationService reservationService =ReservationService.getInstance();

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public  void addRoom(Collection<IRoom> rooms){
        for (IRoom room:rooms
             ) {
            reservationService.addRoom(room);
        }
    }

    public Collection<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    public Collection<IRoom> getAllRooms(){
        return reservationService.getAllRooms();
    }

    public void disPlayAllReservations(){
      reservationService.printAllReservation();

    }
}
