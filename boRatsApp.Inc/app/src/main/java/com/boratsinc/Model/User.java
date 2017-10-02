package com.boratsinc.Model;

/**
 * Created by baseb on 9/24/2017.
 */

public class User {
    private String password;
    private String email;

    public User(String e, String p) {
        email = e;
        password = p;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {return false;}

}
