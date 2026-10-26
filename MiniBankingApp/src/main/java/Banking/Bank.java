package Banking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bank {
    public static void main(String[] args) {

        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        String name;
        int pass_code;
        int ch;

        while (true) {
            System.out.println("\n===============================");
            System.out.println(" Welcome to InBank ");
            System.out.println("===============================");
            System.out.println("1) Create Account");
            System.out.println("2) Login Account");
            System.out.println("3) Exit");

            try {
                System.out.print("\nEnter Choice: ");
                ch = Integer.parseInt(sc.readLine());

                switch (ch) {
                    case 1 -> {
                        System.out.print("Enter Unique Username: ");
                        name = sc.readLine();
                        System.out.print("Enter Password: ");
                        pass_code = Integer.parseInt(sc.readLine());

                        if (BankManagement.createAccount(name, pass_code)) {
                            System.out.println("You can now login from the main menu.");
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter Username: ");
                        name = sc.readLine();
                        System.out.print("Enter Password: ");
                        pass_code = Integer.parseInt(sc.readLine());

                        if (!BankManagement.loginAccount(name, pass_code)) {
                            System.out.println("Login failed. Try again.");
                        }
                    }
                    case 3 -> {
                        System.out.println("Thank you for using InBank! Goodbye.");
                        System.exit(0);
                    }
                    default -> System.out.println("Invalid input! Please try again.");
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid input!");
            }
        }
    }
}
