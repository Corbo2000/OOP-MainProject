package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Person {
    private SimpleStringProperty orderNumber;
    private SimpleStringProperty button;
    public Person(String orderNumber, String button){
        this.orderNumber = new SimpleStringProperty(orderNumber);

        this.button = new SimpleStringProperty(button);
    }
    public String getOrderNumber(){

        return orderNumber.get();
    }

    public void setOrderNumber(SimpleStringProperty orderNumber) {

        this.orderNumber = orderNumber;
    }
    public String getButton(){
        return button.get();
    }
    public void setButton(SimpleStringProperty button){
        this.button = button;
    }
}
