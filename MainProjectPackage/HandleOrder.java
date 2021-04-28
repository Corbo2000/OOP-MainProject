package MainProjectPackage;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleOrder {
    String creditCard, firstTime;
    public void SelectItems(String userType, String CC, String ID, String first){
        Boolean orderSomething = false;
        this.firstTime = first;
        this.creditCard = CC;
        String[] splitString = {"", ""};
        Scanner keyboard = new Scanner(System.in);
        File items = new File("ItemCatalog.txt");
        List<String> itemList = new ArrayList<String>();
        List<Integer> itemQuantity = new ArrayList<Integer>();
        String[] itemInfo = {"", "", "", ""};
        float totalPrice = 0;
        int counter1 = 0, counter2 = 0, choice = -2;

        try {
            Scanner accountsReader = new Scanner(items);
            while (accountsReader.hasNextLine()) {
                //Build a list of all items
                itemList.add(accountsReader.nextLine());
                itemQuantity.add(0);
                counter1++;
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        while (counter1 > 0){
            System.out.println(counter2 + 1 + ") " + itemList.get(counter2));
            counter2++;
            counter1--;
        }

        while (true){
            System.out.println("\nPlease select an item to add to cart. Enter 0 to confirm, or -1 to exit");
            choice = keyboard.nextInt();
            if (choice == -1){
                //Exit
                break;
            }else if (choice == 0){
                if(orderSomething == false){
                    System.out.println("You didnt order anything! Now returning...");
                    return;
                }
                //Process order
                counter1 = itemQuantity.size();
                counter2 = 0;
                System.out.println("The following is currently in your cart:");
                while (counter1 > 0){
                    if (itemQuantity.get(counter2) > 0){
                        itemInfo = itemList.get(counter2).split(",", 4);
                        System.out.println(itemInfo[0] + ", Quantity: " + itemQuantity.get(counter2));
                    }
                    counter1--;
                    counter2++;
                }
                splitString = firstTime.split(";");
                if (splitString[1].equals("true")){
                    totalPrice += 40;
                    this.firstTime = ID + ";false";
                }
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                System.out.println("The total comes out to $" + df.format(totalPrice));
                MakeOrder(itemList, itemQuantity, totalPrice, CC, ID);
                break;
            }
            orderSomething = true;
            counter1++;
            itemInfo = itemList.get(choice - 1).split(",", 4);
            if (userType.equals("regular")){
                itemQuantity.set(choice - 1, itemQuantity.get(choice - 1) + 1);
                totalPrice += Float.parseFloat(itemInfo[2]);
            }else{
                itemQuantity.set(choice - 1, itemQuantity.get(choice - 1) + 1);
                totalPrice += Float.parseFloat(itemInfo[3]);
            }
        }
    }

    public void MakeOrder(List<String> itemList, List<Integer> itemQuantity, float price, String CC, String ID){
        File ccFile = new File("BankCCInfo.txt");
        File accounts = new File("accounts.txt");
        Buffer buff = new Buffer();
        BankHandler bank = new BankHandler(buff, CC);
        boolean cardFound = false;
        int choice;
        long authorization;
        String verifiedCC, newCC;
        String[] itemInfo = {"", "", "", ""};
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please select 1) mail delivery ($3 shipping fee) or 2) in-store pickup");
        choice = keyboard.nextInt();
        keyboard.nextLine();
        if (choice == 1){price += 3;}
        //Get authorization number if valid, 0 if invalid
        bank.run();
        authorization = bank.authorization;
        if (authorization == 0){
            System.out.println("Credit card not found in our system. Please enter 0 to exit or enter a new credit card");
            newCC = keyboard.nextLine();
            if (newCC.equals("0")){
                System.out.println("Exiting...");
                System.exit(0);
            }
            bank.CC = newCC;
            bank.run();
            authorization = bank.authorization;
            this.creditCard = newCC;

            if (bank.authorization == 0){
                System.out.println("You entered an invalid card. Now exiting...");
                System.exit(0);
            }

            try {
                Scanner fileReader = new Scanner(accounts);
                StringBuffer buffer = new StringBuffer();
                while (fileReader.hasNextLine()) {
                    buffer.append(fileReader.nextLine()+System.lineSeparator());
                }
                String fileContents = buffer.toString();
                fileReader.close();
                fileContents = fileContents.replaceAll(CC, newCC);
                this.creditCard = newCC;
                FileWriter writer = new FileWriter(accounts);
                writer.append(fileContents);
                writer.flush();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        System.out.println("Your authorization number is " + authorization);
        //Store the order
        File orderLog = new File("orders.txt");
        try {
            if (!orderLog.exists()) {
                orderLog.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter writer = new FileWriter(orderLog, true);
            BufferedWriter br = new BufferedWriter(writer);
            br.write(ID + ";");
            for (int i = 0; i < itemList.size(); i++){
                if (itemQuantity.get(i) > 0){
                    itemInfo = itemList.get(i).split(",", 4);
                    br.write(itemQuantity.get(i) + " " + itemInfo[0] + "(s) ");
                }
            }
            br.write(";" + authorization + ";ordered;");
            br.write(String.valueOf(java.time.LocalDate.now()));
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            br.write(";" + df.format(price) + "\n");
            br.close();
            writer.close();
            System.out.println("Order successfully placed!");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
}