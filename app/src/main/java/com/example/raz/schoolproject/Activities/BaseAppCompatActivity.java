package com.example.raz.schoolproject.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.raz.schoolproject.LocalDataBase;
import com.example.raz.schoolproject.MusicService;
import com.example.raz.schoolproject.R;

public class BaseAppCompatActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggleMusic:
                Boolean isMusicOn = (new LocalDataBase(this)).load("isMusicOn", Boolean.class);
                Intent musicServiceIntent = new Intent(this, MusicService.class);
                if (!isMusicOn) {
                    startService(musicServiceIntent);
                    item.setIcon(R.drawable.ic_volume_up_black_24dp);
                    (new LocalDataBase(this)).save("isMusicOn", true);
                }
                else {
                    stopService(musicServiceIntent);
                    item.setIcon(R.drawable.ic_volume_off_black_24dp);
                    (new LocalDataBase(this)).save("isMusicOn", false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Boolean isMusicOn = (new LocalDataBase(this)).load("isMusicOn", Boolean.class);
        Intent musicServiceIntent = new Intent(this, MusicService.class);
        MenuItem item = menu.findItem(R.id.toggleMusic);
        if (isMusicOn == null) {
            startService(musicServiceIntent);
            item.setIcon(R.drawable.ic_volume_up_black_24dp);
            (new LocalDataBase(this)).save("isMusicOn", true);
        }
        else {
            if (isMusicOn) {
                item.setIcon(R.drawable.ic_volume_up_black_24dp);
            }
            else {
                item.setIcon(R.drawable.ic_volume_off_black_24dp);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        invalidateOptionsMenu();
    }
}
