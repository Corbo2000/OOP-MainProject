package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class makeOrderController {
    public Button back;

    public void backtoMenu(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/CustomerMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = (Stage) back.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }
}
