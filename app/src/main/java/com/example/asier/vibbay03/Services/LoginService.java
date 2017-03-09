package com.example.asier.vibbay03.Services;

import com.example.asier.vibbay03.Beans.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by asier on 07/03/2017.
 */

public interface LoginService {

    @GET("usuario/auth/{email}/{password}")
    Call<List<Usuario>> auth(@Path("email") String email, @Path("password") String password);
}
