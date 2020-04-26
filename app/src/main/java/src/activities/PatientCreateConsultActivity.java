package src.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import e.wolfsoft1.src.R;
import src.Views.consult.ConsultView;
import src.Views.consult.PatientCreateConsultView;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class PatientCreateConsultActivity extends AppCompatActivity {

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        ConstraintLayout root = findViewById(R.id.consult_root_layout);


        PatientProfileDto profile = new PatientProfileDto();
        Intent intent = getIntent();
        try{
            profile = (PatientProfileDto) intent.getSerializableExtra("PatientProfile");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        final ConsultView consultView = new PatientCreateConsultView(getApplicationContext(), profile);
        root.addView(consultView);
    }
}
