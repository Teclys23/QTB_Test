package com.hirvorn.qtb_test.Batteria;

import android.text.TextUtils;
import android.util.Log;

import com.hirvorn.qtb_test.Drone.Drone;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by laboratorio on 02/09/2016.
 */
public class Batteria {

    public static final String BATTERIA_FILE_EXTENSION = ".batt";

    private String drone;
    private String codice;

    public Batteria(String drone, String codice){
        this.drone = drone;
        this.codice = codice;
    }

    public void salvaBatteria(){

        PropertiesWriter writer = new PropertiesWriter(codice + BATTERIA_FILE_EXTENSION, Principale.getController().getContext());

        ArrayList<String> keys = new ArrayList<>();
        keys.add("drone");
        keys.add("codice");

        ArrayList<String> values = new ArrayList<>();
        values.add(drone);
        values.add(codice);

        writer.write(keys, values);

        //
        //--------------------------------------------------
        //Controllo le batterie già presenti nel drone
        ReadPropertyValues reader = new ReadPropertyValues();

        //prende l'elenco delle batterie
        ArrayList<String> ricerca_batterie = new ArrayList<>();
        ricerca_batterie.add("batterie");
        ArrayList<String> batterie_presenti = null;
        ArrayList<String> batterie_attuali = new ArrayList<>();
        try {
            batterie_presenti = reader.getPropertyValues(drone + Drone.DRONE_FILE_EXTENSION, ricerca_batterie, Principale.getController().getContext(), true);

            if(batterie_presenti.get(0) != null) {
                Log.v(StartPage.LOG_TAG, "Batterie presenti '" + batterie_presenti.get(0) + "'");
                batterie_attuali.addAll(Arrays.asList(batterie_presenti.get(0).split("#")));
            }else{
                batterie_presenti = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v(StartPage.LOG_TAG, "Batterie attuali: " + batterie_attuali.toString());

        //controllo la presenza di questa batteria
        boolean batteriaTrovata = false;
        int index = 0;
        while(index < batterie_attuali.size() && !batteriaTrovata){
            if(batterie_attuali.get(index).equals(codice)){
                batteriaTrovata = true;
            }
            else{
                index++;
            }
        }

        //se non lo ha trovato, lo aggiunge
        if(!batteriaTrovata){
            batterie_attuali.add(codice);

            keys.clear();
            keys.add("batterie");

            //riutilizzo un array precedente
            batterie_presenti.clear();
            batterie_presenti.add(TextUtils.join("#", batterie_attuali));

            PropertiesWriter writerDrone = new PropertiesWriter(drone + Drone.DRONE_FILE_EXTENSION, Principale.getController().getContext());
            writerDrone.write(keys, batterie_presenti);

            Log.v(StartPage.LOG_TAG, "Batteria aggiunta al drone: " + batterie_attuali.toString());
        }
    }
}
