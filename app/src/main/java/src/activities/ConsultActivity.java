package src.activities;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;

import e.wolfsoft1.src.R;
import src.Views.ConsultView;
import src.Views.CreateConsultView;

public class ConsultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);

        ConstraintLayout root = findViewById(R.id.consult_root_layout);
        final ConsultView consultView = new CreateConsultView(getApplicationContext());
        root.addView(consultView);
    }
}
