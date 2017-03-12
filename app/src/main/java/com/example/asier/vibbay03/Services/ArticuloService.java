package com.example.asier.vibbay03.Services;

import com.example.asier.vibbay03.Beans.Articulo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by asier on 07/03/2017.
 */

public interface ArticuloService {

    @GET("articulo/{id}")
    Call<Articulo> getArticulo(@Path("id") int idArticulo);

    @GET("articulo")
    Call<List<Articulo>> getArticulos();

    @GET("articulo/propietario/{email}")
    Call<List<Articulo>> getArticulos(@Path("email") String email);

    @POST("articulo")
    Call<Articulo> crearArticulo(@Body Articulo articulo);

    @PUT("articulo/{id}")
    Call<Articulo> actualizarArticulo(@Path("id") int idArticulo, @Body Articulo articulo);

    @DELETE("articulo/{id}")
    Call<Articulo> eliminarArticulo(@Path("id") int idArticulo);
}
