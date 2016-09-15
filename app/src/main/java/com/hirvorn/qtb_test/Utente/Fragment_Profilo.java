package com.hirvorn.qtb_test.Utente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;

import java.util.ArrayList;

/**
 * Created by laboratorio on 30/08/2016.
 */
public class Fragment_Profilo extends Fragment {

    private static Fragment_Profilo instance = null;

    private TextView tw_nome;
    private TextView tw_cognome;
    private TextView tw_brevetto;
    private ListView listView_Droni;
    private static ArrayList<String> droni_posseduti = new ArrayList<>();
    private static ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilo, container, false);

        tw_nome = (TextView)view.findViewById(R.id.tw_profilo_nome);
        tw_nome.setText(getArguments().getString("nome"));

        tw_cognome = (TextView)view.findViewById(R.id.tw_profilo_cognome);
        tw_cognome.setText(getArguments().getString("cognome"));

        tw_brevetto = (TextView)view.findViewById(R.id.tw_profilo_brevetto);
        tw_brevetto.setText(Principale.getController().getBrevetto().getEnac());

        listView_Droni = (ListView)view.findViewById(R.id.droneList);
        droni_posseduti = Principale.getController().getProfilo().getDroniPosseduti();
        adapter = new ArrayAdapter<String>(Principale.getController().getContext(), R.layout.listview_droni_row, R.id.textViewList_Drone, droni_posseduti);
        listView_Droni.setAdapter(adapter);

        return view;
    }

    public static Fragment nuovaIstanza(String nome, String cognome, String brevetto){

        if (instance == null) {

            instance = new Fragment_Profilo();

            Bundle bundle = new Bundle();

            bundle.putString("nome", nome);
            bundle.putString("cognome", cognome);
            bundle.putString("brevetto", brevetto);

            instance.setArguments(bundle);
            return instance;

        }
        else{
            return instance;
        }

    }

    public static void aggiungiCodiceDrone(String codiceDrone){
        droni_posseduti.add(codiceDrone);
        adapter.notifyDataSetChanged();
    }
}
