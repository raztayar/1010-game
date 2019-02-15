package com.example.raz.schoolproject.DAL;

import android.content.Context;

import com.example.raz.schoolproject.Objects.Game;
import com.example.raz.schoolproject.Objects.GameStats;
import com.example.raz.schoolproject.Objects.IShape;
import com.example.raz.schoolproject.LocalDataBase;
import com.example.raz.schoolproject.ShapeType;

public class GameDAL {

    private LocalDataBase gameDataBase;

    public GameDAL(Context context) {
        gameDataBase = new LocalDataBase(context);
    }

    public Game loadFromDataBase(long userID, Game game) {
        ShapeType[][] boardLoad = gameDataBase.load("board:" + String.valueOf(userID), ShapeType[][].class);
        if (boardLoad != null) {
            game.setBoard(boardLoad);
        }

        String[] shapeTypes = gameDataBase.load("shapeTypes:" + String.valueOf(userID), String[].class);
        if(shapeTypes != null) {
            for (int i = 0; i < shapeTypes.length; i++) {
                if (shapeTypes[i] != null) {
                    try {
                        game.setShapeQueueAt(i, (IShape) gameDataBase.load("queueSlot" + i + ":" + String.valueOf(userID), Class.forName(shapeTypes[i])));
                    } catch (ClassNotFoundException e) {
                        game.setShapeQueueAt(i, null) ;
                    }
                }
                else {
                    game.setShapeQueueAt(i, null) ;
                }
            }
        }

        GameStats gameStatsLoad = gameDataBase.load("currentGameStats:" + String.valueOf(userID), GameStats.class);
        if (gameStatsLoad != null) {
            game.setGameStats(gameStatsLoad);
        }

        return game;
    }

    public void saveToDataBase(long userID, Game game) {

        gameDataBase.save("board:" + String.valueOf(userID), game.getBoard());

        String[] shapeTypes = new String[3];
        for(int i = 0; i < game.getShapeQueue().length; i++) {
            gameDataBase.save("queueSlot" + i + ":" + String.valueOf(userID), game.getShapeQueue()[i]);
            if (game.getShapeQueue()[i] != null) {
                shapeTypes[i] = game.getShapeQueue()[i].getClass().getName();
            }
        }
        gameDataBase.save("shapeTypes:" + String.valueOf(userID), shapeTypes);

        gameDataBase.save("currentGameStats:" + String.valueOf(userID), game.getGameStats());
    }
}
