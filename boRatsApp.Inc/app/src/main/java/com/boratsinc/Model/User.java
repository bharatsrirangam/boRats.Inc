package com.boratsinc.Model;



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

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setName(String email) {
//        this.name = email;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    public String getPassword() {
        return password;
    }

// --Commented out by Inspection START (11/16/2017 10:30 PM):
//    public void setPassword(String password) {
//        this.password = password;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:30 PM)

    // --Commented out by Inspection (11/16/2017 10:30 PM):public boolean isAdmin() {return false;}

}
