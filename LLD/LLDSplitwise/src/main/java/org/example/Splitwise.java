package org.example;

import org.example.Expense.ExpenseSplitType;
import org.example.Expense.Split.Split;
import org.example.Group.Group;
import org.example.Group.GroupController;
import org.example.User.User;
import org.example.User.UserController;

import java.util.ArrayList;
import java.util.List;

public class Splitwise {
    UserController userController;
    GroupController groupController;

    BalanceSheetController balanceSheetController;

    public Splitwise() {
        userController = new UserController();
        groupController = new GroupController();
        balanceSheetController = new BalanceSheetController();
    }

    public void demo() {

        setUpUserAndGroup();
        
        //Step1: add members to the group
        Group group = groupController.getGroup("G1001");
        group.addMember(userController.getUser("U1002"));
        group.addMember(userController.getUser("U1003"));

        //Step2: create an expense inside a group
        List<Split> splits = new ArrayList<>();
        Split split1 = new Split(userController.getUser("U1001"), 300);
        Split split2 = new Split(userController.getUser("U1002"), 300);
        Split split3 = new Split(userController.getUser("U1003"), 300);
        splits.add(split1);
        splits.add(split2);
        splits.add(split3);
        group.createExpense("Exp1001", "Breakfast", 900, splits, ExpenseSplitType.EQUAL, userController.getUser("U1001"));

        //Step3: new expense
        List<Split> otherSplits = new ArrayList<>();
        Split otherSplit1 = new Split(userController.getUser("U1001"), 400);
        Split otherSplit2 = new Split(userController.getUser("U1003"), 100);
        otherSplits.add(otherSplit1);
        otherSplits.add(otherSplit2);
        group.createExpense("Exp1002", "Lunch", 500, otherSplits, ExpenseSplitType.UNEQUAL, userController.getUser("U1003"));

        for(User user: userController.getAllUsers()) {
            balanceSheetController.showBalanceSheetOfUser(user);
        }
    }

    private void setUpUserAndGroup() {

        // onboard users to splitwise app
        addUsersToSplitwiseApp();

        // create a group by user1
        User user1 = userController.getUser("U1001");
        groupController.createNewGroup("G1001", "Outing with Friends", user1);
    }

    private void addUsersToSplitwiseApp() {

        // adding user1
        User user1 = new User("U1001", "User1");

        // adding user2
        User user2 = new User("U1002", "User2");

        // adding user3
        User user3 = new User("U1003", "User 3");

        userController.addUser(user1);
        userController.addUser(user2);
        userController.addUser(user3);
    }


}
