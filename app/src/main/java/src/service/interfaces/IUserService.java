package src.service.interfaces;

import com.androidnetworking.interfaces.JSONObjectRequestListener;

import src.service.interfaces.IService;

public interface IUserService extends IService {
    void loginUser(String username, String password, JSONObjectRequestListener loginResponseListener);
}
