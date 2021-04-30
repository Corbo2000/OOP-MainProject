package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticatedView {
    public Button keepShopping;

    public void keepshopping(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/CustomerMenu.fxml"));
        Stage loginStage = (Stage) keepShopping.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }
}
