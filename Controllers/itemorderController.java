package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class itemorderController {
    public Button logoutButton;
    public Button exitButton;
    public Button appleButton;
    public Button milkButton;
    public Slider appleSlider;
    public Label AppleOrderLabel;
    public Slider orangeSlider;
    public Slider milkSlider;
    public Label OrangeOrderLabel;
    public Label MilkOrderLabel;
    public String[] cart;
    public int i = 0; //String index

    public void logoutbutton(ActionEvent actionEvent) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/Welcome.fxml"));
        Stage createStage = (Stage) logoutButton.getScene().getWindow();
        createStage.setScene(new Scene(root));
    }

    public void exitbutton(ActionEvent actionEvent) {
        System.exit(0);
    }
    /*
    public void breadbutton(ActionEvent event) {
        //take number input from slider
        //take what type of item it is (bread in this case)
        //add it to the currentOrder txt file
            //close bread button if ordered once
        int value = (int) breadSlider.getValue();
        System.out.println(value);
    }
     */

    public void AppleOrder(ActionEvent event) {
        if(AppleOrderLabel.getText().isEmpty()){
            int value = (int) appleSlider.getValue();
            String orderInput = " " + value + " apple(s),";
            cart[i] = orderInput;
        }
    }

    public void orangeOrder(ActionEvent event) {
    }
}
