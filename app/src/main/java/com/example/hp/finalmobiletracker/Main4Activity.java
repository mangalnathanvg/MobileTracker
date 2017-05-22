package com.example.hp.finalmobiletracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main4Activity extends Activity implements View.OnClickListener {


    Session session = null;

    Context context = null;
    // GPSTracker class
    GPSTracker gps;
    String loc,rec,subject,textMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Bundle bundle = getIntent().getExtras();
        rec = bundle.getString("Recipient");
    }

    @Override
    public void onClick(View v) {

        // create class object
        gps = new GPSTracker(Main4Activity.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
+
            // n is for new line
            loc = latitude+","+longitude;
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }

        subject = "Current Location of your ward";
        textMessage = "http://www.google.com/maps/place/"+loc;

        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");


        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("mvbtestapplication@gmail.com","androidappmvb");
            }
        });


        RetrieveFeedTask task = new RetrieveFeedTask();
        task.execute();



    }

    public void onClickExit(View view) {
        finish();
        Intent i = new Intent(Main4Activity.this,Main2Activity.class);
        startActivity(i);
    }

    public class RetrieveFeedTask extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... params) {
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("mvbtestapplication@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage,"text/html; charset=utf-8");

                Transport.send(message);

            } catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getApplicationContext(),"Message sent with Latitude",Toast.LENGTH_LONG).show();
        }
    }

    }

