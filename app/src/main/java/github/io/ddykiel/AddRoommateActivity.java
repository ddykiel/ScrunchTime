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

        Intent intent = getIntent();
        final RoomModel userRoom = (RoomModel) intent.getSerializableExtra("userRoom");
        ArrayList<RoommateModel> roomies = userRoom.getRoommates();
        final int numRoomies = roomies.size();
        System.out.println("Num roomies: " + numRoomies);

        Button buttonCreateRoommate = (Button) findViewById(R.id.buttonCreateRoommate);

        buttonCreateRoommate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                EditText editTextRoommateName = (EditText) findViewById(R.id.editTextRoommateName);
                final String roommateNameString = editTextRoommateName.getText().toString();

                EditText editTextRoommateID = (EditText) findViewById(R.id.editTextRoommateID);
                final String roommateIDString = editTextRoommateID.getText().toString();

                int roommateIDInt = -1;

                if (!roommateIDString.matches("")){
                    roommateIDInt = Integer.parseInt(roommateIDString);
                }

                ServerTranslator translator = userRoom.getTranslator();

                if (numRoomies == 1){
                    RoommateModel secondRoomie = new RoommateModel(false, roommateNameString, 1);
                    translator.addID(secondRoomie);
                    userRoom.addRoommate(secondRoomie);
                }
                if (numRoomies == 2){
                    RoommateModel thirdRoomie = new RoommateModel(false, roommateNameString, 2);
                    translator.addID(thirdRoomie);
                    userRoom.addRoommate(thirdRoomie);
                }
                if (numRoomies > 2){
                    Toast.makeText(AddRoommateActivity.this, "This version of the app only supports 3 other roommates", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean conditions = roommateNameString.matches("") || roommateIDString.matches("");

                if (conditions){
                    Toast.makeText(AddRoommateActivity.this, "Make sure all fields are entered", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent startIntent = new Intent(getApplicationContext(), InfoScreen.class);
                startIntent.putExtra("userRoom", userRoom);
                startActivity(startIntent);
            }
        });
    }
}
