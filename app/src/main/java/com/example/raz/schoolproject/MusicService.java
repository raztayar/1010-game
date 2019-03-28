package com.example.raz.schoolproject;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
        private static final String TAG = null;
        MediaPlayer player;

        public IBinder onBind(Intent intent) {
            return null;
        }
        @Override
        public void onCreate() {
            super.onCreate();
            player = MediaPlayer.create(this, R.raw.tubular_bells);
            player.setLooping(true); // Set looping
            player.setVolume(100,100);

        }
        public int onStartCommand(Intent intent, int flags, int startId) {
            player.start();
            return START_NOT_STICKY;
        }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    public void onStop() {
        player.stop();
        player.release();
    }
    public void onPause() {

    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}