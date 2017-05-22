package com.example.hp.finalmobiletracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


    }
    public void onClickButton1(View v){
        //Intent to move among activities.
        finish();
        Intent i = new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(i);

    }
}
