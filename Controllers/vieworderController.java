package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.IOException;

public class vieworderController {
    public Button logoutButton;
    public Button exitButton;
    public Button breadButton;
    public Slider breadSlider;

    public void logoutbutton(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/Welcome.fxml"));
        Stage createStage = (Stage) logoutButton.getScene().getWindow();
        createStage.setScene(new Scene(root));
    }

    public void exitbutton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void breadbutton(ActionEvent event) {
    }
}
