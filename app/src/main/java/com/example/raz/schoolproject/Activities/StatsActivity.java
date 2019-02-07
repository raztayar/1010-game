package com.example.raz.schoolproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.raz.schoolproject.GameStats;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.UserDAL;

public class StatsActivity extends AppCompatActivity {

    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        userDAL = new UserDAL(this);
        GameStats[] statsHistory = userDAL.getStatsHistoryByUserID(userDAL.getCurrentUser().getUserID());

        ArrayAdapter<GameStats> statsArrayAdapter = new ArrayAdapter<GameStats>(this, android.R.layout.simple_list_item_1, statsHistory);

        ListView statsHisoryList = findViewById(R.id.statsHistoryList);

        statsHisoryList.setAdapter(statsArrayAdapter);
    }
}
