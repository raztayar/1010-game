package com.example.raz.schoolproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.User;
import com.example.raz.schoolproject.UserDAL;

public class MainMenuActivity extends AppCompatActivity {

    UserDAL userDAL;

    Button gotoGame;
    Button gotoRegister;
    Button gotoLogin;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        userDAL = new UserDAL(this);

        gotoGame = findViewById(R.id.gotoGameButton);
        gotoGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, GameActivity.class));
            }
        } );

        gotoRegister = findViewById(R.id.gotoRegisterButton);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, RegisterActivity.class));
            }
        });

        gotoLogin = findViewById(R.id.gotoLoginButton);
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, LoginActivity.class));
            }
        });

        logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDAL.updateCurrentUser(new User());
                startActivity(new Intent(MainMenuActivity.this, MainMenuActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.hello)).setText("Hello, " + userDAL.getCurrentUser().getUsername());
        if (userDAL.getCurrentUser().getUserID() != 1) {
            gotoLogin.setVisibility(View.GONE);
            gotoRegister.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
        else {
            gotoLogin.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
        }
    }
}
