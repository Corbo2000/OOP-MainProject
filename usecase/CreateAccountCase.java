package usecase;

import javafx.fxml.FXML;

import java.io.*;
import java.util.Scanner;

public class CreateAccountCase {
    @FXML
    String pass, Name, adress, telephone, creditCard, userID, accountType, fileLine, duplicate;

    public String accountCreate(String password, String name, String address, String phone, String CC, String ID, String accType){

        boolean continueLoop = true;


        //Create file if necessary
        pass = password; Name = name; adress = address; telephone = phone; creditCard = CC; userID = ID; accountType = accType;



        File accountsFile = new File("accounts.txt");
        try {
            if (!accountsFile.exists()) {
                accountsFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            Scanner accountsReader = new Scanner(accountsFile);

            while (accountsReader.hasNextLine()) {

                fileLine = accountsReader.nextLine();
                if (fileLine.equals("--")) {
                    if (accountsReader.nextLine().equals(userID)) {
                        System.out.println("Account duplicate found");
                        duplicate = "duplicate";
                        continueLoop = false;

                    }
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
            //TODO: Charge $40 when premium selected
        if(continueLoop == true) {
            //Write user information to file
            try {
                FileWriter writer = new FileWriter(accountsFile, true);
                BufferedWriter br = new BufferedWriter(writer);
                br.write("\n--\n" + userID + "\n" + pass + "\n" + Name + "\n" + adress + "\n" + telephone + "\n" + creditCard + "\n" + accountType );
                duplicate = "!duplicate";
                br.close();
                writer.close();
                System.out.println("Account successfully created!");

            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return duplicate;
    }
}
