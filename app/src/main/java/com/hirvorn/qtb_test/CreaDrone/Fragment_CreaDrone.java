package com.hirvorn.qtb_test.CreaDrone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hirvorn.qtb_test.R;

/**
 * Created by laboratorio on 30/08/2016.
 */
public class Fragment_CreaDrone extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crea_drone, container, false);
    }
}
