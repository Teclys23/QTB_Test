package com.hirvorn.qtb_test.LibrettoDiVolo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.StartPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by laboratorio on 20/09/2016.
 */

public class Fragment_LibrettoVolo_Quattro extends Fragment {

    private static EditText durata_missione_uno;
    private static TextView landing_uno;
    private static TextView take_off_uno;

    private static TextView tv_durata_missione_due;
    private static TextView tv_landing_due;
    private static TextView tv_take_off_due;
    private static EditText durata_missione_due;
    private static TextView landing_due;
    private static TextView take_off_due;

    private static TextView durata_totale;
    private static TextView tot_ore_volo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_logbook_quattro, container, false);

        durata_missione_uno = (EditText)view.findViewById(R.id.editText_logbook_quattro_durata_missione_uno);
        landing_uno = (TextView) view.findViewById(R.id.textView_logbook_quattro_landing);
        take_off_uno = (TextView)view.findViewById(R.id.textView_logbook_quattro_ora_take_off);

        tv_durata_missione_due = (TextView)view.findViewById(R.id.textView_logbook_quattro_tv_durata_missione_due);
        tv_landing_due = (TextView)view.findViewById(R.id.textView_logbook_quattro_tv_ora_landing_due);
        tv_take_off_due = (TextView)view.findViewById(R.id.textView_logbook_quattro_tv_ora_take_off_due);
        durata_missione_due = (EditText)view.findViewById(R.id.editText_logbook_quattro_durata_missione_due);
        landing_due = (TextView) view.findViewById(R.id.textView_logbook_quattro_landing_due);
        take_off_due = (TextView)view.findViewById(R.id.textView_logbook_quattro_ora_take_off_due);

        durata_totale = (TextView)view.findViewById(R.id.textView_logbook_quattro_durata_missione);
        tot_ore_volo = (TextView)view.findViewById(R.id.textView_logbook_quattro_tot_ore_volo);

        return view;
    }

    public static void settaLandingUno(){
        String durataMissioneUno = durata_missione_uno.getText().toString();

        if(!TextUtils.isEmpty(durataMissioneUno)){

            int durata = Integer.parseInt(durataMissioneUno);

            Calendar calendar = Calendar.getInstance();

            String takeOff = take_off_uno.getText().toString();
            if(!takeOff.equals("Premere qui per inserire")){
                ArrayList<String> orarioArray = new ArrayList<>(Arrays.asList(takeOff.split(" : ")));
                int ore = Integer.parseInt(orarioArray.get(0));
                int minuti = Integer.parseInt(orarioArray.get(1));

                calendar.set(Calendar.HOUR_OF_DAY, ore);
                calendar.set(Calendar.MINUTE, minuti);
                calendar.add(Calendar.MINUTE, durata);

                landing_uno.setText(calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE));

                if(!durataMinoreCinqueMinuti(durata)){
                    settaTotOreVolo();
                }

            }
        }
    }

    private static boolean durataMinoreCinqueMinuti(int durata){
        if(durata <= 5){
            tv_landing_due.setVisibility(View.VISIBLE);
            tv_durata_missione_due.setVisibility(View.VISIBLE);
            tv_take_off_due.setVisibility(View.VISIBLE);
            durata_missione_due.setVisibility(View.VISIBLE);
            landing_due.setVisibility(View.VISIBLE);
            take_off_due.setVisibility(View.VISIBLE);
            return true;
        }else{
            return false;
        }
    }

    public static void settaLandingDue(){
        String durataMissioneDue = durata_missione_due.getText().toString();

        if(!TextUtils.isEmpty(durataMissioneDue)){

            int durata = Integer.parseInt(durataMissioneDue);

            Calendar calendar = Calendar.getInstance();

            String takeOff = take_off_due.getText().toString();
            if(!takeOff.equals("Premere qui per inserire")){
                ArrayList<String> orarioArray = new ArrayList<>(Arrays.asList(takeOff.split(" : ")));
                int ore = Integer.parseInt(orarioArray.get(0));
                int minuti = Integer.parseInt(orarioArray.get(1));


                calendar.set(Calendar.HOUR_OF_DAY, ore);
                calendar.set(Calendar.MINUTE, minuti);
                calendar.add(Calendar.MINUTE, durata);

                landing_due.setText(calendar.get(Calendar.HOUR_OF_DAY) + " : " + calendar.get(Calendar.MINUTE));

                settaTotOreVolo();
            }
        }
    }

    public static int oraLandingUno(){
        return Integer.parseInt((new ArrayList<>(Arrays.asList(landing_uno.getText().toString().split(" : ")))).get(0));
    }

    public static int minutiLandingUno(){
        return Integer.parseInt((new ArrayList<>(Arrays.asList(landing_uno.getText().toString().split(" : ")))).get(1));
    }

    public static void settaTotOreVolo(){
        String durataUno = durata_missione_uno.getText().toString();
        String durataDue = durata_missione_due.getText().toString();

        if(!TextUtils.isEmpty(durataUno)){
            int durUno = Integer.parseInt(durataUno);

            if(durata_missione_due.getVisibility() == View.VISIBLE){

                if(!TextUtils.isEmpty(durataDue)){
                    int durDue = Integer.parseInt(durataDue);

                    int somma = durUno + durDue;
                    durata_totale.setText(String.valueOf(somma));
                    tot_ore_volo.setText(String.valueOf(Principale.getController().getTotOreVolo() + somma));
                }
            }else{
                durata_totale.setText(String.valueOf(durataUno));
                tot_ore_volo.setText(String.valueOf(Principale.getController().getTotOreVolo() + durUno));
            }
        }
    }
}
