package com.boratsinc.Model;

import android.util.Log;

import com.boratsinc.RatSightingsListView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Model {
    private static final Model instance = new Model();
    private List<RatSighting> sightings;
    private List<RatSighting> sightingsTemp;
    private List<RatSighting> rangeList;
    private List<User> userList;
    private final RatSighting nullSighting = new RatSighting("Loading Failed", "Loading Failed", "00/00/000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
    private final RatSighting loadingSighting = new RatSighting("LOADING", "LOADING", "00/00/0000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
    private RatSighting current;
    private FirebaseDatabase fire;
    private DatabaseReference baseRef;
    private DatabaseReference userRef;
    private static String head;
    private RatSightingsListView.MyAdapter adapter;

    private Model() {
        sightings = new ArrayList<>();
        RatSighting temp = new RatSighting("IDLE", "IDLE", "00/00/000", "NULL", "-1", "NULL", "NULL", "-1", "-1");
        sightings.add(temp);

        rangeList = new ArrayList<>();
        rangeList.add(temp);

        getUserList();
        userList.add(new User("BobWRats", "password"));
    }

    /**MUST MANUALLY LOAD THE USERLIST BEFORE CALLING THIS
     *
     * @return UserList
     */
    public List<User> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
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
        sightings = new ArrayList<>();
        sightings.add(new RatSighting("1", "6146", "01/01/1997", "idk", "77069", "New York City", "bronx", "30", "50"));
        sightings.add(new RatSighting("2", "7126", "02/02/2005", "idk2", "30318", "Rochester", "brooklyn", "35", "50"));
        sightings.add(new RatSighting("3", "1983", "03/01/2017", "idk", "54699", "Appleton", "bronx", "40", "50"));
        sightings.add(new RatSighting("4", "4568", "03/03/2017", "idk", "54699", "Appleton", "bronx", "40", "50"));
        current = sightings.get(0);
        Log.d("Load", "Loaded 4 RatSightings.");
    }

// --Commented out by Inspection START (11/16/2017 10:33 PM):
//    public void loadDummyRangeData() {
//        rangeList = new ArrayList<>();
//        rangeList.add(new RatSighting("1", "6146", "1/1/1997", "idk", "77069", "New York City", "bronx", "30", "50"));
//        rangeList.add(new RatSighting("2", "7126", "1/2/2005", "idk2", "30318", "Rochester", "bronasdfax", "35", "50"));
//        rangeList.add(new RatSighting("3", "1983", "1/3/2017", "idk", "54699", "Appleton", "bronx", "40", "50"));
//
//        Log.d("Load", "Dummy Range Data has been loaded.");
//        Log.d("Load", "Element 0 of rangeList: " + rangeList.get(0).getIncident_address());
//    }
// --Commented out by Inspection STOP (11/16/2017 10:33 PM)

    public void loadData() {
        sightings = new ArrayList<>();
        sightings.add(loadingSighting);
        current = sightings.get(0);
        fire = FirebaseDatabase.getInstance();
        baseRef = fire.getReference();
        baseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sightingsTemp = new ArrayList<>();
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
        Log.d("rangeList", "Beginning loading of rangeList");
        start = start + "000000";
        end = end + "000000";
        rangeList = new ArrayList<>();
        rangeList.add(loadingSighting);
        fire = FirebaseDatabase.getInstance();
        DatabaseReference rangeRef = fire.getReference();
        Query query = rangeRef.child("RatSightings").orderByChild("date_num").startAt(start).endAt(end);
        Log.d("rangeList","Completed querying");
        Log.d("rangeListTest", "Size of list: " + rangeList.size());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Data is ordered by increasing height, so we want the first entry
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                int count = 0;
                while(iterator.hasNext()) {
                    rangeList.add(iterator.next().getValue(RatSighting.class));
                    count++;
                    Log.d("rangeList",rangeList.get(rangeList.size()-1).toString());
                }
                if (rangeList.get(0).getIncident_address().equals("LOADING")) {
                    rangeList.remove(0);
                }
                Log.d("rangeList", "I loaded " + count + " items");
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });
    }

    public void loadUserDataBase() {
        Log.d("UserTakeIn","Loading Users");
        userList = new ArrayList<>();
        FirebaseDatabase userFire = FirebaseDatabase.getInstance();
        userRef = userFire.getReference();

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("UserTakeIn", "User data has Changed. ");

                int count = 0;
                try {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.child("users").getChildren()) {
                        userList.add(dataSnapshot1.getValue(User.class));
                        Log.d("UserTakeIn", "User " + count + " loaded");
                        count++;
                    }
                } catch (Exception e) {
                    Log.e("Load", "Could not load Users. ", e);
                    userList.add(new User("user","name"));
                }

                Log.d("Load", "Loaded " + count + " users.");
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
        if (sightings.get(0).getIncident_address().equals("IDLE") ||
                sightings.get(0).getIncident_address().equals("LOADING") ||
                sightings.get(0).getIncident_address().equals("LOADING FAILED")) {
            sightings.remove(0);
        }
        return sightings.add(r);
    }

// --Commented out by Inspection START (11/16/2017 10:33 PM):
//    public RatSighting getRatSighting(int id) {
//
//        if (sightings.size() > id) {
//            return sightings.get(id);
//        }
//        return nullSighting;
//    }
// --Commented out by Inspection STOP (11/16/2017 10:33 PM)

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

// --Commented out by Inspection START (11/16/2017 10:33 PM):
//    public void addRatSighting(RatSighting newAdd, String position) {
//        baseRef.child("RatSightings").child(position).setValue(newAdd);
//    }
// --Commented out by Inspection STOP (11/16/2017 10:33 PM)
}
