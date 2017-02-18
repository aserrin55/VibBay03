package com.example.asier.vibbay03.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asier.vibbay03.R;

public class mainFragment extends Fragment {

    public mainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);

        Log.i("Fragment","Main mostrado");
    }

}