package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import e.wolfsoft1.src.R;
import src.Views.consult.CommentsEditableConsultView;
import src.Views.consult.ConsultView;
import src.Views.consult.PatientCreateConsultView;
import src.domain.PacientProfileDto;

public class CommentsEditableConsultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        PacientProfileDto profile = new PacientProfileDto();
        Intent intent = getIntent();
        try{
            profile = (PacientProfileDto) intent.getSerializableExtra("PatientProfile");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        ConstraintLayout root = findViewById(R.id.consult_root_layout);
        final ConsultView consultView = new CommentsEditableConsultView(getApplicationContext(), profile);
        root.addView(consultView);
    }
}
