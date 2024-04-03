import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Ủe
 */
public class DataInput {
     private final Scanner sc;
     SearchData sd = new SearchData();

    public DataInput() {
        sc = new Scanner(System.in);         
    }

    public int inputInteger(String msg, int x, int y) {
        System.out.println(msg);
        boolean check = true;
        int input;
        while (check) {
            try {
                input = Integer.parseInt(sc.nextLine());
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + " to " + y);
                    check = true;
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.err.println("This must be number");
                check = true;
            }
        }
        return 0;
    }
    
    public String inputStringNotEmpty(String msg) {
        String input = "";
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty());
        return input;
    }
    
    public String inputHotelID(ArrayList<HotelInfomation> arr) {
        String id = "";
        
        do {
            System.out.println("Enter id of hotel: ");
            id = sc.nextLine().toUpperCase();
            if (sd.searchHotelByID(arr, id) != null) {
                System.err.println("Duplicated code.Try with another one");
            } else if (id.trim().isEmpty()) {
                System.err.println("ID can't not empty!");
            } else {
                return id.toUpperCase();
            }
        } while (true);
    }
    
    public boolean inputYN(String msg) {
        String choice;
        while (true) {
            System.out.println(msg);
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                return true;
            } else if (choice.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.err.println("Must be Y or N");
                continue;
            }
        }
    }
    
     public String inputStringCanBlank(String msg) {
        String input = "";
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty());
        return input;
    }
    
    public String inputStringPattern(String msg, String pt) {
        String input = "";
        Pattern pattern = Pattern.compile(pt);
        do {
            System.out.println(msg);
            input = sc.nextLine();
        } while (input.trim().isEmpty() || !pattern.matcher(input).matches());
        return input;
    }
    
    public String inputNameUD(String msg, HotelInfomation hotel){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getHotel_Name();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the name");
            }else{
                return name;
            }
        } while (true);
    }
    
    public int inputHotelAvailableUD(String msg, int x, int y, HotelInfomation hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = hotel.getHotel_Room_Available();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
    
    public String inputAddressUD(String msg,HotelInfomation hotel){
        String name = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        do {            
            name = sc.nextLine();
            if(name.trim().isEmpty()){
                return hotel.getHotel_Name();
            }else if(!pattern.matcher(name).matches()){
                System.out.println("Please enter the correct format of the address");
            }else{
                return name;
            }
        } while (true);
    }
    public String inputPhoneUD(String msg,HotelInfomation hotel){
        String phone = "";
        System.out.println(msg);
        Pattern pattern = Pattern.compile("0\\d{9}");
        do {            
            phone = sc.nextLine();
            if(phone.trim().isEmpty()){
                return hotel.getHotel_Phone();
            }else if(!pattern.matcher(phone).matches()){
                System.out.println("Please enter the correct format of the phone");
            }else{
                return phone;
            }
        } while (true);
    }
    
    public int inputHotelRatingUD(String msg, int x, int y, HotelInfomation hotel) {
        System.out.println(msg);
        boolean check = true;
        int input;
        try {
            while (check) {
                String string = sc.nextLine();
                if (string.trim().isEmpty()) {
                    input = hotel.getHotel_Rating();
                    return input;
                }
                input = Integer.parseInt(string);
                if (input < x || input > y) {
                    System.out.println("This number must be " + x + "to" + y);
                    check = true;
                } else {
                    return input;
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("This must be number");
            check = true;
        }
        return 0;
    }
}