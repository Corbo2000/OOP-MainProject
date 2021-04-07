package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HandleOrder {
    public void SelectItems(String userType){
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
                System.out.println("The total comes out to " + totalPrice);
                //TODO: Go to order processing
                break;
            }
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
}
