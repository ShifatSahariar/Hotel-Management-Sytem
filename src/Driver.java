import model.*;

import service.ReservationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Driver {
    public static void main(String[] args) {
       // Customer customer = new Customer("frist","second","email");
        ReservationService reservationService = ReservationService.getInstance();
        //CustomerService customerService = CustomerService.getInstance();
       // customerService.addCustomer("s@gmail.com","shif","sa");
       // customerService.addCustomer("se@gmail.com","shif","sam");
        //customerService.addCustomer("s@gmailok","shif","sam");
        //System.out.println(customerService.getAllCustomers());
        Customer customer = new Customer("ss","shif","s@gmail.com");
        Customer customer1 = new Customer("bb","shif","bb@gmail.com");
        IRoom room = new Room("222",345.0, RoomType.SINGLE);
        IRoom room2 = new FreeRoom("333",RoomType.DOUBLE);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");



        //System.out.println(reservationService.getCustomersReservation(new Customer("s","ss","s@c.com")));
        reservationService.reserveARoom(customer,room2,new Date(22/12/2022),new Date(23/12/2022));
        //reservationService.reserveARoom(customer,room,new Date(22/12/2022),new Date(23/12/2022));

        //reservationService.reserveARoom(customer1,room,new Date(),new Date());

        System.out.println(reservationService.getCustomersReservation(customer));
      //reservationService.printAllReservation();
        //reservationService.printAllReservation();
    }
}
