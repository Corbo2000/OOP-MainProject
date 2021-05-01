package Controllers;

import javafx.beans.property.SimpleStringProperty;

public class Supplier {
    private SimpleStringProperty orderNumber;
    private SimpleStringProperty orderID;
    private SimpleStringProperty orderItems;
    private SimpleStringProperty button;
    public Supplier(String orderID,String orderNumber, String orderItems,String button){
        this.orderNumber = new SimpleStringProperty(orderNumber);
        this.orderID = new SimpleStringProperty(orderID);
        this.button = new SimpleStringProperty(button);
        this.orderItems = new SimpleStringProperty(orderItems);
    }
    public String getOrderNumber(){
        return orderNumber.get();
    }

    public void setOrderNumber(SimpleStringProperty orderNumber) {

        this.orderNumber = orderNumber;
    }
    public String getOrderID(){
        return orderID.get();
    }

    public void setOrderID(SimpleStringProperty orderNumber) {

        this.orderID = orderNumber;
    }
    public String getOrderItems(){
        return orderItems.get();
    }

    public void setOrderItems(SimpleStringProperty orderNumber) {

        this.orderItems = orderNumber;
    }
    public String getButton(){
        return button.get();
    }
    public void setButton(SimpleStringProperty button){
        this.button = button;
    }
}
