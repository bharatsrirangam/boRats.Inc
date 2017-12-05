package com.boratsinc;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;
import com.boratsinc.RegisterUser;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    /*
     * Written by Jarrett Schultz to test addSighting(RatSighting r) et al. in
     * Model class
     */
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
        int size;
        for (int x=0;x<1000;x++) {
            size = model.getSightings().size();
            rat = new RatSighting("" + x, "Address", "01/01/2017", "Location", "11111", "New York", "Queens", "1", "1");
            added = model.addSighting(rat);
            //check if it got added
            if (added) {
                //Last RatSighting should be the last one in the list
                assertEquals(model.getSightings().get(x), rat);
            } else {
                //The size of the List should not have changed
                assertEquals(model.getSightings().size(), size);
            }
        }
    }
    @Test
    /*
     * Written by Ashik Najib to test isUsernameValid in
     * RegisterUser class
     */
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
    @Test
    /*
     * Determines if getItemCount() works correctly
     * Robert Kuramshin
     */
    public void getItemCountTest(){
        List<RatSighting> testList = new ArrayList<>();
        for (int i =0;i<5;i++){
            testList.add(new RatSighting());
        }

        RatSightingsListView listView = new RatSightingsListView();
        RatSightingsListView.MyAdapter adapter = listView.new MyAdapter(testList);

        assertEquals(5,adapter.getItemCount());

        testList.add(new RatSighting());

        assertEquals(6,adapter.getItemCount());

        testList.add(new RatSighting());

        assertEquals(7,adapter.getItemCount());
    }

    @Test
    public void testIsDatesValid() {
        UserView test = new UserView();
        String testFormat1a = "11/dd/1912";
        String testFormat2a = "1112/dd/1912";
        String testFormat3a = "1112/12/1912";
        String testFormat4a = "13/1/1912";
        String testFormat5a = "11/12/191";
        String testFormat6a = "11/45/1912";
        String testFormat7a = "11/13/2016";
        String testFormat8a = "11/12/2016";
        String testFormat9a = "1/2016";
        String testFormat10a = "";
        String testFormat11a = "null";


        String testFormat1b = "12/dd/1912";
        String testFormat2b = "1112/dd/1912";
        String testFormat3b = "1112/12/1912";
        String testFormat4b = "12/1/1912";
        String testFormat5b = "12/12/191";
        String testFormat6b = "12/45/1912";
        String testFormat7b = "11/11/2016";
        String testFormat8b = "12/12/2016";
        String testB = "12/12/2017";

        assertTrue(!test.isValidDates(testFormat1a, testFormat1b));
        assertTrue(!test.isValidDates(testFormat2a, testFormat2b));
        assertTrue(!test.isValidDates(testFormat3a, testFormat3b));
        assertTrue(!test.isValidDates(testFormat4a, testFormat4b));
        assertTrue(!test.isValidDates(testFormat5a, testFormat5b));
        assertTrue(!test.isValidDates(testFormat6a, testFormat6b));
        assertTrue(!test.isValidDates(testFormat7a, testFormat7b));
        assertTrue(test.isValidDates(testFormat8a, testFormat8b));  // the one true test
        assertTrue(!test.isValidDates(testFormat9a, testB));
        assertTrue(!test.isValidDates(testFormat10a, testB));
        assertTrue(!test.isValidDates(testFormat11a, testB));

    }

    @Test
    /*
     * Tests isPasswordValid in RegisterUser class
     * Yalini Senthil Kumar
     */
    public void testIsPasswordValid() {
        //instantiate RegisterUser Class
        RegisterUser aUser = new RegisterUser();
        //ensure a valid password can be used
        assertEquals(true, aUser.isPasswordValid("passsword"));
        //ensure an invalid password that is too short cannot be used
        assertEquals(false, aUser.isPasswordValid("cat"));
    }
}