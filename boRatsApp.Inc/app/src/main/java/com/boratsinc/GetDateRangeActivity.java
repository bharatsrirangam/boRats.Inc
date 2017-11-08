package com.boratsinc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GetDateRangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_date_range);
    }

    public boolean datesAreValid(String s, String e) {
        //TODO: Make this valid
        if (!(s.equals("") || e.equals(""))) {
            return true;
        }
        return false;
    }
}
