package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.raz.schoolproject.Game;
import com.example.raz.schoolproject.IShape;
import com.example.raz.schoolproject.Point;
import com.example.raz.schoolproject.R;

public class GameActivity extends AppCompatActivity {

    private Game game;

    public Context thisContext;

    private Button getShapes;
    private Button removeShapes;

    private int currentShapeToPlaceIndex = -1;

    private LinearLayout queueSlot1;
    private LinearLayout queueSlot2;
    private LinearLayout queueSlot3;

    private TableLayout boardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(this);
        thisContext = this;

        getShapes = findViewById(R.id.getShapesButton);
        removeShapes = findViewById(R.id.removeShapesButton);
        queueSlot1 = findViewById(R.id.slot1);
        queueSlot2 = findViewById(R.id.slot2);
        queueSlot3 = findViewById(R.id.slot3);
        boardView = findViewById(R.id.board);

        getShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.bringShapesToQueue();
            }
        });

        removeShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.removeShapesFromQueue();
            }
        });

        for(int i = 0; i < 3; i++){
            final int slotIndex = i;
            getQueueSlotLayout(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(game.getShapeQueue()[slotIndex] != null){
                        setCurrentShapeToPlaceIndex(slotIndex);
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
                        if(currentShapeToPlaceIndex != -1){
                            IShape currentShapeToPlace = game.getShapeQueue()[currentShapeToPlaceIndex];
                            if(currentShapeToPlace.isPlaceable(point, game.getBoard())){
                                currentShapeToPlace.placeShape(point, game.getBoard());
                                updateBoardView();
                                game.removeShapeFromSlot(currentShapeToPlaceIndex);
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

    public int getCurrentShapeToPlaceIndex() {
        return currentShapeToPlaceIndex;
    }

    public void setCurrentShapeToPlaceIndex(int currentShapeToPlaceIndex) {
        this.currentShapeToPlaceIndex = currentShapeToPlaceIndex;
    }

    private void updateBoardView(){
        int color = Color.parseColor("#BEB8B8");

        for (int i = 0; i < boardView.getChildCount(); i++) {
            TableRow row = (TableRow) boardView.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                final Point point = new Point(i, j);
                if(game.getBoard()[i][j] != null){
                    row.getChildAt(j).setBackgroundColor(Color.BLACK);
                }
                else{
                    row.getChildAt(j).setBackgroundColor(color);
                }
            }
        }
    }
}
