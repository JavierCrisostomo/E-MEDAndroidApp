package src.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.consult.ConsultView;
import src.Views.consult.PatientCreateConsultView;
import src.domain.PacientProfileDto;
import src.service.impl.PacientProfileServiceMock;
import src.service.interfaces.IProfileService;

public class PatientCreateConsultActivity extends AppCompatActivity {

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        ConstraintLayout root = findViewById(R.id.consult_root_layout);


        PacientProfileDto profile = new PacientProfileDto();
        Intent intent = getIntent();
        try{
            profile = (PacientProfileDto) intent.getSerializableExtra("PatientProfile");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        final ConsultView consultView = new PatientCreateConsultView(getApplicationContext(), profile);
        root.addView(consultView);
    }
}
