package com.example.cardgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Deck gamedeck;
    ArrayList<Card> player1Hand;
    ArrayList<Card> player2Hand;
    int player1Score;
    int player2Score;
    int pointsCount;
    boolean gameStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void playNext(View view) {
        if(gameStarted){

            int rand = (int) (Math.random()*player1Hand.size());
            Card player1choice = player1Hand.get(rand);
            player1Hand.remove(rand);

            rand = (int) (Math.random()*player1Hand.size());
            Card player2choice = player2Hand.get(rand);
            player2Hand.remove(rand);

            if(player1choice.getNumber() > player2choice.getNumber()) {
                player1Score+=pointsCount;
                pointsCount = 1;
            }
            else if(player1choice.getNumber() < player2choice.getNumber()) {
                player2Score+=pointsCount;
                pointsCount = 1;
            }
            else {
                pointsCount++;
            }

            //Update Scoreboard

            if(!gamedeck.getDeck().isEmpty()) {
                player1Hand.add(gamedeck.dealCard());
                player2Hand.add(gamedeck.dealCard());
            }
        }
        else{
            gameStarted = true;
            gamedeck = new Deck(1);
            player1Hand = new ArrayList<Card>();
            player2Hand = new ArrayList<Card>();
            player1Score = 0;
            player2Score = 0;

            for(int i=0;i<7;i++) {
                player1Hand.add(gamedeck.dealCard());
                player2Hand.add(gamedeck.dealCard());
            }

            pointsCount = 1;

            updateCards();

        }
    }

    public void updateCards(){
        for(int i=0;i<player1Hand.size();i++){
            String name = player1Hand.get(i).getShortName();
            Log.d("test",name);

            int resID = getResources().getIdentifier(name, "drawable", getPackageName());
            String imageSlot = "card" + String.valueOf(i);
            Log.d("test", String.valueOf(resID));
            ImageView temp = (ImageView) findViewById(getResources().getIdentifier(imageSlot, "id", getPackageName()));
            temp.setImageDrawable(getResources().getDrawable(resID, null));
        }
    }
}
