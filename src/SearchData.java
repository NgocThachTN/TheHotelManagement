import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class SearchData {
    public HotelInfomation searchHotelByID(ArrayList<HotelInfomation> arr, String id){
        for (HotelInfomation hotelInfomation : arr) {
            if(id.equals(hotelInfomation.getHotel_Id())){
                return hotelInfomation;
            }
        }
        return null;
    }
    
}