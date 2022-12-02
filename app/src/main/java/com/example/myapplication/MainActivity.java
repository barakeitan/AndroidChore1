package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {


    private String[][] board = new String[3][3];
    private ImageButton[][] imgButtons = new ImageButton[3][3];
    private boolean player1Turn = true;
    private int roundCount;
    private int player1score;
    private int player2score;
    private TextView player1_tv;
    private TextView player2_tv;
    private LinearLayout linearLayout;
    private  Button againBtn;
    private ImageView banner;
    private boolean canPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1_tv = findViewById(R.id.text_view_p1);
        player2_tv = findViewById(R.id.text_view_p2);
        linearLayout = findViewById(R.id.main_linearlayout_lo);
        againBtn = findViewById(R.id.main_playagain_btn);
        banner = findViewById(R.id.imv_player);

        againBtn.setVisibility(View.INVISIBLE);
        againBtn.setOnClickListener(v->resetBoard());
        banner.setImageResource(R.drawable.xplay);

    for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id", getPackageName());
                imgButtons[i][j] = findViewById(resID);
                imgButtons[i][j].setOnClickListener(this);
                board[i][j] = "empty";
            }
        }
    }

    @Override
    public void onClick(View v) {
        String vId = getResources().getResourceEntryName(v.getId());
        int i = vId.charAt(vId.length()-2)-48;
        int j = vId.charAt(vId.length()-1)-48;
        if (!board[i][j].equals("empty")){
            return;
        }

        if(canPlay)
        {
            if (player1Turn) {
                ((ImageButton) v).setImageResource(R.drawable.x);
                board[i][j] = "x";
            } else {
                ((ImageButton) v).setImageResource(R.drawable.o);
                board[i][j] = "o";
            }

            roundCount++;

            if (checkVictory()) {
                if (player1Turn) {
                    player1Wins();
                } else {
                    player2Wins();
                }
            } else if (roundCount == 9) {
                draw();
            } else {
                if (player1Turn) {
                    banner.setImageResource(R.drawable.oplay);
                } else {
                    banner.setImageResource(R.drawable.xplay);
                }
                player1Turn = !player1Turn;
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private boolean checkVictory() {

        if (board[0][0].equals(board[0][1])
                    && board[0][0].equals(board[0][2])
                    && !board[0][0].equals("empty"))
            {
                linearLayout.setForeground(getDrawable(R.drawable.mark6));
                return true;
            }
        if (board[1][0].equals(board[1][1])
                && board[1][0].equals(board[1][2])
                && !board[1][0].equals("empty"))
        {
            linearLayout.setForeground(getDrawable(R.drawable.mark7));
            return true;
        }
        if (board[2][0].equals(board[2][1])
                && board[2][0].equals(board[2][2])
                && !board[2][0].equals("empty"))
        {
            linearLayout.setForeground(getDrawable(R.drawable.mark8));
            return true;
        }


        if (board[0][0].equals(board[1][0])
                    && board[0][0].equals(board[2][0])
                    && !board[0][0].equals("empty")) {
                linearLayout.setForeground(getDrawable(R.drawable.mark3));
                return true;
            }
        if (board[0][1].equals(board[1][1])
                && board[0][1].equals(board[2][1])
                && !board[0][1].equals("empty")) {
            linearLayout.setForeground(getDrawable(R.drawable.mark4));
            return true;
        }
        if (board[0][2].equals(board[1][2])
                && board[0][2].equals(board[2][2])
                && !board[0][2].equals("empty")) {
            linearLayout.setForeground(getDrawable(R.drawable.mark5));
            return true;
        }


        if (board[0][0].equals(board[1][1])
                && board[0][0].equals(board[2][2])
                && !board[0][0].equals("empty")) {
            linearLayout.setForeground(getDrawable(R.drawable.mark1));
            return true;
        }

        if (board[0][2].equals(board[1][1])
                && board[0][2].equals(board[2][0])
                && !board[0][2].equals("empty")) {
            linearLayout.setForeground(getDrawable(R.drawable.mark2));
            return true;
        }
        return false;
    }

    private void player1Wins() {
            player1score++;
            banner.setImageResource(R.drawable.xwin);
            updatePlayerScore();
            againBtn.setVisibility(View.VISIBLE);
            canPlay = false;
    }

    private void player2Wins() {

            player2score++;
            banner.setImageResource(R.drawable.owin);
            updatePlayerScore();
            againBtn.setVisibility(View.VISIBLE);
            canPlay = false;

    }

    private void draw() {
        banner.setImageResource(R.drawable.nowin);
        againBtn.setVisibility(View.VISIBLE);
    }

    private void updatePlayerScore() {
        player1_tv.setText("Player x: " + player1score);
        player2_tv.setText("Player o: " + player2score);
    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imgButtons[i][j].setImageResource(R.drawable.empty);
                board[i][j] = "empty";
            }
        }
        againBtn.setVisibility(View.INVISIBLE);
        linearLayout.setForeground(getDrawable(R.drawable.empty));
        banner.setImageResource(R.drawable.xplay);
        roundCount = 0;
        canPlay = true;
        player1Turn = true;
    }
}