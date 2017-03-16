package com.example.asier.vibbay03.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asier.vibbay03.Beans.Articulo;
import com.example.asier.vibbay03.R;
import com.example.asier.vibbay03.Services.ArticuloService;
import com.example.asier.vibbay03.Tools.RetroTools;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllArticlesFragment extends Fragment {

    GridLayout fl;


    public AllArticlesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fl = (GridLayout) inflater.inflate(R.layout.fragment_allarticles, container, false);
       return fl;

    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        Log.i("Fragment","Todos los artículos fragment terminado");

        ArticuloService as = RetroTools.getArticuloService();
        Call<List<Articulo>> call = as.getArticulos();
        call.enqueue(new Callback<List<Articulo>>() {
            @Override
            public void onResponse(Call<List<Articulo>> call, Response<List<Articulo>> response) {
                if(response.isSuccessful()){
                    Log.i("ARTICULOS",String.valueOf(response.body().size()));
                    Iterator<Articulo> it = response.body().iterator();
                    while(it.hasNext()){
                        Articulo a = it.next();
                        LinearLayout x = new LinearLayout(getContext());
                        TextView nombre = new TextView(x.getContext());
                        TextView precio = new TextView(x.getContext());
                        //ImageView imagen = new ImageView(x.getContext());
                        nombre.setText(a.getTitulo());
                        precio.setText(String.valueOf(a.getPrecio()));
                        //imagen.setImageBitmap(ImageTools.decodeBase64(a.getImagen()));
                        x.addView(nombre);
                        x.addView(precio);
                        //x.addView(imagen);
                        fl.addView(x);
                        //Mostrar grid de articulos
                }

                }else{
                    try {
                        Log.i("FALLOS RESPUESTA",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Articulo>> call, Throwable t) {
                Log.i("FALLOS",t.getMessage());
            }
        });
    }


}
