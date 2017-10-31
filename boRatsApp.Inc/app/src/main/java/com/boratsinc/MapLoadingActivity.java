package com.boratsinc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;

import java.util.List;

public class MapLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_loading);

        List<RatSighting> sightings = Model.getInstance().getRangeList();

        while(sightings.get(0).getIncident_address().equals("LOADING")) {
            try{
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                Log.d("Load", "Could not continue checking the range list to see if it has loaded.");
            }
            sightings = Model.getInstance().getRangeList();
        }

        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
