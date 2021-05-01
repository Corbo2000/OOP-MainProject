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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class verifyBankController {
    public Button completeButton;
    public Label errorCardLabel;
    public String CartLine;
    public String[] CartElements ={"",""};
    public File currentOrders = new File("TextFiles/currentUserOrders.txt");
    public File AllOrders = new File("TextFiles/orders.txt");
    public File cartFile = new File("TextFiles/Cart.txt");
    public File CartPrice = new File("TextFiles/cartPrice.txt");
    public String priceString;
    private FadeTransition fadeIn = new FadeTransition(
            Duration.millis(2000)
    );
    private void fadeOut(Label emptyCartLabel, int time) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(time));
        fade.setNode(emptyCartLabel);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }
    private void fadeIn(Label label,int time) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.seconds(time));
        fade.setNode(label);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }


    public void completebutton(ActionEvent actionEvent) throws IOException {
        errorCardLabel.setVisible(false);
        Buffer buff = new Buffer();
        String userAccount = Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(5);

        BankHandler bank = new BankHandler(buff, userAccount);
        bank.run();
        long authorization = bank.authorization;
        if (authorization == 0){
            if(!errorCardLabel.isVisible()){
                errorCardLabel.setVisible(true);
                fadeOut(errorCardLabel,4000);
            }

        }
        else{
            Scanner cartPriceUpdate = new Scanner(CartPrice);
            priceString =cartPriceUpdate.nextLine();
            cartPriceUpdate.close();
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
            Stage backStage = (Stage) completeButton.getScene().getWindow();
            backStage.setScene(new Scene(root));
        }
    }
}
