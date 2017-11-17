package com.boratsinc;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.User;


public class ModelBackground {

    private static final ModelBackground _instance = new ModelBackground();
    public static ModelBackground getInstance() { return _instance; }
//    private static List<User> usersAndAdmins;


//    private ModelBackground() {
//        usersAndAdmins = Model.getInstance().getUserList();
//    }

    public void addUser(User user) {

        Model.getInstance().addUser(user);
    }

// --Commented out by Inspection START (11/16/2017 10:33 PM):
//    public List<User> getUsers() {
//        return usersAndAdmins;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:33 PM)
}
