package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddRoommateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommate);

        // Create/Access objects from the last activity
        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        ArrayList<RoommateModel> roomies = userRoom.getRoommates();
        final int numRoomies = roomies.size();

        // Button user clicks after inputting information about a new roommate - creates roommate
        Button buttonCreateRoommate = (Button) findViewById(R.id.buttonCreateRoommate);

        buttonCreateRoommate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                // Get the user's input from EditTexts and convert them to other data types

                EditText editTextRoommateName = (EditText) findViewById(R.id.editTextRoommateName);
                final String roommateNameString = editTextRoommateName.getText().toString();

                EditText editTextRoommateID = (EditText) findViewById(R.id.editTextRoommateID);
                final String roommateIDString = editTextRoommateID.getText().toString();

                int roommateIDInt = -1; // Default if nothing is entered

                // If the user entered something for the roommate's ID, convert it to an int
                if (!roommateIDString.matches("")){
                    roommateIDInt = Integer.parseInt(roommateIDString);
                }

                // Get the RoomModel's ServerTranslator object
                ServerTranslator translator = userRoom.getTranslator();

                // Client IDs are 0 for first roomie, 1 for second roomie, 2 for 3rd roomie
                // Correspond with positions in the roomies ArrayList<RoommateModel>
                if (numRoomies == 1){
                    RoommateModel secondRoomie = new RoommateModel(false, roommateNameString, 1);
                    translator.addID(secondRoomie, roommateIDInt);
                    userRoom.addRoommate(secondRoomie);
                }
                if (numRoomies == 2){
                    RoommateModel thirdRoomie = new RoommateModel(false, roommateNameString, 2);
                    translator.addID(thirdRoomie, roommateIDInt);
                    userRoom.addRoommate(thirdRoomie);
                }
                // Give the user an error message if they try to add a fourth roommate
                if (numRoomies > 2){
                    Toast.makeText(AddRoommateActivity.this, "This version of the app only supports 3 other roommates", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Checks to see if the user left any fields blank
                boolean conditions = roommateNameString.matches("") || roommateIDString.matches("");

                // If the user left any fields blank, give them an error message and exit the function
                if (conditions){
                    Toast.makeText(AddRoommateActivity.this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Once the new roommate has been added, bring the user back to the Info Screen
                // Carry over the RoomModel object
                Intent startIntent = new Intent(getApplicationContext(), InfoScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startActivity(startIntent);
            }
        });
    }
}
