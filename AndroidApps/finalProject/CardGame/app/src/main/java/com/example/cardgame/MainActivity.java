package com.example.cardgame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Deck gamedeck;
    ArrayList<Card> player1Hand;
    ArrayList<Card> player2Hand;
    Card selectedCard;
    int player1Score;
    int player2Score;
    int pointsCount;
    boolean gameStarted = false;
    boolean needContinue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void playNext(View view) {
        if(gameStarted && !needContinue){
            if(selectedCard != null){
                needContinue = true;
                Button tempButton = (Button) findViewById(R.id.gameButton);
                tempButton.setText("Continue");

                for(int i=0;i<player1Hand.size();i++){
                    if(selectedCard.isEqual(player1Hand.get(i))){
                        player1Hand.remove(i);
                        break;
                    }
                }

                int rand = (int) (Math.random()*player2Hand.size());
                Card player2choice = player2Hand.get(rand);
                player2Hand.remove(rand);

                String name = player2choice.getShortName();
                ImageView cpucardimage = (ImageView) findViewById(R.id.cpucard);
                cpucardimage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));


                if(selectedCard.getNumber() > player2choice.getNumber()) {
                    player1Score+=pointsCount;
                    pointsCount = 1;
                    TextView temp = (TextView) findViewById(R.id.playerScore);
                    temp.setText("Your Score:\n" + player1Score);
                    temp = findViewById(R.id.selectCardText);
                    temp.setText("You Won the Round!");
                }
                else if(selectedCard.getNumber() < player2choice.getNumber()) {
                    player2Score+=pointsCount;
                    pointsCount = 1;
                    TextView temp = (TextView) findViewById(R.id.cpuScore);
                    temp.setText("CPU Score:\n" + player2Score);
                    temp = findViewById(R.id.selectCardText);
                    temp.setText("CPU Won the Round!");
                }
                else {
                    pointsCount++;
                    TextView temp = findViewById(R.id.selectCardText);
                    temp.setText("Draw!");
                }

                if(!gamedeck.getDeck().isEmpty()) {
                    TextView temp = findViewById(R.id.remTurn);
                    temp.setText("Turns Remaining:\n" + String.valueOf(gamedeck.getDeck().size()/2+player1Hand.size()));

                    player1Hand.add(gamedeck.dealCard());
                    player2Hand.add(gamedeck.dealCard());
                }
                else{
                    for(int i=player1Hand.size();i<7;i++){
                        String imageSlot = "card" + String.valueOf(i);
                        int resID = R.drawable.gray_back;
                        ImageView temp = (ImageView) findViewById(getResources().getIdentifier(imageSlot, "id", getPackageName()));
                        temp.setImageDrawable(getResources().getDrawable(resID, null));
                    }
                }

                if(player1Hand.size() == 0){
                    gameStarted = false;
                    if(player1Score > player2Score) {
                        TextView temp = findViewById(R.id.selectCardText);
                        temp.setText("You Win!\nPlay Again?");
                    }
                    else if(player2Score > player1Score){
                        TextView temp = findViewById(R.id.selectCardText);
                        temp.setText("You Lose!\nPlay Again?");
                    }
                    else{
                        TextView temp = findViewById(R.id.selectCardText);
                        temp.setText("It's a Draw!\nPlay Again?");
                    }
                    tempButton = (Button) findViewById(R.id.gameButton);
                    tempButton.setText("Play Again");
                }

                updateCards();
                selectedCard = null;

                Log.d("Score", String.valueOf(player1Score));
            }

        }
        else if(gameStarted && needContinue){
            needContinue = false;
            Button tempButton = (Button) findViewById(R.id.gameButton);
            tempButton.setText("Play Card");
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("gray_back", "drawable", getPackageName()),null));
            temp = (ImageView) findViewById(R.id.cpucard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("gray_back", "drawable", getPackageName()),null));
        }
        else{
            gameStarted = true;
            Button temp = (Button) findViewById(R.id.gameButton);
            temp.setText("Play Card");
            TextView temptext = (TextView) findViewById(R.id.selectCardText);
            temptext.setVisibility(View.VISIBLE);
            gamedeck = new Deck(1);
            player1Hand = new ArrayList<Card>();
            player2Hand = new ArrayList<Card>();
            player1Score = 0;
            player2Score = 0;

            temptext = findViewById(R.id.playerScore);
            temptext.setText("Your Score:\n" + player1Score);

            temptext = findViewById(R.id.cpuScore);
            temptext.setText("CPU Score:\n" + player2Score);

            ImageView tempimage = (ImageView) findViewById(R.id.playercard);
            tempimage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("gray_back", "drawable", getPackageName()),null));
            tempimage = (ImageView) findViewById(R.id.cpucard);
            tempimage.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("gray_back", "drawable", getPackageName()),null));


            for(int i=0;i<7;i++) {
                player1Hand.add(gamedeck.dealCard());
                player2Hand.add(gamedeck.dealCard());
            }

            pointsCount = 1;

            updateCards();
        }
    }

    //Update the images for each card in the player's hand
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

    //When button selected in hand, update the player's choice
    // /**********

    public void selectCard0(View view) {
        if (gameStarted && !needContinue) {
            selectedCard = player1Hand.get(0);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));

        }
    }
    public void selectCard1(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 1) {
            selectedCard = player1Hand.get(1);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    public void selectCard2(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 2) {
            selectedCard = player1Hand.get(2);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    public void selectCard3(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 3) {
            selectedCard = player1Hand.get(3);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    public void selectCard4(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 4) {
            selectedCard = player1Hand.get(4);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    public void selectCard5(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 5) {
            selectedCard = player1Hand.get(5);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    public void selectCard6(View view) {
        if (gameStarted && !needContinue && player1Hand.size() > 6) {
            selectedCard = player1Hand.get(6);
            String name = selectedCard.getShortName();
            ImageView temp = (ImageView) findViewById(R.id.playercard);
            temp.setImageDrawable(getResources().getDrawable(getResources().getIdentifier(name, "drawable", getPackageName()), null));
        }
    }

    // ********/
}
