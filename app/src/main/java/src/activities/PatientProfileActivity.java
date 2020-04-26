package src.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import e.wolfsoft1.src.R;
import src.Views.menu.PatientMenuView;
import src.Views.profile.PatientProfileView;
import src.domain.PacientProfileDto;
import src.service.impl.PacientProfileServiceMock;
import src.service.interfaces.IProfileService;

public class PatientProfileActivity extends AppCompatActivity {
    private PacientProfileDto profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PacientProfileDto profile;
        Intent intent = getIntent();
        try{
            profile = (PacientProfileDto) intent.getSerializableExtra("PatientProfile");
            this.profile = profile;

            PatientProfileView patientProfileView = new PatientProfileView(getApplicationContext(), profile);
            setContentView(patientProfileView);
        }catch (NullPointerException e){
            e.printStackTrace();
        }


    }


}
