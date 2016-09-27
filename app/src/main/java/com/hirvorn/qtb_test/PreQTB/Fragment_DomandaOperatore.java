package com.hirvorn.qtb_test.PreQTB;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by laboratorio on 26/09/2016.
 */

public class Fragment_DomandaOperatore extends Fragment {

    private static RadioButton checkBox_si;
    private static RadioButton checkBox_no;
    private static TextView tv_seleziona_operatore;
    private static Spinner qualeOperatore;
    private static TextView tv_seleziona_macchina;
    private static Spinner qualeMacchina;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_domanda_operatore, container, false);

        checkBox_si = (RadioButton) view.findViewById(R.id.radioButton_domanda_operatore_si);
        checkBox_no = (RadioButton) view.findViewById(R.id.radioButton_domanda_operatore_no);
        tv_seleziona_operatore = (TextView)view.findViewById(R.id.textView_domanda_operatore_domanda_quale_operatore);
        qualeOperatore = (Spinner)view.findViewById(R.id.spinner_domanda_operatore_seleziona_operatore);
        tv_seleziona_macchina = (TextView)view.findViewById(R.id.textView_domanda_operatore_domanda_quale_macchina);
        qualeMacchina = (Spinner)view.findViewById(R.id.spinner_domanda_operatore_quale_macchina);

        return view;
    }

    public static void seiOperatore(){
        if(checkBox_si.isChecked()){
            if(tv_seleziona_operatore.getVisibility() == View.VISIBLE){
                tv_seleziona_operatore.setVisibility(View.INVISIBLE);
            }

            if(qualeOperatore.getVisibility() == View.VISIBLE){
                qualeOperatore.setVisibility(View.INVISIBLE);
            }

            tv_seleziona_macchina.setVisibility(View.VISIBLE);

            qualeMacchina.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "drones").split("#")))));

            qualeMacchina.setVisibility(View.VISIBLE);
        }

        if(checkBox_no.isChecked()){
            tv_seleziona_operatore.setVisibility(View.VISIBLE);
            qualeOperatore.setVisibility(View.VISIBLE);

            qualeOperatore.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "operatori").split("#")))));
        }
    }
}
