package com.boratsinc.Model;

import android.util.Log;
import android.view.ViewGroup;

import com.boratsinc.R;
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
    private final RatSighting nullSighting = new RatSighting("NULL", "00/00/000", "NULL", "-1", "NULL", "null", "-1", "-1");
    private final RatSighting loadingSighting = new RatSighting("LOADING", "00/00/0000", "NULL", "-1", "NULL", "null", "-1", "-1");
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
        sightings.add(new RatSighting("1", "1/1/2017", "idk", "77069", "New York City", "bronx", "30", "50"));
        sightings.add(new RatSighting("2", "1/2/2017", "idk2", "30318", "New York Cityd", "bronasdfax", "3d0", "5sd0"));
        sightings.add(new RatSighting("1", "1/3/2017", "idk", "77069", "New York City", "bronx", "30", "50"));
        current = sightings.get(0);
    }

    private void loadData() {
        sightings.add(loadingSighting);
        fire = FirebaseDatabase.getInstance();
        baseRef = fire.getReference();
        baseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
<<<<<<< HEAD
                    int number = 36907530;
                    for (int x=0;x<20;x++) {
                        sightingsTemp.add(dataSnapshot.child("RatSightings").child("36907530").getValue(RatSighting.class));
                        number++;
                    }
=======
                    RatSighting rat = dataSnapshot.child("RatSightings").child("36907529").getValue(RatSighting.class);
                    sightingsTemp.add(rat);
                    current = sightings.get(0);
                    
                    /**
                    for (DataSnapshot ds: dataSnapshot.child("RatSightings").getChildren()) {
                        RatSighting rat = ds.getValue(RatSighting.class);
                        sightingsTemp.add(rat);
                    }
                    current = sightingsTemp.get(0);
                    **/
>>>>>>> e6d68ab716614d724578bb67d073a2503472311e
                } catch (Exception e) {
                    sightingsTemp.add(nullSighting);
                }
                sightings = sightingsTemp;
                current = sightings.get(0);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                sightingsTemp.add(nullSighting);
                sightings = sightingsTemp;
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
