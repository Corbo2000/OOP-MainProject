package usecase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogInCase {
    public String ID, password, Name, Address, Phone, CCNumber, Type;
    public boolean log(String user, String pass){
        boolean ReturnBoolean = false;
        File accountsFile = new File("accounts.txt");
        String fileLine, userInfo = "";

        /* Old input method
        System.out.println("Please enter your user ID:");
        ID = keyboardInput.nextLine();
        System.out.println("Please enter your password:");
        password = keyboardInput.nextLine();
         */
        //Verify valid info
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
}
