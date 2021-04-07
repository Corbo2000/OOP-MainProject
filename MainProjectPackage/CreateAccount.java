package MainProjectPackage;

import java.io.*;
import java.util.Scanner;

public class CreateAccount {
    void accountCreate(){
        Scanner keyboardInput = new Scanner(System.in);
        boolean continueLoop = true;
        String password, name, address, phone, CC, ID, accType, fileLine;

        //Create file if necessary

        File accountsFile = new File("accounts.txt");
        try {
            if (!accountsFile.exists()) {
                accountsFile.createNewFile();
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
                    continueLoop = true;
                    fileLine = accountsReader.nextLine();
                    if (fileLine.equals(ID)) {
                        System.out.println("This ID is already registered. Please try again.");
                        continueLoop = false;
                        break;
                    }
                }
                accountsReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            if (!continueLoop){
                continue;
            }

            System.out.println("Please enter your name:");
            name = keyboardInput.nextLine();
            System.out.println("Please enter your address:");
            address = keyboardInput.nextLine();
            System.out.println("Please enter your phone number:");
            phone = keyboardInput.nextLine();
            System.out.println("Please enter your credit card number:");
            CC = keyboardInput.nextLine();
            System.out.println("Please select a premium ($40) or a regular membership or verify that you are a supplier by entering (premium/regular/supplier)");
            accType = keyboardInput.nextLine();
            //TODO: Charge $40 when premium selected

            //Write user information to file
           try {
               FileWriter writer = new FileWriter(accountsFile, true);
               BufferedWriter br = new BufferedWriter(writer);
               br.write("--\n" + ID + "\n" + password + "\n" + name + "\n" + address + "\n" + phone + "\n" + CC + "\n" + accType + "\n");

               br.close();
               writer.close();
               System.out.println("Account successfully created!");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            break;
        }
    }
}
