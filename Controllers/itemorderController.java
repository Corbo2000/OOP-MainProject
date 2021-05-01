package Controllers;

import MainProjectPackage.BankHandler;
import MainProjectPackage.Buffer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class itemorderController {
    public Button logoutButton;
    public Button backButton;
    public Button exitButton;
    public Button appleButton;
    public Button orangeButton;
    public Button milkButton;
    public Slider appleSlider;
    public Slider orangeSlider;
    public Slider milkSlider;
    public Label AppleOrderLabel;
    public Label OrangeOrderLabel;
    public Label MilkOrderLabel;
    public double price;
    public int value;
    public String priceString;
    public Label emptyCartLabel;
    ArrayList<String> cart = new ArrayList<String>();


    private FadeTransition fadeIn = new FadeTransition(
            Duration.millis(2000)
    );
    private void fadeOut(Label emptyCartLabel,int time) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(time));
        fade.setNode(emptyCartLabel);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    public File cartpriceFile = new File("TextFiles/cartPrice.txt");
    public File cartFile = new File("TextFiles/Cart.txt");
    public Button viewcartButton;
    public void logoutbutton(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/Welcome.fxml"));
        Stage createStage = (Stage) logoutButton.getScene().getWindow();
        createStage.setScene(new Scene(root));
    }
    public void backbutton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/CustomerMenu.fxml"));

        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
        FileWriter writer = new FileWriter(cartpriceFile, false);
        writer.write("premium");
        writer.close();
    }
    public void exitbutton(ActionEvent actionEvent) {
        System.exit(0);
    }
    /*
    public void breadbutton(ActionEvent event) {
        //take number input from slider
        //take what type of item it is (bread in this case)
        //add it to the currentOrder txt file
            //close bread button if ordered once
        int value = (int) breadSlider.getValue();
        System.out.println(value);
    }
     */

    public void AppleOrder(ActionEvent event) throws IOException {
        String customerType = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(6);
        System.out.println(customerType);
        double normalApple = 1.50;
        double premiumApple = 1.00;
        if(MilkOrderLabel.getText().isEmpty()){
            value = (int) appleSlider.getValue();
            String orderInput = value + " apple(s), ";
            cart.add(orderInput);
        }
        Scanner cartScan = new Scanner(cartpriceFile);

        if(!cartScan.hasNextLine()){
            if(customerType.equals("premium")){
                price = price +(value*premiumApple);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalApple);
            }

            priceString = Double.toString(price);
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        else if(cartScan.hasNextLine()){
            priceString = cartScan.nextLine();
            price = Double.parseDouble(priceString);
            if(customerType.equals("premium")){
                price = price +(value*premiumApple);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalApple);
            }

            priceString = Double.toString(price);
            PrintWriter wipe = new PrintWriter(cartpriceFile);
            wipe.print("");
            wipe.close();
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        cartScan.close();
    }

    public void OrangeOrder(ActionEvent event) throws IOException {
        String customerType = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(6);
        System.out.println(customerType);
        double normalOrange = 4.99;
        double premiumOrange = 3.99;
        if(MilkOrderLabel.getText().isEmpty()){
            value = (int) orangeSlider.getValue();
            String orderInput = value + " orange(s), ";
            cart.add(orderInput);
        }
        Scanner cartScan = new Scanner(cartpriceFile);

        if(!cartScan.hasNextLine()){
            if(customerType.equals("premium")){
                price = price +(value*premiumOrange);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalOrange);
            }

            priceString = Double.toString(price);
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        else if(cartScan.hasNextLine()){
            priceString = cartScan.nextLine();
            price = Double.parseDouble(priceString);
            if(customerType.equals("premium")){
                price = price +(value*premiumOrange);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalOrange);
            }

            priceString = Double.toString(price);
            PrintWriter wipe = new PrintWriter(cartpriceFile);
            wipe.print("");
            wipe.close();
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        cartScan.close();
    }
    public void MilkOrder(ActionEvent event) throws IOException {
        String customerType = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(6);
        System.out.println(customerType);
        double normalMilk = 5;
        double premiumMilk = 4.50;
        if(MilkOrderLabel.getText().isEmpty()){
            value = (int) milkSlider.getValue();
            String orderInput = value + " milk(s),";
            cart.add(orderInput);
        }
        Scanner cartScan = new Scanner(cartpriceFile);

        if(!cartScan.hasNextLine()){
            if(customerType.equals("premium")){
                price = price +(value*premiumMilk);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalMilk);
            }

            priceString = Double.toString(price);
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        else if(cartScan.hasNextLine()){
            priceString = cartScan.nextLine();
            price = Double.parseDouble(priceString);
            if(customerType.equals("premium")){
                price = price +(value*premiumMilk);
            }
            else if(customerType.equals("regular")){
                price = price+(value*normalMilk);
            }

            priceString = Double.toString(price);
            PrintWriter wipe = new PrintWriter(cartpriceFile);
            wipe.print("");
            wipe.close();
            FileWriter writer = new FileWriter(cartpriceFile, false);
            writer.write(priceString);
            writer.close();
        }
        cartScan.close();
    }

    public void viewCart(ActionEvent actionEvent) throws IOException {

        Writer writer = new FileWriter(cartFile);
        for(int i=0;i<cart.size();i++){
            System.out.print(cart.get(i));
            writer.write(cart.get(i));
        }
        Scanner cartScan = new Scanner(cartpriceFile);
        if(!cartScan.hasNextLine()){
            System.out.print("Cart is empty");
            if(!emptyCartLabel.isVisible()){
                emptyCartLabel.setVisible(true);
                fadeOut(emptyCartLabel,2000);
            }
        }
        else if(cartScan.hasNextLine()){
            String price = ";$"+cartScan.nextLine();
            System.out.print(price);
            writer.write(price);
            writer.close();
            cartScan.close();
            Parent root = FXMLLoader.load(getClass().getResource("../resources/cartView.fxml"));
            Stage backStage = (Stage) viewcartButton.getScene().getWindow();
            backStage.setScene(new Scene(root));

        }




    }

}
