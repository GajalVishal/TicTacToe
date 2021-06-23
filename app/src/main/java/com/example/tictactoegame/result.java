package com.example.tictactoegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class result extends AppCompatActivity {
    int active_player = 0;
    int  []place={2,2,2,2,2,2,2,2,2};
    int [] [] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8}, {2,4,6}};
    String winner;
    int tie=1;
 boolean gameOn=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar actiomBar;
         actiomBar =findViewById(R.id.main_toolbar);


      setSupportActionBar(actiomBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }
    public boolean onOptionsItemSelected(@NonNull MenuItem actionBar) {
        switch (actionBar.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(actionBar);
    }


    public void game(View view) {
        ImageView img = (ImageView) view;
        int tag = Integer.parseInt(img.getTag().toString());
        if(!gameOn){
            reset(view);
        }
      if (place[tag]==2){
          place[tag]=active_player;
          if (active_player == 0) {
              img.setImageResource(R.drawable.o);
              active_player = 1;

          } else {
              img.setImageResource(R.drawable.x);
              active_player = 0;
          }
            tie++;
      }
      else {
          Toast.makeText(this, "Place is already filled, please choose another", Toast.LENGTH_SHORT).show();
      }
        for (int []arr:winPositions) {
            if (!gameOn){
                reset(view);
            }
            if (place[arr[0]] == place[arr[1]] && place[arr[1]] == place[arr[2]] && place[arr[0]]!=2 ){
                    gameOn=false;
                if (place[arr[0]]==0){
                    winner="0 WINS";


                }
                else {

                    winner="X WINS";

                }
              playerWinner(winner,view);
            }


        }
        if (tie>9){
           playerWinner("tie ",view);

        }
    }

    private void playerWinner(String msg,View view) {
        final AlertDialog dialog=new AlertDialog.Builder(result.this)
                .setTitle("Winner is :")
                .setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        reset(view);
                    }
                })
                .create();
        dialog.show();

    }

    private void reset(View view) {active_player=0;
        for(int i=0; i<place.length; i++){
            place[i] = 2;
        }
        ((ImageView)findViewById(R.id.image1)).setImageResource(0);
        ((ImageView)findViewById(R.id.image2)).setImageResource(0);
        ((ImageView)findViewById(R.id.image3)).setImageResource(0);
        ((ImageView)findViewById(R.id.image4)).setImageResource(0);
        ((ImageView)findViewById(R.id.image5)).setImageResource(0);
        ((ImageView)findViewById(R.id.image6)).setImageResource(0);
        ((ImageView)findViewById(R.id.image7)).setImageResource(0);
        ((ImageView)findViewById(R.id.image8)).setImageResource(0);
        ((ImageView)findViewById(R.id.image9)).setImageResource(0);
        tie=1;
        gameOn=true;


    }
}