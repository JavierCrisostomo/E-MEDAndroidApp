package src.activities;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;

import e.wolfsoft1.src.R;
import src.Views.consult.ConsultView;
import src.Views.consult.PatientCreateConsultView;

public class ViewConsultWithCommentsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        ConstraintLayout root = findViewById(R.id.consult_root_layout);
        final ConsultView consultView = new PatientCreateConsultView(getApplicationContext());
        root.addView(consultView);
    }
}
