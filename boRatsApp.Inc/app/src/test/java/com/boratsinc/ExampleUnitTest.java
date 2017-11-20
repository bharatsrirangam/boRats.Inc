package com.boratsinc;

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
        //check if it says loading
        //add one
        //check if it got added
        //add another for simplicity
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