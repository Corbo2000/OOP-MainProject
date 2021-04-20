package MainProjectPackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application{

    private Label selectionLabel;

    // For the sake of simplicity, we have only one window and multiple scenes
    // all this needs to do is take in data and put it into is respective classes/methods
    Stage window; // main window
    Scene menuScene, LoginScene, CNAScene; // our initial scene

    Scene CustomerMenuScene, //our menu for the customer
            SelectItemsScene, MakeOrderScene, ViewInvoiceScene, ViewOrderScene; // used by customer
    Scene SupplierMenuScene, // our menu for the supplier
            ProcessOrderScene, ShipOrderScene, ViewStockScene; //used by producer

    // some text fields that we will need for manipulation
    TextField password, username;
        //Create account text fields
        TextField ID, pass, name, address, phone, cc, type;

    // same labels that will need to be manipulated
    Label tryagain;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage){
        // our initial window
        window = primaryStage;

        //Scene: MenuScene
        {
            Label selectionLabel = new Label("Select Login or Create New Account");
            Button loginButton = new Button("Login");
            Button cnaButton = new Button("Create New Account");
            Button exit = new Button("Exit");

            loginButton.setOnAction(new LoginHandler());
            cnaButton.setOnAction(new cnaHandler());
            exit.setOnAction(new exitHandler());

            VBox menu = new VBox(20, selectionLabel, loginButton, cnaButton, exit);
            menu.setAlignment(Pos.CENTER);
            menu.setPadding(new Insets(25));

            menuScene = new Scene(menu);

            // initial set scene
            window.setScene(menuScene);
            window.setTitle("Online Shopping System");
            window.show();
        }
    }

    class LoginHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*
                LogIn login = new LogIn();

                login.log();
                */
                //Label Creation
                Label UsernamePrompt = new Label("Enter your username: ");
                Label PasswordPrompt = new Label("Enter your password: ");
                tryagain = new Label();

                //TextField Creation
                username = new TextField();
                password = new TextField();

                HBox H_user = new HBox(20,UsernamePrompt, username);
                HBox P_user = new HBox(20,PasswordPrompt, password);

                //Button creation
                Button confirmLogin = new Button("Login");
                Button exit = new Button("Exit");

                VBox HP_user = new VBox(20,H_user, P_user, confirmLogin, exit, tryagain);
                HP_user.setAlignment(Pos.CENTER);
                HP_user.setPadding(new Insets(25));

                //Actions
                confirmLogin.setOnAction(new LoginCall());
                exit.setOnAction(new exitHandler());

                LoginScene = new Scene(HP_user);

                window.setScene(LoginScene);
                window.setTitle("Login");
                window.show();
            }
        }
        class cnaHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*
                CreateAccount acc = new CreateAccount();

                acc.accountCreate();
                */
                //Heres a big one
                Label IDPrompt = new Label("Enter a User ID: ");
                Label PassPrompt = new Label("Enter a Password: ");
                Label NamePrompt = new Label("Enter your Name: ");
                Label AddressPrompt = new Label("Enter your Address: ");
                Label PhonePrompt = new Label("Enter your Phone Number (000-000-0000) : ");
                Label CCPrompt = new Label("Enter your Credit Card Number (0000-0000-0000-0000) : ");
                Label TypePrompt = new Label("Please select a premium ($40) or a regular membership or verify that you are a supplier by entering (premium/regular/supplier): ");

                // Button
                Button CA = new Button("Create Account");
                Button exit = new Button("Exit");

                // TextFields, (Defined Globally)
                ID = new TextField();
                pass = new TextField();
                name = new TextField();
                address = new TextField();
                phone = new TextField();
                cc = new TextField();
                type = new TextField();

                // setting up hboxes
                HBox idh = new HBox(20, IDPrompt, ID);
                HBox passh = new HBox (20, PassPrompt, pass);
                HBox nameh = new HBox (20, NamePrompt, name);
                HBox addressh = new HBox(20, AddressPrompt, address);
                HBox phoneh = new HBox (20, PhonePrompt, phone);
                HBox cch = new HBox (20, CCPrompt, cc);
                HBox typeh = new HBox (20, TypePrompt, type);

                VBox cnav = new VBox(20, idh, passh, nameh, addressh, phoneh, cch, typeh, CA, exit);
                cnav.setAlignment(Pos.CENTER);
                cnav.setPadding(new Insets(25));

                //Actions
                exit.setOnAction(new exitHandler());
                //CA.setOnAction();

                CNAScene = new Scene(cnav);

                window.setScene(CNAScene);
                window.setTitle("Create New Account");
                window.show();
            }
        }
        class exitHandler implements EventHandler<ActionEvent>{

            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        }
        class LoginCall implements EventHandler<ActionEvent>{

            @Override
            public void handle(ActionEvent event) {
                String user, pass;

                LogIn login = new LogIn();
                user = username.getText();
                pass = password.getText();

                //tryagain prompt
                tryagain = new Label();

                boolean check = login.log(user, pass);

                String type = login.Type;

                //check is for if the username or password is correct
                if(check == false){
                    //this prompt does not show and needs to be worked on, however the program does not error if info is incorrect
                    tryagain.setText("Username or Password incorrect");
                }else if (check){   //this is just an error check, it is completely redundant otherwise
                    if (login.Type.equals("supplier")){
                        producerScene();
                    }else{
                        customerScene();
                    }
                }
            }

            // Scene: ProducerMenuScene
            public void producerScene(){
                Button LogOut = new Button("Logout");
                Button ProcessOrder = new Button("Process Order");
                Button ShipOrder = new Button("Ship Order");
                Button ViewStock = new Button("View Stock");

                //THIS IS A TEMPORARY MENU LABEL NOT INTENDED TO BE A PART OF THE FINAL PROJECT
                Label templabel = new Label("These Buttons do not work, need to bind to classes");
                Label templabel2 = new Label("Classes will all have references to Login, where our info is located");

                VBox vbox = new VBox(20,LogOut, ProcessOrder, ShipOrder, ViewStock, templabel, templabel2);
                vbox.setPadding(new Insets(25));
                vbox.setAlignment(Pos.CENTER);

                //THIS IS WHERE OUR ACTIONS GO, LOOK AT LoginCall TO GET AN IDEA OF HOW THIS IS USED
                LogOut.setOnAction(new exitHandler());
                //ProcessOrder.setOnAction();
                //ShipOrder.setOnAction();
                //ViewStock.setOnAction();

                SupplierMenuScene = new Scene(vbox);

                window.setScene(SupplierMenuScene);
                window.setTitle("Supplier Menu Scene");
                window.show();
            }

            //Scene: Customer Scene
            public void customerScene(){
                Button LogOut = new Button("LogOut");
                Button SelectItems = new Button("Select Items");
                Button MakeOrder = new Button("Make Order");
                Button ViewInvoice = new Button("View Invoice");
                Button ViewOrder = new Button("View Order");

                //THIS IS A TEMPORARY MENU LABEL NOT INTENDED TO BE A PART OF THE FINAL PROJECT
                Label templabel = new Label("These Buttons do not work, need to bind to classes");
                Label templabel2 = new Label("Classes will all have references to Login, where our info is located");

                VBox vbox = new VBox(20, SelectItems, MakeOrder, ViewInvoice, ViewOrder, LogOut, templabel, templabel2);
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(25));

                //Action list
                LogOut.setOnAction(new exitHandler());
                //SelectItems.setOnAction();
                //MakeOrder.setOnAction();
                //ViewInvoice.setOnAction();
                //ViewOrder.setOnAction();

                CustomerMenuScene = new Scene(vbox);

                window.setScene(CustomerMenuScene);
                window.setTitle("Customer Menu");
                window.show();
            }
        }
        class CNACall implements EventHandler<ActionEvent>{

            @Override
            public void handle(ActionEvent event) {
                // this is where all of the data gets passed to the CreateAccount class
            }
        }
    }