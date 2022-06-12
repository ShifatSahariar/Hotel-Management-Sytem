package service;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import java.util.*;
/**
 * Singleton service class for customer reservations
 * used static reference
 * used public private default access modifiers for methods
 *
 * @author shifatsahariar
 */
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

    /**
     * This section of codes to store information of rooms and reservation in collections
     *
     */
    // Rooms Collections
    private final HashMap<String,IRoom> roomsHashMap = new HashMap<>();


    HashMap<String,Collection<Reservation>> reservationMap = new HashMap<>();

    /**
     * Take user inputs and check if we have already customers reservation in list
     * if there is no reservation then create a new list for the customer and store in reservationmap
     * if existing reservation found > add the current reservation to that customers reservation list
     * @param customer Customer type
     * @param room IRoom type
     * @param checkIndate Date type , valid date
     * @param checkOutDate Date type , valid date
     * @return Reservation
     */
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
            System.out.println("No reservation found");
        }


        return reservation;


        }


    /**
     * Add rooms to roomsHashmap
     * @param room IRoom type
     */

    public void addRoom(IRoom room){
        roomsHashMap.put(room.getRoomNumber(),room);

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

    /**
     * this method will use as a sub method from findAROom () method
     * this method take the desire date from user and check,which rooms are available for booking
     *
     * @param checkInDate Date type,
     * @param checkOutDate Date type,
     * @return Hashset Collection of available Rooms .
     */
    private Collection<IRoom> avaiableRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> availableRoomsCollection = new LinkedList<>();
        Collection<Reservation> reservationsList = new ArrayList<>();
        HashMap<String,IRoom> reservedRooms = new HashMap<>();
        HashMap<String,IRoom> tempRoomHashmap = new HashMap<>(roomsHashMap);
        // First Remove the all the reserved room and store non booked room in the list to return
        try {
            // checking if the reservation map is empty or not
            if (!reservationMap.isEmpty()){
                            // if not empty then store the reservations from the map to a list
                            // called reservationsList
                            for (Collection<Reservation> reservation: reservationMap.values()
                            ) {
                                reservationsList.addAll(reservation);

                            }
                            // then trying to getting the room numbers from those room which are reserved .
                            for (Reservation reservation: reservationsList
                            ) {

                                reservedRooms.put(reservation.getRoom().getRoomNumber(),reservation.getRoom());

                            }
                            /* trying to remove the rooms from the main roomsHashMap and store in aa collection .

                            */
                            tempRoomHashmap.values().removeAll(reservedRooms.values());
                            availableRoomsCollection.addAll(tempRoomHashmap.values());



                            /**
                             * If the reserved room will be free according to new customers desired checkin and checkout date .
                             * @Add those rooms to @availableRoomsCollection
                             * >> if the allready reserved rooms checkin() date is grater than customer checkOut date
                             * or if the reserved rooms checkOut() date is smaller than customer checkIn date
                             * @Add those rooms also in available rooms
                             *
                             */

                            for (Reservation reservation: reservationsList
                            ) {
                                if (reservation.getCheckInDate().compareTo(checkOutDate) > 0
                                        || reservation.getCheckOutDate().compareTo(checkInDate) < 0){
                                    availableRoomsCollection.add(reservation.getRoom());
                                }
                            }

            }


            // if there is no reservation of the rooms , so i can add all rooms from map to my avilable collection .
            else {

        availableRoomsCollection.addAll(roomsHashMap.values());

        }






        }
        catch (NullPointerException ex){
            ex.getLocalizedMessage();
        }



        return availableRoomsCollection;
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

            return avaiableRooms(checkInDate,checkOutDate);

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
