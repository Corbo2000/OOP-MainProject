package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerMenuController {

    public Button vsItems;
    public Label welcomeLabel;
    public Button VInvoice;
    public Button mOrder;
    public Button exit;

    public void View_SelectItems(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/ViewOrder.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = (Stage) vsItems.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }

    public void ViewInvoice(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/ViewInvoice.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = (Stage) VInvoice.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }

    public void MakeOrder(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/MakeOrder.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = (Stage) mOrder.getScene().getWindow();
        loginStage.setScene(new Scene(root));
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}
