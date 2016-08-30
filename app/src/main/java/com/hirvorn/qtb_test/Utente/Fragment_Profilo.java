package com.hirvorn.qtb_test.Utente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

/**
 * Created by laboratorio on 30/08/2016.
 */
public class Fragment_Profilo extends Fragment {

    private static Fragment_Profilo instance = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profilo, container, false);

        TextView tw_nome = (TextView)view.findViewById(R.id.tw_profilo_nome);
        tw_nome.setText(getArguments().getString("nome"));

        TextView tw_cognome = (TextView)view.findViewById(R.id.tw_profilo_cognome);
        tw_cognome.setText(getArguments().getString("cognome"));

        TextView tw_brevetto = (TextView)view.findViewById(R.id.tw_profilo_brevetto);
        tw_brevetto.setText(getArguments().getString("brevetto"));

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
}
