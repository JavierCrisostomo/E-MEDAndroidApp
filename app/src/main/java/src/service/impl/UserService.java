package src.service.impl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONObject;

import okhttp3.Response;
import src.service.interfaces.IUserService;

public class UserService implements IUserService {
    protected String server_address;

    public UserService(String server_address) {
        this.server_address = server_address;
    }

    @Override
    public void loginUser(String username, String password, JSONObjectRequestListener loginResponseListener) {
         AndroidNetworking.post(server_address + "/rest-auth/login/")
                .addBodyParameter("username", username)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                 .getAsJSONObject(loginResponseListener);
    }
}
