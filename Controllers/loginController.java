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

import java.io.IOException;

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
    public String labelpass = "Please enter password";
    public String labeluser = "Please enter user ID";
    public void backbutton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/Welcome.fxml"));
        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
    public void exitbutton() throws Exception{
        System.exit(0);
    }


    public void loginbutton(ActionEvent actionEvent) {
        username = userField.getText();
        password = passwordField.getText();

        if (username == vide)
            userLabel.setText(labeluser);
        if (password == vide)
            passLabel.setText(labelpass);
        if(password != vide && username!=vide){
            LogInCase newlog = new LogInCase();
            boolean userCheck = newlog.log(username,password);
            if(userCheck == true){
                System.out.println("This is the username: "+username+" and this is the password: "+password);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("../resources/ViewOrder.fxml"));
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

