package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewInvoice {

    public TableView invoiceTable;
    public TableColumn dateColumn;
    public TableColumn totalColumn;
    public TableColumn itemsColumn;
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

    // there should be a method that reads the orders file, finds the orders from the logged in customerm, and shows ALL of them in this table
}
