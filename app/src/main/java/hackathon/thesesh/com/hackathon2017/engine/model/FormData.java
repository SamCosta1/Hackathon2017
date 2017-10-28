package hackathon.thesesh.com.hackathon2017.engine.model;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

/**
 * Created by Sam on 28/10/2017.
 */

public class FormData {

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("complaint")
    private String complaint;

    @SerializedName("favouriteColor")
    private String favouriteColor;

    @SerializedName("starSign")
    private String starSign;

    @SerializedName("dateOfBirth")
    private LocalDateTime dateOfBirth;

    private MutableLiveData<Boolean> validity = new MutableLiveData<>();

    public String getComplaint() {
        return complaint;
    }

    public MutableLiveData<Boolean> getValidity() {
        return validity;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateValidity();
    }

    public void setName(String name) {
        this.name = name;
        updateValidity();
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
        updateValidity();
    }

    private void updateValidity() {
        boolean valid = email != null && complaint != null && name != null && starSign != null && dateOfBirth != null;
        validity.setValue(valid);
    }
}

