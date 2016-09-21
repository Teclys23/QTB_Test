package com.hirvorn.qtb_test.LibrettoDiVolo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;

import java.util.ArrayList;

/**
 * Created by laboratorio on 20/09/2016.
 */

public class Fragment_LibrettoVolo_Tre extends Fragment {

    private static CheckBox sperimentali;
    private static CheckBox critiche;
    private static CheckBox nonCritiche;
    private static CheckBox addestramento;
    private static CheckBox aggiornatmento;
    private static CheckBox vlos;
    private static CheckBox blos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logbook_tre, container, false);

        sperimentali = (CheckBox)view.findViewById(R.id.checkBox_sperimentali);
        critiche = (CheckBox)view.findViewById(R.id.checkBox_critiche);
        nonCritiche = (CheckBox)view.findViewById(R.id.checkBox_non_critiche);
        addestramento = (CheckBox)view.findViewById(R.id.checkBox_addestramento);
        aggiornatmento = (CheckBox)view.findViewById(R.id.checkBox_aggiornamento);
        vlos = (CheckBox)view.findViewById(R.id.checkBox_vlos);
        blos = (CheckBox)view.findViewById(R.id.checkBox_blos);

        return view;
    }

    public static void salvaDati(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("sperimentali");
        keys.add("critiche");
        keys.add("nonCritiche");
        keys.add("addestramento");
        keys.add("aggiornamento");
        keys.add("vlos");
        keys.add("blos");

        ArrayList<String> values = new ArrayList<>();
        values.add(sperimentali.isChecked() ? "true" : "false");
        values.add(critiche.isChecked() ? "true" : "false");
        values.add(nonCritiche.isChecked() ? "true" : "false");
        values.add(addestramento.isChecked() ? "true" : "false");
        values.add(aggiornatmento.isChecked() ? "true" : "false");
        values.add(vlos.isChecked() ? "true" : "false");
        values.add(blos.isChecked() ? "true" : "false");

        Principale.getController().getLibrettoDiVolo().salvaDati(keys, values);
    }
}
