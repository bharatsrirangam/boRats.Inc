package com.boratsinc.Model;

import android.util.Log;
import android.view.ViewGroup;

import com.boratsinc.MapsActivity;
import com.boratsinc.R;
import com.boratsinc.RatSightingsListView;
import com.boratsinc.UserView;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.PrintWriter;
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
    List<RatSighting> rangeList;
    boolean wait;
    private List<User> userList;
    private final RatSighting nullSighting = new RatSighting("Loading Failed", "Loading Failed", "00/00/000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
    private final RatSighting loadingSighting = new RatSighting("LOADING", "LOADING", "00/00/0000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
    private RatSighting current;
    private FirebaseDatabase fire;
    private FirebaseDatabase userFire;
    private DatabaseReference baseRef;
    private DatabaseReference rangeRef;
    private DatabaseReference userRef;
    private static String head;
    private RatSightingsListView.MyAdapter adapter;

    private Model() {
        sightings = new ArrayList<RatSighting>();
        RatSighting temp = new RatSighting("IDLE", "IDLE", "00/00/000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
        sightings.add(temp);

        rangeList = new ArrayList<RatSighting>();
        rangeList.add(temp);
    }

    public List<User> getUserList() {
        if (userList == null) {
            loadUserData();
        }
        return userList;
    }

    public List<RatSighting> getRangeList() {
        return rangeList;
    }

    public static String getHead() {return head;}

    public static Model getInstance() {
        return instance;
    }

    public void setAdapter(RatSightingsListView.MyAdapter a) {
        adapter = a;
    }

    public void loadDummyData() {
        sightings.add(new RatSighting("1", "6146", "1/1/1997", "idk", "77069", "New York City", "bronx", "30", "50"));
        sightings.add(new RatSighting("2", "7126", "1/2/2005", "idk2", "30318", "Rochester", "bronasdfax", "35", "50"));
        sightings.add(new RatSighting("3", "1983", "1/3/2017", "idk", "54699", "Appleton", "bronx", "40", "50"));
        current = sightings.get(0);
    }

    public void loadDummyRangeData() {
        rangeList = new ArrayList<>();
        rangeList.add(new RatSighting("1", "6146", "1/1/1997", "idk", "77069", "New York City", "bronx", "30", "50"));
        rangeList.add(new RatSighting("2", "7126", "1/2/2005", "idk2", "30318", "Rochester", "bronasdfax", "35", "50"));
        rangeList.add(new RatSighting("3", "1983", "1/3/2017", "idk", "54699", "Appleton", "bronx", "40", "50"));
    }

    public void loadData() {
        sightings = new ArrayList<>();
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
                    if (head == null) {
                        head = (String) dataSnapshot.child("head").getValue();
                    }
                    Iterator<DataSnapshot> thing = dataSnapshot.child("RatSightings").getChildren().iterator();
                    while (thing.hasNext() && count < 20) {
                        sightingsTemp.add(thing.next().getValue(RatSighting.class));
                        count++;
                    }
                } catch (Exception e) {
                    sightingsTemp.add(nullSighting);
                    Log.e("Load", "Could not load RatSightings. ", e);
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
                Log.e("Load", "RatSightings load was cancelled.");
            }
        });
    }

    public void loadDateRangeData(String start, String end) {
        wait = true;
        start = start + "000000";
        end = end + "000000";
        rangeList = new ArrayList<>();
        rangeList.add(loadingSighting);
        fire = FirebaseDatabase.getInstance();
        rangeRef = fire.getReference();
        Query query = rangeRef.child("RatSightings").orderByChild("date_num").startAt(start).endAt(end);
        Log.d("RANGE","MADE IT");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Data is ordered by increasing height, so we want the first entry
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    rangeList.add(iterator.next().getValue(RatSighting.class));
                    Log.d("rangeList",rangeList.get(rangeList.size()-1).toString());
                }
                wait = false;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public void loadUserData() {
        if (userList != null) {
            return;
        }
        if (userList == null) {
            userList = new ArrayList<>();
        }

        //userList.add(new User("user","name"));
        userFire = FirebaseDatabase.getInstance();
        userRef = userFire.getReference();
        Log.d("UserTakeIn","HELPPPPPPPPP--");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("UserTakeIn","HELPPPPPPPPP1");

                int count = 0;
                try {
                    Iterator<DataSnapshot> thing = dataSnapshot.child("users").getChildren().iterator();
                    while (thing.hasNext()) {
                        userList.add(thing.next().getValue(User.class));
                        Log.d("UserTakeIn","HELPPPPPPPPP2");
                        count++;
                    }
                } catch (Exception e) {
                    Log.e("Load", "Could not load Users. ", e);
                    userList.add(new User("user","name"));
                }

                Log.d("Load", "Loaded " + count + " UserList.");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                userList.add(new User("user","name"));
                Log.e("Load", "UserList load was cancelled.");
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

    public void addRatSighting(RatSighting newAdd) {
        Integer headInt = Integer.parseInt(head);
        headInt = headInt + 1;
        baseRef.child("RatSightings").child(Integer.toString(headInt)).setValue(newAdd);
        baseRef.child("head").setValue(Integer.toString(headInt));
    }

    public void addUser(User newAdd) {
        userRef.child("users").child(newAdd.getName()).setValue(newAdd);
    }

    public void addRatSighting(RatSighting newAdd, String position) {
        baseRef.child("RatSightings").child(position).setValue(newAdd);
    }
}
