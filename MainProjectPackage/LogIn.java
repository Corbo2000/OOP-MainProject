package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogIn {
    String ID, password, Name, Address, Phone, CCNumber, Type;
    void log(){
        File accountsFile = new File("accounts.txt");
        String fileLine, userInfo = "";
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Please enter your user ID:");
        ID = keyboardInput.nextLine();
        System.out.println("Please enter your password:");
        password = keyboardInput.nextLine();

        //Verify valid info
        try {
            Scanner accountsReader = new Scanner(accountsFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                if (fileLine.equals("--")){
                    if (accountsReader.nextLine().equals(ID)) {
                        userInfo = userInfo + ID;
                        if (accountsReader.nextLine().equals(password)){
                            Name = accountsReader.nextLine();
                            Address = accountsReader.nextLine();
                            Phone = accountsReader.nextLine();
                            CCNumber = accountsReader.nextLine();
                            Type = accountsReader.nextLine();
                            main2();
                        }
                    }
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void main2(){
        // Displays welcome message and our menu which will interact with the Menu Interface and all of the subsequent
        // classes
    }
}
