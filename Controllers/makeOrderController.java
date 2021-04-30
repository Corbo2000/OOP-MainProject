package Controllers;

import MainProjectPackage.BankHandler;
import MainProjectPackage.Buffer;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.Scanner;

public class makeOrderController {
    public Button back;
    public RadioButton instoreRadio;
    public RadioButton mailRadio;
    public double price;
    public String priceString;
    public File CartPrice = new File("TextFiles/cartPrice.txt");
    public File cartFile = new File("TextFiles/Cart.txt");
    public String CartLine;
    public String[] CartElements ={"",""};
    public String[] lastTimeOrder = {"","","","",""};
    public String orderLine;
    public String dateLine;
    public boolean firstTimeOrder = false;
    public boolean auth = false;
    public String[] presentDate = {"","",""};
    public File BankInfo = new File("TextFiles/BankCCInfo.txt");
    public File currentOrders = new File("TextFiles/currentUserOrders.txt");
    public File AllOrders = new File("TextFiles/orders.txt");
    public Year year = Year.now();
    public String CurrentYear = year.toString();
    public void backtoMenu(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/cartView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = (Stage) back.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }

    public void instoreradio(ActionEvent actionEvent) throws IOException {
        Buffer buff = new Buffer();
        String userType = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(6);
        if(userType=="premium"){//This adds $40 if user is a premium customer
            Scanner CurrentUserOrders = new Scanner(currentOrders);
            while (CurrentUserOrders.hasNextLine()) {
                orderLine = CurrentUserOrders.nextLine();
                lastTimeOrder = orderLine.split(";");
                dateLine = lastTimeOrder[4];
                presentDate = dateLine.split("-");
                System.out.println("This is the date of orders: "+presentDate[0]);
                if (presentDate[0].equals(CurrentYear)){
                    firstTimeOrder = true;
                }
            }
            CurrentUserOrders.close();
            if(firstTimeOrder){
                Scanner cartPriceUpdate = new Scanner(CartPrice);
                priceString =cartPriceUpdate.nextLine();
                cartPriceUpdate.close();
                price = Double.parseDouble(priceString);
                price= price+40;
                priceString = Double.toString(price);
                PrintWriter wipe = new PrintWriter(CartPrice);
                wipe.print("");
                wipe.close();
                FileWriter writer = new FileWriter(CartPrice, false);
                writer.write(priceString);
                writer.close();
                Scanner cartScanner = new  Scanner(cartFile);
                CartLine = cartScanner.nextLine();
                cartScanner.close();
                CartElements = CartLine.split(";");
                CartElements[1] = ";$"+priceString;
                CartLine = CartElements[0]+CartElements[1];
                PrintWriter newCart = new PrintWriter(cartFile);
                newCart.print(CartLine);
                newCart.close();

            }
        }
        String userAccount = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(5);

        BankHandler bank = new BankHandler(buff, userAccount);
        bank.run();
        long authorization = bank.authorization;
        if (authorization == 0){
            Parent root = FXMLLoader.load(getClass().getResource("../resources/verifyOrder.fxml"));
            Stage verifyOrder = (Stage) instoreRadio.getScene().getWindow();
            verifyOrder.setScene(new Scene(root));
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            Scanner cartScanner = new  Scanner(cartFile);
            String userID = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(0);
            CartLine = cartScanner.nextLine();
            cartScanner.close();
            CartElements = CartLine.split(";");
            FileWriter writer = new FileWriter(AllOrders,true);
            writer.write("\n"+userID+";"+CartElements[0]+";"+authorization+";"+"ordered"+";"+dateFormat.format(date)+priceString);
            writer.close();
            FileWriter currentWriter = new FileWriter(currentOrders,true);
            currentWriter.write(userID+";"+CartElements[0]+";"+authorization+";"+"ordered"+";"+dateFormat.format(date)+";"+priceString);
            currentWriter.close();
            PrintWriter newCart = new PrintWriter(cartFile);
            newCart.print("");
            newCart.close();
            //the confirmation number has been added to the cart string and has been added to general orders
            Parent root = FXMLLoader.load(getClass().getResource("../resources/OrderAuthenticated.fxml"));
            Stage backStage = (Stage) instoreRadio.getScene().getWindow();
            backStage.setScene(new Scene(root));
        }

    }

    public void mailradio(ActionEvent actionEvent) throws IOException {
        Buffer buff = new Buffer();
        String userType = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(6);

        Scanner cartPriceScan = new Scanner(CartPrice);
        while(cartPriceScan.hasNextLine()){//this adds the $3 to the cart order
            priceString =cartPriceScan.nextLine();

            price = Double.parseDouble(priceString);
            price= price+3;
            priceString = Double.toString(price);
            PrintWriter wipe = new PrintWriter(CartPrice);
            wipe.print("");
            wipe.close();
            FileWriter writer = new FileWriter(CartPrice, false);
            writer.write(priceString);
            writer.close();
            Scanner cartScanner = new  Scanner(cartFile);
            CartLine = cartScanner.nextLine();
            cartScanner.close();
            CartElements = CartLine.split(";");
            CartElements[1] = ";$"+priceString;
            CartLine = CartElements[0]+CartElements[1];
            PrintWriter newCart = new PrintWriter(cartFile);
            newCart.print(CartLine);
            newCart.close();
        }
        cartPriceScan.close();
        if(userType=="premium"){//This adds $40 if user is a premium customer
            Scanner CurrentUserOrders = new Scanner(currentOrders);
            while (CurrentUserOrders.hasNextLine()) {
                orderLine = CurrentUserOrders.nextLine();
                lastTimeOrder = orderLine.split(";");
                dateLine = lastTimeOrder[4];
                presentDate = dateLine.split("-");
                System.out.println("This is the date of orders: "+presentDate[0]);
                if (presentDate[0].equals(CurrentYear)){
                    firstTimeOrder = true;
                }
            }
            CurrentUserOrders.close();
            if(firstTimeOrder){
                Scanner cartPriceUpdate = new Scanner(CartPrice);
                priceString =cartPriceUpdate.nextLine();
                cartPriceUpdate.close();
                price = Double.parseDouble(priceString);
                price= price+40;
                priceString = Double.toString(price);
                PrintWriter wipe = new PrintWriter(CartPrice);
                wipe.print("");
                wipe.close();
                FileWriter writer = new FileWriter(CartPrice, false);
                writer.write(priceString);
                writer.close();
                Scanner cartScanner = new  Scanner(cartFile);
                CartLine = cartScanner.nextLine();
                cartScanner.close();
                CartElements = CartLine.split(";");
                CartElements[1] = ";$"+priceString;
                CartLine = CartElements[0]+CartElements[1];
                PrintWriter newCart = new PrintWriter(cartFile);
                newCart.print(CartLine);
                newCart.close();

            }
        }
        String userAccount = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(5);

        BankHandler bank = new BankHandler(buff, userAccount);
        bank.run();
        long authorization = bank.authorization;
        if (authorization == 0){
            Parent root = FXMLLoader.load(getClass().getResource("../resources/verifyOrder.fxml"));
            Stage verifyOrder = (Stage) instoreRadio.getScene().getWindow();
            verifyOrder.setScene(new Scene(root));
        }
        else{
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            Scanner cartScanner = new  Scanner(cartFile);
            String userID = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(0);
            CartLine = cartScanner.nextLine();
            cartScanner.close();
            CartElements = CartLine.split(";");
            FileWriter writer = new FileWriter(AllOrders,true);
            writer.write("\n"+userID+";"+CartElements[0]+";"+authorization+";"+"ordered"+";"+dateFormat.format(date)+";"+priceString);
            writer.close();
            FileWriter currentWriter = new FileWriter(currentOrders,true);
            currentWriter.write(userID+";"+CartElements[0]+";"+authorization+";"+"ordered"+";"+dateFormat.format(date)+";"+priceString);
            currentWriter.close();
            PrintWriter newCart = new PrintWriter(cartFile);
            newCart.print("");
            newCart.close();
            //the confirmation number has been added to the cart string and has been added to general orders
            Parent root = FXMLLoader.load(getClass().getResource("../resources/OrderAuthenticated.fxml"));
            Stage backStage = (Stage) instoreRadio.getScene().getWindow();
            backStage.setScene(new Scene(root));
        }
    }
}
