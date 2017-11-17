package com.boratsinc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.boratsinc.Model.Model;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchLogin();
            }
        });

        TextView registerText = findViewById(R.id.register);
        registerText.setClickable(true);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegister();
            }
        });
        Model.getInstance().loadUserDataBase();

    }

    private void launchLogin() {
        Intent intent = new Intent(this,LoginUser.class);
        startActivity(intent);
    }

    private void launchRegister() {
        Intent intent = new Intent(this,RegisterUser.class);
        startActivity(intent);
    }
}
