package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SupplierClient {
    public void ProcessOrder(){
        File ordersFile = new File("orders.txt");
        File stockFile = new File("stocks.txt");
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        String fileLine, orderID;
        String[] orderInfo = {"", "", "", "", "", ""};
        String[] itemInfo = {};
        String[] stockInfo = {"", "", ""};
        List<String> itemStocks = new ArrayList<String>();
        List<String> allUserOrders = new ArrayList<String>();
        List<String> specOrderInfo = new ArrayList<String>();
        try {
            Scanner stockReader = new Scanner(stockFile);
            while (stockReader.hasNextLine()) {
                fileLine = stockReader.nextLine();
                itemStocks.add(fileLine);
            }
            stockReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        try {
            Scanner accountsReader = new Scanner(ordersFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                allUserOrders.add(i, fileLine + "\n");
                i++;
                System.out.println(i + ") Order ID: " + orderInfo[2]);
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Enter ID of order to view");
        orderID = keyboard.nextLine();
        for (int j = 0; j < allUserOrders.size();j++){
            orderInfo = allUserOrders.get(j).split(";");
            if (orderInfo[2].equals(orderID)){
                if (!orderInfo[3].equals("ordered")){
                    System.out.println("This order has already been processed");
                    return;
                }
                itemInfo = orderInfo[1].split(" ");
                for (int k = 0; k < itemStocks.size();k++){
                    stockInfo = itemStocks.get(k).split(",",3);
                    for (int y = 0; y < itemInfo.length-1;y = y + 2){
                        if (itemInfo[y+1].equals(stockInfo[0])){
                            if (Integer.parseInt(itemInfo[y]) <= Integer.parseInt(stockInfo[1])){
                                //Item is in stock
                                stockInfo[2] = String.valueOf(Integer.parseInt(stockInfo[2]) + Integer.parseInt(itemInfo[y]));
                                int stockLeft = Integer.parseInt(stockInfo[1]) - Integer.parseInt(itemInfo[y]);
                                stockInfo[1] = String.valueOf(stockLeft);
                                itemStocks.set(k, stockInfo[0] + "," + stockInfo[1] + "," + stockInfo[2]);
                            }else{
                                System.out.println("Stock of " + stockInfo[0] + " is too low");
                                return;
                            }
                        }
                    }
                }
                //Rewrite stocks file
                try {
                    FileWriter stockWriter = new FileWriter(stockFile);
                    for (int k = 0; k < itemStocks.size();k++){
                        stockWriter.write(itemStocks.get(k) + "\n");
                    }
                    stockWriter.close();
                    System.out.println("Order successfully processed.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                //Change order status
                try {
                    Scanner fileReader = new Scanner(ordersFile);
                    StringBuffer buffer = new StringBuffer();
                    while (fileReader.hasNextLine()) {
                        fileLine = fileReader.nextLine();
                        specOrderInfo.add(fileLine);
                        buffer.append(fileLine + System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
                    fileReader.close();
                    for (int k = 0;k < specOrderInfo.size();k++){
                        orderInfo = specOrderInfo.get(k).split(";");
                        if (orderInfo[2].equals(orderID)){
                            fileContents = fileContents.replaceAll(orderInfo[2] + ";" + orderInfo[3], orderInfo[2] + ";ready");
                            FileWriter writer = new FileWriter(ordersFile);
                            writer.append(fileContents);
                            writer.flush();
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Order not found. Returning to menu...");
    }

    public void ShipOrder(){
        File ordersFile = new File("orders.txt");
        File stockFile = new File("stocks.txt");
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        String fileLine, orderID;
        String[] orderInfo = {"", "", "", "", "", ""};
        String[] itemInfo = {};
        String[] stockInfo = {"", "", ""};
        List<String> itemStocks = new ArrayList<String>();
        List<String> allUserOrders = new ArrayList<String>();
        List<String> specOrderInfo = new ArrayList<String>();
        //Move stock info from file
        try {
            Scanner stockReader = new Scanner(stockFile);
            while (stockReader.hasNextLine()) {
                fileLine = stockReader.nextLine();
                itemStocks.add(fileLine);
            }
            stockReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Get the ready orders
        try {
            Scanner accountsReader = new Scanner(ordersFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                if (orderInfo[3].equals("ready")){
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
        for (int j = 0; j < allUserOrders.size();j++){
            orderInfo = allUserOrders.get(j).split(";");
            if (orderInfo[2].equals(orderID)){
                itemInfo = orderInfo[1].split(" ");
                for (int k = 0; k < itemStocks.size();k++){
                    stockInfo = itemStocks.get(k).split(",",3);
                    for (int y = 0; y < itemInfo.length-1;y = y + 2){
                        if (itemInfo[y+1].equals(stockInfo[0])){
                            if (Integer.parseInt(itemInfo[y]) <= Integer.parseInt(stockInfo[1])){
                                //Change the stocks
                                stockInfo[2] = String.valueOf(Integer.parseInt(stockInfo[2]) - Integer.parseInt(itemInfo[y]));
                                itemStocks.set(k, stockInfo[0] + "," + stockInfo[1] + "," + stockInfo[2]);
                            }
                        }
                    }
                }
                orderInfo[3] = "shipped";
                allUserOrders.set(j, orderInfo[0] + ";" + orderInfo[1] + ";" + orderInfo[2] + ";shipped;" + orderInfo[4] + ";" + orderInfo[5]);


                //Rewrite stocks file
                try {
                    FileWriter stockWriter = new FileWriter(stockFile);
                    for (int k = 0; k < itemStocks.size();k++){
                        stockWriter.write(itemStocks.get(k) + "\n");
                    }
                    stockWriter.close();
                    System.out.println("Order successfully processed.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                //Change order status
                try {
                    Scanner fileReader = new Scanner(ordersFile);
                    StringBuffer buffer = new StringBuffer();
                    while (fileReader.hasNextLine()) {
                        fileLine = fileReader.nextLine();
                        specOrderInfo.add(fileLine);
                        buffer.append(fileLine + System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
                    fileReader.close();
                    for (int k = 0;k < specOrderInfo.size();k++){
                        orderInfo = specOrderInfo.get(k).split(";");
                        if (orderInfo[2].equals(orderID)){
                            fileContents = fileContents.replaceAll(orderInfo[2] + ";" + orderInfo[3], orderInfo[2] + ";shipped");
                            FileWriter writer = new FileWriter(ordersFile);
                            writer.append(fileContents);
                            writer.flush();
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
        }
    }
    public void ViewStock(){
        String[] stockList = {"", "", ""};
        try {
            File stockFile = new File("stocks.txt");
            Scanner accountsReader = new Scanner(stockFile);
            while (accountsReader.hasNextLine()) {
                stockList = accountsReader.nextLine().split(",");
                System.out.println(stockList[0] + ": " + stockList[1] + " available. " + stockList[2] + " reserved.");
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
