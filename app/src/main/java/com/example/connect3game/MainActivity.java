package com.example.connect3game;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0: alex  1: steve  2: empty
    int[] gameStatus = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameStatus[tappedCounter] == 2 && gameActive) {
            gameStatus[tappedCounter] = activePlayer;
            counter.setTranslationY(-1500);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.alex);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.steve);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            for (int[] winningPosition : winningPositions) {
                if (gameStatus[winningPosition[0]] == gameStatus[winningPosition[1]] && gameStatus[winningPosition[1]] == gameStatus[winningPosition[2]] && gameStatus[winningPosition[0]] != 2) {
                    // Someone has won!
                    String winner;
                    if (activePlayer == 1) {
                        winner = "Alex";
                    } else {
                        winner = "Steve";
                    }
                    Button playAgainButton = (Button) findViewById(R.id.playagainButton);
                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    String msg = winner + " has won!";
                    winnerTextView.setText(msg);
                    winnerTextView.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
        }
    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playagainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gameStatus, 2);
        activePlayer = 0;
        gameActive = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}