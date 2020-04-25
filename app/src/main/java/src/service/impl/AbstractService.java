package src.service.impl;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.ANRequest;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import src.service.interfaces.IService;

public abstract class AbstractService implements IService {
    protected JSONObject responseJSON;

    JSONObject temaplateSyncPostRequestJSON(String resourceURL, Map<String, String> bodyParams) {

        ANRequest.PostRequestBuilder requestBuilder = AndroidNetworking.post(resourceURL);
        for (String key : bodyParams.keySet()) {
            requestBuilder.addBodyParameter(key, bodyParams.get(key));
        }

        requestBuilder.setPriority(Priority.MEDIUM);
        ANRequest request = requestBuilder.build();
        synchronized (request) {
            try {
                request.wait();

            } catch(InterruptedException e){
                e.printStackTrace();
                return null;
            }
        }

        request.getAsJSONObject(new JSONObjectRequestListener() {
            @Override
            public void onResponse(JSONObject response) {
                responseJSON = response;
            }

            @Override
            public void onError(ANError anError) {
                responseJSON = null;
            }
        });
        return responseJSON;
    }
}
