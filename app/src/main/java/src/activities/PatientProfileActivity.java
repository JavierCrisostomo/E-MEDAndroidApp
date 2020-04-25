package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import src.Views.profile.PatientProfileView;
import src.domain.PacientProfileDto;
import src.service.impl.PacientProfileServiceMock;
import src.service.interfaces.IProfileService;

public class PatientProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IProfileService profileService = new PacientProfileServiceMock();
        Intent intent = getIntent();
        String userKey = "";
        try{
            userKey = intent.getExtras().get("UserKey").toString();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        PacientProfileDto profile = profileService.getProfile(userKey);

        PatientProfileView patientProfileView = new PatientProfileView(getApplicationContext(), profile);
        setContentView(patientProfileView);
    }
}
