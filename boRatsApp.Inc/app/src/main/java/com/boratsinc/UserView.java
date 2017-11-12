package com.boratsinc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        //Begin loading Data upon successful login
        Model.getInstance().loadData();

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
        findViewById(R.id.btn_view_chart_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewChart(view);
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

    private void ViewChart(View view) {
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
                        String startDate = "08/09/2017"; //TODO((TextView)findViewById(R.id.StartDate)).toString();
                        String endDate = "08/12/2017"; //TODO((TextView)findViewById(R.id.EndDate)).toString();
                        if (isValidDates(startDate, endDate)) {
                            String[] remake = startDate.split("/");
                            startDate = remake[2] + remake[0] + remake[1];
                            remake = endDate.split("/");
                            endDate = remake[2] + remake[0] + remake[1];
                            //TODO: Bharat's Thing
                            //TODO: Use the two dates above to get a range to get a list of ratsightings.
                            Model.getInstance().loadDateRangeData(startDate,endDate);
                            viewChartScreen();
                        }
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

    private void ViewMap(View view) {
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
                        String startDate = "08/09/2017"; //TODO((TextView)findViewById(R.id.StartDate)).toString();
                        String endDate = "08/12/2017"; //TODO((TextView)findViewById(R.id.EndDate)).toString();
                        if (isValidDates(startDate, endDate)) {
                            String[] remake = startDate.split("/");
                            startDate = remake[2] + remake[0] + remake[1];
                            remake = endDate.split("/");
                            endDate = remake[2] + remake[0] + remake[1];
                            //TODO: Bharat's Thing
                            //TODO: Use the two dates above to get a range to get a list of ratsightings.
                            Model.getInstance().loadDateRangeData(startDate,endDate);
                            //go to a loading screen to wait for the dates to load.
                            viewMapLoadingScreen();
                        }
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

    private boolean isValidDates(String start, String end) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date startD;
        Date endD;
        try {
            startD = format.parse(start);
            endD = format.parse(end);

            if(startD.after(endD)) {
                Log.d("Bool","TRUE1");

                return false;
            }

        } catch (ParseException e) {
            Log.d("Bool","TRUE2" + e.getMessage() + "\n" + e.getStackTrace());

            return false;
        }

        Log.d("Bool","TRUE");

        return true;
    }

    private void viewChartScreen() {
        Intent intent = new Intent(this, ChartActivity.class);
        startActivity(intent);
    }

    private void viewMapLoadingScreen() {
        Intent intent = new Intent(this, MapLoadingActivity.class);
        startActivity(intent);
    }

    private void Logout(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context, WelcomeScreen.class);
        startActivity(intent);
    }



}
