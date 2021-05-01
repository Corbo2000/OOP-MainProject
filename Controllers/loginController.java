package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import usecase.LogInCase;

import java.io.*;
import java.util.Scanner;

public class loginController {
    public TextField userField;
    public TextField passwordField;
    public Label userLabel;
    public Label passLabel;
    public Label wrongLogIn;
    public Button loginButton;
    @FXML
    Button backButton,exitButton;
    public String vide = "";
    public String username,password;
    public String premium = "premium";
    public String regular = "regular";
    public String supplier = "supplier";
    public String labelpass = "Please enter password";
    public String labeluser = "Please enter user ID";
    public File cart = new File("TextFiles/Cart.txt");
    public void backbutton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/Welcome.fxml"));
        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
    public void exitbutton() throws Exception{
        System.exit(0);
    }


    public void loginbutton(ActionEvent actionEvent) throws IOException {
        File accountsFile = new File("TextFiles/orders.txt");
        File currentUser = new File("TextFiles/currentUser.txt");
        File currentOrders = new File("TextFiles/currentUserOrders.txt");
        String[] orderInfo = {"", "", "", "","",""};
        String fileLine, userInfo = "";
        username = userField.getText();
        password = passwordField.getText();
        PrintWriter wipe = new PrintWriter(cart);
        wipe.print("");
        wipe.close();
        if (username == vide)
            userLabel.setText(labeluser);
        if (password == vide)
            passLabel.setText(labelpass);
        if(password != vide && username!=vide){
            LogInCase newlog = new LogInCase();
            String[] userCheck = newlog.log(username,password);
            System.out.println(userCheck[0]);
            System.out.println(userCheck[1]);
            if(userCheck[0].equals("true")&&(userCheck[1].equals("premium")||userCheck[1].equals("regular"))){
                System.out.println("This is the username: "+username+" and this is the password: "+password);

                Scanner accountsReader = new Scanner(accountsFile);
                Scanner CurrentUser = new Scanner(currentUser);
                userInfo = CurrentUser.nextLine();
                wipe = new PrintWriter(currentOrders);
                wipe.print("");
                wipe.close();
                FileWriter writer = new FileWriter(currentOrders, false);
                while (accountsReader.hasNextLine()) {
                    fileLine = accountsReader.nextLine();
                    orderInfo = fileLine.split(";");
                    if (orderInfo[0].equals(userInfo)){
                        writer.write(fileLine+"\n");

                    }

                }
                accountsReader.close();
                CurrentUser.close();
                writer.close();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/CustomerMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.setScene(new Scene(root));
            }
            else if(userCheck[0].equals("true")&&(userCheck[1].equals("supplier") )){
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/SupplierMenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage loginStage = (Stage) loginButton.getScene().getWindow();
                loginStage.setScene(new Scene(root));

            }
            else
                wrongLogIn.setText("Wrong log-in credentials please try again");
        }

    }

}

