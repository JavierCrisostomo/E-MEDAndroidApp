package src;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import e.wolfsoft1.src.R;
import src.activities.HomeActivity;
import src.activities.PatientHomeActivity;
import src.service.impl.UserService;
import src.service.interfaces.IUserService;


public class SignInActivity extends AppCompatActivity {
    TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn=(TextView)findViewById(R.id.btnSignIn);

        final IUserService userService = new UserService(getResources().getString(R.string.server_host));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username_editbox)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_editbox)).getText().toString();

                userService.loginUser(username, password, loginRequestListener);
            }
        });
    }

    private JSONObjectRequestListener loginRequestListener = new JSONObjectRequestListener() {
        @Override
        public void onResponse(JSONObject response) {
            Intent intent =new Intent(SignInActivity.this, PatientHomeActivity.class);
            startActivity(intent);
            Toast toast = Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_LONG);
            toast.show();
        }

        @Override
        public void onError(ANError anError) {
            Toast toast = Toast.makeText(getBaseContext(), "Login failed!", Toast.LENGTH_LONG);
            toast.show();
        }
    };
}
