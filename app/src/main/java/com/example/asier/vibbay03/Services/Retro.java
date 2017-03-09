package com.example.asier.vibbay03.Services;

import com.example.asier.vibbay03.Services.ArticuloService;
import com.example.asier.vibbay03.Services.LoginService;
import com.example.asier.vibbay03.Services.UsuarioService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asier on 07/03/2017.
 */

public class Retro {

    private static final String BASE_URL = "http://192.168.1.128:8084/Vibbay03Web/rest/";
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static LoginService getLoginService(){
        return retrofit.create(LoginService.class);
    }

    public static ArticuloService getArticuloService(){
        return retrofit.create(ArticuloService.class);
    }

    public static UsuarioService getUsuarioService(){
        return retrofit.create(UsuarioService.class);
    }

}
