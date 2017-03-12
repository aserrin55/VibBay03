package com.example.asier.vibbay03.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asier.vibbay03.Beans.Articulo;
import com.example.asier.vibbay03.R;
import com.example.asier.vibbay03.Services.ArticuloService;
import com.example.asier.vibbay03.Services.Retro;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArticleFragment extends Fragment {

    LinearLayout ll;
    EditText nombre;
    EditText precio;
    Button add;

    public NewArticleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ll = (LinearLayout) inflater.inflate(R.layout.fragment_newarticle, container, false);
        add = (Button)ll.findViewById(R.id.Add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArticle();
            }
        });
        return ll;

    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);

        Log.i("Fragment","Nuevo artículo mostrado");
    }

    private void addArticle(){
        nombre = (EditText) ll.findViewById(R.id.ArticleName);
        precio = (EditText) ll.findViewById(R.id.ArticlePrice);

        ArticuloService as = Retro.getArticuloService();
        Call<Articulo> call = as.crearArticulo(new Articulo(0,nombre.getText().toString(),Retro.loggedIn.getEmail(),true,"",Integer.parseInt(precio.getText().toString())));
        call.enqueue(new Callback<Articulo>() {
            @Override
            public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                nombre.setText("");
                precio.setText("");
                Toast toast = Toast.makeText(getContext(), "Nuevo artículo creado", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Call<Articulo> call, Throwable t) {
                Log.i("FALLOS",t.getMessage());
                Toast toast = Toast.makeText(getContext(), "Connection failure", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}