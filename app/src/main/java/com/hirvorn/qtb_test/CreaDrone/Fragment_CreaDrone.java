package com.hirvorn.qtb_test.CreaDrone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;

import java.util.ArrayList;

/**
 * Created by laboratorio on 30/08/2016.
 */
public class Fragment_CreaDrone extends Fragment {

    Spinner spinner;
    Spinner spinner_marca_modello;
    ListView listView_spr;
    Spinner spinner_numero_motori;

    private static ArrayList<String> values;
    private static ArrayAdapter<String> adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crea_drone, container, false);

        spinner = (Spinner)view.findViewById(R.id.spinner_categorie);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Principale.getController().getContext(),
                R.array.drone_categorie, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner_marca_modello = (Spinner)view.findViewById(R.id.spinner_marca_modello);

        adapter = ArrayAdapter.createFromResource(Principale.getController().getContext(), R.array.drone_marca_modello, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_marca_modello.setAdapter(adapter);

        listView_spr = (ListView)view.findViewById(R.id.listView_spr);

        values = new ArrayList<>();
        adapter2 = new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView_spr.setAdapter(adapter2);

        spinner_numero_motori = (Spinner)view.findViewById(R.id.spinner_numero_motori);

        adapter = ArrayAdapter.createFromResource(Principale.getController().getContext(), R.array.drone_numero_motori, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_numero_motori.setAdapter(adapter);

        return view;
    }

    public static void addItem(String string){
        values.add(string);
        adapter2.notifyDataSetChanged();
    }
}
