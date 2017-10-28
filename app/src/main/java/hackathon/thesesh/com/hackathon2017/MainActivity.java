package hackathon.thesesh.com.hackathon2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createButton = findViewById(R.id.create_complaint_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // start new
            }
        });
    }
}
