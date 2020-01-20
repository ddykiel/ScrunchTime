package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                EditText roommateName = (EditText) findViewById(R.id.editTextRoommateName);
                String roommateNameString = roommateName.getText().toString();

                int roommateIDInt = -1; // Default value

                EditText roommateID = (EditText) findViewById(R.id.editTextRoommateID);
                String roommateIDString = roommateID.getText().toString();
                if (!roommateIDString.matches("")){
                    roommateIDInt = Integer.parseInt(roommateIDString);
                }

                EditText roomName = (EditText) findViewById(R.id.editTextRoomName);
                String roomNameString = roomName.getText().toString();

                // Hard-coded for client testing: later these ints will be accessed from server
                int userIDInt = 00000;

                // Checks if any fields are left empty
                boolean conditions = userNameString.matches("") || roommateNameString.matches("")
                        || roommateIDString.matches("") || roomNameString.matches("");


                if (!conditions){
                    RoommateModel user = new RoommateModel(true, userNameString, 0);
                    RoommateModel firstRoomie = new RoommateModel(false, roommateNameString, 1);
                    RoomModel userRoom = new RoomModel(roomNameString, user, firstRoomie);

                    ServerTranslator translator = userRoom.getTranslator();
                    translator.addID(user);
                    translator.addID(firstRoomie);

                    Intent startIntent = new Intent(getApplicationContext(), HomeScreen.class);
                    startIntent.putExtra("userRoom", userRoom);
                    startActivity(startIntent);
                }

                if (conditions){
                    Toast.makeText(WelcomeScreen.this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }
}
