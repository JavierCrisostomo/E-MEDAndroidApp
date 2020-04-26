package src.service.impl;

import org.json.JSONObject;

import src.service.interfaces.IUserService;

public class UserServiceMock implements IUserService {
    @Override
    public boolean loginUser(String username, String password) {
        return true;
    }
}
