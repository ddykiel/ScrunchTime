package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import android.os.Handler;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        final Button buttonRedStatus = (Button) findViewById(R.id.buttonRedStatus);
        final Button buttonPurpleStatus = (Button) findViewById(R.id.buttonPurpleStatus);
        final Button buttonBlueStatus = (Button) findViewById(R.id.buttonBlueStatus);
        final Button buttonGreenStatus = (Button) findViewById(R.id.buttonGreenStatus);
        final Button buttonYellowStatus = (Button) findViewById(R.id.buttonYellowStatus);

        final Button buttonInfoScreen = (Button) findViewById(R.id.buttonInfoScreen);

        final Switch switchActiveScrunchie = (Switch) findViewById(R.id.switchActiveScrunchie);

        final ImageView imageViewScrunchie = (ImageView) findViewById(R.id.imageViewScrunchie);

        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        final RoommateModel user = (RoommateModel) intent.getSerializableExtra("user");
        final RoommateModel firstRoomie = (RoommateModel) intent.getSerializableExtra("firstRoomie");

        TextView textViewRoom = (TextView) findViewById(R.id.textViewRoom);
        String textViewRoomString = "You are in room " + userRoom.getRoomName();
        textViewRoom.setText(textViewRoomString);

        TextView textViewRoommate1 = (TextView) findViewById(R.id.textViewRoommate1);
        textViewRoommate1.setText(firstRoomie.getRoommateName());


        final ArrayList<Button> statusButtons = new ArrayList<Button>();
        statusButtons.add(buttonRedStatus);
        statusButtons.add(buttonPurpleStatus);
        statusButtons.add(buttonBlueStatus);
        statusButtons.add(buttonGreenStatus);
        statusButtons.add(buttonYellowStatus);

        for (Button b: statusButtons){
            b.setEnabled(false);
            b.setBackgroundColor(Color.parseColor("#d8d8d8"));
        }

        final TextView textViewHandlerTest = (TextView) findViewById(R.id.textViewHandlerTest);
        final Handler handler = new Handler();

        handler.post(new Runnable(){
            boolean defaultHandlerState = false;
            @Override
            public void run(){
                if (defaultHandlerState){
                    textViewHandlerTest.setText("State A");
                    //defaultHandlerState = !defaultHandlerState;
                    //handler.postDelayed(this, 30,000);
                } else {
                    textViewHandlerTest.setText("State B");
                    //defaultHandlerState = !defaultHandlerState;
                    //handler.postDelayed(this, 30,000);
                }
                defaultHandlerState = !defaultHandlerState;
                handler.postDelayed(this, 30,000);
            }
        });


        switchActiveScrunchie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    for (Button b: statusButtons){
                        b.setEnabled(true);
                        if (b.equals(buttonRedStatus)){
                            b.setBackgroundColor(Color.parseColor("#eb7573"));
                        }
                        if (b.equals(buttonPurpleStatus)){
                            b.setBackgroundColor(Color.parseColor("#b476c0"));
                        }
                        if (b.equals(buttonBlueStatus)){
                            b.setBackgroundColor(Color.parseColor("#7f7ebc"));
                        }
                        if (b.equals(buttonGreenStatus)){
                            b.setBackgroundColor(Color.parseColor("#73ad6d"));
                        }
                        if (b.equals(buttonYellowStatus)){
                            b.setBackgroundColor(Color.parseColor("#e6d996"));
                        }
                    }
                } else {
                    for (Button b: statusButtons){
                        b.setEnabled(false);
                        b.setBackgroundColor(Color.parseColor("#d8d8d8"));
                    }
                    imageViewScrunchie.setImageResource(R.drawable.grayscrunchie);
                }
            }
        });

        buttonRedStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageViewScrunchie.setImageResource(R.drawable.redscrunchie);
                // Call server to update ID
            }
        });

        buttonPurpleStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageViewScrunchie.setImageResource(R.drawable.purplescrunchie);
            }
        });

        buttonBlueStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageViewScrunchie.setImageResource(R.drawable.bluescrunchie);
            }
        });

        buttonGreenStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageViewScrunchie.setImageResource(R.drawable.greenscrunchie);
            }
        });

        buttonYellowStatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                imageViewScrunchie.setImageResource(R.drawable.yellowscrunchie);
            }
        });
        buttonInfoScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), InfoScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startIntent.putExtra("user", user);
                startIntent.putExtra("firstRoomie", firstRoomie);
                startActivity(startIntent);
            }
        });


    }

}
