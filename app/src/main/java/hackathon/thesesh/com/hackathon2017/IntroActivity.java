package hackathon.thesesh.com.hackathon2017;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import hackathon.thesesh.com.hackathon2017.engine.OmbudsmanApplication;
import hackathon.thesesh.com.hackathon2017.engine.model.FormData;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameTextData;
    private EditText emailTextData;
    private EditText complaintTextData;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        setupViews();
        goButton.setOnClickListener(this);
    }

    public static Intent createIntent(Context context) {
        return new Intent(context, IntroActivity.class);
    }

    private void setupViews() {
        nameTextData = findViewById(R.id.nameText);
        emailTextData = findViewById(R.id.emailText);
        complaintTextData = findViewById(R.id.complaintText);
        goButton = findViewById((R.id.go_button));
    }

    @Override
    public void onClick(View view) {
        FormData formData = ((OmbudsmanApplication)getApplication()).getFormData();
        if (editTextPopulated(nameTextData) && editTextPopulated(emailTextData) && editTextPopulated(complaintTextData)) {
            formData.setName(nameTextData.getText().toString());
            formData.setEmail(emailTextData.getText().toString());
            formData.setComplaint(complaintTextData.getText().toString());
        }
    }

    private boolean editTextPopulated(EditText textData) {
        if (textData.getText().toString().trim().length() == 0) {
            return false;
        }
        return true;
    }
}
