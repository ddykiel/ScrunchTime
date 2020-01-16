package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Button buttonStartApp = (Button) findViewById(R.id.buttonStartApp);

        // When user clicks "Start App," create RoommateModel and RoomModel objects
        buttonStartApp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                // Access text the user input and convert it to strings/ints to create RoomModel and RoommateModel objects
                EditText userName = (EditText) findViewById(R.id.editTextUserName);
                String userNameString = userName.getText().toString();
                System.out.println("User name: " + userNameString);

                EditText roommateName = (EditText) findViewById(R.id.editTextRoommateName);
                String roommateNameString = roommateName.getText().toString();

                EditText roommateID = (EditText) findViewById(R.id.editTextRoommateID);
                int roommateIDInt = Integer.parseInt(roommateID.getText().toString());

                EditText roomName = (EditText) findViewById(R.id.editTextRoomName);
                String roomNameString = roomName.getText().toString();

                // Hard-coded for client testing: later these ints will be accessed from server
                int userIDInt = 00000;

                RoommateModel user = new RoommateModel(true, userNameString, userIDInt);
                RoommateModel firstRoomie = new RoommateModel(false, roommateNameString, roommateIDInt);
                RoomModel userRoom = new RoomModel(roomNameString, user, firstRoomie);

                Intent startIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startIntent.putExtra("user", user);
                startIntent.putExtra("firstRoomie", firstRoomie);
                startActivity(startIntent);

            }
        });
    }
}
