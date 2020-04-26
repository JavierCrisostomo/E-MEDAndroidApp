package src.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import src.Views.home.HomeView;
import src.Views.home.PatientHomeView;
import src.domain.LocalStorage;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;
import src.service.impl.PacientProfileServiceMock;
import src.service.interfaces.IPatientProfileService;
import src.service.interfaces.IProfileService;

public class PatientHomeActivity extends HomeActivity {


    protected ProfileDto profile;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PatientHomeActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        IPatientProfileService profileService = new PacientProfileServiceMock();
        Intent intent = getIntent();
        PatientProfileDto profile = null;


        try{
            profile = (PatientProfileDto) intent.getSerializableExtra("PatientProfile");
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        this.profile = profile;

        if(profile == null){
            profile = new PacientProfileServiceMock().getProfile("");
            LocalStorage.setCurrentProfile(profile);
        }

        HomeView patientHomeView = new PatientHomeView(getApplicationContext(), null, profile);
        setContentView(patientHomeView);
    }




}
