package org.example.users;

public class UserController {
    UserService userService;

    public UserController() {
        userService = new UserService();
    }

    public UserDO addUser(UserDO userDOObj) {
        return userService.addUser(userDOObj);
    }

    public UserDO getUser(int userID) {
        return userService.getUser(userID);
    }
}
