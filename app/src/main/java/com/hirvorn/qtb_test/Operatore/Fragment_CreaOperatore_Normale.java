package com.hirvorn.qtb_test.Operatore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

/**
 * Created by laboratorio on 26/09/2016.
 */

public class Fragment_CreaOperatore_Normale extends Fragment{

    private static EditText codiceEnac;
    private static TextView scadenza;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crea_operatore_normale, container, false);

        codiceEnac = (EditText)view.findViewById(R.id.editText_crea_operatore_normale_codice_enac);
        scadenza = (TextView)view.findViewById(R.id.textView_crea_operatore_normale_data_scadenza);

        return view;
    }
}
