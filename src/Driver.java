import model.*;

import service.ReservationService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        ReservationService reservationService = ReservationService.getInstance();
        Collection<IRoom> availableRoomsCollection = new LinkedList<>();
        HashMap<String,Collection<Reservation>> reservationMap = new HashMap<>();
        Collection<Reservation> reservationCollection = new ArrayList<>();

        Customer customer = new Customer("ss","shif","s@gmail.com");
        Customer customer1 = new Customer("bb","shif","bb@gmail.com");
        IRoom room = new Room("2",345.0, RoomType.SINGLE);
        IRoom room2 = new Room("3",145.0, RoomType.DOUBLE);
       // IRoom room3 = new Room("4",145.0, RoomType.DOUBLE);
        reservationCollection.add(reservationService.reserveARoom(customer, room,new Date(22/12/2022),new Date(23/12/2022 )));
        reservationCollection.add(reservationService.reserveARoom(customer, room2,new Date(22/12/2022),new Date(23/12/2022 )));

        reservationService.addRoom(room);
        reservationService.addRoom(room2);
       // reservationService.addRoom(room3);
        reservationMap.put("shifata",reservationCollection);










        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(reservationService.findRooms(new Date(22/12/2022),new Date(23/12/2022)));
        //System.out.println("all rooms");
       // System.out.println( reservationService.getAllRooms());



        //System.out.println(reservationService.getCustomersReservation(new Customer("s","ss","s@c.com")));
        //reservationService.reserveARoom(customer,room2,new Date(22/12/2022),new Date(23/12/2022));
        //reservationService.reserveARoom(customer,room,new Date(22/12/2022),new Date(23/12/2022));

        //reservationService.reserveARoom(customer1,room,new Date(),new Date());

        //System.out.println(reservationService.getCustomersReservation(customer));
      //reservationService.printAllReservation();
        //reservationService.printAllReservation();
    }
}
