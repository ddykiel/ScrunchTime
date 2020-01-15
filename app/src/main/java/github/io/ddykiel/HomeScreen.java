package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        final Switch switchActiveScrunchie = (Switch) findViewById(R.id.switchActiveScrunchie);

        final ImageView imageViewScrunchie = (ImageView) findViewById(R.id.imageViewScrunchie);

        Intent intent = getIntent();
        RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        RoommateModel user = (RoommateModel) intent.getSerializableExtra("user");
        RoommateModel firstRoomie = (RoommateModel) intent.getSerializableExtra("firstRoomie");

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
        }

        switchActiveScrunchie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if (isChecked){
                    for (Button b: statusButtons){
                        b.setEnabled(true);
                    }
                    /* buttonRedStatus.setEnabled(true);
                    buttonPurpleStatus.setEnabled(true);
                    buttonBlueStatus.setEnabled(true);
                    buttonGreenStatus.setEnabled(true);
                    buttonYellowStatus.setEnabled(true);
                    System.out.println("Buttons enabled!");*/
                } else {
                    /*buttonRedStatus.setEnabled(false);
                    buttonPurpleStatus.setEnabled(false);
                    buttonBlueStatus.setEnabled(false);
                    buttonGreenStatus.setEnabled(false);
                    buttonYellowStatus.setEnabled(false);
                    System.out.println("Buttons disabled!");*/
                    for (Button b: statusButtons){
                        b.setEnabled(false);
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

    }

    // Figure out how to make these methods work
    public ArrayList<Button> enableButtons(ArrayList<Button> statusButtons){
        for (Button b: statusButtons){
            b.setEnabled(true);
        }
        return statusButtons;
    }

    public ArrayList<Button> disableButtons(ArrayList<Button> statusButtons){
        for (Button b: statusButtons){
            b.setEnabled(false);
        }
        return statusButtons;
    }

}
