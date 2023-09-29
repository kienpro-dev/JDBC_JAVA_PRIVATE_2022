package main;

import java.sql.SQLException;
import java.util.Scanner;

import static main.MainAccount.changePassword;
import static main.MainAccount.forgotPassword;
import static main.MainUser.*;

public class RunMain {
    public static void main(String[] args) throws SQLException {
        int choose;

        do {
            System.out.println("*----- WELCOME TO FITNESS CENTER ----*");
            System.out.println("|   	1. Login                     |");
            System.out.println("|   	2. Registration              |");
            System.out.println("|   	3. Change password           |");
            System.out.println("|   	4. Forgot password           |");
            System.out.println("|   	5. Exit                      |");
            System.out.println("*------------------------------------*");
            System.out.print(" Enter your choose : ");
            choose = new Scanner(System.in).nextInt();

            switch (choose) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    changePassword();
                    break;
                case 4:
                	forgotPassword();
                    break;
                case 5:
                	System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Option is not valid, try again");
            }
        } while (choose != 5);
    }
}
