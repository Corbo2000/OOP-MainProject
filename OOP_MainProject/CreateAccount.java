package OOP_MainProject;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class CreateAccount {
    void accountCreate(){
        Scanner keyboardInput = new Scanner(System.in);
        boolean loopDone = true;
        String password, name, address, phone, CC, ID, accType, fileLine;

        //Create file if necessary
        File accountsFile = new File("accounts.txt");
        try {
            //WARNING: I'm not sure if this will create a new file every time. Check later
            if (accountsFile.createNewFile()) {
                System.out.println("File created: " + accountsFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (true) {
            System.out.println("Please enter a user ID:");
            ID = keyboardInput.nextLine();
            System.out.println("Please enter a password:");
            password = keyboardInput.nextLine();
            //Check if account ID already exists
            try {
                Scanner accountsReader = new Scanner(accountsFile);
                while (accountsReader.hasNextLine()) {
                    fileLine = accountsReader.nextLine();
                    if (fileLine.equals(ID)) {
                        System.out.println("This ID is already registered. Please try again.");
                        loopDone = false;
                        break;
                    }
                }
                if (loopDone == true){
                    continue;
                }
                accountsReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("Please enter your name:");
            name = keyboardInput.nextLine();
            System.out.println("Please enter your address:");
            address = keyboardInput.nextLine();
            System.out.println("Please enter your phone number:");
            phone = keyboardInput.nextLine();
            System.out.println("Please enter your credit card number:");
            CC = keyboardInput.nextLine();
            System.out.println("Please select a premium ($40) or a regular membership by entering (premium/regular)");
            accType = keyboardInput.nextLine();
            //TODO: Charge $40 when premium selected

            //Write user information to file
            try {
                FileWriter writer = new FileWriter("accounts.txt");
                writer.write(ID + "\n" + name + "\n" + address + "\n" + phone + "\n" + CC + "\n" + accType + "\n\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            break;
        }


    }
}
