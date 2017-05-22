package com.example.hp.finalmobiletracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText emid;
    String emd1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }

        public void onClickButtonToTrack(View v){

            emid = (EditText) findViewById(R.id.et1);
            emd1 = emid.getText().toString();
            finish();
            Intent i = new Intent(Main3Activity.this,Main4Activity.class);
            i.putExtra("Recipient",emd1);
            startActivity(i);
    }

    }

