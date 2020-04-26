package src.service.impl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;
import src.service.interfaces.IUserService;

public class UserService extends AbstractService implements IUserService {
    protected String server_address;
    private JSONObject loginToken;

    public UserService(String server_address) {
        this.server_address = server_address;
    }

    @Override
    public boolean loginUser(String username, String password) {
        Map<String, String> bodyParams = new HashMap<>();
        bodyParams.put("username", username);
        bodyParams.put("password", password);

        JSONObject loginResponse = temaplateSyncPostRequestJSON(server_address + "/rest-auth/login/", bodyParams);
        if(loginResponse != null){
            return  true;
        }
        else{
            return false;
        }
    }
}
