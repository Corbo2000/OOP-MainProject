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

    public static void main(String[] args) {
        launch(args);
        /*int choice;
        String userInfo;
        Scanner keyboardInput = new Scanner(System.in);
        CreateAccount acc = new CreateAccount();
        LogIn login = new LogIn();
            System.out.println("Please select an option: \n1) Log in\n2) Create Account");
            choice = keyboardInput.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("You will log in here...");
                    login.log();
                    break;
                case 2:
                    System.out.println("Directing you to account creation...");
                    acc.accountCreate();
                    break;
            }*/
        }
        @Override
        public void start(Stage primaryStage){
            Label selectionLabel = new Label("Select Login or Create New Account");
            Button loginButton = new Button("Login");
            Button cnaButton = new Button("Create New Account");

            loginButton.setOnAction(new LoginHandler());
            cnaButton.setOnAction(new cnaHandler());

            VBox menu = new VBox(20, selectionLabel, loginButton, cnaButton);
            menu.setAlignment(Pos.CENTER);
            menu.setPadding(new Insets(25));

            Scene menuScene = new Scene(menu);
            primaryStage.setScene(menuScene);
            primaryStage.setTitle("Menu");
            primaryStage.show();
        }

        class LoginHandler implements EventHandler<ActionEvent> {
            @Override
            public void handle(ActionEvent actionEvent) {

                LogIn login = new LogIn();

                login.log();

            }
        }

    class cnaHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {

            CreateAccount acc = new CreateAccount();

            acc.accountCreate();

        }
    }



    }
