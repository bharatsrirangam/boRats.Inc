package com.boratsinc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.boratsinc.Model.RatSighting;

public class AddRatSighting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rat_sighting);

        final Spinner boroughSpinner = (Spinner) findViewById(R.id.BoroughSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, RatSighting.legalBoroughs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(adapter);

        findViewById(R.id.addbtn_add_rat_sighting).setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View v) {
                String address = ((EditText) findViewById(R.id.Address)).getText().toString();
                String created = ((EditText) findViewById(R.id.Date)).getText().toString();;
                String location_type = ((EditText) findViewById(R.id.LocationType)).getText().toString();
                String incident_zip = ((EditText) findViewById(R.id.ZipCode)).getText().toString();
                String city = ((EditText) findViewById(R.id.City)).getText().toString();
                String borough = boroughSpinner.getSelectedItem().toString();
                String lat = ((EditText) findViewById(R.id.Latitude)).getText().toString();
                String lon = ((EditText) findViewById(R.id.Longitude)).getText().toString();
                RatSighting rat = new RatSighting(address, created, location_type, incident_zip, city, borough, lat, lon);
                //Bharat does something here
                finish();
            }
        });

        findViewById(R.id.cancelbtn_add_rat_sighting).setOnClickListener(new View.OnClickListener()    {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
