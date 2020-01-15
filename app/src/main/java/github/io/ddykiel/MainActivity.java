package github.io.ddykiel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //Go directly to the WelcomeScreen upon starting
        Intent startIntent = new Intent(getApplicationContext(), WelcomeScreen.class);
        startActivity(startIntent);
    }
}
