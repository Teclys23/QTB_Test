package com.hirvorn.qtb_test.Brevetto;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

import java.util.ArrayList;

/**
 * Created by laboratorio on 12/09/2016.
 */
public class BrevettoPratica {

    private String codiceUtente;
    private String luogo;
    private String data;
    private String numero;

    public BrevettoPratica(String codiceUtente, String luogo, String data, String numero){
        this.codiceUtente = codiceUtente;
        this.luogo = luogo;
        this.data = data;
        this.numero = numero;
    }

    public void salvaBrevettoPratica(){

        ReadPropertyValues reader = new ReadPropertyValues();

        // Salvo la teoria e la visita medica
        String brevetto_teoria_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_teoria");
        String visita_medica_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_visita_medica");

        // Creo stringa pratica
        String brevetto_pratica = this.luogo + "#" + this.data + "#" + numero + "#";

        // Salvo il tutto
        PropertiesWriter writer = new PropertiesWriter(this.codiceUtente + Brevetto.BREVETTO_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");

        ArrayList<String> values = new ArrayList<>();
        values.add(brevetto_teoria_old);
        values.add(brevetto_pratica);
        values.add(visita_medica_old);

        writer.write(keys, values);

    }
}
