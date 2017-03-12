package com.example.asier.vibbay03.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asier.vibbay03.Beans.Articulo;
import com.example.asier.vibbay03.R;
import com.example.asier.vibbay03.Services.ArticuloService;
import com.example.asier.vibbay03.Services.Retro;

import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyArticlesFragment extends Fragment {

    public MyArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myarticles, container, false);


    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);

        Log.i("Fragment","Mis articulos mostrado");

        ArticuloService as = Retro.getArticuloService();
        Call<List<Articulo>> call = as.getArticulos(Retro.loggedIn.getEmail());
        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                Iterator<Articulo> it = response.body().iterator();
                while(it.hasNext()){
                    Articulo a = it.next();
                    //Mostrar grid de articulos
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Log.i("FALLOS",t.getMessage());
                Toast toast = Toast.makeText(getContext(), "Connection failure", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

}