package hackathon.thesesh.com.hackathon2017.engine;

import com.google.gson.Gson;

import hackathon.thesesh.com.hackathon2017.engine.model.FormData;

/**
 * Created by Sam on 28/10/2017.
 */

public class NetworkManager {

    private FormData formData;

    public NetworkManager(FormData formData) {
        this.formData = formData;
    }

    public void sendRequest() {
        String JSON = new Gson().toJson(formData);

        // Perform network request
    }

}
