package MainProjectPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LogIn {
    String ID, password, Name, Address, Phone, CCNumber, Type;
    void log(){
        File accountsFile = new File("accounts.txt");
        String fileLine, userInfo = "";
        Scanner keyboardInput = new Scanner(System.in);
        System.out.println("Please enter your user ID:");
        ID = keyboardInput.nextLine();
        System.out.println("Please enter your password:");
        password = keyboardInput.nextLine();

        //Verify valid info
        try {
            Scanner accountsReader = new Scanner(accountsFile);
            while (accountsReader.hasNextLine()) {
                fileLine = accountsReader.nextLine();
                if (fileLine.equals("--")){
                    if (accountsReader.nextLine().equals(ID)) {
                        userInfo = userInfo + ID;
                        if (accountsReader.nextLine().equals(password)){
                            Name = accountsReader.nextLine();
                            Address = accountsReader.nextLine();
                            Phone = accountsReader.nextLine();
                            CCNumber = accountsReader.nextLine();
                            Type = accountsReader.nextLine();
                            main2();
                            return;
                        }
                    }
                }
            }
            System.out.println("Account information was incorrect:");
            accountsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void main2(){
        // Displays welcome message and our menu which will interact with the Menu Interface and all of the subsequent
        // classes

        //Temporarily accesses method without user input. Fix later
        Scanner keyboard = new Scanner(System.in);
        int choice;
        while(true){
            if (Type.equals("regular") || Type.equals("premium")){
                //Consumer
                HandleOrder order = new HandleOrder();
                View view = new View();
                System.out.println("Please choose an option:\n1) Make Order\n2) View Order\n3) View Invoice\n4) Log out");
                choice = keyboard.nextInt();
                switch(choice){
                    case 1:
                        order.SelectItems(Type, CCNumber, ID);
                        if (!order.creditCard.equals(CCNumber)){
                            this.CCNumber = order.creditCard;
                        }
                        break;
                    case 2:
                        view.ViewOrder(ID);
                        break;
                    case 3:
                        view.ViewInvoice(ID, CCNumber);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }else{
                //Supplier
                SupplierClient sup = new SupplierClient();
                System.out.println("Please choose an option:\n1) Process Order\n2) Ship Order\n3) View Stock\n4) Log out");
                choice = keyboard.nextInt();
                switch(choice){
                    case 1:
                        sup.ProcessOrder();
                        break;
                    case 2:
                        sup.ShipOrder();
                        break;
                    case 3:
                        sup.ViewStock();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option");
                        break;
                }
            }
        }
    }
}