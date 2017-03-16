package com.example.asier.vibbay03.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asier.vibbay03.Beans.Articulo;
import com.example.asier.vibbay03.R;
import com.example.asier.vibbay03.Services.ArticuloService;
import com.example.asier.vibbay03.Tools.RetroTools;
import com.example.asier.vibbay03.Tools.ImageTools;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewArticleFragment extends Fragment {

    private int GALLERY_REQUEST = 1;
    private int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    LinearLayout ll;
    EditText nombre;
    EditText precio;
    Button add;
    Bitmap bm;

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
        imageView = (ImageView)ll.findViewById(R.id.imageTaken);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /// Con esto la cogemos de la galeria, camera_request tiene que ser 1
                //Intent cameraIntent = new Intent();
                //cameraIntent.setType("image/*");
                //cameraIntent.setAction(Intent.ACTION_GET_CONTENT);
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(Intent.createChooser(cameraIntent,"Elige"), CAMERA_REQUEST);
                Log.i("CAMARA","ENTRA A LO SIGUINTE");
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
        String foto64 = ImageTools.encodeToBase64(bm,Bitmap.CompressFormat.JPEG,20);

        ArticuloService as = RetroTools.getArticuloService();
        Call<Articulo> call = as.crearArticulo(new Articulo(nombre.getText().toString(), RetroTools.loggedIn.getEmail(),1,foto64,Integer.parseInt(precio.getText().toString())));
        call.enqueue(new Callback<Articulo>() {
            @Override
            public void onResponse(Call<Articulo> call, Response<Articulo> response) {
                if(response.isSuccessful()){
                    nombre.setText("");
                    precio.setText("");
                    Toast toast = Toast.makeText(getContext(), "Nuevo artículo creado", Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    try {
                        Log.i("FALLOS RESPUESTA",response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Articulo> call, Throwable t) {
                Log.i("FALLOS DE CONEXION",t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("VUELTA","ESTA VOLVIENDO AL MISMO ACTIVITY");
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Log.i("VUELTA",uri.toString());
            try {
                bm = ImageTools.getResizedBitmap(MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri),600);
                // Log.d(TAG, String.valueOf(bitmap));
                imageView.setImageBitmap(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}