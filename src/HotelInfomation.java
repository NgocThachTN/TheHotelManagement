import java.io.Serializable;

/**
 *
 * @author NguyenDuy
 */
public class HotelInfomation implements Serializable, Comparable<HotelInfomation> {

    private String hotel_Id;
    private String hotel_Name;
    private int hotel_Room_Available;
    private String hotel_Address;
    private String hotel_Phone;
    private int hotel_Rating;

    public HotelInfomation(String hotel_Id, String hotel_Name, int hotel_Room_Available, String hotel_Address, String hotel_Phone, int hotel_Rating) {
        this.hotel_Id = hotel_Id;
        this.hotel_Name = hotel_Name;
        this.hotel_Room_Available = hotel_Room_Available;
        this.hotel_Address = hotel_Address;
        this.hotel_Phone = hotel_Phone;
        this.hotel_Rating = hotel_Rating;
    }

    public String getHotel_Id() {
        return hotel_Id;
    }

    public void setHotel_Id(String hotel_Id) {
        this.hotel_Id = hotel_Id;
    }

    public String getHotel_Name() {
        return hotel_Name;
    }

    public void setHotel_Name(String hotel_Name) {
        this.hotel_Name = hotel_Name;
    }

    public int getHotel_Room_Available() {
        return hotel_Room_Available;
    }

    public void setHotel_Room_Available(int hotel_Room_Available) {
        this.hotel_Room_Available = hotel_Room_Available;
    }

    public String getHotel_Address() {
        return hotel_Address;
    }

    public void setHotel_Address(String hotel_Address) {
        this.hotel_Address = hotel_Address;
    }

    public String getHotel_Phone() {
        return hotel_Phone;
    }

    public void setHotel_Phone(String hotel_Phone) {
        this.hotel_Phone = hotel_Phone;
    }

    public int getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(int hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    @Override
    public String toString() {
        return String.format( hotel_Id, hotel_Name, hotel_Room_Available, hotel_Address, hotel_Phone, hotel_Rating);
    }

    @Override
    public int compareTo(HotelInfomation o) {
    return this.getHotel_Id().compareTo(o.getHotel_Id());
}


}
