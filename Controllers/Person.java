package Controllers;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private SimpleStringProperty orderNumber;

    public Person(String orderNumber){
        this.orderNumber = new SimpleStringProperty(orderNumber);
    }
    public SimpleStringProperty getOrderNumber(){
        return orderNumber;
    }

    public void setOrderNumber(SimpleStringProperty orderNumber) {
        this.orderNumber = orderNumber;
    }

}
