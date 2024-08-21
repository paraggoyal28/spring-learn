package org.example.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserService {
    static List<User> usersList = new ArrayList<>();

    public UserDO addUser(UserDO userDO) {
        // some validations and create User obj

        User userObj = new User();
        userObj.setUserName(userDO.getName());
        userObj.setEmail(userDO.getMail());
        userObj.setUserID((int) new Random().nextInt(90) + 10);
        usersList.add(userObj);
        return convertUserDOToUser(userObj);
    }

    public UserDO getUser(int userID) {
        for(User user: usersList) {
            if(user.getUserID() == userID) {
                return convertUserDOToUser(user);
            }
        }
        return null;
    }

    private UserDO convertUserDOToUser(User userObj) {
        UserDO userDO = new UserDO();
        userDO.setName(userObj.getUserName());
        userDO.setMail(userObj.getEmail());
        userDO.setUserID(userObj.getUserID());
        return userDO;
    }
}
