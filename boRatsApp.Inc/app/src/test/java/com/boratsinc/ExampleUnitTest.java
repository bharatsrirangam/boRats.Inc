package com.boratsinc;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;
import com.boratsinc.RegisterUser;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void addRatCorrectly() {
        //instantiate class
        Model model = Model.getInstance();
        //check RatSightings List
        assertEquals(model.getSightings().get(0).getIncident_address(), "IDLE");
        assertEquals(model.getSightings().size(), 1);
        //check date list
        assertEquals(model.getRangeList().get(0).getIncident_address(), "IDLE");
        assertEquals(model.getRangeList().size(), 1);
        //check current sighting
        assertEquals(model.getCurrentSighting(), null);
        //add one
        RatSighting rat;
        boolean added;
        for (int x=0;x<100;x++) {
            rat = new RatSighting("1", "Address", "01/01/2017", "Location", "11111", "New York", "Queens", "1", "1");
            added = model.addSighting(rat);
            //check if it got added
            if (added) {
                assertEquals(model.getSightings().get(model.getSightings().size() - 1), rat);
            } else {
                //Should not have added anything
                assertEquals(model.getSightings().get(0).getIncident_address(), "IDLE");
                assertEquals(model.getSightings().size(), 1);
            }
        }
    }
    @Test
    public void isUserValidTest() {
        //instantiate RegisterUser Class
        RegisterUser rUser = new RegisterUser();
        //ensure a username that already exists cannot be used
        assertEquals(false, rUser.isUsernameValid("BobWRats"));
        //ensure a valid, unused username can be used
        assertEquals(true, rUser.isUsernameValid("anajib6"));
        //ensure an invalid username that is too short cannot be used
        assertEquals(false, rUser.isUsernameValid("an6"));

    }
}