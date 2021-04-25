package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class tableviewController implements Initializable {
    File accountsFile = new File("TextFiles/orders.txt");
    File currentUser = new File("TextFiles/currentUser.txt");
    File currentOrders = new File("TextFiles/currentUserOrders.txt");
    String fileLine, userInfo = "";
    String[] orderInfo = {"", "", "", ""};
    String fullOrder = "View Order";


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



    //public void initData(usecase.LogInCase )

}
