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
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    /*
     * Written by Jarrett Schultz to test addSighting(RatSighting r) in
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
}