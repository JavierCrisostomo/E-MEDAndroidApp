package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import src.Views.profile.PatientProfileView;
import src.Views.profile.ProfileView;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class PatientProfileActivity extends AppCompatActivity {
    private PatientProfileDto profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PatientProfileDto profile;
        Intent intent = getIntent();
        try{
            profile = (PatientProfileDto) intent.getSerializableExtra("PatientProfile");
            this.profile = profile;

            ProfileView patientProfileView = new PatientProfileView(getApplicationContext(), profile);
            setContentView(patientProfileView);
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }


}
