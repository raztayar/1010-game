package com.example.raz.schoolproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.raz.schoolproject.DAL.StatsDAL;
import com.example.raz.schoolproject.Objects.GameStats;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.DAL.UserDAL;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {

    StatsDAL statsDAL;
    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsDAL = new StatsDAL(this);
        userDAL = new UserDAL(this);
        ArrayList<GameStats> statsHistory = statsDAL.getAllStatsByUserID(userDAL.getCurrentUser().getUserID());

        ArrayAdapter<GameStats> statsArrayAdapter = new ArrayAdapter<GameStats>(this, android.R.layout.simple_list_item_1, statsHistory);

        ListView statsHisoryList = findViewById(R.id.statsHistoryList);

        statsHisoryList.setAdapter(statsArrayAdapter);
    }
}
