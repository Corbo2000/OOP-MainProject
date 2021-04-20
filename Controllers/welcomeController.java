package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class welcomeController {
    @FXML
    Button login, accountCreate;

    public void logIn() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/LogIn.fxml"));
        Stage loginStage = (Stage) login.getScene().getWindow();
        loginStage.setScene(new Scene(root));

    }

    public void accCreate() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/CreateAccount.fxml"));
        Stage createStage = (Stage) accountCreate.getScene().getWindow();
        createStage.setScene(new Scene(root));
    }
}
