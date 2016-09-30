package com.hirvorn.qtb_test.PreQTB;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Threads.CountDown_Thread;
import com.hirvorn.qtb_test.Threads.Countdown_Interface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * Created by laboratorio on 29/09/2016.
 */

public class Fragment_Volo extends Fragment{

    private static Button btn_take_off;
    private static Button btn_landing;
    private static TextView tv_take_off;
    private static TextView tv_landing;
    private static Button btn_termina_missione;
    private static TextView tv_avviso;
    private static Button btn_missione_terminata;

    private static String take_off_ora;
    private static String take_off_minuti;
    private static String landing_ora;
    private static String landing_minuti;
    private static String take_off_ora_secondaria;
    private static String take_off_minuti_secondaria;
    private static String landing_ora_secondaria;
    private static String landing_minuti_secondaria;
    private static boolean missionePrimaria;
    private static int durataMissione_ore;
    private static int durataMissione_minuti;
    private static int durataMissione_ore_secondaria;
    private static int durataMissione_minuti_secondaria;

    private static Thread countdown;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volo, container, false);

        btn_take_off = (Button)view.findViewById(R.id.btn_take_off);
        btn_landing = (Button)view.findViewById(R.id.btn_landing);
        tv_take_off = (TextView)view.findViewById(R.id.textView_volo_orario_take_off);
        tv_landing = (TextView)view.findViewById(R.id.textView_volo_orario_landing);
        btn_termina_missione = (Button)view.findViewById(R.id.btn_termina_missione);
        tv_avviso = (TextView)view.findViewById(R.id.textView_volo_avviso);
        btn_missione_terminata = (Button)view.findViewById(R.id.btn_missione_terminata);

        missionePrimaria = true;
        Principale.getController().setPreQTBMissionePrimaria(missionePrimaria);

        return view;
    }

    public static void takeOff(){
        Calendar calendar = Calendar.getInstance();
        String ora = String.valueOf(calendar.get(Calendar.HOUR));
        String minuti = String.valueOf(calendar.get(Calendar.MINUTE));
        Date date = calendar.getTime();

        tv_take_off.setText(date.toString());
        if (missionePrimaria)
            settaTakeOff(ora, minuti);
        else {
            settaTakeOffSecondaria(ora, minuti);
            tv_avviso.setVisibility(View.INVISIBLE);
        }

        btn_take_off.setVisibility(View.INVISIBLE);
        btn_landing.setVisibility(View.VISIBLE);

        if(btn_termina_missione.getVisibility() == View.VISIBLE)
            btn_termina_missione.setVisibility(View.INVISIBLE);

        if(missionePrimaria)
            Principale.getController().setMissioneChiusa(false);
        else
            Principale.getController().setMissioneChiusa(true);


    }

    public static void landing(){
        Calendar calendar = Calendar.getInstance();
        String ora = String.valueOf(calendar.get(Calendar.HOUR));
        String minuti = String.valueOf(calendar.get(Calendar.MINUTE));
        Date date = calendar.getTime();

        tv_landing.setText(date.toString());
        if(missionePrimaria)
            settaLanding(ora, minuti);
        else
            settaLandingSecondaria(ora, minuti);

        if(missionePrimaria){
            countdoown();
        }

        //if(missioneMinoreDiCinqueMinuti() && missionePrimaria){
        if(missionePrimaria && !Principale.getController().isMissioneChiusa()){
            mostraTerminaMissione();
            tv_avviso.setVisibility(View.VISIBLE);
            btn_landing.setVisibility(View.INVISIBLE);
            btn_take_off.setVisibility(View.VISIBLE);
            missionePrimaria = false;
            Principale.getController().setPreQTBMissionePrimaria(missionePrimaria);
        }else{
            btn_landing.setVisibility(View.INVISIBLE);
            btn_missione_terminata.setVisibility(View.VISIBLE);
            salvaDati();
        }

    }

    private static void settaTakeOff(String ora, String minuti){
        take_off_ora = ora;
        take_off_minuti = minuti;

    }

    private static void settaLanding(String ora, String minuti){
        landing_ora = ora;
        landing_minuti = minuti;
    }

    private static void settaTakeOffSecondaria(String ora, String minuti){
        take_off_ora_secondaria = ora;
        take_off_minuti_secondaria = minuti;

    }

    private static void settaLandingSecondaria(String ora, String minuti){
        landing_ora_secondaria = ora;
        landing_minuti_secondaria = minuti;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date orario_take_off_secondaria;
        Date orario_landing_secondaria;
        try {
            orario_take_off_secondaria = simpleDateFormat.parse(take_off_ora + ":" + take_off_minuti);
            orario_landing_secondaria = simpleDateFormat.parse(landing_ora + ":" + landing_minuti);

            long difference = orario_landing_secondaria.getTime() - orario_take_off_secondaria.getTime();
            if (difference < 0) {
                Date dateMax = simpleDateFormat.parse("24:00");
                Date dateMin = simpleDateFormat.parse("00:00");
                difference = (dateMax.getTime() - orario_landing_secondaria.getTime()) + (orario_take_off_secondaria.getTime() - dateMin.getTime());
            }
            int days = (int) (difference / (1000 * 60 * 60 * 24));
            int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
            int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);

            durataMissione_ore_secondaria = hours;
            durataMissione_minuti_secondaria = min;

        }catch (ParseException exc){
            exc.printStackTrace();
        }finally {
            btn_landing.setVisibility(View.INVISIBLE);
            btn_missione_terminata.setVisibility(View.VISIBLE);
        }
    }

    private static void mostraTerminaMissione(){
        btn_termina_missione.setVisibility(View.VISIBLE);
    }

    private static void countdoown(){
       /*
        new Thread() {
            public void run() {
                for (int i = 0; i < 5; i++){

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ((Activity)(Principale.getController().getContext())).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        chiudiMissione();
                    }
                });

                }
            }.start();*/
        countdown = new Thread(new CountDown_Thread());
        countdown.start();


    }

    private static boolean missioneMinoreDiCinqueMinuti(){
        boolean minore = false;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date orario_take_off;
        Date orario_landing;
        try {
            orario_take_off = simpleDateFormat.parse(take_off_ora + ":" + take_off_minuti);
            orario_landing = simpleDateFormat.parse(landing_ora + ":" + landing_minuti);

            long difference = orario_landing.getTime() - orario_take_off.getTime();
            if(difference<0)
            {
                Date dateMax = simpleDateFormat.parse("24:00");
                Date dateMin = simpleDateFormat.parse("00:00");
                difference = (dateMax.getTime() - orario_landing.getTime()) + (orario_take_off.getTime() - dateMin.getTime());
            }
            int days = (int) (difference / (1000*60*60*24));
            int hours = (int) ((difference - (1000*60*60*24*days)) / (1000*60*60));
            int min = (int) (difference - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
            durataMissione_ore = hours;
            durataMissione_minuti = min;

            if(min <= 5)
                minore = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return minore;
    }

    public static void salvaDati(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("takeOffUno");
        keys.add("landingUno");
        keys.add("durataMissioneUnoOre");
        keys.add("durataMissioneUnoMinuti");

        keys.add("takeOffDue");
        keys.add("landingDue");
        keys.add("durataMissioneDueOre");
        keys.add("durataMissioneDueMinuti");

        keys.add("durataMissioneTotale");
        keys.add("totOreDiVolo");
        keys.add("completo");

        ArrayList<String> values = new ArrayList<>();
        values.add(take_off_ora + ":" + take_off_minuti);
        values.add(landing_ora + ":" + landing_minuti);
        values.add(String.valueOf(durataMissione_ore));
        values.add(String.valueOf(durataMissione_minuti));

        if(!missionePrimaria) {
            values.add(take_off_ora_secondaria + ":" + take_off_minuti_secondaria);
            values.add(landing_ora_secondaria + ":" + landing_minuti_secondaria);
            values.add(String.valueOf(durataMissione_ore_secondaria));
            values.add(String.valueOf(durataMissione_minuti_secondaria));
        }else{
            values.add("null");
            values.add("null");
            values.add("null");
            values.add("null");
        }

        if(missionePrimaria) {
            values.add(String.valueOf(60 * durataMissione_ore + durataMissione_minuti));
            values.add(String.valueOf((Principale.getController().getTotOreVolo() + (60*durataMissione_ore + durataMissione_minuti))));

        }else{
            values.add(String.valueOf((60*durataMissione_ore + durataMissione_minuti) + (60*durataMissione_ore_secondaria + durataMissione_minuti_secondaria)));
            values.add(String.valueOf((Principale.getController().getTotOreVolo() + ((60*durataMissione_ore + durataMissione_minuti) + (60*durataMissione_ore_secondaria + durataMissione_minuti_secondaria)))));
        }

        values.add("true");

        Principale.getController().getLibrettoDiVolo().salvaDati(keys, values);

        //aggiorna tot ore volo profilo
        if(missionePrimaria) {
            Principale.getController().addTotOreVolo(60 * durataMissione_ore + durataMissione_minuti);
            Log.v(StartPage.LOG_TAG, "Durata aggiunta: " + (60 * durataMissione_ore + durataMissione_minuti));
        }else {
            Principale.getController().addTotOreVolo((60*durataMissione_ore + durataMissione_minuti) + (60 * durataMissione_ore_secondaria + durataMissione_minuti_secondaria));
            Log.v(StartPage.LOG_TAG, "Durata aggiunta: " + (60*durataMissione_ore + durataMissione_minuti) + (60 * durataMissione_ore_secondaria + durataMissione_minuti_secondaria));
        }
    }

    public static void terminaMissione(){
        btn_landing.setVisibility(View.INVISIBLE);
        btn_missione_terminata.setVisibility(View.VISIBLE);
        btn_termina_missione.setVisibility(View.INVISIBLE);
        tv_avviso.setVisibility(View.INVISIBLE);
        Principale.getController().setMissioneChiusa(true);
        salvaDati();
    }

    public static void chiudiMissione(){
        if(btn_take_off.getVisibility() == View.VISIBLE)
            btn_take_off.setVisibility(View.INVISIBLE);

        if(btn_landing.getVisibility() == View.VISIBLE)
            btn_landing.setVisibility(View.INVISIBLE);

        tv_avviso.setVisibility(View.INVISIBLE);
        btn_missione_terminata.setVisibility(View.VISIBLE);
        btn_termina_missione.setVisibility(View.INVISIBLE);

        Principale.getController().setMissioneChiusa(true);
        salvaDati();
    }

}
