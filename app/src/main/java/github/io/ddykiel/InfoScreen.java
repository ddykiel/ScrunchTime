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
        Button buttonAddRoommate = (Button) findViewById(R.id.buttonAddRoommate);

        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");

        buttonHomeScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), HomeScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startActivity(startIntent);
            }
        });

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
