package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;


public class cartviewController implements Initializable {
    public File cartFile = new File("TextFiles/Cart.txt");
    public Button makeorderButton;

    String fileLine, userInfo = "";
    String[] cartInfo = {"", ""};
    String fullOrder = "View Order";

    public Button backButton;
    @FXML private TableView<cartView> cartviewTable;
    @FXML private TableColumn<cartView, String> CartItemsColumn;
    @FXML private TableColumn<cartView, String> CartTotalColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CartItemsColumn.setCellValueFactory(new PropertyValueFactory<cartView,String>("cartItems"));
        CartTotalColumn.setCellValueFactory(new PropertyValueFactory<cartView, String>("cartTotal"));
        cartviewTable.setItems(getCartItems());

    }
    public ObservableList<cartView> getCartItems(){
        ObservableList <cartView> people = FXCollections.observableArrayList();

        try {

            Scanner cartScan = new Scanner(cartFile);

            while (cartScan.hasNextLine()) {
                userInfo = cartScan.nextLine();
                System.out.println("This is the actual line from cart:"+userInfo);
                System.out.println("This is userinfo:"+userInfo);
                cartInfo = userInfo.split(";");
                people.add(new cartView(cartInfo[0], cartInfo[1]));

            }
            cartScan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return people;
    }

    public void backbutton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/itemOrder.fxml"));

        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
    public void exitbutton(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void makeorderbutton(ActionEvent actionEvent) {
    }


    //public void initData(usecase.LogInCase )

}
