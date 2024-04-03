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
public class Menu {  
     private final DataInput ip;

    public Menu() {
        ip = new DataInput();
    }
    
    public int inputInteger(String msg, int x, int y) {
        Scanner sc = new Scanner(System.in);
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

    public int int_getChoice(ArrayList options, int x, int y) {
        int respose;
        for (int i = 0; i < options.size(); i++) {
            System.out.printf("%2d.", i + 1);
            System.out.println(options.get(i));
        }
        respose = ip.inputInteger("Please input integer " + x + " to " + y +"", x, y);
        return respose;
    }     
    
    public int int_getChoiceString(ArrayList<String> options, int x, int y) {
        int respose;
        for (int i = 0; i < options.size(); i++) {
            String result = options.get(i) ;
            System.out.printf("%s\n",result);
        }    
        respose = ip.inputInteger("Please input integer " + x + " to " + y +"", x, y);
        return respose;
    }
    
}
