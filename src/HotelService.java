import java.io.EOFException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
public class HotelService implements IHotelService{
    
    private ArrayList<HotelInfomation> arrHotel;
    private DataInput di;
    private DataInput dv;
    private FileManager sf;
    private SearchData sd;
    private Menu mn;
    private static int idCounter = 1;
    private Set <String> deletedIds = new HashSet<>();
    public HotelService() throws EOFException {

        arrHotel = new ArrayList<>();
        di = new DataInput();
        dv = new DataInput();
        sf = new FileManager();
        sd = new SearchData();
        mn = new Menu();
        try {
            sf.loadDataFromFile(arrHotel, "Hotel.dat");
        } catch (Exception e) {
            System.out.println("List empty");
        }

        if (arrHotel.isEmpty()) {
            System.out.println("Empty list, add new one");
            addHotel();
        }
    }
   private boolean checkHotelId(String id){
        for(int i = 0;i < arrHotel.size() ; i++){
            if(arrHotel.get(i).getHotel_Id().equalsIgnoreCase(id)){
                return false;
            }
        }
        return true;
    }
    
    public String autoGenerateId(){
        String id= "";
        String prefix = "H";
        do{
            id = prefix + String.format("%02d" , idCounter);
            if (!deletedIds.contains(id) && checkHotelId(id)) {
                return id;
            }
            idCounter++;
        }while(true);
    }
    public void deleteId(String id) {
        deletedIds.add(id);
    }

    @Override
    public void addHotel() {
        String hotel_Id;
        String hotel_Name;
        int hotel_Room_Available;
        String hotel_Address;
        String hotel_Phone;
        int hotel_Rating;
        boolean choice = true;
        while (choice) {
            hotel_Id = autoGenerateId();
            hotel_Name = di.inputStringPattern("Enter name of hotel: ", "^[a-zA-Z\\s]+$");
            hotel_Room_Available = di.inputInteger("Enter the number of available rooms", 0, 10000);
            hotel_Address = di.inputStringCanBlank("Enter hotel address: ");
            hotel_Phone = di.inputStringPattern("Enter hotel phone number(like 0xxxxxxxx)", "0\\d{9}");
            hotel_Rating = di.inputInteger("Enter hotel rating(0-6)", 0, 6);
            arrHotel.add(new HotelInfomation(hotel_Id, hotel_Name, hotel_Room_Available, hotel_Address, hotel_Phone, hotel_Rating));
            sf.saveDataFromFile(arrHotel, "Hotel.dat");
            choice = di.inputYN("Do you want to continue(Y/N): ");
        }
    }

    @Override
    public void checkExitsHotel() {
        String id = di.inputStringNotEmpty("Enter id of hotel you want to check: ");
        ArrayList<HotelInfomation> arrTemp = new ArrayList<>();
        sf.loadDataFromFile(arrTemp, "Hotel.dat");
        HotelInfomation hi = sd.searchHotelByID(arrTemp, id);
        if (hi != null) {
            System.out.println("Exist Hotel");
        } else {
            System.out.println("“No Hotel Found!");
        }

    }

    @Override
    public void updateHotelInfomation() {
        System.out.println("Enter product you want to update: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        HotelInfomation hotel = sd.searchHotelByID(arrHotel, id);
        String hotel_Name;
        int hotel_Room_Available;
        String hotel_Address;
        String hotel_Phone;
        int hotel_Rating;
        boolean check = true;
        boolean choice = true;
        if (hotel != null) {
            System.out.println("Found! Here is product: ");
            System.out.println("Hotel ID: " + hotel.getHotel_Id());
            System.out.println("Hotel Name: " + hotel.getHotel_Name());
            System.out.println("Available Rooms: " + hotel.getHotel_Room_Available());
            System.out.println("Hotel Address: " + hotel.getHotel_Address());
            System.out.println("Hotel Phone: " + hotel.getHotel_Phone());
            System.out.println("Hotel Rating: " + hotel.getHotel_Rating()); 
            hotel_Name = dv.inputNameUD("Enter name you want to update: ", hotel);
            hotel_Room_Available = dv.inputHotelAvailableUD("Enter available of hotel update: ", 0, 10000, hotel);
            hotel_Address = dv.inputAddressUD("Enter hotel address you want to update: ", hotel);
            hotel_Phone = dv.inputPhoneUD("Enter phone number you want to update: ", hotel);
            hotel_Rating = dv.inputHotelRatingUD("Enter hotel rating you want to update: ", 0, 5, hotel);
            arrHotel.set(arrHotel.indexOf(hotel), new HotelInfomation(id, hotel_Name, hotel_Room_Available, hotel_Address, hotel_Phone, hotel_Rating));
            System.out.println("Here is hotel after update: ");
            System.out.println(hotel);
            sf.saveDataFromFile(arrHotel, "Hotel.dat");
        } else {
            System.out.println("Not found!");
        }
    }
    
    @Override
    public void deleteHotel() {
        System.out.print("Enter id of product you want to delete: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        HotelInfomation hotel = sd.searchHotelByID(arrHotel, id);
        boolean choice = true;
        if (hotel != null) {
            System.out.println("Found! Here is product: ");
            System.out.println("Hotel ID: " + hotel.getHotel_Id());
            System.out.println("Hotel Name: " + hotel.getHotel_Name());
            System.out.println("Available Rooms: " + hotel.getHotel_Room_Available());
            System.out.println("Hotel Address: " + hotel.getHotel_Address());
            System.out.println("Hotel Phone: " + hotel.getHotel_Phone());
            System.out.println("Hotel Rating: " + hotel.getHotel_Rating()); 
            choice = di.inputYN("You really want to delete(Y/N): ");
            if (choice) {
                arrHotel.remove(hotel);
                System.out.println("Delete successfully!");
            }
        } else {
            System.out.println("Not found!");
        }
        sf.saveDataFromFile(arrHotel, "Hotel.dat");
    }

    @Override
    public void searchHotel() {
        ArrayList<String> ops = new ArrayList<>();
        int choice;
        ArrayList<HotelInfomation> arrTemp = arrHotel;
        Scanner sc = new Scanner(System.in);
        System.out.println("Select the information you want to search");
        ops.add("1. Search by Hotel_ID");
        ops.add("2. Search by Hotel_Name");
        ops.add("3. Exit");
        do {
            boolean check = false;
            choice = mn.int_getChoiceString(ops, 1, 3);
            switch (choice) {
                case 1:
                    System.out.print("Enter ID : ");
                    String id = sc.nextLine();
                    for (HotelInfomation hotelInfomation : arrTemp) {
                        if (hotelInfomation.getHotel_Id().contains(id)) {  
                            System.out.println("------------------------------------");
                            System.out.println("Hotel ID: " + hotelInfomation.getHotel_Id());
                            System.out.println("Hotel Name: " + hotelInfomation.getHotel_Name());
                            System.out.println("Available Rooms: " + hotelInfomation.getHotel_Room_Available());
                            System.out.println("Hotel Address: " + hotelInfomation.getHotel_Address());
                            System.out.println("Hotel Phone: " + hotelInfomation.getHotel_Phone());
                            System.out.println("Hotel Rating: " + hotelInfomation.getHotel_Rating()); 
                            System.out.println("------------------------------------");
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found id" + id);
                    }
                    break;
                case 2:
                    System.out.print("Enter Name :");
                    String name = sc.nextLine();
                    for (HotelInfomation hotelInfomation : arrTemp) {
                        if (hotelInfomation.getHotel_Name().contains(name)) {
                            System.out.println("------------------------------------");
                            System.out.println("Hotel ID: " + hotelInfomation.getHotel_Id());
                            System.out.println("Hotel Name: " + hotelInfomation.getHotel_Name());
                            System.out.println("Available Rooms: " + hotelInfomation.getHotel_Room_Available());
                            System.out.println("Hotel Address: " + hotelInfomation.getHotel_Address());
                            System.out.println("Hotel Phone: " + hotelInfomation.getHotel_Phone());
                            System.out.println("Hotel Rating: " + hotelInfomation.getHotel_Rating());
                            System.out.println("------------------------------------");
                            check = true;
                        }
                    }
                    if (!check) {
                        System.out.println("Can't found name" + name);
                    }
                    break;

                default:
                    System.out.println("Thanks you for using program!");
            }
        } while (!(choice < 1 || choice > 2));
    } 

    @Override
	public void displayHotel() {
		Collections.sort(arrHotel);
		System.out.println(
				"Hotel_ID   Hotel_Name              Hotel_Room_Available      Hotel_Address                                                                Hotel_Phone   Hotel_Rating");
		for (HotelInfomation hotelInfomation : arrHotel) {
			String address = hotelInfomation.getHotel_Address();
			Pattern pattern = Pattern.compile("(.{0,30})\\b");
			Matcher matcher = pattern.matcher(address);
			System.out.printf("%-11s%-24s%-26s%-77s%-14s%-12s\n", hotelInfomation.getHotel_Id(),
					hotelInfomation.getHotel_Name(), hotelInfomation.getHotel_Room_Available(),
					hotelInfomation.getHotel_Address(), hotelInfomation.getHotel_Phone(),
					hotelInfomation.getHotel_Rating());
			System.out.println();
		
        
      }
    }
    
}
