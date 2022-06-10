import java.util.ArrayList;

public class HotelApplication {
    public static void main(String[] args) {
        MainMenu.mainMenuItems();
    }




    public static void menuOptions(ArrayList<String> menuItems){

        String leftAlignFormat = "| %-2d > %-25s |%n";

        System.out.format("+----+---------------------------+%n");
        System.out.format("| ID |    MENU OPTIONS           |%n");
        System.out.format("+----+---------------------------+%n");
        for(int i = 0 ;i<menuItems.size() ; i++){
            System.out.format(leftAlignFormat, i+1, menuItems.get(i) );
        }
        System.out.format("+----+---------------------------+%n");
        System.out.println("Type a number from above list to Access \n ");
    }
}
