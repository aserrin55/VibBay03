package com.example.asier.vibbay03.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import com.example.asier.vibbay03.Beans.Usuario;
import com.example.asier.vibbay03.R;
import com.example.asier.vibbay03.Services.Retro;
import com.example.asier.vibbay03.Services.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginFragment extends Fragment {


    EditText username;
    EditText password;
    Button buttonClick;
    GridLayout fL;


    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    testLogin();}
        };
        fL = (GridLayout) inflater.inflate(R.layout.fragment_login, container, false);
        buttonClick = (Button)fL.findViewById(R.id.buttonAccept);
        buttonClick.setOnClickListener(mClickListener);
        return fL;
    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        Log.i("Fragment","Login fragment terminado");
    }


    public void testLogin(){


        username = (EditText)fL.findViewById(R.id.login_username);
        password = (EditText)fL.findViewById(R.id.login_password);

        LoginService ls = Retro.getLoginService();
        Call<Usuario> call = ls.auth(username.getText().toString(), password.getText().toString());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usu = response.body();
                Toast toast = Toast.makeText(getContext(), usu.getEmail(), Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                t.getStackTrace();
                Toast toast = Toast.makeText(getContext(), "Connection failure", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
//        Log.i("Login",username.getText().toString());
//        Log.i("Login",password.getText().toString());
//        if(username.getText().toString().equals("mco@mco") && password.getText().toString().equals("12345")){
//            Toast toast = Toast.makeText(getContext(), "CORRECT", Toast.LENGTH_SHORT);
//            toast.show();
//        }else{
//            Toast toast = Toast.makeText(getContext(), "USER OR PASS INCORRECT", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }
}
