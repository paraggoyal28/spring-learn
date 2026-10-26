package Banking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class BankManagement {

    private static final int NULL = 0;
    static Connection con
            = DatabaseConnection.getConnection();
    // Create Account
    public static boolean createAccount(String name,
                                        int passCode)
    {
        if (name.isEmpty() || passCode == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            String sql
                    = "INSERT INTO customer(cname, balance, pass_code) VALUES (?, 1000, ?)";
            PreparedStatement ps
                    = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, passCode);

            int rows = ps.executeUpdate();
            if (rows == 1) {
                System.out.println(
                        "Account created successfully! You can now login.");
                return true;
            }
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(
                    "Username already exists! Try another one.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login Account
    public static boolean loginAccount(String name,
                                       int passCode)
    {
        if (name.isEmpty() || passCode == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            String sql
                    = "SELECT * FROM customer WHERE cname = ? AND pass_code = ?";
            PreparedStatement ps
                    = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, passCode);
            ResultSet rs = ps.executeQuery();

            BufferedReader sc = new BufferedReader(
                    new InputStreamReader(System.in));

            if (rs.next()) {
                int senderAc = rs.getInt("ac_no");
                int ch;

                while (true) {
                    System.out.println(
                            "\n Hello, " + rs.getString("cname")
                                    + "! What would you like to do?");
                    System.out.println("1) Transfer Money");
                    System.out.println("2) View Balance");
                    System.out.println("3) Logout");
                    System.out.print("Enter Choice: ");
                    ch = Integer.parseInt(sc.readLine());

                    if (ch == 1) {
                        System.out.print(
                                "Enter Receiver A/c No: ");
                        int receiverAc = Integer.parseInt(
                                sc.readLine());
                        System.out.print("Enter Amount: ");
                        int amt = Integer.parseInt(
                                sc.readLine());

                        if (transferMoney(senderAc,
                                receiverAc,
                                amt)) {
                            System.out.println(
                                    "Transaction successful!");
                        }
                        else {
                            System.out.println(
                                    "Transaction failed! Please try again.");
                        }
                    }
                    else if (ch == 2) {
                        getBalance(senderAc);
                    }
                    else if (ch == 3) {
                        System.out.println(
                                "Logged out successfully. Returning to main menu.");
                        break;
                    }
                    else {
                        System.out.println(
                                "Invalid choice! Try again.");
                    }
                }
                return true;
            }
            else {
                System.out.println(
                        "Invalid username or password!");
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get Balance
    public static void getBalance(int acNo)
    {
        try {
            String sql
                    = "SELECT * FROM customer WHERE ac_no = ?";
            PreparedStatement ps
                    = con.prepareStatement(sql);
            ps.setInt(1, acNo);
            ResultSet rs = ps.executeQuery();

            System.out.println(
                    "\n-------------------------------------------------");
            System.out.printf("%12s %15s %10s\n",
                    "Account No", "Customer Name",
                    "Balance");

            while (rs.next()) {
                System.out.printf("%12d %15s %10d.00\n",
                        rs.getInt("ac_no"),
                        rs.getString("cname"),
                        rs.getInt("balance"));
            }
            System.out.println(
                    "-------------------------------------------------");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Transfer Money
    public static boolean transferMoney(int sender_ac,
                                        int receiver_ac,
                                        int amount)
    {
        if (receiver_ac == NULL || amount == NULL) {
            System.out.println("All fields are required!");
            return false;
        }

        try {
            con.setAutoCommit(false);

            String checkBalance
                    = "SELECT balance FROM customer WHERE ac_no = ?";
            PreparedStatement ps
                    = con.prepareStatement(checkBalance);
            ps.setInt(1, sender_ac);
            ResultSet rs = ps.executeQuery();

            if (rs.next()
                    && rs.getInt("balance") < amount) {
                System.out.println("Insufficient Balance!");
                return false;
            }

            String debit
                    = "UPDATE customer SET balance = balance - ? WHERE ac_no = ?";
            PreparedStatement psDebit
                    = con.prepareStatement(debit);
            psDebit.setInt(1, amount);
            psDebit.setInt(2, sender_ac);
            psDebit.executeUpdate();

            String credit
                    = "UPDATE customer SET balance = balance + ? WHERE ac_no = ?";
            PreparedStatement psCredit
                    = con.prepareStatement(credit);
            psCredit.setInt(1, amount);
            psCredit.setInt(2, receiver_ac);
            psCredit.executeUpdate();

            con.commit();
            return true;
        }
        catch (Exception e) {
            try {
                con.rollback();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        return false;
    }
}