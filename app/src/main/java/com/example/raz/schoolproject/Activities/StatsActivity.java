package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.raz.schoolproject.DAL.StatsDAL;
import com.example.raz.schoolproject.Objects.GameStats;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.DAL.UserDAL;
import com.example.raz.schoolproject.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class StatsActivity extends BaseAppCompatActivity {

    StatsDAL statsDAL;
    UserDAL userDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        statsDAL = new StatsDAL(this);
        userDAL = new UserDAL(this);
        ArrayList<GameStats> statsHistory = statsDAL.getAllStatsByUserID(userDAL.getCurrentUser().getUserID());
        TableLayout statsHistoryTable = findViewById(R.id.statsHistoryTableLayout);

        if(statsHistory.size() != 0) {
            findViewById(R.id.noStatsMessage).setVisibility(View.GONE);
            for (int i = 0; i < statsHistory.size(); i++) {
                statsHistoryTable.addView(createTableRowFromGameStats(statsHistory.get(i)));
            }
        }
        else {
            findViewById(R.id.noStatsMessage).setVisibility(View.VISIBLE);
        }
    }

    private TableRow createTableRowFromGameStats(GameStats stats) {
        TableRow row = new TableRow(this);
        TableLayout.LayoutParams params = new TableLayout.LayoutParams();
        params.setMargins(0, 20, 0, 0);
        row.setLayoutParams(params);
        String dateString = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(stats.getDate());

        row.addView(createTextView(dateString));
        row.addView(createTextView(String.valueOf(stats.getScore())));
        row.addView(createTextView(String.valueOf(stats.getShapesPlacedCount())));
        row.addView(createTextView(Utilities.millisToString(stats.getTimeCountInMillis())));

        return row;
    }

    private TextView createTextView(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        TableRow.LayoutParams params = new TableRow.LayoutParams(Utilities.dpToPixels(this, 80), ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(Utilities.dpToPixels(this, 5), 0, Utilities.dpToPixels(this, 5), 0);
        tv.setLayoutParams(params);
        return tv;
    }
}
