package com.hirvorn.qtb_test.Controller;

import android.widget.Toast;

import com.hirvorn.qtb_test.Brevetto.Brevetto;
import com.hirvorn.qtb_test.Drone.Drone;
import com.hirvorn.qtb_test.LibrettoDiVolo.LibrettoDiVolo;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Operatore.Operatore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Jhonny on 07/11/2016.
 */

public class ThreadLogCreator implements Runnable {

    final static String XML_EXTENSION = ".xml";
    FileOutputStream out;

    String name;

    public ThreadLogCreator(FileOutputStream out, String name){
        this.out = out;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //Prende tutti i dati dal file name + librettodivolo.logbook_extension
            String s = Principale.getController().ottieniDatiPerLog(name + LibrettoDiVolo.LOGBOOK_EXTENSION);
            System.out.println("------------------ Prova dati: " + s);
            out.write(s.getBytes());

            //recupero tutti gli altri dati
            String[] splittedName = name.split("_");
            name = splittedName[0];

            //inserisce i dati dell'utente .user
            s = "\n--- .user ---";
            out.write(s.getBytes());
            //GESTIONE DA CAMBIARE
            s = Principale.getController().ottieniDatiPerLog(name + ".user");
            System.out.println("USER : " + s);
            out.write(s.getBytes());

            //inserisce i dati del brevetto .brev
            s = "\n--- " + Brevetto.BREVETTO_EXT + "  ---";
            out.write(s.getBytes());
            System.out.println("SSSSSS " + name + Brevetto.BREVETTO_EXT);
            s = Principale.getController().ottieniDatiPerLog(name + Brevetto.BREVETTO_EXT);
            out.write(s.getBytes());
            /*
            //inserisce i dati dell'operatore .op
            s = "\n--- " + Operatore.OPERATORE_EXT + " ---\n";
            out.write(s.getBytes());
            s = Principale.getController().ottieniDatiPerLog(name + Operatore.OPERATORE_EXT);
            out.write(s.getBytes());
            */
            //ricerca i dati operatori terzi
            File prefsdir = new File(Principale.getController().getContext().getApplicationInfo().dataDir, "shared_prefs");
            if(prefsdir.exists() && prefsdir.isDirectory()){
                ArrayList<String> listaTerzi = new ArrayList<>(Arrays.asList(prefsdir.list()));
                ArrayList<String> listaTerziFinale = new ArrayList<>();

                for(int i = 0; i < listaTerzi.size(); i++){
                    String[] token = listaTerzi.get(i).split("\\.");
                    if(token[1].equals("opt")){
                       listaTerziFinale.add(token[0] + "." + token[1]);
                    }
                }

                for(String terzi : listaTerziFinale){
                    s = "\n--- " + terzi + " ---";
                    out.write(s.getBytes());
                    s = Principale.getController().ottieniDatiPerLog(terzi);
                    out.write(s.getBytes());
                }
            }

            //Informazioni drone attuale
            s = "\n--- " + Principale.getController().getDroneAttuale() + " ---";
            out.write(s.getBytes());
            s = Principale.getController().ottieniDatiPerLog(Principale.getController().getDroneAttuale() + Drone.DRONE_FILE_EXTENSION);
            out.write(s.getBytes());

            //leggi i dati di tutte le batterie
            if(prefsdir.exists() && prefsdir.isDirectory()){
                ArrayList<String> listaBatterieDir = new ArrayList<>(Arrays.asList(prefsdir.list()));
                ArrayList<String> listaBatterie = new ArrayList<>();

                for(int i = 0; i < listaBatterieDir.size(); i++){
                    String[] token = listaBatterieDir.get(i).split("\\.");
                    if(token[0].contains(Principale.getController().getDroneAttuale()) && token[1].equals("batt")){
                        listaBatterie.add(token[0] +  "." + token[1]);
                    }
                }

                for(String batteria : listaBatterie){
                    s = "\n--- " + batteria + " ---";
                    out.write(s.getBytes());
                    s = Principale.getController().ottieniDatiPerLog(batteria);
                    out.write(s.getBytes());
                }
            }


            Toast.makeText(Principale.getController().getContext(), "Log creato " + name, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
