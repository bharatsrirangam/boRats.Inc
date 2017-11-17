package com.boratsinc;

/**
 * Created by Najib on 11/13/2017.
 */

import org.junit.Test;
import junit.framework.TestCase;

public class isValidPasswordTest extends TestCase{

    RegisterUser User = new RegisterUser();
    String valid;
    String validCopy;
    String invalid;
    String invalidCopy;

    public void setUp() throws Exception{
        valid = "abcde";
        validCopy = new String(valid);
        invalid = "abc";
        invalidCopy = new String(invalid);
    }

    @Test
    public void testValid() throws Exception{
        boolean validBool = User.isPasswordValid(valid);
        assertTrue(validBool);
    }

    @Test
    public void testInvalid() throws Exception{
        boolean invalidBool = User.isPasswordValid(invalid);
        assertFalse(invalidBool);
    }

    @Test
    public void testUnchanged() {
        assertEquals(valid, validCopy);
        assertEquals(invalid, invalidCopy);
    }

}
