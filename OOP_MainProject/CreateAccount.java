package OOP_MainProject;

import java.util.Scanner;

public class CreateAccount {
    public static void accountCreate(){
        Scanner keyboardInput = new Scanner(System.in);
        String password, name, address, phone, CC, ID;
        System.out.println("Please enter a user ID:");
        ID = keyboardInput.nextLine();
        System.out.println("Please enter your name:");
        name = keyboardInput.nextLine();
        System.out.println("Please enter your address:");
        address = keyboardInput.nextLine();
        System.out.println("Please enter your phone number:");
        phone = keyboardInput.nextLine();
        System.out.println("Please enter your credit card number:");
        CC = keyboardInput.nextLine();
    }
}
