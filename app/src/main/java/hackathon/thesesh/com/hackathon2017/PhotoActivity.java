package hackathon.thesesh.com.hackathon2017;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Button photoButton = findViewById(R.id.photo_button);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                capture(view);



            }
        });
        drawView = (DrawingView)findViewById(R.id.drawing);
        drawView.setEnabled(false);

    }

    private DrawingView drawView;
    static final int REQUEST_PIC = 1;

    public void capture(View view) {
        Intent takepicintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takepicintent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takepicintent, REQUEST_PIC);
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PIC && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
            drawView.setEnabled(true);
        }
    }




}
