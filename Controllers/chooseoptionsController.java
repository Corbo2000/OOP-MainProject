package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class chooseoptionsController {

    public Button selectItemsButton;

    public void selectitemsbutton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/ViewOrder.fxml"));
        Stage backStage = (Stage) selectItemsButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
}
