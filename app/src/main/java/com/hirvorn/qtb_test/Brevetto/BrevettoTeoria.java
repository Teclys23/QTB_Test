package com.hirvorn.qtb_test.Brevetto;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utente.Brevetto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by laboratorio on 12/09/2016.
 */
public class BrevettoTeoria {


    private String codiceUtente;
    private String luogo;
    private String data;
    private String numero;

    public BrevettoTeoria(String codiceUtente, String luogo, String data, String numero){
        this.codiceUtente = codiceUtente;
        this.luogo = luogo;
        this.data = data;
        this.numero = numero;
    }

    public void salvaBrevettoTeoria(){

        ReadPropertyValues reader = new ReadPropertyValues();

        // Salvo la pratica e la visita medica
        String brevetto_pratica_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_pratica");
        String visita_medica_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_visita_medica");

        // Creo stringa teoria
        String brevetto_teoria = this.luogo + "#" + this.data + "#" + numero + "#";

        // Salvo il tutto
        PropertiesWriter writer = new PropertiesWriter(this.codiceUtente + Brevetto.BREVETTO_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");

        ArrayList<String> values = new ArrayList<>();
        values.add(brevetto_teoria);
        values.add(brevetto_pratica_old);
        values.add(visita_medica_old);


        System.out.println("OMG VALUES " + values.toString());
        writer.write(keys, values);

    }
}
