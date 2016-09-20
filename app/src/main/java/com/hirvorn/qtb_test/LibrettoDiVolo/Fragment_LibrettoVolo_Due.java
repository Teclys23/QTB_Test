package com.hirvorn.qtb_test.LibrettoDiVolo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private static LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logbook_due, container, false);

        spinner_apr = (Spinner)view.findViewById(R.id.spinner_logbook_due_apr);
        String lista_apr = (new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(),
                    "drones");
        ArrayList<String> lista = new ArrayList<>(Arrays.asList(lista_apr.split("#")));

        spinner_apr.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, lista));

        layout = (LinearLayout)view.findViewById(R.id.logbook_due_layout_spr);

        spinner_apr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ArrayList<String> lista_spr = new ArrayList<String>(Arrays.asList((new ReadPropertyValues()).getPropValue(spinner_apr.getSelectedItem() + Drone.DRONE_FILE_EXTENSION, "spr").split("#")));
                for(String spr : lista_spr){
                    TextView textView_spr = new TextView(Principale.getController().getContext());
                    textView_spr.setText(spr);
                    layout.addView(textView_spr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        return view;
    }
}
