package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.raz.schoolproject.Game;
import com.example.raz.schoolproject.IShape;
import com.example.raz.schoolproject.Point;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.Theme;

public class GameActivity extends AppCompatActivity {

    private Game game;
    public Theme theme;

    public Context thisContext;

    private Button newGame;

    private int currentSlotIndex = -1;

    private LinearLayout queueSlot1;
    private LinearLayout queueSlot2;
    private LinearLayout queueSlot3;

    private TableLayout boardView;

    public GameActivity() {
        Log.d("fdsfsd", "*********************************************");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("lalala", "onCreate: ");

        game = new Game(this);
        game.loadFromDataBase("");

        thisContext = this;
        theme = new Theme();

        newGame = findViewById(R.id.newGameButton);
        queueSlot1 = findViewById(R.id.slot1);
        queueSlot2 = findViewById(R.id.slot2);
        queueSlot3 = findViewById(R.id.slot3);
        boardView = findViewById(R.id.board);

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.bringShapesToQueue();
                updateQueueView();
            }
        });

        game.bringShapesToQueue();
        updateQueueView();


        for(int i = 0; i < 3; i++){
            final int slotIndex = i;
            getQueueSlotLayout(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(game.getShapeQueue()[slotIndex] != null){
                        setCurrentSlotIndex(slotIndex);
                    }
                }
            });
        }

        for (int i = 0; i < boardView.getChildCount(); i++){
            TableRow row = (TableRow) boardView.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++){
                final Point point = new Point(i,j);
                row.getChildAt(j).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(currentSlotIndex != -1){
                            IShape currentShapeToPlace = game.getShapeQueue()[currentSlotIndex];
                            if(currentShapeToPlace.isPlaceable(point, game.getBoard())){
                                currentShapeToPlace.placeShape(point, game.getBoard());
                                game.removeFullRowsAndColumns();
                                updateBoardView();

                                game.removeShapeFromSlot(currentSlotIndex);
                                if(!game.hasShapesInQueue()) {
                                    game.bringShapesToQueue();
                                }
                                updateQueueView();

                                if (game.isGameOver()) {
                                    MediaPlayer player = MediaPlayer.create(thisContext, Settings.System.DEFAULT_ALARM_ALERT_URI);
                                    Toast.makeText(thisContext, "GAME OVER", Toast.LENGTH_LONG).show();
                                    resetGame();
                                }
                            }
                            else {
                                MediaPlayer player = MediaPlayer.create(thisContext, Settings.System.DEFAULT_NOTIFICATION_URI);
                                player.start();
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.saveToDataBase("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        game.loadFromDataBase("");
        updateBoardView();
        updateQueueView();
    }

    public LinearLayout getQueueSlotLayout(int i) {
        switch (i) {
            case 0:
                return queueSlot1;
            case 1:
                return queueSlot2;
            case 2:
                return queueSlot3;
        }
        throw new RuntimeException("shape slot should be only 0, 1, 2");
    }

    public int getCurrentSlotIndex() {
        return currentSlotIndex;
    }

    public void setCurrentSlotIndex(int currentSlotIndex) {
        this.currentSlotIndex = currentSlotIndex;
    }

    private void updateBoardView(){
        int emptyColor = Color.parseColor("#BEB8B8");

        for (int i = 0; i < boardView.getChildCount(); i++) {
            TableRow row = (TableRow) boardView.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                if(game.getBoard()[i][j] != null){
                    row.getChildAt(j).setBackgroundColor(theme.getColorHashMap().get(game.getBoard()[i][j]));
                }
                else{
                    row.getChildAt(j).setBackgroundColor(emptyColor);
                }
            }
        }
    }

    private void updateQueueView() {
        for(int i = 0; i < 3; i++) {
            getQueueSlotLayout(i).removeAllViews();
            IShape shape = game.getShapeQueue()[i];
            if(shape != null) {
                getQueueSlotLayout(i).addView(shape.createShapeAsTableLayout(this));
            }
        }
    }

    private void resetGame() {
        game = new Game(this);
        updateQueueView();
        updateBoardView();
    }
}
