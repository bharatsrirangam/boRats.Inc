package com.boratsinc.Model;

import android.util.Log;
import android.view.ViewGroup;

import com.boratsinc.R;
import com.boratsinc.RatSightingsListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
    private RatSightingsListView.MyAdapter adapter;

    private Model() {
        sightings = new ArrayList<RatSighting>();

        loadData();
    }

    public static Model getInstance() {
        return instance;
    }

    public void setAdapter(RatSightingsListView.MyAdapter a) {
        adapter = a;
    }

    private void loadDummyData() {
        sightings.add(new RatSighting("1", "1/1/2017", "idk", "77069", "New York City", "bronx", "30", "50"));
        sightings.add(new RatSighting("2", "1/2/2017", "idk2", "30318", "New York Cityd", "bronasdfax", "3d0", "5sd0"));
        sightings.add(new RatSighting("1", "1/3/2017", "idk", "77069", "New York City", "bronx", "30", "50"));
        current = sightings.get(0);
    }

    private void loadData() {
        sightings.add(loadingSighting);
        current = sightings.get(0);
        fire = FirebaseDatabase.getInstance();
        baseRef = fire.getReference();
        baseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sightingsTemp = new ArrayList<RatSighting>();
                int count = 0;
                try {
                    Iterator<DataSnapshot> thing = dataSnapshot.child("RatSightings").getChildren().iterator();
                    while (thing.hasNext() && count < 20) {
                        sightingsTemp.add(thing.next().getValue(RatSighting.class));
                        count++;
                    }
                } catch (Exception e) {
                    sightingsTemp.add(nullSighting);
                    Log.v("FAILURE", "Could not load Ratsightings.");
                }
                sightings = sightingsTemp;
                current = sightings.get(0);
                if (adapter != null) {
                    adapter.updateList(sightings);
                    Log.v("Load", "Data Set has been notified as changed.");
                }
                Log.d("Load", "Loaded " + count + " RatSightings.");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                sightingsTemp.add(nullSighting);
                sightings = sightingsTemp;
                current = sightings.get(0);
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
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
