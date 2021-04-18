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
    Stage window;
    Scene menuScene, LoginScene, CNAScene, SelectItemsScene, MakeOrderScene, ViewInvoiceScene, ViewOrderScene; // used by customer
    Scene ProcessOrderScene, ShipOrderScene, ViewStockScene;

    int i = 0;
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

                //TextField Creation
                TextField username = new TextField();
                TextField password = new TextField();

                HBox H_user = new HBox(20,UsernamePrompt, username);
                HBox P_user = new HBox(20,PasswordPrompt, password);

                //Button creation
                Button confirmLogin = new Button("Login");
                Button exit = new Button("Exit");

                VBox HP_user = new VBox(20,H_user, P_user, confirmLogin, exit);
                HP_user.setAlignment(Pos.CENTER);
                HP_user.setPadding(new Insets(25));

                //Actions
                confirmLogin.setOnAction(new LoginHandler());

                LoginScene = new Scene(HP_user);

                window.setScene(LoginScene);
                window.setTitle("Login");
                window.show();
            }
        }

    class cnaHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {

            CreateAccount acc = new CreateAccount();

            acc.accountCreate();

        }
    }
    class exitHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
            System.exit(0);
        }
    }



    }
