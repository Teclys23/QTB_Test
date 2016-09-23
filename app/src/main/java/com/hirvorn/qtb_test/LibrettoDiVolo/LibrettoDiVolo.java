package com.hirvorn.qtb_test.LibrettoDiVolo;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.hirvorn.qtb_test.Enum.VolumeDiVolo;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Objects.Sessione;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Utente.Profilo;

import java.io.File;
import java.util.ArrayList;

public class LibrettoDiVolo {

    public static final String LOGBOOK_EXTENSION = ".logbk";
    private static final String APP_PACKAGE = "com.hirvorn.qtb_test";

    private String proprietario;
    private String numeroLogbook;

	private String data;
    private VolumeDiVolo volume_volo;
    private String luogo;
    //coordinate;
    private String meteo;
    private String vento;
    private String apr;
    private String spr;
    private String secondo_dl;
    private String kill_fly;
    private boolean simulatore;
    private String nome_simulatore;
    private boolean att_sperimentali;
    private boolean att_critiche;
    private boolean att_non_critiche;
    private boolean att_addestramento;
    private boolean att_aggiornamento;
    private boolean att_vlos;
    private boolean att_blos;
    private String ora_to_1;
    private String ora_ldg_1;
    private String durata_missione;
    private String ora_to_2;
    private String ora_ldg_2;
    private String tot_ore_attivita;
    private String tot_ore_volo;
    private String qtb;

    public LibrettoDiVolo(String proprietario, String numeroLogbook){
        this.proprietario = proprietario;
        this.numeroLogbook = String.valueOf(Integer.parseInt(numeroLogbook));

        //controllo se il file esiste già

        File file = new File("/data/data/" + APP_PACKAGE + "/shared_prefs/" + proprietario + "_" + this.numeroLogbook + LOGBOOK_EXTENSION + ".xml");
        if(file.exists()){
            //non completo
            if((new ReadPropertyValues()).getPropValue(proprietario + "_" + this.numeroLogbook + LOGBOOK_EXTENSION, "completo").equals("false")){
            }else{
                this.numeroLogbook = String.valueOf(Integer.parseInt(this.numeroLogbook) + 1);
            }
        }

        PropertiesWriter writer = new PropertiesWriter(proprietario + "_" + this.numeroLogbook + LOGBOOK_EXTENSION, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("proprietario");
        keys.add("numeroLogbook");
        keys.add("completo");

        ArrayList<String> values = new ArrayList<>();
        values.add(proprietario);
        values.add(this.numeroLogbook);
        values.add("false");

        writer.write(keys, values);

        //aggiorna il profilo con il numero di logbook
        writer = new PropertiesWriter(proprietario + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
        keys.clear();
        keys.add("numeroLogbook");

        values.clear();
        values.add(this.numeroLogbook);

        writer.write(keys, values);
    }

    public void salvaDati(ArrayList<String> keys, ArrayList<String> values){
        PropertiesWriter writer = new PropertiesWriter(proprietario + "_" + this.numeroLogbook + LOGBOOK_EXTENSION, Principale.getController().getContext());
        writer.write(keys, values);
    }

}
