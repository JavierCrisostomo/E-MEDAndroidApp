package src.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import e.wolfsoft1.src.R;
import src.Views.consult.ConsultView;
import src.Views.consult.PatientCreateConsultView;
import src.Views.profile.PatientProfileView;

public class PatientProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PatientProfileView patientProfileView = new PatientProfileView(getApplicationContext());
        setContentView(patientProfileView);
    }
}
