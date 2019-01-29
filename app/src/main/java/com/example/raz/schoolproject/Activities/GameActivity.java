package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raz.schoolproject.Game;
import com.example.raz.schoolproject.IShape;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.Shape;
import com.example.raz.schoolproject.Theme;
import com.example.raz.schoolproject.Utilities;

public class GameActivity extends AppCompatActivity {

    private Game game;
    public Theme theme;

    public Context thisContext;

    private Button newGame;

    private int currentSlotIndex = -1;

    private LinearLayout queueView;

    private TableLayout boardView;

    public GameActivity() {
        Log.d("fdsfsd", "*********************************************");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Log.d("lalala", "onCreate: ");

        newGame = findViewById(R.id.resetGameButton);
        queueView = findViewById(R.id.queue);
        boardView = findViewById(R.id.board);

        theme = new Theme();

        drawBoard();

        game = new Game(this);
        game.loadFromDataBase("");

        thisContext = this;

        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        if(!game.hasShapesInQueue()) {
            game.bringShapesToQueue();
        }
        updateQueueView();


        for(int i = 0; i < 3; i++){
            final int slotIndex = i;
            getQueueSlotLayout(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            if(game.getShapeQueue()[slotIndex] != null){
                                setCurrentSlotIndex(slotIndex);
                                Log.d("start drag", "x:" + event.getX() + "                y:" + event.getY());
                                v.startDrag(null, new View.DragShadowBuilder(v), game.getShapeQueue()[slotIndex], 0);
                            }
                            return true;
                    }
                    return false;
                }
            });
        }

        boardView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d("drag started", "************");
                        return true;
                    case DragEvent.ACTION_DROP:
                        Log.d("drop coordinates", "x:" + event.getY() + "   y:" + event.getX() + "    data:" + event.getLocalState());
                        if(currentSlotIndex != -1){
                            IShape currentShapeToPlace = game.getShapeQueue()[currentSlotIndex];
                            Point placePoint = getPlacePointFromCoordinates(event.getY(), event.getX(), (Shape) currentShapeToPlace);
                            Log.d("place point", "place point:" + placePoint.toString() + "    data:" + event.getLocalState());
                            if(currentShapeToPlace.isPlaceable(placePoint, game.getBoard())){
                                currentShapeToPlace.placeShape(placePoint, game.getBoard());
                                game.removeFullRowsAndColumns();
                                updateBoardView();

                                game.removeShapeFromSlot(currentSlotIndex);
                                if(!game.hasShapesInQueue()) {
                                    game.bringShapesToQueue();
                                }
                                updateQueueView();

                                if (game.isGameOver()) {
                                    MediaPlayer.create(thisContext, Settings.System.DEFAULT_ALARM_ALERT_URI).start();
                                    Toast.makeText(thisContext, "GAME OVER", Toast.LENGTH_LONG).show();
                                    resetGame();
                                }
                            }
                            else {
                                MediaPlayer.create(thisContext, Settings.System.DEFAULT_NOTIFICATION_URI).start();
                            }
                        }
                        return true;
                }
                return false;
            }
        });


        /*for (int i = 0; i < boardView.getChildCount(); i++) {
            TableRow row = (TableRow) boardView.getChildAt(i);
            for(int j = 0; j < row.getChildCount(); j++) {
                final Point point = new Point(i,j);
                row.getChildAt(j).setOnDragListener(new View.OnDragListener() {
                    @Override
                    public boolean onDrag(View v, DragEvent event) {
                        switch (event.getAction()) {
                            case DragEvent.ACTION_DRAG_STARTED:
                                Log.d("drag started", "************");
                                return true;
                            case DragEvent.ACTION_DROP:
                                Log.d("drop", "x:" + event.getX() + "   y:" + event.getY() + "    data:" + event.getLocalState());
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
                                            MediaPlayer.create(thisContext, Settings.System.DEFAULT_ALARM_ALERT_URI).start();
                                            Toast.makeText(thisContext, "GAME OVER", Toast.LENGTH_LONG).show();
                                            resetGame();
                                        }
                                    }
                                    else {
                                        MediaPlayer.create(thisContext, Settings.System.DEFAULT_NOTIFICATION_URI).start();
                                    }
                                }
                        }
                        return false;
                    }
                });
            }
        }*/
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
        if(i < 0 || i > 2) throw new RuntimeException("shape slot should be only 0, 1, 2");

        return (LinearLayout) queueView.getChildAt(i);
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
                getQueueSlotLayout(i).addView(shape.createShapeAsTableLayout(this, theme));
            }
        }
    }

    private void resetGame() {
        game = new Game(this);
        game.bringShapesToQueue();
        updateQueueView();
        updateBoardView();
    }

    private void drawBoard() {
        for (int i = 0; i < Game.BOARD_HEIGHT ; i++) {
            TableRow row = new TableRow(this);
            boardView.addView(row);
            for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                TextView textView = new TextView(this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(Utilities.dpToPixels(this, 32), Utilities.dpToPixels(this, 32));
                params.setMargins(Utilities.dpToPixels(this, 2), Utilities.dpToPixels(this, 2), Utilities.dpToPixels(this, 2), Utilities.dpToPixels(this, 2));
                textView.setLayoutParams(params);
                textView.setBackgroundColor(Color.parseColor("#BEB8B8"));
                textView.setGravity(Gravity.CENTER);
                textView.setTag(new Point(i, j));
                row.addView(textView);
            }
        }
    }

    private Point getPlacePointFromCoordinates(float x, float y, Shape shape) {
        final float boardSize = 935;
        double offsetX = 0;
        double offsetY = 0;
        if (shape.getShapeMatrix().length % 2 == 0)
            offsetX = (boardSize / 20);
        if (shape.getShapeMatrix()[0].length % 2 == 0)
            offsetY = (boardSize / 20);
        Point dropPoint = new Point((int) ((x - offsetX) / (boardSize / 10)), (int) ((y - offsetY) / (boardSize / 10)));
        Log.d("drop point", "drop point" + dropPoint.toString());
        return new Point(dropPoint.x - shape.getMidPoint().x, dropPoint.y - shape.getMidPoint().y);
    }
}
