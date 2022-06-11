package service;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;


import java.util.*;

public class ReservationService {

    // Declaring Static Reference
    private static ReservationService singletonReservationService ;
    private ReservationService(){};
    public static ReservationService getInstance(){
        if (singletonReservationService == null){
            singletonReservationService = new ReservationService();
        }
        return singletonReservationService;
    }


    // Rooms Collections
    private HashMap<String,IRoom> roomsHashMap = new HashMap<>();
    private Collection<IRoom> totalRooms = new LinkedList<>();
    Collection<IRoom> availableRoomsCollection = new ArrayList<>();

    HashMap<String,Collection<Reservation>> reservationMap = new HashMap<>();



    public Reservation reserveARoom(Customer customer, IRoom room, Date checkIndate, Date checkOutDate){
        Collection<Reservation> customersReservations ;
       Reservation reservation = new Reservation(customer, room ,checkIndate,checkOutDate);
       try {
            if (getCustomersReservation(customer) != null){

                reservationMap.get(customer.getEmail()).add(reservation);

           }
           else {
                customersReservations = new LinkedList<>();
                customersReservations.add(reservation);
                reservationMap.put(customer.getEmail(),customersReservations);
           }


        }
        catch (NullPointerException exception){
            System.out.println("HIHIHI");
        }




       totalRooms.remove(room);

        return reservation;


        }





    public void addRoom(IRoom room){
        roomsHashMap.put(room.getRoomNumber(),room);
        totalRooms.add(room);
    }
    public Collection<IRoom> getAllRooms(){
        if (roomsHashMap.isEmpty()){
            System.out.println("No Rooms added yet ! Please Add some room");
            return null;
        }
        else {
            return roomsHashMap.values();
        }

    }
    public IRoom getARoom(String roomId){
    return roomsHashMap.get(roomId);
    }
    private Collection<IRoom> avaiableRooms(Date checkInDate, Date checkOutDate){
        Collection<Reservation> reservationsList = new LinkedList<>();

        for (Collection<Reservation> reservation: reservationMap.values()
        ) {
            reservationsList.addAll(reservation);

        }

        for (IRoom room: totalRooms
        ) {
            availableRoomsCollection.add(room);
        }
        for (Reservation reservation: reservationsList
        ) {
            if (reservation.getCheckInDate().compareTo(checkOutDate) > 0
                    || reservation.getCheckOutDate().compareTo(checkInDate) < 0){
                availableRoomsCollection.add(reservation.getRoom());
            }
        }
        return availableRoomsCollection;
    }


     Date updateTimeforRecomandation (Date dateWantToChange){
        Date updatedDate;
        Calendar c = Calendar.getInstance();
        c.setTime(dateWantToChange);
        c.add(Calendar.DATE,7);
        updatedDate = c.getTime();
        return updatedDate;
    }

    private Collection<IRoom> recommandedRooms(Date checkInDate, Date checkOutDate) {


        Date updateTimeForCheckin =updateTimeforRecomandation(checkInDate);
        Date updateTimeForCheckOut =updateTimeforRecomandation(checkOutDate);

        return avaiableRooms(updateTimeForCheckin,updateTimeForCheckOut);

    }



    // Find a room for reservation
    public Collection<IRoom> findRooms(Date checkInDate , Date checkOutDate){

        /* store the alreaady booked room in a list
            - search available rooms by given date.
                - if room is not reserved ,to show the room .
                            > ( compare the total rooms with reserved rooms)
                            > store the available rooms in availaberoomCollection
                - for the reserved rooms >
                            - compare the given dates if the reserved rooms are empty within those date or not
                                    > check reserved room checkin date and given checkout date
                                        >> if reserved checkin date is greater than given checkout date
                                           >> store the room in availaberoomCollection
                                    < check reserved room checkout date and given checkin dates
                                        >> if reserved checkout date is lower than given checkin date
                                           >> store the room in availaberoomCollection
                - to show alternative rooms > 7 days after the given date ...
                            - checin+7 days and compare again with checkout date of reserved date .
                            checkout+7 days and compate again with checkin date of reserved date
         */

         if (availableRoomsCollection.isEmpty()){
             return recommandedRooms(checkInDate ,checkOutDate);
         }
         else return avaiableRooms(checkInDate,checkOutDate);
    }



    public  Collection<Reservation> getCustomersReservation(Customer customer){
        try {
            if (!reservationMap.isEmpty()){
                if (reservationMap.containsKey(customer.getEmail())){
                    return  reservationMap.get(customer.getEmail());
                }
                else {
                    System.out.println("Opps! No Reservation found for " + customer.getEmail());
                }


            }
            else {

                throw new  NullPointerException("There is no reservation yet ");
            }

        }
        catch (NullPointerException exception){
             exception.getLocalizedMessage();
        }

    return null;
    }

   public void printAllReservation(){
        try {

                if (reservationMap.isEmpty()){
                    System.out.println("No Reservation found");
                    throw new  NullPointerException("empty");
                }
                else {
                    for (String reservation: reservationMap.keySet()){
                        System.out.println(reservationMap.get(reservation));
                    }

                }



        }
       catch (Exception ex){
           System.out.println(ex.getLocalizedMessage());
       }
   }



}
