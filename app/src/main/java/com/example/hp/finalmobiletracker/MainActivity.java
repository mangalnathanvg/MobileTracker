package com.example.hp.finalmobiletracker;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Count down method for executing splash screen
        new CountDownTimer(3000,1000){
            public void onTick(long millisUntilFinished){

            }
            // Statements to execute after countdown.
            public void onFinish(){
                finish();
                Intent i = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }

        }.start();//Starting Countdown.
    }
}
