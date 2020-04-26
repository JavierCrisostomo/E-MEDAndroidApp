package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import e.wolfsoft1.src.R;
import src.Views.consult.CommentsEditableConsultView;
import src.Views.consult.ConsultView;
import src.domain.DoctorProfileDto;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;
import src.service.impl.DoctorProfileServiceMock;
import src.service.interfaces.IDoctorProfileService;

public class CommentsEditableConsultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        PatientProfileDto profile = new PatientProfileDto();
        Intent intent = getIntent();
        try{
            profile = (PatientProfileDto) intent.getSerializableExtra("PatientProfile");
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        IDoctorProfileService doctorService = new DoctorProfileServiceMock();
        DoctorProfileDto doctorProfileDto = doctorService.getProfile("");

        ConstraintLayout root = findViewById(R.id.consult_root_layout);
        final ConsultView consultView = new CommentsEditableConsultView(getApplicationContext(), profile, doctorProfileDto);
        root.addView(consultView);
    }
}
