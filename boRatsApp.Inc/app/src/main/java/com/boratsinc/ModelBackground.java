package com.boratsinc;

import com.boratsinc.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bharat_Srirangam on 10/2/17.
 */

public class ModelBackground {

    private static final ModelBackground _instance = new ModelBackground();
    public static ModelBackground getInstance() { return _instance; }

    List<User> usersAndAdmins;

    private ModelBackground() {
        usersAndAdmins = new ArrayList<User>();
        usersAndAdmins.add(new User("user", "name"));
    }

    public boolean addUser(User user) {
        return usersAndAdmins.add(user);
    }

    public List<User> getUsers() {
        return usersAndAdmins;
    }
}
