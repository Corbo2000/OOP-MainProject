package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class loginController {
    @FXML
    Button backButton;
    public void backbutton() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/Welcome.fxml"));
        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }

}
