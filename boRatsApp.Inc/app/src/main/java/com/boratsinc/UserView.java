package com.boratsinc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Logout();
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewRatSightings();
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
