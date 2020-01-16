package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);

        Button buttonHomeScreen = (Button) findViewById(R.id.buttonHomeScreen);

        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        final RoommateModel user = (RoommateModel) intent.getSerializableExtra("user");
        final RoommateModel firstRoomie = (RoommateModel) intent.getSerializableExtra("firstRoomie");

        buttonHomeScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startIntent.putExtra("user", user);
                startIntent.putExtra("firstRoomie", firstRoomie);
                startActivity(startIntent);
            }
        });

    }
}
