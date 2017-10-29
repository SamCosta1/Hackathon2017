package hackathon.thesesh.com.hackathon2017;

import android.arch.lifecycle.Observer;
import android.opengl.GLSurfaceView;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import hackathon.thesesh.com.hackathon2017.engine.OmbudsmanApplication;

public class NavigationActivity extends AppCompatActivity {

    private GLSurfaceView surfaceView;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        setupViews();
        setupValidityChecker();

        surfaceView.setRenderer(new NavigationRenderer(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        surfaceView.onResume();
    }

    private void setupViews() {
        submit = findViewById(R.id.submit_button);
        surfaceView = findViewById(R.id.surfaceView);
    }

    private void setupValidityChecker() {
        ((OmbudsmanApplication) getApplication()).getFormData().getValidity().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean valid) {
                submit.setEnabled(valid);
            }
        });
    }
}
