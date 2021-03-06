package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        // Create the button to go to the Home Screen and the button to add a roommate
        Button buttonHomeScreen = (Button) findViewById(R.id.buttonHomeScreen);
        Button buttonAddRoommate = (Button) findViewById(R.id.buttonAddRoommate);

        // Create/Access objects from the last activity
        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        final ServerTranslator translator = userRoom.getTranslator();
        final RoommateModel user = userRoom.getUser();

        // Show the user's ServerID on the screen (which their roommmates would need to use to add the user to their app)
        TextView textViewYourID = (TextView) findViewById(R.id.textViewYourID);
        String userServerID = "Your ID is: " + translator.convertID(user);
        textViewYourID.setText(userServerID);

        // If the user clicks the button to go the home screen, take them to that screen.
        // Carry over the roomModel object
        buttonHomeScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startActivity(startIntent);
            }
        });

        // If the user clicks the button to go add a roommate, take them to that activity.
        // Carry over the roomModel object
        buttonAddRoommate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), AddRoommateActivity.class);
                startIntent.putExtra("userRoom", userRoom);
                startActivity(startIntent);
            }
        });

    }
}
