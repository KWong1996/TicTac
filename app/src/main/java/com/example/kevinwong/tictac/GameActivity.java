package com.example.kevinwong.tictac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    boolean player1Turn = true;
    int tilesPlaced = 0;
    boolean gameDone = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        for(int i = 0; i < 3;i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "tile_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());

                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button) v).setText("X");
        }
        else{
            ((Button) v).setText("O");
        }

        tilesPlaced++;

        if(checkWinner()){
            playerWins(player1Turn);
        }
        else if (tilesPlaced == 9){
            draw();
        }
        else{
            player1Turn = !player1Turn;
        }

    }

    private void playerWins(boolean playerTurn){

        if(playerTurn){
            Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show();
            resetGame();
        }
        else{
            Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show();
            resetGame();
        }

    }


    private boolean checkWinner(){
        String[][] field = new String[3][3];

        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3;j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for(int i = 0;i < 3; i++){
            if(field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for(int i = 0;i < 3; i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    private void draw(){
        Toast.makeText(this,"Draw", Toast.LENGTH_SHORT).show();
        resetGame();
    }

    private void resetGame(){
        for(int i = 0; i < 3;i++){
            for(int j = 0; j < 3;j++){
                buttons[i][j].setText("");
            }
        }

        tilesPlaced = 0;
        player1Turn = true;
    }

}
