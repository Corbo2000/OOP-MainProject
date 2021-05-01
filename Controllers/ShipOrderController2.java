package Controllers;

import javafx.animation.FadeTransition;
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
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShipOrderController2 implements Initializable {
    public Label noOrderLabel;
    public Label OrderSuccess;

    File accountsFile = new File("TextFiles/orders.txt");
    File currentUser = new File("TextFiles/currentUser.txt");
    File currentOrders = new File("TextFiles/currentUserOrders.txt");
    File orderedOrders = new File("TextFiles/OrderedOrders.txt");
    File readyOrders = new File("TextFiles/ReadyOrders.txt");
    File temporary = new File("TextFiles/temporaryHold.txt");
    String[] stockInfo = {"", "", ""};
    String fileLine="", userInfo = "";
    String[] orderInfo = {"", "", "", "","",""};
    String[] itemInfo = {};
    String fullOrder = "Process Order";
    String actualOrder="";
    File ordersFile = new File("TextFiles/orders.txt");
    File stockFile = new File("TextFiles/stocks.txt");


    public Button backButton;
    @FXML
    private TableView<Supplier> tableView;
    private void fadeOut(Label OrderSuccess,int time) {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(time));
        fade.setNode(OrderSuccess);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    @FXML private TableColumn<Supplier, String> ButtonColumn;
    @FXML private TableColumn<Supplier, String> orderItemColumn;
    @FXML private TableColumn<Supplier, String> orderNumberColumn;
    @FXML private TableColumn<Supplier,String> orderIDColumn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderNumberColumn.setCellValueFactory(new PropertyValueFactory<Supplier,String>("orderNumber"));
        ButtonColumn.setCellValueFactory(new PropertyValueFactory<Supplier, String>("button"));
        orderItemColumn.setCellValueFactory(new PropertyValueFactory<Supplier,String>("orderItems"));
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<Supplier,String>("orderID"));
        try {
            tableView.setItems(getOrderNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ObservableList<Supplier> getOrderNumber() throws IOException {
        generate();
        if(!OrderSuccess.isVisible()){
            OrderSuccess.setVisible(true);
            fadeOut(OrderSuccess,2000);
        }
        ObservableList <Supplier> people = FXCollections.observableArrayList();

        try {
            Scanner accountsReader = new Scanner(accountsFile);
            Scanner Unprocessed = new Scanner(readyOrders);

            PrintWriter wipe = new PrintWriter(currentOrders);
            wipe.print("");
            wipe.close();
            while (Unprocessed.hasNextLine()) {
                fileLine = Unprocessed.nextLine();
                orderInfo = fileLine.split(";");
                people.add(new Supplier(orderInfo[0],orderInfo[2],orderInfo[1],fullOrder));
                if(fileLine.equals("")){
                    noOrderLabel.setText("There are no orders to ship");
                }
                else
                    noOrderLabel.setText("");
            }
            accountsReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return people;
    }

    public void backbutton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/SupplierMenu.fxml"));

        Stage backStage = (Stage) backButton.getScene().getWindow();
        backStage.setScene(new Scene(root));
    }
    @FXML
    public void clickItem(MouseEvent event) throws IOException {

        generate();
        String confirmNumber = tableView.getSelectionModel().getSelectedItem().getOrderNumber();
        ShipOrder(confirmNumber);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/shipOrderView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage backStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        backStage.setScene(new Scene(root));


    }
    public void generate() throws IOException {
        PrintWriter wipe = new PrintWriter(orderedOrders);
        wipe.print("");
        wipe.close();
        wipe = new PrintWriter(readyOrders);
        wipe.print("");
        wipe.close();
        FileWriter fileWriter = new FileWriter(orderedOrders,true);
        FileWriter writer = new FileWriter(readyOrders,true);
        Scanner ordersFile = new Scanner(accountsFile);
        while(ordersFile.hasNextLine()){
            fileLine = ordersFile.nextLine();
            orderInfo = fileLine.split(";");
            if (orderInfo[3].equals("ordered")){
                fileWriter.write(fileLine+"\n");
            }
            else if(orderInfo[3].equals("ready")){
                writer.write(fileLine+"\n");
            }
        }
        fileWriter.close();
        writer.close();

    }
    public void ShipOrder(String conrfirmation){
        File ordersFile = new File("TextFiles/orders.txt");
        File stockFile = new File("TextFiles/stocks.txt");

        int i = 0;
        String fileLine, orderID;
        String[] orderInfo = {"", "", "", "", "", ""};
        String[] itemInfo = {};
        String[] stockInfo = {"", "", ""};
        List<String> itemStocks = new ArrayList<String>();
        List<String> allUserOrders = new ArrayList<String>();
        List<String> specOrderInfo = new ArrayList<String>();
        //Move stock info from file
        try {
            Scanner stockReader = new Scanner(stockFile);
            while (stockReader.hasNextLine()) {
                fileLine = stockReader.nextLine();
                itemStocks.add(fileLine);
            }
            stockReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //Get the ready orders
        try {
            Scanner accountsReader = new Scanner(ordersFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                orderInfo = fileLine.split(";");
                allUserOrders.add(i, fileLine + "\n");
                i++;
                System.out.println(i + ") Order ID: " + orderInfo[2]);
            }
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (int j = 0; j < allUserOrders.size();j++){
            orderInfo = allUserOrders.get(j).split(";");
            if (orderInfo[2].equals(conrfirmation)){
                itemInfo = orderInfo[1].split(" ");
                for (int k = 0; k < itemStocks.size();k++){
                    stockInfo = itemStocks.get(k).split(",",3);
                    for (int y = 0; y < itemInfo.length-1;y = y + 2){
                        if (itemInfo[y+1].equals(stockInfo[0]+";")){
                            if (Integer.parseInt(itemInfo[y]) <= Integer.parseInt(stockInfo[1])){
                                //Change the stocks
                                stockInfo[2] = String.valueOf(Integer.parseInt(stockInfo[2]) - Integer.parseInt(itemInfo[y]));
                                itemStocks.set(k, stockInfo[0] + "," + stockInfo[1] + "," + stockInfo[2]);
                            }
                        }
                    }
                }
                orderInfo[3] = "shipped";
                allUserOrders.set(j, orderInfo[0] + ";" + orderInfo[1] + ";" + orderInfo[2] + ";shipped;" + orderInfo[4] + ";" + orderInfo[5]);


                //Rewrite stocks file
                try {
                    FileWriter stockWriter = new FileWriter(stockFile);
                    for (int k = 0; k < itemStocks.size();k++){
                        stockWriter.write(itemStocks.get(k) + "\n");
                    }
                    stockWriter.close();
                    System.out.println("Order successfully processed.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

                //Change order status
                try {
                    Scanner fileReader = new Scanner(ordersFile);
                    StringBuffer buffer = new StringBuffer();
                    while (fileReader.hasNextLine()) {
                        fileLine = fileReader.nextLine();
                        specOrderInfo.add(fileLine);
                        buffer.append(fileLine + System.lineSeparator());
                    }
                    String fileContents = buffer.toString();
                    fileReader.close();
                    for (int k = 0;k < specOrderInfo.size();k++){
                        orderInfo = specOrderInfo.get(k).split(";");
                        String confirmation = conrfirmation;
                        if (orderInfo[2].equals(confirmation)){
                            fileContents = fileContents.replaceAll(orderInfo[2] + ";" + orderInfo[3], orderInfo[2] + ";shipped");
                            FileWriter writer = new FileWriter(ordersFile);
                            writer.append(fileContents);
                            writer.flush();
                            return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }

            }
        }
    }

}

//public void initData(usecase.LogInCase )

