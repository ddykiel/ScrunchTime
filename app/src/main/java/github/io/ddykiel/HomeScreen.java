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
        switchActiveScrunchie.setTextOn("Active");
        switchActiveScrunchie.setTextOff("Inactive");

        final ImageView imageViewScrunchie = (ImageView) findViewById(R.id.imageViewScrunchie);
        final ImageView imageViewFirstRoomie = (ImageView) findViewById(R.id.imageViewFirstRoomie);
        final ImageView imageViewSecondRoomie = (ImageView) findViewById(R.id.imageViewSecondRoomie);
        final ImageView imageViewThirdRoomie = (ImageView) findViewById(R.id.imageViewThirdRoomie);

        TextView textViewFirstRoomie = (TextView) findViewById(R.id.textViewFirstRoomie);
        TextView textViewSecondRoomie = (TextView) findViewById(R.id.textViewSecondRoomie);
        TextView textViewThirdRoomie = (TextView) findViewById(R.id.textViewThirdRoomie);

        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        final RoommateModel user = userRoom.getUser();
        ArrayList<RoommateModel> roomies = userRoom.getRoommates();

        // Create roommates: there can be up to 3

        int numRoomies = roomies.size();
        String secondRoomieName = "";
        String thirdRoomieName = "";

        final RoommateModel firstRoomie = roomies.get(0);
        if (roomies.size() > 1){
            final RoommateModel secondRoomie = roomies.get(1);
            secondRoomieName = secondRoomie.getRoommateName();
            setRoommateScrunchieColor(imageViewSecondRoomie, secondRoomie);
        }
        if (roomies.size() > 2){
            final RoommateModel thirdRoomie = roomies.get(2);
            thirdRoomieName = thirdRoomie.getRoommateName();
            setRoommateScrunchieColor(imageViewThirdRoomie, thirdRoomie);
        }

        textViewFirstRoomie.setText(firstRoomie.getRoommateName());
        textViewSecondRoomie.setText(secondRoomieName);
        textViewThirdRoomie.setText(thirdRoomieName);

        TextView textViewRoom = (TextView) findViewById(R.id.textViewRoom);
        String textViewRoomString = "You are in room " + userRoom.getRoomName();
        textViewRoom.setText(textViewRoomString);

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
        /*final Handler handler = new Handler();

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
                    //System.out.println("Print test");
                    handler.postDelayed(this, 30,000);

                }
            });*/


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
                        user.setStatus("INACTIVE");
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
                    user.setStatus("RED");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    // Call server to update ID
                }
            });

        buttonPurpleStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("PURPLE");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    // Call server to update ID
                }
            });

        buttonBlueStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("BLUE");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    // Call server to update ID
                }
            });

        buttonGreenStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("GREEN");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    // Call server to update ID
                }
            });

        buttonYellowStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("YELLOW");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    // Call server to update ID
                }
            });
        buttonInfoScreen.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(), InfoScreen.class);
                    startIntent.putExtra("userRoom", userRoom);
                    startActivity(startIntent);
            }
        });

    }

    // Right now, we only call this function once. In future versions of the app, we want to call this function every time the background update method registers a change in a roommate status.
    public void setRoommateScrunchieColor(ImageView i, RoommateModel r){
        String status = r.getStatus();

        if (status.equals("RED")){
            i.setImageResource(R.drawable.redscrunchie);
        }
        if (status.equals("PURPLE")){
            i.setImageResource(R.drawable.purplescrunchie);
        }
        if (status.equals("BLUE")){
            i.setImageResource(R.drawable.bluescrunchie);
        }
        if (status.equals("GREEN")){
            i.setImageResource(R.drawable.greenscrunchie);
        }
        if (status.equals("YELLOW")){
            i.setImageResource(R.drawable.yellowscrunchie);
        }
        if (status.equals("INACTIVE")){
            i.setImageResource(R.drawable.grayscrunchie);
        }

    }

}
