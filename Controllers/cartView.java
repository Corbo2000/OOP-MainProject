package Controllers;

import javafx.beans.property.SimpleStringProperty;

public class cartView {
    private SimpleStringProperty cartItems;
    private SimpleStringProperty cartTotal;
    public cartView(String cartItems, String cartTotal){
        this.cartItems = new SimpleStringProperty(cartItems);
        this.cartTotal = new SimpleStringProperty(cartTotal);
    }
    public String getCartItems(){

        return cartItems.get();
    }

    public void setCartItems(SimpleStringProperty cartItems) {

        this.cartItems = cartItems;
    }
    public String getCartTotal(){
        return cartTotal.get();
    }
    public void setCartTotal(SimpleStringProperty cartTotal){
        this.cartTotal = cartTotal;
    }
}
