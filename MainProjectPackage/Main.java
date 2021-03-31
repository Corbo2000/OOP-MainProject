package MainProjectPackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int choice;
        Scanner keyboardInput = new Scanner(System.in);
        CreateAccount acc = new CreateAccount();
	// changes to update repository
        //add somethingS
        //Hey, it's me
        //Hola papi
        Menu test = new testClass();
        test.testMethod();
        System.out.println("Please select an option: \n1) Log in\n2)Create Account");
        choice = keyboardInput.nextInt();
        switch(choice){
            case 1:
                System.out.println("You will log in here...");
                break;
            case 2:
                System.out.println("Directing you to account creation...");
                acc.accountCreate();
                break;
        }

    }
}
