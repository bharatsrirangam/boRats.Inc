package com.boratsinc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;

public class RatSightingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rat_sighting_detail);
        TextView details = (TextView) findViewById(R.id.Details);
        RatSighting cur = Model.getInstance().getCurrentSighting();
        details.append("Incident Address: " + cur.getIncident_address() + "\n");
        details.append("Date Created: " + cur.getCreated() + "\n");
        details.append("Location Type: " + cur.getLocation_type() + "\n");
        details.append("Zip Code: " + cur.getIncident_zip() + "\n");
        details.append("City: " + cur.getCity() + "\n");
        details.append("Borough: " + cur.getBorough() + "\n");
        details.append("Latitude: " + cur.getLat() + "\n");
        details.append("Longitude: " + cur.getLon() + "\n");
    }
}
