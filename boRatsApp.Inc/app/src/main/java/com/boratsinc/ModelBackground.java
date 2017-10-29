package com.boratsinc;

import android.util.Log;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bharat_Srirangam on 10/2/17.
 */

public class ModelBackground {

    private static final ModelBackground _instance = new ModelBackground();
    public static ModelBackground getInstance() { return _instance; }
    private static List<User> usersAndAdmins;


    private ModelBackground() {
        usersAndAdmins = Model.getInstance().getUserList();
    }

    public boolean addUser(User user) {

        Model.getInstance().addUser(user);
        return usersAndAdmins.add(user);
    }

    public List<User> getUsers() {
        return usersAndAdmins;
    }
}
