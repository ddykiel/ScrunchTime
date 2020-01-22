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

        // Create button objects that the user uses to change their status
        final Button buttonRedStatus = (Button) findViewById(R.id.buttonRedStatus);
        final Button buttonPurpleStatus = (Button) findViewById(R.id.buttonPurpleStatus);
        final Button buttonBlueStatus = (Button) findViewById(R.id.buttonBlueStatus);
        final Button buttonGreenStatus = (Button) findViewById(R.id.buttonGreenStatus);
        final Button buttonYellowStatus = (Button) findViewById(R.id.buttonYellowStatus);

        // Create button user clicks to go to the Info Screen
        final Button buttonInfoScreen = (Button) findViewById(R.id.buttonInfoScreen);

        // Create the switch for Active/Inactive and set the text depending on its state
        final Switch switchActiveScrunchie = (Switch) findViewById(R.id.switchActiveScrunchie);
        switchActiveScrunchie.setTextOn("Active");
        switchActiveScrunchie.setTextOff("Inactive");

        // Create scrunchie image for user
        final ImageView imageViewScrunchie = (ImageView) findViewById(R.id.imageViewScrunchie);
        // Create scrunchie images for roomies. If the roomie hasn't been created, defaults to a transparent square
        final ImageView imageViewFirstRoomie = (ImageView) findViewById(R.id.imageViewFirstRoomie);
        final ImageView imageViewSecondRoomie = (ImageView) findViewById(R.id.imageViewSecondRoomie);
        final ImageView imageViewThirdRoomie = (ImageView) findViewById(R.id.imageViewThirdRoomie);

        // Create Text Views for roommate names. If the roomie hasn't been created, defaults to an empty string.
        TextView textViewFirstRoomie = (TextView) findViewById(R.id.textViewFirstRoomie);
        TextView textViewSecondRoomie = (TextView) findViewById(R.id.textViewSecondRoomie);
        TextView textViewThirdRoomie = (TextView) findViewById(R.id.textViewThirdRoomie);

        // Create/Access objects from the last activity
        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        // Access other objects from the RoomModel
        final RoommateModel user = userRoom.getUser();
        ArrayList<RoommateModel> roomies = userRoom.getRoommates();

        //  Create/Access a ServerTranslator. It translates language used on the client side to language used on the server side.
        // For more information, look at the comments for that class.
        final ServerTranslator translator = userRoom.getTranslator();

        // Create information for roommates and access RoommateModel Objoects. There can be up to 3
        int numRoomies = roomies.size();
        String secondRoomieName = "";
        String thirdRoomieName = "";

        final RoommateModel firstRoomie = roomies.get(0); // There's always at least one roommate
        // If there's a second roommate, create them
        if (roomies.size() > 1){
            final RoommateModel secondRoomie = roomies.get(1);
            secondRoomieName = secondRoomie.getRoommateName();
            setRoommateScrunchieColor(imageViewSecondRoomie, secondRoomie);
        }
        // If there's a third roommate, create them
        if (roomies.size() > 2){
            final RoommateModel thirdRoomie = roomies.get(2);
            thirdRoomieName = thirdRoomie.getRoommateName();
            setRoommateScrunchieColor(imageViewThirdRoomie, thirdRoomie);
        }

        // Set roommate names. Defaults to empty strings if they're nonexistant.
        textViewFirstRoomie.setText(firstRoomie.getRoommateName());
        textViewSecondRoomie.setText(secondRoomieName);
        textViewThirdRoomie.setText(thirdRoomieName);

        // Display what room the user is in
        TextView textViewRoom = (TextView) findViewById(R.id.textViewRoom);
        String textViewRoomString = "You are in room " + userRoom.getRoomName();
        textViewRoom.setText(textViewRoomString);

        // Make an ArrayList of buttons to change statuses to make later code less verbose
        final ArrayList<Button> statusButtons = new ArrayList<Button>();
        statusButtons.add(buttonRedStatus);
        statusButtons.add(buttonPurpleStatus);
        statusButtons.add(buttonBlueStatus);
        statusButtons.add(buttonGreenStatus);
        statusButtons.add(buttonYellowStatus);

        if (user.getStatus().equals("INACTIVE")){
            // If the user is inactive, disable the switch. Also disable and gray out the status buttons.
            switchActiveScrunchie.setChecked(false);
            for (Button b: statusButtons){
                b.setEnabled(false);
                b.setBackgroundColor(Color.parseColor("#d8d8d8"));
            }
        } else {
            // If the user is active, reflect this in the switch.
            switchActiveScrunchie.setChecked(true);
        }

        // Set the image for the user's scrunchie
        setRoommateScrunchieColor(imageViewScrunchie, user);

        // This code is supposed to run in the background every 30 seconds
        // I could get it to run continuously in the background, but not every 30 seconds
        // The postdelayed method requires an API of 29 or above to work. My phone is at API 25, and my emulator stopped working when the code got more complex.
        // As such, I commented it out so I could test the other code on my phone.
        // To get this function to work, it would have to be tested on an emulator.
        /*final TextView textViewHandlerTest = (TextView) findViewById(R.id.textViewHandlerTest);
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
                    //System.out.println("Print test");
                    handler.postDelayed(this, 30,000);

                }
            });*/

        switchActiveScrunchie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                    if (isChecked){
                        // If the user is active, allow them to click the buttons.
                        // Also change the button background colors based on what status it corresponds with.
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
                        // If the switch is unchecked (i.e. the user is inactive,) disable all the buttons and turn them gray.
                        for (Button b: statusButtons){
                            b.setEnabled(false);
                            b.setBackgroundColor(Color.parseColor("#d8d8d8"));
                        }
                        // If the user is inactive, change their scrunchie color to gray.
                        setRoommateScrunchieColor(imageViewScrunchie, user);
                    }
                }
            });

        // These onClickListeners execute code when a certain status button is clicked
        // They change the user's status and change the user's scrunchie color.
        // Eventually, they will also tell the server the user's ID has changed.
        // The translator gives an int that represents the user's status
        // The translator gives a serverID based on the user's clientID
        // This status and ID is used to setIDState on the server side
        buttonRedStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("RED");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    /* int userServerID = translator.convertID(user);
                    int userServerStatus = translator.convertStatus(user);
                    serverObject.setIDState(userServerID, userServerStatus); */
                }
            });

        buttonPurpleStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("PURPLE");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                    /* int userServerID = translator.convertID(user);
                    int userServerStatus = translator.convertStatus(user);
                    serverObject.setIDState(userServerID, userServerStatus); */
                }
            });

        buttonBlueStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("BLUE");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                     /* int userServerID = translator.convertID(user);
                    int userServerStatus = translator.convertStatus(user);
                    serverObject.setIDState(userServerID, userServerStatus); */
                }
            });

        buttonGreenStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("GREEN");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                     /* int userServerID = translator.convertID(user);
                    int userServerStatus = translator.convertStatus(user);
                    serverObject.setIDState(userServerID, userServerStatus); */
                }
            });

        buttonYellowStatus.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    user.setStatus("YELLOW");
                    setRoommateScrunchieColor(imageViewScrunchie, user);
                     /* int userServerID = translator.convertID(user);
                    int userServerStatus = translator.convertStatus(user);
                    serverObject.setIDState(userServerID, userServerStatus); */
                }
            });

        // If the user clicks the button to go the info screen, take them to that screen.
        // Carry over the roomModel object
        buttonInfoScreen.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent startIntent = new Intent(getApplicationContext(), InfoScreen.class);
                    startIntent.putExtra("userRoom", userRoom);
                    startActivity(startIntent);
            }
        });

    }

    // This function takes an ImageView and RoommateModel
    // It changes the image for the RoommateModel's scrunchie to an image of the appropriately-colored scrunchie
    // In this version of the app, it's only used to change the user's scrunchie color
    // In future versions of the app, the background eventHandler should run this function for a roommate every time their status changes.
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
