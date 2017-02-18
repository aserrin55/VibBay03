package com.example.asier.vibbay03.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.asier.vibbay03.R;

public class loginFragment extends Fragment {


    EditText username;
    EditText password;
    Button buttonClick;


    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View.OnClickListener mClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testLogin(username.getText().toString(),password.getText().toString());
            }
        };
        FrameLayout fL = (FrameLayout) inflater.inflate(R.layout.fragment_login, container, false);
        username = (EditText)fL.findViewById(R.id.login_username);
        password = (EditText)fL.findViewById(R.id.login_password);
        buttonClick = (Button)fL.findViewById(R.id.buttonAccept);
        buttonClick.setOnClickListener(mClickListener);
        return fL;
    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        Log.i("Fragment","Login fragment terminado");
    }


    public void testLogin(String user, String pass){
        if(username.equals("mco@mco") && password.equals("12345")){
            Toast toast = Toast.makeText(getContext(), "CORRECT", Toast.LENGTH_SHORT);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getContext(), "USER OR PASS INCORRECT", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
