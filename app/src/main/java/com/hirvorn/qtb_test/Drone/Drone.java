package com.hirvorn.qtb_test.Drone;

import android.text.TextUtils;
import android.util.Log;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Drone {

    private static final String DRONE_FILE_EXTENSION = ".drone";

    private String data;
    private String categoria;
    private String marca;
    private String apr;
    private String spr;
    private String numero_motori;
    private String proprietario;

    public Drone(String data, String categoria, String marca, String apr, String spr, String numero_motori, String proprietario){
        this.data = data;
        this.categoria = categoria;
        this.marca = marca;
        this.apr = apr;
        this.spr = spr;
        this.numero_motori = numero_motori;
        this.proprietario = proprietario;
    }

    public void salvaNuovoDrone(){

        PropertiesWriter writer = new PropertiesWriter(this.getApr() + this.getSpr() + DRONE_FILE_EXTENSION, Principale.getController().getContext());

        /**
         * Crea il file.drone
         */
        //creo elenco delle chiavi
        ArrayList<String> keys = new ArrayList<>();

        keys.add("data");
        keys.add("categoria");
        keys.add("marca");
        keys.add("apr");
        keys.add("spr");
        keys.add("numero_motori");

        //creo elenco dei valori
        ArrayList<String> values = new ArrayList<>();

        values.add(this.getData());
        values.add(this.getCategoria());
        values.add(this.getMarca());
        values.add(this.getApr());
        values.add(this.getSpr());
        values.add(this.getNumero_motori());

        writer.write(keys, values);

        //--------------------------------------------------
        //Controlla nel file del profilo se ci sono già droni posseduti e lo aggiunge
        ReadPropertyValues reader = new ReadPropertyValues();

        //prende l'elenco dei droni
        ArrayList<String> ricerca_droni = new ArrayList<>();
        ricerca_droni.add("drones");
        ArrayList<String> droni_posseduti_prima = null;
        ArrayList<String> droni_posseduti = new ArrayList<>();
        try {
            droni_posseduti_prima = reader.getPropertyValues(this.getProprietario() + Principale.getConfig().getUserExtension(), ricerca_droni, Principale.getController().getContext(), true);

            if(droni_posseduti_prima != null) {
                Log.v(StartPage.LOG_TAG, "DRONI POSSEDUTI PRIMA '" + droni_posseduti_prima.get(0) + "'");
                droni_posseduti.addAll(Arrays.asList(droni_posseduti_prima.get(0).split("#")));
            }else{
                droni_posseduti_prima = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v(StartPage.LOG_TAG, "Droni posseduti prima: " + droni_posseduti.toString());

        //controlla la presenza di questo drone
        boolean droneTrovato = false;
        int index = 0;
        while(!droneTrovato && index < droni_posseduti.size()){
            if(droni_posseduti.get(index).equals(this.getApr()+this.getSpr())){
                droneTrovato = true;
            }else{
                index++;
            }
        }

        //se non lo ha trovato, lo aggiunge
        if(!droneTrovato){
            droni_posseduti.add(this.getApr() + this.getSpr());

            //lo riscrive sul file del profilo
            keys.clear();
            keys.add("drones");

            //riutilizzo droni_posseduti_prima
            droni_posseduti_prima.clear();
            droni_posseduti_prima.add(TextUtils.join("#", droni_posseduti));

            PropertiesWriter writerProfilo = new PropertiesWriter(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
            writerProfilo.write(keys, droni_posseduti_prima);

            Log.v(StartPage.LOG_TAG, "Drone aggiunto al profilo: " + droni_posseduti_prima.toString());

        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getApr() {
        return apr;
    }

    public void setApr(String apr) {
        this.apr = apr;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }

    public String getNumero_motori() {
        return numero_motori;
    }

    public void setNumero_motori(String numero_motori) {
        this.numero_motori = numero_motori;
    }

    public String getProprietario(){
        return this.proprietario;
    }
    /*
        private Date data;
        private int num_serie_APR_SPR;
        private int num_motori;

        //da analizzare
        private int categoria;
        private int marca;
        private int modello;
        private String note;
        //da vedere
        //- timbro
        //- firma


        /**
         * Costruttori

        public Drone(){

        }



        /**
         * Metodi

        public Date getData() {
            return data;
        }


        public void setData(Date data) {
            this.data = data;
        }


        public int getNum_serie_APR_SPR() {
            return num_serie_APR_SPR;
        }


        public void setNum_serie_APR_SPR(int num_serie_APR_SPR) {
            this.num_serie_APR_SPR = num_serie_APR_SPR;
        }


        public int getNum_motori() {
            return num_motori;
        }


        public void setNum_motori(int num_motori) {
            this.num_motori = num_motori;
        }


        public int getCategoria() {
            return categoria;
        }


        public void setCategoria(int categoria) {
            this.categoria = categoria;
        }


        public int getMarca() {
            return marca;
        }


        public void setMarca(int marca) {
            this.marca = marca;
        }


        public int getModello() {
            return modello;
        }


        public void setModello(int modello) {
            this.modello = modello;
        }


        public String getNote() {
            return note;
        }


        public void setNote(String note) {
            this.note = note;
        }
        */

}
