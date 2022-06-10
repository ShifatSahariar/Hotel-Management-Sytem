import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.ReservationService;

import java.util.*;

public class AdminMenu {
    private static final AdminResource adminResources = AdminResource.adminResource();
    private static final HotelResource hotelResource = HotelResource.HotelResource();


    public static void mainAdminMenu(){
        System.out.println("Welcome to Admin Panel \n");
        ArrayList<String> mainAdminMenu = new ArrayList<>();
        mainAdminMenu.add("See all Customers");
        mainAdminMenu.add("See all Rooms");
        mainAdminMenu.add("See all Reservations");
        mainAdminMenu.add("Add a Room");
        mainAdminMenu.add("Back to Main Menu");
        HotelApplication.menuOptions(mainAdminMenu);
        adminMenuScanner();
    }
    protected static void adminMenuScanner(){

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        do {

            try {

                option = scanner.nextInt();
                if (option > 0 && option <= 5) {
                    switch (option){

                        case 1: seeAllCustomers();
                            break;
                        case 2: seeAllRooms();

                            break;
                        case 3: seeAllReservations();
                            break;
                        case 4: addARoom();
                            break;
                        case 5: backToMainMenu();
                            break;

                    }

                    break;
                } else System.out.println("Enter Only from 1-5 ");

            } catch (InputMismatchException ex) {
                System.out.println("Please enter only 1-5 to access");
                scanner.next();
            }
        } while (true);

    }

    private static void backToMainMenu() {
        MainMenu.mainMenuItems();
    }

    private static void addARoom() {
        String finalRoomNumber;
        double finalRoomPrice;
        RoomType finalRoomType;
        List<IRoom> newRooms = new ArrayList<>();
        boolean AddmoreRoomCheck = false;
        Scanner addRoomScanner = new Scanner(System.in);

// Main check if user want to add multiple rooms or not !
    do {
        // check the input validation for room number
        do {
            try {
                System.out.println("Enter room number |1-100|: ");
                int roomNumberCheck = addRoomScanner.nextInt();

                if (roomNumberCheck < 1 || roomNumberCheck > 100 ){
                    System.out.println("Enter room number |1-100|:");
                }
                else {
                    finalRoomNumber = String.valueOf(roomNumberCheck);
                    break;
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter a Valid Number");
                addRoomScanner.next();
            }
        }while (true);

        // check the input validation for room price
        do {
            try {
                System.out.println("Enter room price > 100-1000|$: ");
                double roomPriceCheck = addRoomScanner.nextInt();
                if (roomPriceCheck < 100 || roomPriceCheck > 1000 ){
                    System.out.println("Enter room Price > 100-1000|$:");
                }
                else {
                    finalRoomPrice = roomPriceCheck;
                    break;
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter a Valid Number");
                addRoomScanner.next();
            }
        }while (true);

        // check the input validation for Room Type
        do {
            try {
                System.out.println("Enter room type : 1 for single bed , 2 for double bed ");
                int roomTypeCheck = addRoomScanner.nextInt();
                if (roomTypeCheck == 1){
                    finalRoomType = RoomType.SINGLE;
                    break;
                } else if (roomTypeCheck == 2) {
                    finalRoomType = RoomType.DOUBLE;
                    break;
                } else {
                    System.out.println("Please type 1 or 2:");
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter 1 or 2");
                addRoomScanner.next();
            }
        }while (true);
        Room newRoomToAdd = new Room(finalRoomNumber,finalRoomPrice,finalRoomType);
        newRooms.add(newRoomToAdd);

        // check if admin want to add more rooms !
        do {
                try {
                    System.out.println("Would you like to add another room y/n");
                    char anotherRoomAdding = Character.toLowerCase(addRoomScanner.next().charAt(0));
                    if (anotherRoomAdding == 'y' || anotherRoomAdding == 'n'){

                                if (anotherRoomAdding == 'y' ){
                                    AddmoreRoomCheck = true;
                                    break;
                                }
                                else {

                                    adminResources.addRoom(newRooms);
                                    System.out.println("Rooms has been added !");
                                    AddmoreRoomCheck = false;

                                    break;
                                }


                    }
                    else {
                        System.out.println("Please type y or n:");
                    }
                }
                catch (InputMismatchException ex){
                    System.out.println("Please Enter y or n only");
                    addRoomScanner.next();
                }
        }while (true);


    }while (AddmoreRoomCheck);
       mainAdminMenu();
        adminMenuScanner();
    }

    private static void seeAllReservations() {

        adminResources.disPlayAllReservations();
        adminMenuScanner();
    }

    private static void seeAllRooms() {

        System.out.println(adminResources.getAllRooms());
        adminMenuScanner();
    }

    private static void seeAllCustomers() {

        System.out.println(adminResources.getAllCustomers());
        adminMenuScanner();

    }


}
