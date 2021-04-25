package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogIn {
    String ID, password, Name, Address, Phone, CCNumber, Type;
    boolean log(String user, String pass){
        boolean ReturnBoolean = false;
        File accountsFile = new File("TextFiles/accounts.txt");
        String fileLine, userInfo = "";
        Scanner keyboardInput = new Scanner(System.in);
        /* Old input method
        System.out.println("Please enter your user ID:");
        ID = keyboardInput.nextLine();
        System.out.println("Please enter your password:");
        password = keyboardInput.nextLine();
         */
        password = pass;
        ID = user;

        //Verify valid info
        try {
            Scanner accountsReader = new Scanner(accountsFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                if (fileLine.equals("--")){
                    if (accountsReader.nextLine().equals(ID)) {
                        userInfo = userInfo + ID;
                        if (accountsReader.nextLine().equals(password)) {
                            Name = accountsReader.nextLine();
                            Address = accountsReader.nextLine();
                            Phone = accountsReader.nextLine();
                            CCNumber = accountsReader.nextLine();
                            Type = accountsReader.nextLine();
                            ReturnBoolean = true;
                            //main2();
                        }
                    }
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return ReturnBoolean;
    }

    void main2(){
        // Displays welcome message and our menu which will interact with the Menu Interface and all of the subsequent
        // classes

        //Temporarily accesses method without user input. Fix later
        HandleOrder order = new HandleOrder();
        Scanner keyboard = new Scanner(System.in);
        int choice;
        while(true){
            System.out.println("1) Make order\n2) View Order\n3) View Invoice 4) Exit");
            choice = keyboard.nextInt();
            switch (choice){
                case 1:
                    order.SelectItems("regular", CCNumber, ID);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
    }
}