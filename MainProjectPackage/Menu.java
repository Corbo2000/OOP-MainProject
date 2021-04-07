package MainProjectPackage;

public interface Menu {
    abstract public void LogOut();
    abstract public void ViewOrder();
    abstract public void ViewInvoice();
    abstract public void SelectItems();
    abstract public void MakeOrder();
    abstract public void ProcessOrder();
    abstract public void ShipOrder();
    abstract public void ViewStock();
}

class Customer implements Menu{
    @Override
    public void LogOut() {
        System.exit(0);
    }

    @Override
    public void ViewOrder() {

    }

    @Override
    public void ViewInvoice() {

    }

    @Override
    public void SelectItems() {

    }

    @Override
    public void MakeOrder() {

    }

    @Override
    public void ProcessOrder() {
        //does nothing
    }

    @Override
    public void ShipOrder() {
        //does nothing
    }

    @Override
    public void ViewStock() {
        //does nothing
    }
}

class Supplier implements Menu{
    @Override
    public void LogOut() {
        System.exit(0);
    }

    @Override
    public void ViewOrder() {
        //does nothing
    }

    @Override
    public void ViewInvoice() {
        //does nothing
    }

    @Override
    public void SelectItems() {
        //does nothing
    }

    @Override
    public void MakeOrder() {
        //does nothing
    }

    @Override
    public void ProcessOrder() {

    }

    @Override
    public void ShipOrder() {

    }

    @Override
    public void ViewStock() {

    }
}

class Bank implements Menu{
    @Override
    public void LogOut() {

    }

    @Override
    public void ViewOrder() {
        //does nothing
    }

    @Override
    public void ViewInvoice() {
        //does nothing
    }

    @Override
    public void SelectItems() {
        //does nothing
    }

    @Override
    public void MakeOrder() {

    }

    @Override
    public void ProcessOrder() {
        //does nothing
    }

    @Override
    public void ShipOrder() {
        //does nothing
    }

    @Override
    public void ViewStock() {
        //does nothing
    }
}