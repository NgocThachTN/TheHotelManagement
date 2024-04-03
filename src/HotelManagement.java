import java.io.EOFException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class HotelManagement {

    public static void main(String[] args) throws EOFException {
       String filename = "Hotel.txt";
       ArrayList<String> opt = new ArrayList<>();
       Scanner sc = new Scanner(System.in);
       Menu menu = new Menu();
       HotelService hse = new HotelService();
       int choice;
       opt.add("THE HOTEL MANAGEMENT");
       opt.add("1. Adding new Hotel");
       opt.add("2. Checking exits Hotel");
       opt.add("3. Updating Hotel information");
       opt.add("4. Deleting Hotel");
       opt.add("5. Searching Hotel.");
       opt.add("6. Displaying a hotel list");
       opt.add("7. Exit");
        do {
            choice = menu.int_getChoiceString(opt, 1, 7);
            switch (choice) {
                case 1:
                    hse.addHotel();
                    break;
                case 2:
                    hse.checkExitsHotel();
                    break;
                case 3:
                    hse.updateHotelInfomation();
                    break;
                case 4:
                    hse.deleteHotel();
                    break;
                case 5:
                    hse.searchHotel();
                    break;
                case 6:
                    hse.displayHotel();
                    break;
                default:
                    System.out.println("Bye!");
            }
        } while (!(choice < 1 || choice > 6));
    }
}
