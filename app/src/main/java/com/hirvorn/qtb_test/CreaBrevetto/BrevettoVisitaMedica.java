package com.hirvorn.qtb_test.CreaBrevetto;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utente.Brevetto;

import java.util.ArrayList;

/**
 * Created by laboratorio on 12/09/2016.
 */
public class BrevettoVisitaMedica {

    private String codiceUtente;
    private String luogo;
    private String data;
    private String scadenza;

    public BrevettoVisitaMedica(String codiceUtente, String luogo, String data, String scadenza){
        this.codiceUtente = codiceUtente;
        this.luogo = luogo;
        this.data = data;
        this.scadenza = scadenza;
    }

    public void salvaBrevettoVisitaMedica(){

        ReadPropertyValues reader = new ReadPropertyValues();

        // Salvo la teoria e pratica
        String brevetto_teoria_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_teoria");
        String brevetto_pratica_old = reader.getPropValue(this.codiceUtente + Brevetto.BREVETTO_EXT, "brevetto_pratica");

        // Creo stringa teoria
        String brevetto_visita_medica = this.luogo + "#" + this.data + "#" + scadenza + "#";

        // Salvo il tutto
        PropertiesWriter writer = new PropertiesWriter(this.codiceUtente + Brevetto.BREVETTO_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");

        ArrayList<String> values = new ArrayList<>();
        values.add(brevetto_teoria_old);
        values.add(brevetto_pratica_old);
        values.add(brevetto_visita_medica);


        System.out.println("OMG VALUES " + values.toString());
        writer.write(keys, values);

    }
}
