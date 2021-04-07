package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogIn {
    String log(){
        File accountsFile = new File("accounts.txt");
        String ID, password, fileLine, userInfo = "";
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
                            userInfo = userInfo + " " + password;
                            userInfo = userInfo + " " + accountsReader.nextLine() + " " + accountsReader.nextLine() + " " + accountsReader.nextLine() + " " + accountsReader.nextLine() + " " + accountsReader.nextLine();
                            return userInfo;
                        }
                    }
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return "User not found";
    }
}
