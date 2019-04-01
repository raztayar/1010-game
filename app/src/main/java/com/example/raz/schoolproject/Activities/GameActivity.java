package com.example.raz.schoolproject.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.raz.schoolproject.DAL.GameDAL;
import com.example.raz.schoolproject.Objects.Game;
import com.example.raz.schoolproject.Objects.IShape;
import com.example.raz.schoolproject.R;
import com.example.raz.schoolproject.Objects.Shape;
import com.example.raz.schoolproject.Objects.Theme;
import com.example.raz.schoolproject.Objects.User;
import com.example.raz.schoolproject.DAL.UserDAL;
import com.example.raz.schoolproject.Utilities;

import static com.example.raz.schoolproject.Utilities.dpToPixels;
import static com.example.raz.schoolproject.Utilities.playSound;

public class GameActivity extends BaseAppCompatActivity {

    private Game game;
    public Theme theme;

    private TextView scoreView;

    private int currentSlotIndex = -1;

    private LinearLayout queueView;

    private TableLayout boardView;

    private UserDAL userDAL;
    private GameDAL gameDAL;
    private long currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        userDAL = new UserDAL(this);
        gameDAL = new GameDAL(this);

        Button resetGame = findViewById(R.id.resetGameButton);
        scoreView = findViewById(R.id.score);
        queueView = findViewById(R.id.queue);
        boardView = findViewById(R.id.board);

        game = new Game(this);
        game.bringShapesToQueue();

        theme = new Theme();
        drawBoard();

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        for(int i = 0; i < 3; i++){
            final int slotIndex = i;
            getQueueSlotLayout(i).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            final IShape shape = game.getShapeQueue()[slotIndex];
                            if(shape != null){
                                setCurrentSlotIndex(slotIndex);
                                Log.d("start drag", "x:" + event.getX() + "                y:" + event.getY());
                                v.startDrag(null, new View.DragShadowBuilder(v) {
                                    @Override
                                    public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
                                        super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
                                        outShadowTouchPoint.set(outShadowTouchPoint.x, outShadowTouchPoint.y);
                                    }
                                }, shape, 0);
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
                        getQueueSlotLayout(currentSlotIndex).setVisibility(View.INVISIBLE);
                        return true;
                    case DragEvent.ACTION_DROP:
                        getQueueSlotLayout(currentSlotIndex).setVisibility(View.VISIBLE);
                        IShape currentShapeToPlace = game.getShapeQueue()[currentSlotIndex];
                        Point placePoint = getPlacePointFromCoordinates(event.getY(), event.getX(), (Shape) currentShapeToPlace);

                        if(currentShapeToPlace.isPlaceable(placePoint, game.getBoard())){
                            currentShapeToPlace.placeShape(placePoint, game.getBoard());
                            game.raiseShapesPlacedCountByOne();

                            int fullLines = game.removeFullRowsAndColumns();

                            game.addScore(currentShapeToPlace.getShapeScore() + (fullLines * 10));
                            playSound(GameActivity.this, R.raw.apple_ding, fullLines);

                            User newUser = userDAL.getUserByID(currentUserID);
                            newUser.addCoins(fullLines);
                            userDAL.updateUser(newUser);

                            updateBoardView();
                            updateScoreView();

                            game.removeShapeFromSlot(currentSlotIndex);
                            boolean queueWasEmpty = false;
                            if(!game.hasShapesInQueue()) {
                                game.bringShapesToQueue();
                                queueWasEmpty = true;
                            }
                            updateQueueView();
                            if (queueWasEmpty) animateQueueView();

                            if (game.isGameOver()) {
                                playSound(GameActivity.this, R.raw.game_over, 1);

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);

                                Toast.makeText(GameActivity.this, "GAME OVER, " + "it took you: " + Utilities.millisToString(game.getGameStats().getTimeCountInMillis()) + ", to get: " + game.getGameStats().getScore(), Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            playSound(GameActivity.this, R.raw.bruh, 1);
                        }
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        if (currentSlotIndex != -1) {
                            getQueueSlotLayout(currentSlotIndex).setVisibility(View.VISIBLE);
                        }
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!game.isGameOver()) {
            game.pauseAndUpdateTimer();
        }
        gameDAL.saveToDataBase(currentUserID, game);
    }

    @Override
    protected void onResume() {
        super.onResume();
        currentUserID = userDAL.getCurrentUser().getUserID();
        game = gameDAL.loadFromDataBase(currentUserID, game);

        if(game.isGameOver()) {
            theme = Theme.GAME_OVER;
            game.pauseAndUpdateTimer();
            game.saveStatsToHistory(currentUserID);

            showGameOverDialog();
        }
        findViewById(R.id.mainGameActivityLayout).setBackgroundColor(theme.getBackgroundColor());

        game.resumeTimer();

        updateBoardView();
        updateQueueView();
        updateScoreView();
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
        for (int i = 0; i < boardView.getChildCount(); i++) {
            TableRow row = (TableRow) boardView.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                if(game.getBoard()[i][j] != null){
                    row.getChildAt(j).setBackgroundColor(theme.getColorHashMap().get(game.getBoard()[i][j]));
                }
                else{
                    row.getChildAt(j).setBackgroundColor(theme.getEmptySquareColor());
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

    private void animateQueueView() {
        for(int i = 0; i < 3; i++) {
            View slotChild = getQueueSlotLayout(i).getChildAt(0);
            if(slotChild != null) {
                slotChild.startAnimation(AnimationUtils.loadAnimation(GameActivity.this, R.anim.righttoleft));
            }
        }
    }

    private void updateScoreView() {
        scoreView.setText(String.valueOf(game.getGameStats().getScore()));
        scoreView.setTextColor(theme.getScoreColor());
    }


    private void resetGame() {
        game = new Game(this);
        game.bringShapesToQueue();
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);
    }

    private void drawBoard() {
        for (int i = 0; i < Game.BOARD_HEIGHT ; i++) {
            TableRow row = new TableRow(this);
            boardView.addView(row);
            for (int j = 0; j < Game.BOARD_WIDTH; j++) {
                TextView textView = new TextView(this);
                TableRow.LayoutParams params = new TableRow.LayoutParams(dpToPixels(this, 32), dpToPixels(this, 32));
                params.setMargins(dpToPixels(this, 2), dpToPixels(this, 2), dpToPixels(this, 2), dpToPixels(this, 2));
                textView.setLayoutParams(params);
                textView.setBackgroundColor(theme.getEmptySquareColor());
                textView.setGravity(Gravity.CENTER);
                textView.setTag(new Point(i, j));
                row.addView(textView);
            }
        }
    }

    public Point getPlacePointFromCoordinates(float x, float y, Shape shape) {
        final float boardSize = 935;
        double offsetX = 0;
        double offsetY = 0;
        if (shape.getShapeMatrix().length % 2 == 0)
            offsetX += (boardSize / 10 / 2);
        if (shape.getShapeMatrix()[0].length % 2 == 0)
            offsetY += (boardSize / 10 / 2);
        Point dropPoint = new Point((int) ((x - offsetX) / (boardSize / 10)), (int) ((y - offsetY) / (boardSize / 10)));
        return new Point(dropPoint.x - shape.getMidPoint().x, dropPoint.y - shape.getMidPoint().y);
    }

    private void showGameOverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle("Game Over!");
        builder.setMessage("The game has ended with a score of: " + game.getGameStats().getScore() + "\n What would you like to do now?");
        builder.setPositiveButton("Reset Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
            }
        });
        builder.setNegativeButton("Keep Trying", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                startActivity(new Intent(GameActivity.this, MainMenuActivity.class));
            }
        });
        builder.show();
    }
}
