package com.example.raz.schoolproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.Objects.User;
import com.example.raz.schoolproject.DAL.UserDAL;

public class MainMenuActivity extends BaseAppCompatActivity {

    UserDAL userDAL;

    Button gotoGame;
    Button gotoStats;
    Button gotoLogin;
    Button logout;
    Button gotoRegister;

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

        gotoStats = findViewById(R.id.gotoStatsButton);
        gotoStats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, StatsActivity.class));
            }
        } );

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
                userDAL.setCurrentUser(new User());
                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);
            }
        });

        gotoRegister = findViewById(R.id.gotoRegisterButton);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenuActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.hello)).setText("Hello, " + userDAL.getCurrentUser().getUsername());
        ((TextView) findViewById(R.id.coins)).setText("Coins: " + userDAL.getCurrentUser().getCoins());
        if (userDAL.getCurrentUser().getUserID() == -1) {
            gotoLogin.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);
            gotoStats.setVisibility(View.GONE);
            ((TextView) findViewById(R.id.coins)).setText("Coins: log in to collect coins");
        }
        else {
            gotoLogin.setVisibility(View.GONE);
            gotoRegister.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toggleMusic: {

            }
        }
        return super.onOptionsItemSelected(item);
    }
}
