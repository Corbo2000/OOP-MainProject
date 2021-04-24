package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class tableviewController {
    File accountsFile = new File("orders.txt");
    String fileLine, userInfo = "";
    String[] orderInfo = {"", "", "", ""};
    @FXML private TableView<Person> tableView;
    @FXML private TableColumn<Person, String> orderNumberColumn;
    public ObservableList<Person> getOrderNumber(){
        ObservableList <Person> people = FXCollections.observableArrayList();
        try {
            Scanner accountsReader = new Scanner(accountsFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                people.add(new Person(orderInfo[2]));
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return people;
    }

    //public void initData(usecase.LogInCase )

}
