package com.boratsinc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.boratsinc.Model.Model;

public class RatSightingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_detail);
        TextView details = (TextView) findViewById(R.id.Details);
        details.setText(Model.getInstance().getCurrentSighting().toString());
    }
}
