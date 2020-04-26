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

import org.json.JSONException;
import org.json.JSONObject;

import e.wolfsoft1.src.R;
import src.activities.HomeActivity;
import src.activities.PatientHomeActivity;
import src.domain.LocalStorage;
import src.service.impl.UserService;
import src.service.interfaces.IUserService;


public class SignInActivity extends AppCompatActivity {
    TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnSignIn = (TextView) findViewById(R.id.btnSignIn);

        final IUserService userService = new UserService(getResources().getString(R.string.server_host));

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText) findViewById(R.id.username_editbox)).getText().toString();
                String password = ((EditText) findViewById(R.id.password_editbox)).getText().toString();

                JSONObject loginResponse = userService.loginUser(username, password);

                if (loginResponse == null) {
                    loginFailedToast();
                } else {
                    try {
                        String userKey = loginResponse.get("key").toString();
                        // pass userkey to home activity
                        Intent intent = new Intent(SignInActivity.this, PatientHomeActivity.class);
                        intent.putExtra("UserKey", userKey);
                        LocalStorage.setUserKey(userKey);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        loginFailedToast();
                    }
                }
            }
        });
    }

    private void loginFailedToast() {
        Toast toast = Toast.makeText(getBaseContext(), "Login successful!", Toast.LENGTH_LONG);
        toast.show();
    }

}
