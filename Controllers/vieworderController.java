package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class vieworderController {

    public Label confirmationField;
    public Label itemsField;
    public Label statusField;
    public Label dateField;
    public Button backButton;
    public void textSetter(String confirmationNumber, String itemsOrdered, String orderStatus, String orderDate){
        confirmationField.setText(confirmationNumber);
        itemsField.setText(itemsOrdered);
        statusField.setText(orderStatus);
        dateField.setText(orderDate);
    }

    public void backbutton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/tableView.fxml"));

        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
}
