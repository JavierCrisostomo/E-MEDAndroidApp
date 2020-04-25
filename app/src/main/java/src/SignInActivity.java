package src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import e.wolfsoft1.src.R;
import src.activities.HomeActivity;
import src.activities.PatientHomeActivity;


public class SignInActivity extends AppCompatActivity {
    TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn=(TextView)findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SignInActivity.this, PatientHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
