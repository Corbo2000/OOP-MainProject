package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.Scanner;

public class invoicesController implements Initializable {
    File accountsFile = new File("TextFiles/orders.txt");
    File currentUser = new File("TextFiles/currentUser.txt");
    File currentOrders = new File("TextFiles/currentUserOrders.txt");
    String fileLine, userInfo = "";
    String[] orderInfo = {"", "", "", "","",""};
    String fullOrder = "View Order";
    String actualOrder="";

    public Button backButton;
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> orderNumberColumn;
    @FXML private TableColumn<Person, String> ButtonColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<Person,String>("orderNumber"));
        ButtonColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("button"));
        tableView.setItems(getOrderNumber());

    }
    public ObservableList<Person> getOrderNumber(){
        ObservableList <Person> people = FXCollections.observableArrayList();

        try {
            Scanner accountsReader = new Scanner(accountsFile);
            Scanner CurrentUser = new Scanner(currentUser);
            userInfo = CurrentUser.nextLine();
            PrintWriter wipe = new PrintWriter(currentOrders);
            wipe.print(" ");
            wipe.close();
            FileWriter writer = new FileWriter(currentOrders, false);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                if (orderInfo[0].equals(userInfo)){
                    writer.write(fileLine+"\n");

                    System.out.println(fileLine);
                    people.add(new Person(orderInfo[2],fullOrder));
                }

            }
            accountsReader.close();
            CurrentUser.close();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    public void backbutton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/CustomerMenu.fxml"));

        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
    @FXML
    public void clickinvoiceItem(MouseEvent event) throws IOException {

        String confirmNumber = tableView.getSelectionModel().getSelectedItem().getOrderNumber();
        try {

            Scanner currentuserOrders= new Scanner(currentOrders);
            while (currentuserOrders.hasNextLine()) {
                fileLine = currentuserOrders.nextLine();
                orderInfo = fileLine.split(";");
                if (orderInfo[2].equals(confirmNumber)){
                    actualOrder = fileLine;
                    System.out.println(fileLine);
                }

            }
            currentuserOrders.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../resources/viewInvoice.fxml"));
        Parent root = loader.load();
        viewinvoiceController invoicecontroller = loader.getController();
        orderInfo = actualOrder.split(";");
        invoicecontroller.textSetter(orderInfo[4],orderInfo[1],"$"+orderInfo[5], Files.readAllLines(Paths.get("TextFiles/currentUser.txt")).get(5));
        Stage backStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        backStage.setScene(new Scene(root));


    }


    //public void initData(usecase.LogInCase )

}
