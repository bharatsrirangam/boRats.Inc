package com.boratsinc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;

public class UserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        //Begin loading Data upon successful login
        Model.getInstance().loadDummyData();

        findViewById(R.id.btn_view_rat_sightings_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewRatSightings(view);
            }
        });
        findViewById(R.id.addbtn_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ViewAddRatSighting(view);
            }
        });
        findViewById(R.id.btn_view_map_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewMap(view);
            }
        } );
        findViewById(R.id.logoutbtn_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout(view);
            }
        });
    }

    private void ViewRatSightings(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, RatSightingsListView.class);
        startActivity(intent);
    }

    private void ViewAddRatSighting(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, AddRatSighting.class);
        startActivity(intent);
    }

    private void ViewMap(View view) {
            String currentRatAddress = Model.getInstance().getCurrentSighting().getIncident_address();

            if (currentRatAddress.equals("LOADING") || currentRatAddress.equals("IDLE")) {
                //Display loading screen
            } else if (currentRatAddress.equals("Loading Failed")) {
                //Show a pop-up with load error
            } else {
                //The RatSightings have been loaded, so launch the map as usual
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(view.getContext(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(view.getContext());
                }
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                builder.setTitle("Please enter date range")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //TODO: Bharat's thing here
                                viewMapScreen();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setView(inflater.inflate(R.layout.activity_get_date_range, null))
                        .show();
            }
    }

    private void viewMapScreen() {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void Logout(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, WelcomeScreen.class);
        startActivity(intent);
    }



}
