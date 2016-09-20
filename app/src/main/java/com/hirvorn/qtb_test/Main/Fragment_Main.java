package com.hirvorn.qtb_test.Main;

import android.os.Bundle;
import android.os.health.PidHealthStats;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Fragment_Main extends Fragment {

    private  static TextView nomeProfilo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        nomeProfilo = (TextView)view.findViewById(R.id.textView_nome_profilo);
        nomeProfilo.setText("Ciao " + Principale.getController().getProfilo().getNome());

        String droni = Principale.getController().getProfilo().haDroniPosseduti();
        List<String> array = new ArrayList<>(Arrays.asList(droni.split("#")));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = (Spinner)view.findViewById(R.id.spinner_droni);
        spinner.setAdapter(adapter);

        return view;
    }

}
