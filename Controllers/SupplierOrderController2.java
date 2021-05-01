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

public class SupplierOrderController2 implements Initializable {
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
            Scanner Unprocessed = new Scanner(orderedOrders);

            PrintWriter wipe = new PrintWriter(currentOrders);
            wipe.print("");
            wipe.close();
            while (Unprocessed.hasNextLine()) {
                fileLine = Unprocessed.nextLine();
                orderInfo = fileLine.split(";");
                people.add(new Supplier(orderInfo[0],orderInfo[2],orderInfo[1],fullOrder));
                if(fileLine.equals("")){
                    noOrderLabel.setText("There are no orders to process");
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
        int i = 0;
        String confirmNumber = tableView.getSelectionModel().getSelectedItem().getOrderNumber();
        List<String> itemStocks = new ArrayList<String>();
        List<String> allUserOrders = new ArrayList<String>();
        List<String> specOrderInfo = new ArrayList<String>();
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
        try {
            Scanner accountsReader = new Scanner(orderedOrders);
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
            System.out.println("This is the orderInfo[2]: "+orderInfo[2]);
            System.out.println("This is the order confirm : "+orderInfo[2]);

            if (orderInfo[2].equals(confirmNumber)){
                itemInfo = orderInfo[1].split(" ");
                for (int k = 0; k < itemStocks.size();k++){
                    stockInfo = itemStocks.get(k).split(",",3);
                    for (int y = 0; y < itemInfo.length-1;y = y + 2){
                        if (itemInfo[y+1].equals(stockInfo[0]+",")){
                            if (Integer.parseInt(itemInfo[y]) <= Integer.parseInt(stockInfo[1])){
                                //Item is in stock
                                stockInfo[2] = String.valueOf(Integer.parseInt(stockInfo[2]) + Integer.parseInt(itemInfo[y]));
                                int stockLeft = Integer.parseInt(stockInfo[1]) - Integer.parseInt(itemInfo[y]);
                                stockInfo[1] = String.valueOf(stockLeft);
                                itemStocks.set(k, stockInfo[0] + "," + stockInfo[1] + "," + stockInfo[2]);
                            }else{
                                System.out.println("Stock of " + stockInfo[0] + " is too low");
                                return;
                            }
                        }
                    }
                }
                //Rewrite stocks file
                try {
                    FileWriter stockWriter = new FileWriter(stockFile);
                    for (int k = 0; k < itemStocks.size();k++){
                        stockWriter.write(itemStocks.get(k) + "\n");
                    }
                    stockWriter.close();
                    //System.out.println("Order successfully processed.");//to be changed to label
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                try {

                    Scanner orderOrders= new Scanner(orderedOrders);
                    while (orderOrders.hasNextLine()) {
                        fileLine = orderOrders.nextLine();
                        orderInfo = fileLine.split(";");
                        if (orderInfo[2].equals(confirmNumber)){
                            actualOrder = fileLine;
                            String replaceString=actualOrder.replace("ordered","ready");
                            FileWriter writer = new FileWriter(readyOrders,true);
                            writer.write(replaceString);
                            writer.close();
                            System.out.println(fileLine);

                        }
                        else if(!orderInfo[3].equals("ordered")){
                            orderInfo[3] = "ordered";
                        }

                    }
                    orderOrders.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
                try {//this resets the ordered text file

                    Scanner orderOrders= new Scanner(orderedOrders);
                    while (orderOrders.hasNextLine()) {
                        fileLine = orderOrders.nextLine();
                        orderInfo = fileLine.split(";");
                        if (!orderInfo[2].equals(confirmNumber)){
                            actualOrder = fileLine;
                            FileWriter writer = new FileWriter(temporary,true);
                            writer.write(fileLine);
                            writer.close();
                            System.out.println(fileLine);
                        }

                    }
                    PrintWriter wipe = new PrintWriter(orderedOrders);
                    wipe.print("");
                    wipe.close();
                    Scanner writeOrders = new Scanner(temporary);
                    FileWriter orderWrite = new FileWriter(orderedOrders,true);
                    while(writeOrders.hasNextLine()){
                        orderWrite.write(writeOrders.nextLine());
                    }
                    orderWrite.close();
                    writeOrders.close();
                    wipe = new PrintWriter(temporary);
                    wipe.print("");
                    wipe.close();

                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }//This changes the order from ordered to ready and removes it from the orderedOrders textfile.

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
                        if (orderInfo[2].equals(confirmNumber)){
                            fileContents = fileContents.replaceAll(orderInfo[2] + ";" + orderInfo[3], orderInfo[2] + ";ready");
                            FileWriter writer = new FileWriter(ordersFile);
                            writer.append(fileContents);
                            writer.flush();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../resources/supplierOrderView.fxml"));
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


    //public void initData(usecase.LogInCase )

}
