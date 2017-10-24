package com.example.sherry.games;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class TicTacToe extends AppCompatActivity {

    int board[][];
    Button button[][];
    TextView statusTextView, scoreTextView;
    int wins=0, loses=0, draws=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);

        setBoard();
    }

    private void setBoard() {
        board = new int[3][3];
        button = new Button[3][3];
        button[0][0]=(Button) findViewById(R.id.button1);
        button[0][1]=(Button) findViewById(R.id.button2);
        button[0][2]=(Button) findViewById(R.id.button3);
        button[1][0]=(Button) findViewById(R.id.button4);
        button[1][1]=(Button) findViewById(R.id.button5);
        button[1][2]=(Button) findViewById(R.id.button6);
        button[2][0]=(Button) findViewById(R.id.button7);
        button[2][1]=(Button) findViewById(R.id.button8);
        button[2][2]=(Button) findViewById(R.id.button9);
        statusTextView = (TextView) findViewById(R.id.status);
        scoreTextView = (TextView) findViewById(R.id.results);

        for(int i= 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = 0;
                button[i][j].setText(" ");
                button[i][j].setEnabled(true);
            }
        }
        statusTextView.setText("Click a square to BEGIN.");
    }

    public void makeMove(View view){
        Button b = (Button) view;
        b.setText("O");
        b.setTextColor(Color.GREEN);
        b.setEnabled(false);

        switch (view.getId()) {
            case R.id.button1: board[0][0] = 1; break;
            case R.id.button2: board[0][1] = 1; break;
            case R.id.button3: board[0][2] = 1; break;
            case R.id.button4: board[1][0] = 1; break;
            case R.id.button5: board[1][1] = 1; break;
            case R.id.button6: board[1][2] = 1; break;
            case R.id.button7: board[2][0] = 1; break;
            case R.id.button8: board[2][1] = 1; break;
            case R.id.button9: board[2][2] = 1; break;
        }
        if (!checkBoard()) {
            botMove();
        }
    }

    public void restartGame(View view){
        setBoard();
    }

    private void botMove() {
        Random ai = new Random();
        int x = ai.nextInt(3);
        int y = ai.nextInt(3);

        if (board[x][y] == 0) {
            button[x][y].setEnabled(false);
            button[x][y].setText("X");
            button[x][y].setTextColor(Color.RED);
            board[x][y] = 2;
            checkBoard();
        }
        else {
            botMove();
        }
    }

    private void notClickable(){
        for(int i= 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setEnabled(false);
            }
        }
    }

    private boolean checkBoard() {
        boolean game = false;
        if ((board[0][0] == 1 && board[1][0] == 1 && board[2][0] == 1)
                || (board[0][1] == 1 && board[1][1] == 1 && board[2][1] == 1)
                || (board[0][2] == 1 && board[1][2] == 1 && board[2][2] == 1)
                || (board[0][0] == 1 && board[0][1] == 1 && board[0][2] == 1)
                || (board[1][0] == 1 && board[1][1] == 1 && board[1][2] == 1)
                || (board[2][0] == 1 && board[2][1] == 1 && board[2][2] == 1)
                || (board[0][0] == 1 && board[1][1] == 1 && board[2][2] == 1)
                || (board[0][2] == 1 && board[1][1] == 1 && board[2][0] == 1)) {
            statusTextView.setText("GAME OVER! WINNER!");
            game = true;
            notClickable();
            wins++;
        }
        else if ((board[0][0] == 2 && board[1][0] == 2 && board[2][0] == 2)
                || (board[0][1] == 2 && board[1][1] == 2 && board[2][1] == 2)
                || (board[0][2] == 2 && board[1][2] == 2 && board[2][2] == 2)
                || (board[0][0] == 2 && board[0][1] == 2 && board[0][2] == 2)
                || (board[1][0] == 2 && board[1][1] == 2 && board[1][2] == 2)
                || (board[2][0] == 2 && board[2][1] == 2 && board[2][2] == 2)
                || (board[0][0] == 2 && board[1][1] == 2 && board[2][2] == 2)
                || (board[0][2] == 2 && board[1][1] == 2 && board[2][0] == 2)) {
            statusTextView.setText("GAME OVER! LOSER!");
            game = true;
            notClickable();
            loses++;
        }
        else {
            statusTextView.setText("Your turn.");
            boolean empty = false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        empty = true;
                        break;
                    }
                }
            }
            if (!empty) {
                statusTextView.setText("GAME OVER! DRAW!");
                game = true;
                draws++;
            }
        }
        scoreTextView.setText("Wins: " + wins + " Loses: " + loses + " Draws: " + draws);
        return game;
    }
}


