package com.boratsinc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        findViewById(R.id.btn_view_rat_sightings_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewRatSightings();
            }
        });
        findViewById(R.id.addbtn_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, AddRatSighting.class);
                context.startActivity(intent);
            }
        });
        findViewById(R.id.logoutbtn_user_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
    }
    private void Logout() {
        Intent intent = new Intent(this, WelcomeScreen.class);
        startActivity(intent);
    }

    private void ViewRatSightings() {
        Intent intent = new Intent(this, RatSightingsListView.class);
        startActivity(intent);
    }

}
