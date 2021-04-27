package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class View {
    public void ViewOrder(String ID){
        File ordersFile = new File("orders.txt");
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        String fileLine, orderID;
        String[] orderInfo = {"", "", "", ""};
        List<String> allUserOrders = new ArrayList<String>();

        try {
            Scanner accountsReader = new Scanner(ordersFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                if (orderInfo[0].equals(ID)){
                    allUserOrders.add(i, fileLine + "\n");
                    i++;
                    System.out.println(i + ") Order ID: " + orderInfo[2]);
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.print("Enter ID of order to view:\n");
        orderID = keyboard.nextLine();
        for (int j = 0; j< allUserOrders.size();j++){
            orderInfo = allUserOrders.get(j).split(";");
            if (orderInfo[2].equals(orderID)){
                System.out.println("Order of ID " + orderInfo[2] + " consists of " + orderInfo[1]);
                System.out.println("Status: " + orderInfo[3]);
                return;
            }
        }
        System.out.println("Order not found. Returning to menu...");
    }

    public void ViewInvoice(String ID, String CC){
        File ordersFile = new File("orders.txt");
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        String fileLine, orderID;
        String[] orderInfo = {"", "", "", "", "", ""};
        List<String> allUserOrders = new ArrayList<String>();

        try {
            Scanner accountsReader = new Scanner(ordersFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                if (orderInfo[0].equals(ID)){
                    allUserOrders.add(i, fileLine + "\n");
                    i++;
                    System.out.println(i + ") Order ID: " + orderInfo[2]);
                }
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Enter ID of order to view");
        orderID = keyboard.nextLine();
        for (int j = 0; j< allUserOrders.size();j++){
            orderInfo = allUserOrders.get(j).split(";");
            if (orderInfo[2].equals(orderID)){
                System.out.println("Order of ID " + orderInfo[2] + " placed on " + orderInfo[4] + " consists of " + orderInfo[1]);
                System.out.println("The price was " + orderInfo[5] + "Paid with card " + CC);
                return;
            }
        }
        System.out.println("Order not found. Returning to menu...");
    }
}

