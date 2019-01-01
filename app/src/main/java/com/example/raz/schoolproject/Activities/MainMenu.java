package com.example.raz.schoolproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.raz.schoolproject.R;

public class MainMenu extends AppCompatActivity {

    private Button gotoGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        gotoGame = findViewById(R.id.gotoGameButton);
        gotoGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, GameActivity.class));
            }
        } );
    }
}
