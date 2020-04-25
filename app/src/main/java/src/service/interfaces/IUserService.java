package src.service.interfaces;

import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import src.service.interfaces.IService;

public interface IUserService extends IService {
    JSONObject loginUser(String username, String password);
}
