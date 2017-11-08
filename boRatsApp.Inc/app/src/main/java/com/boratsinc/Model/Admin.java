package com.boratsinc.Model;

/**
 * Created by Bharat_Srirangam on 10/2/17.
 */

public class Admin extends User {
    public Admin(String e, String p) {
        super(e,p);
    }

    @Override
    public boolean isAdmin() {return true;}

}
