package hackathon.thesesh.com.hackathon2017.engine;

import android.app.Application;

import hackathon.thesesh.com.hackathon2017.engine.model.FormData;

/**
 * Created by Sam on 28/10/2017.
 */

public class OmbudsmanApplication extends Application {

    private FormData formData;
    private NetworkManager networkManager;
    public OmbudsmanApplication() {
        formData = new FormData();
        networkManager = new NetworkManager(formData);;
    }

    public FormData getFormData() {
        return formData;
    }

    public NetworkManager getNetworkManager() {
        return networkManager;
    }
}
