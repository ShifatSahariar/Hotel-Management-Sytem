import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main Menu , Customer can access this Menu Items
 * @author shifatsahariar
 */
public class MainMenu {
    private static final AdminResource adminResources = AdminResource.adminResource();
    private static final HotelResource hotelResource = HotelResource.HotelResource();

    protected static void mainMenuItems(){
        System.out.println("Welcome to Our Hotel Management System \n");
        ArrayList<String> mainMenu = new ArrayList<>();
        mainMenu.add("Find and reserve a room");
        mainMenu.add("See my reservations");
        mainMenu.add("Create an Account");
        mainMenu.add("Admin");
        mainMenu.add("Exit");
        HotelApplication.menuOptions(mainMenu);
        mainManuScanner();
    }



 protected static void mainManuScanner(){

     Scanner scanner = new Scanner(System.in);
     int option = 0;

     do {

         try {

             option = scanner.nextInt();
             if (option > 0 && option <= 5) {
                 switch (option){

                     case 1: findAndReserveARoom();
                         break;
                     case 2: seeMyReservation();

                         break;
                     case 3: createNewAccount();
                         break;
                     case 4: redirectToAdminMenu();
                         break;
                     case 5: exitCall();
                         break;

                 }

                 break;
             } else System.out.println("Enter Only from 1-5 ");

         } catch (InputMismatchException ex) {
             System.out.println("Please enter only 1-5 to access");
             scanner.next();
         }
     } while (true);

 } // main menu finished






    private static void exitCall() {
        System.out.println(" Have a good Time , See you soon !");
    }

    private static void redirectToAdminMenu() {
        AdminMenu.mainAdminMenu();
    }

    private static void createNewAccount() {
        String email,firstName,lastName ;
        Scanner addCustomer = new Scanner(System.in);
        String EMAIL_REGEX = "^(.+)@(.+).com$";
        String NAME_REGEX = "[a-zA-Z]{2,}";
        do {
            try {
                System.out.println("Enter Email > format: shifat@domain.com");
                String emailFormatChecker = addCustomer.nextLine();

                if (!emailFormatChecker.matches(EMAIL_REGEX)){
                    System.out.println("Enter valid email format.");
                }
                else {
                    email = emailFormatChecker;
                    break;
                }
            }
            catch (Exception ex){
                System.out.println("Please Enter a Valid email");
                addCustomer.next();
            }
        }while (true);
        do {
            try {
                System.out.println("First Name :");
                String firstNameChecker = addCustomer.nextLine();

                if (!firstNameChecker.matches(NAME_REGEX)){
                    System.out.println("Enter valid name");
                }
                else {
                    firstName = firstNameChecker;
                    break;
                }
            }
            catch (Exception ex){
                System.out.println("Please Enter a Valid name");
                addCustomer.next();
            }
        }while (true);
        do {
            try {
                System.out.println("Last Name :");
                String lastNameChecker = addCustomer.nextLine();

                if (!lastNameChecker.matches(NAME_REGEX)){
                    System.out.println("Enter valid name");
                }
                else {
                    lastName = lastNameChecker;
                    break;
                }
            }
            catch (Exception ex){
                System.out.println("Please Enter a Valid name");
                addCustomer.next();
            }
        }while (true);

        hotelResource.createACustomer(email,firstName,lastName);
        System.out.println("Account successfully created ! ");
        mainMenuItems();
        mainManuScanner();
    }

   private static void seeMyReservation() {
       String EMAIL_REGEX = "^(.+)@(.+).com$";
       String email;
       Scanner myReservationScanner = new Scanner(System.in);

        do {
            try {
                System.out.println("Enter Email to check your reservation > format: shifat@domain.com");
               String emailFormatChecker = myReservationScanner.nextLine();

               if (!emailFormatChecker.matches(EMAIL_REGEX)){
                    System.out.println("Enter valid email format.");
                }
                else {

                    email = emailFormatChecker;

                    if (hotelResource.getCustomer(email) == null){
                        System.out.println("No Customer found for  :" + email);

                        mainManuScanner();
                        break;
                    }
                    else {
                         if (hotelResource.getCustomersReservations(email) == null){
                             System.out.println("No reservation found for :" + email);
                             break;
                         }
                         else {
                             System.out.println(hotelResource.getCustomersReservations(email));
                             break;
                         }
                    }

                }
            }
            catch (Exception ex){
                System.out.println("Please Enter a Valid email");
                myReservationScanner.next();
            }
        }while (true);

    }

    private static void findAndReserveARoom() {
        Date checkIn,checkOut;
        Date currentDate = new Date();
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
        String DATE_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
        Scanner reserveScanner = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2022");
                String checkInDateChecker = reserveScanner.nextLine();

                if (!checkInDateChecker.matches(DATE_REGEX)){
                    System.out.println("Enter valid Date");
                }
                else {
                    checkIn = format.parse(checkInDateChecker);
                    if (checkIn.after(currentDate))
                    {
                        break;
                    }

                    else System.out.println("Past Dates are not allowed for Reservation ");
                }
            }
            catch (Exception ex){
                System.out.println("Please enter valid date");
                reserveScanner.next();
            }
        }while (true);
        do {
            try {
                System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2022");
                String checkInDateChecker = reserveScanner.nextLine();

                if (!checkInDateChecker.matches(DATE_REGEX) ){
                    System.out.println("Enter valid Date");
                }
                else {

                    checkOut = format.parse(checkInDateChecker);
                    if (checkIn.before(checkOut)){
                        break;
                    }
                    else {
                        System.out.println("Checkout date cant before than checkin");
                    }
                }
            }
            catch (Exception ex){
                System.out.println("Please enter valid date");
                reserveScanner.next();
            }
        }while (true);



        do {
            if (hotelResource.findARoom(checkIn,checkOut).isEmpty()){
                System.out.println("Sorry ! No rooms found for booking ! ");
                break;
            }
            else System.out.println(hotelResource.findARoom(checkIn,checkOut));
            try {
                System.out.println("Would you like to book a room y/n");
                char wantToBook = Character.toLowerCase(reserveScanner.next().charAt(0));
                if (wantToBook == 'y' || wantToBook == 'n'){

                    if (wantToBook == 'y' ){
                       reserveARoom(checkIn ,checkOut);
                       break;
                    }
                    else {
                            break;
                    }


                }
                else {
                    System.out.println("Please type y or n:");
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter y or n");
                reserveScanner.next();
            }
        }while (true);
        mainMenuItems();
        mainManuScanner();
    }

    private static void reserveARoom(Date checkIn, Date checkOut) {
        Scanner reserveScanner = new Scanner(System.in);

        String email,roomNumber;
        String EMAIL_REGEX = "^(.+)@(.+).com$";
        do {
            try {
                System.out.println("Do you have an account ? y/n");
                char wantToBook = Character.toLowerCase(reserveScanner.next().charAt(0));
                if (wantToBook == 'y' || wantToBook == 'n'){

                    if (wantToBook == 'y' ){

                      break;
                    }
                    else {
                        System.out.println("Please Create a account first .");
                        MainMenu.createNewAccount();

                        break;

                    }


                }
                else {
                    System.out.println("Please type y or n:");
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter y or n");
                reserveScanner.next();
            }
        }while (true);
        do {
            try {
                System.out.println("Enter Email > format: shifat@domain.com");
                String emailFormatChecker = reserveScanner.nextLine();

                if (!emailFormatChecker.matches(EMAIL_REGEX)){
                    System.out.println("Enter valid email format.");
                }
                else {

                    email = emailFormatChecker;

                    if (hotelResource.getCustomer(email).getEmail().isEmpty()){
                       MainMenu.createNewAccount();
                    }
                    else {
                        break;
                    }

                }
            }
            catch (Exception ex){
                System.out.println("Please Enter a Valid email");
                reserveScanner.next();
            }
        }while (true);
        do {
            try {
                System.out.println("What room number would you like to reserve ?");
                int roomNumberChecker = reserveScanner.nextInt();

                if (roomNumberChecker>0 && roomNumberChecker<= 100){

                    roomNumber = String.valueOf(roomNumberChecker);
                    System.out.println("room number is "+hotelResource.getRoom(roomNumber).getRoomNumber());
                    System.out.println(hotelResource.bookARoom(email,hotelResource.getRoom(roomNumber),checkIn,checkOut));
                    break;

                }
                else {
                    System.out.println("The room is not available .");
                }

            }
            catch (InputMismatchException ex){
                System.out.println("Please Enter a Valid Room Number");
                reserveScanner.next();
            }
        }while (true);
    }


}
