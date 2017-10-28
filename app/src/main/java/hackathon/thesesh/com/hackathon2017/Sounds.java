package hackathon.thesesh.com.hackathon2017;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sounds extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sounds);
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.sound);
            Button createButton = findViewById(R.id.create_complaint_button);
            createButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    mp.start();
                }
            });
        }
}
