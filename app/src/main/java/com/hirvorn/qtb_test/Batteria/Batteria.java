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

    private String codice;
    private String numero_celle;
    private String amperaggio;
    private String moltiplicatore_carica;
    private String moltiplicatore_scarica;
    private String valore_batteria_carica;
    private String valore_batteria_scarica;
    private String valore_batteria_storage;
    private String valore_percentuale_efficienza;
    private String valore_tensione_carica;

    public Batteria(String codice,
                    String numero_celle,
                    String amperaggio,
                    String moltiplicatore_carica,
                    String moltiplicatore_scarica,
                    String valore_batteria_carica,
                    String valore_batteria_scarica,
                    String valore_batteria_storage,
                    String valore_percentuale_efficienza,
                    String valore_tensione_carica){
        this.codice = codice;
        this.numero_celle = numero_celle;
        this.amperaggio = amperaggio;
        this.moltiplicatore_carica = moltiplicatore_carica;
        this.moltiplicatore_scarica = moltiplicatore_scarica;
        this.valore_batteria_carica = valore_batteria_carica;
        this.valore_batteria_scarica = valore_batteria_scarica;
        this.valore_batteria_storage = valore_batteria_storage;
        this.valore_percentuale_efficienza = valore_percentuale_efficienza;
        this.valore_tensione_carica = valore_tensione_carica;
    }

    public void salvaBatteria(String drone){

        Log.v(StartPage.LOG_TAG, "DRONE : " + drone);

       PropertiesWriter writer = new PropertiesWriter(drone + "_" + codice + BATTERIA_FILE_EXTENSION, Principale.getController().getContext());


        ArrayList<String> keys = new ArrayList<>();
        keys.add("codice");
        keys.add("numero_celle");
        keys.add("amperaggio");
        keys.add("moltiplicatore_carica");
        keys.add("moltiplicatore_scarica");
        keys.add("valore_batteria_carica");
        keys.add("valore_batteria_scarica");
        keys.add("valore_batteria_storage");
        keys.add("valore_percentuale_efficienza");
        keys.add("valore_tensione_carica");

        ArrayList<String> values = new ArrayList<>();
        values.add(codice);
        values.add(numero_celle);
        values.add(amperaggio);
        values.add(moltiplicatore_carica);
        values.add(moltiplicatore_scarica);
        values.add(valore_batteria_carica);
        values.add(valore_batteria_scarica);
        values.add(valore_batteria_storage);
        values.add(valore_percentuale_efficienza);
        values.add(valore_tensione_carica);

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
            batterie_presenti = reader.getPropertyValues(Principale.getController().getDroneAttuale() + Drone.DRONE_FILE_EXTENSION, ricerca_batterie, Principale.getController().getContext(), true);

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
