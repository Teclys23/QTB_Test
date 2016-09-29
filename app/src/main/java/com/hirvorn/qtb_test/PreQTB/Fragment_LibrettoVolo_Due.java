package com.hirvorn.qtb_test.PreQTB;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hirvorn.qtb_test.Drone.Drone;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by laboratorio on 20/09/2016.
 */

public class Fragment_LibrettoVolo_Due extends Fragment {


    private static Spinner spinner_apr;
    private static TextView apr_utilizzato;
    private static LinearLayout layout;
    private static CheckBox checkBox_simulatore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logbook_due, container, false);

        //spinner_apr = (Spinner)view.findViewById(R.id.spinner_logbook_due_apr);
        //String lista_apr = (new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(),"drones");
        //ArrayList<String> lista = new ArrayList<>(Arrays.asList(lista_apr.split("#")));

        //spinner_apr.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, lista));
        apr_utilizzato = (TextView)view.findViewById(R.id.textView_apr_utilizzato);
        apr_utilizzato.setText(Principale.getController().getDroneAttuale());
        Log.v("ASD", "Drone attuale: " + Principale.getController().getDroneAttuale());

        layout = (LinearLayout)view.findViewById(R.id.logbook_due_layout_spr);

        ArrayList<String> lista_spr = new ArrayList<String>(Arrays.asList((new ReadPropertyValues()).getPropValue(apr_utilizzato.getText().toString() + Drone.DRONE_FILE_EXTENSION, "spr").split("#")));
        for(String spr : lista_spr){
            TextView textView_spr = new TextView(Principale.getController().getContext());
            textView_spr.setText(spr);
            textView_spr.setTextColor(Color.BLACK);
            layout.addView(textView_spr);
        }
        checkBox_simulatore = (CheckBox)view.findViewById(R.id.checkBox_simulatore);

        return view;
    }

    public static void salvaDati(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("apr");
        keys.add("spr");
        keys.add("utilizzatoSimulatore");

        ArrayList<String> values = new ArrayList<>();
        values.add(apr_utilizzato.getText().toString());
        values.add((new ReadPropertyValues()).getPropValue(apr_utilizzato.getText().toString() + Drone.DRONE_FILE_EXTENSION, "spr"));
        values.add(checkBox_simulatore.isChecked() ? "true" : "false");

        Principale.getController().getLibrettoDiVolo().salvaDati(keys, values);
    }
}
