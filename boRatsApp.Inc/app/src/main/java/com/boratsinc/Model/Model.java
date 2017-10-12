package com.boratsinc.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by baseb on 10/10/2017.
 */

public class Model {
    private static final Model instance = new Model();
    List<RatSighting> sightings;
    List<RatSighting> sightingsTemp;
    private final RatSighting nullSighting = new RatSighting("-1", "new Date()", "NULL", "-1", "NULL", "null", "-1", "-1");
    private RatSighting current;
    private FirebaseDatabase fire;
    private DatabaseReference baseRef;

    private Model() {
        sightings = new ArrayList<RatSighting>();
        sightingsTemp = new ArrayList<RatSighting>();

        loadData();
    }

    public static Model getInstance() {
        return instance;
    }

    private void loadDummyData() {
        sightings.add(new RatSighting("1", "new Date(2017, 1, 1)", "idk", "77069", "New York City", "bronx", "30", "50"));
        sightings.add(new RatSighting("2", "new Date(2017, 1, 2)", "idk2", "30318", "New York Cityd", "bronasdfax", "3d0", "5sd0"));
        sightings.add(new RatSighting("1", "new Date(2017, 1, 1)", "idk", "77069", "New York City", "bronx", "30", "50"));
        current = sightings.get(0);
    }

    private void loadData() {
        sightings.add(nullSighting);
        fire = FirebaseDatabase.getInstance();
        baseRef = fire.getReference();
        baseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    RatSighting rat = dataSnapshot.child("RatSightings").child("36907529").getValue(RatSighting.class);
                    sightingsTemp.add(rat);
                    current = sightings.get(0);
                } catch (Exception e) {
                    current = sightings.get(0);
                }
                sightings = sightingsTemp;
                current = sightings.get(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                current = sightings.get(0);
            }
        });
    }

    public List<RatSighting> getSightings() {
        return sightings;
    }

    public boolean addSighting(RatSighting r) {
        return sightings.add(r);
    }

    public RatSighting getRatSighting(int id) {
//        for (RatSighting r: sightings) {
//            if (r.getKey() == id) {
//                return r;
//            }
//        }

        if (sightings.size() > id) {
            return sightings.get(id);
        }
        return nullSighting;
    }

    public RatSighting getCurrentSighting() {
        return current;
    }
    public void setCurrentSighting(RatSighting c) {
        current = c;
    }
}
