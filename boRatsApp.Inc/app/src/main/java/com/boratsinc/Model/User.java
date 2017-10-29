package com.boratsinc.Model;

/**
 * Created by baseb on 9/24/2017.
 */

public class User {
    private String password;
    private String name;

    public User(){

    }

    public User(String e, String p) {
        name = e;
        password = p;
    }
    public String getName() {
        return name;
    }

    public void setName(String email) {
        this.name = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {return false;}

}
