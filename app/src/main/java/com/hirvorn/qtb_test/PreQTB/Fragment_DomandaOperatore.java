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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.hirvorn.qtb_test.Drone.Drone;
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
    private static TextView tv_domanda_critico;
    private static RadioButton checkBox_crit_si;
    private static RadioButton checkBox_crit_no;
    private static LinearLayout layout;
    private static LinearLayout layout_macchina;

    private static TextView tv_scegli_operatore_terzi;
    private static Spinner scegli_operatore_terzi;
    private static TextView tv_allerta_critico;

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
        tv_domanda_critico = (TextView)view.findViewById(R.id.textView_domanda_operatore_domanda_critico);
        checkBox_crit_si = (RadioButton)view.findViewById(R.id.radioButton_domanda_operatore_critico_si);
        checkBox_crit_no = (RadioButton)view.findViewById(R.id.radioButton_domanda_operatore_critico_no);
        layout = (LinearLayout)view.findViewById(R.id.domanda_operatore_layout);
        tv_allerta_critico = new TextView(Principale.getController().getContext());
        layout_macchina = (LinearLayout)view.findViewById(R.id.domanda_operatore_layout_macchina);

        return view;
    }

    public static void seiOperatore() {

        if (tv_domanda_critico.getVisibility() == View.INVISIBLE)
            tv_domanda_critico.setVisibility(View.VISIBLE);

        if (checkBox_crit_si.getVisibility() == View.INVISIBLE)
            checkBox_crit_si.setVisibility(View.VISIBLE);

        if (checkBox_crit_no.getVisibility() == View.INVISIBLE)
            checkBox_crit_no.setVisibility(View.VISIBLE);

        if (tv_seleziona_macchina.getVisibility() == View.VISIBLE)
            tv_seleziona_macchina.setVisibility(View.INVISIBLE);

        if (qualeMacchina.getVisibility() == View.VISIBLE)
            qualeMacchina.setVisibility(View.INVISIBLE);

        if(scegli_operatore_terzi != null && scegli_operatore_terzi.getVisibility() == View.VISIBLE)
            rimuoviSceltaOperatoreTerzi();

    }

    public static void qualeMacchina(){

        if(tv_allerta_critico != null && tv_allerta_critico.getVisibility() == View.VISIBLE)
            layout_macchina.removeView(tv_allerta_critico);

        if(checkBox_si.isChecked() && (checkBox_crit_si.isChecked() || checkBox_crit_no.isChecked())){
            if(tv_seleziona_operatore.getVisibility() == View.VISIBLE){
                tv_seleziona_operatore.setVisibility(View.INVISIBLE);
            }

            if(qualeOperatore.getVisibility() == View.VISIBLE){
                qualeOperatore.setVisibility(View.INVISIBLE);
            }

            tv_seleziona_macchina.setVisibility(View.VISIBLE);

            if(checkBox_crit_si.isChecked()) {
                //solo critici
                String droniDaSplittare = (new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "drones");

                ArrayList<String> droni = new ArrayList<>(Arrays.asList(droniDaSplittare.split("#")));
                ArrayList<String> droniCritici = new ArrayList<>();

                boolean critico;
                for (String drone : droni) {
                    critico = Boolean.valueOf((new ReadPropertyValues()).getPropValue(drone + Drone.DRONE_FILE_EXTENSION, "critico"));
                    if (critico) {
                        droniCritici.add(drone);
                    }
                }

                qualeMacchina.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, droniCritici));
            }else{
                ArrayList<String> droni = new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "drones").split("#")));

                qualeMacchina.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, droni));
            }

            qualeMacchina.setVisibility(View.VISIBLE);
        }

        if(checkBox_no.isChecked() && (checkBox_crit_si.isChecked() || checkBox_crit_no.isChecked())){
            tv_seleziona_operatore.setVisibility(View.INVISIBLE);
            qualeOperatore.setVisibility(View.INVISIBLE);


            if(checkBox_crit_si.isChecked()) {

                //TextView
                tv_allerta_critico.setText(Principale.getController().getContext().getResources().getString(R.string.domanda_operatore_allerta_critico));
                tv_allerta_critico.setTextColor(Color.RED);
                layout_macchina.addView(tv_allerta_critico);
            }
            ArrayList<String> operatori = new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(),
                    "fileOperatoreTerzi").split("#")));

            ArrayList<String> nomeOperatori = new ArrayList<>();

            if(operatori.contains("null")){
                operatori.remove("null");
            }

            nomeOperatori.add(Principale.getController().getContext().getResources().getString(R.string.domanda_operatore_seleziona_operatore));

            for(String nome : operatori){
                nomeOperatori.add((new ReadPropertyValues()).getPropValue(nome, "nomeOperatoreTerzi"));
            }

            String apr = (new ReadPropertyValues()).getPropValue(operatori.get(scegli_operatore_terzi.getSelectedItemPosition()-1), "aprUtilizzati");

            ArrayList<String> droni = new ArrayList<>(Arrays.asList(apr.split("#")));
            if(droni.contains("null"))
                droni.remove("null");

            qualeMacchina.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, droni));


            tv_seleziona_macchina.setVisibility(View.VISIBLE);
            qualeMacchina.setVisibility(View.VISIBLE);
            //qualeOperatore.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "operatori").split("#")))));
        }
    }

    public static void nonOperatore(){

        if (tv_seleziona_macchina.getVisibility() == View.VISIBLE)
            tv_seleziona_macchina.setVisibility(View.INVISIBLE);

        if (qualeMacchina.getVisibility() == View.VISIBLE)
            qualeMacchina.setVisibility(View.INVISIBLE);

        if (tv_domanda_critico.getVisibility() == View.VISIBLE)
            tv_domanda_critico.setVisibility(View.INVISIBLE);

        if (checkBox_crit_si.getVisibility() == View.VISIBLE)
            checkBox_crit_si.setVisibility(View.INVISIBLE);

        if (checkBox_crit_no.getVisibility() == View.VISIBLE)
            checkBox_crit_no.setVisibility(View.INVISIBLE);

        if(scegli_operatore_terzi != null && scegli_operatore_terzi.getVisibility() == View.VISIBLE)
            rimuoviSceltaOperatoreTerzi();

        tv_scegli_operatore_terzi = new TextView(Principale.getController().getContext());
        scegli_operatore_terzi = new Spinner(Principale.getController().getContext());


        tv_scegli_operatore_terzi.setText(Principale.getController().getContext().getResources().getString(R.string.domanda_operatore_domanda_quale_operatore));

        ArrayList<String> operatori = new ArrayList<>(Arrays.asList((new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(),
                "fileOperatoreTerzi").split("#")));

        ArrayList<String> nomeOperatori = new ArrayList<>();

        if(operatori.contains("null")){
            operatori.remove("null");
        }

        nomeOperatori.add(Principale.getController().getContext().getResources().getString(R.string.domanda_operatore_seleziona_operatore));

        for(String nome : operatori){
            nomeOperatori.add((new ReadPropertyValues()).getPropValue(nome, "nomeOperatoreTerzi"));
        }


        scegli_operatore_terzi.setAdapter(new ArrayAdapter<String>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, nomeOperatori));


        final int iCurrentSelection = scegli_operatore_terzi.getSelectedItemPosition();

        scegli_operatore_terzi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(iCurrentSelection != position)
                    mostraDomandaCritico();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        layout.addView(tv_scegli_operatore_terzi);
        layout.addView(scegli_operatore_terzi);
;    }

    public static void rimuoviSceltaOperatoreTerzi(){
        layout.removeView(tv_scegli_operatore_terzi);
        layout.removeView(scegli_operatore_terzi);
    }

    private static void mostraDomandaCritico(){
        if(checkBox_crit_si.isChecked())
            checkBox_crit_si.setChecked(false);

        if(checkBox_crit_no.isChecked())
            checkBox_crit_no.setChecked(false);

        if (tv_domanda_critico.getVisibility() == View.INVISIBLE)
            tv_domanda_critico.setVisibility(View.VISIBLE);

        if (checkBox_crit_si.getVisibility() == View.INVISIBLE)
            checkBox_crit_si.setVisibility(View.VISIBLE);

        if (checkBox_crit_no.getVisibility() == View.INVISIBLE)
            checkBox_crit_no.setVisibility(View.VISIBLE);


    }
}
