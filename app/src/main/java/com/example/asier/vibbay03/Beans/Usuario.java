package com.example.asier.vibbay03.Beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by asier on 07/03/2017.
 */

public class Usuario {

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
