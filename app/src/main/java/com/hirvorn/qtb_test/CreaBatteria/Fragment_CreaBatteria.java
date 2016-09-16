package com.hirvorn.qtb_test.CreaBatteria;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.StartPage;

/**
 * Created by laboratorio on 02/09/2016.
 */
public class Fragment_CreaBatteria extends Fragment {

    private static Fragment_CreaBatteria istanza = null;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crea_batteria, container, false);


        return view;
    }
}
