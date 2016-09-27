package com.hirvorn.qtb_test.Operatore;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

import java.util.ArrayList;

/**
 * Created by laboratorio on 23/09/2016.
 */

public class Operatore {

    public static final String OPERATORE_EXT = ".op";

    private String proprietario;

    private String codiceEnac;
    private String scadenza;


    public Operatore(String proprietario, String codiceEnac, String scadenza){
        this.proprietario = proprietario;
        this.codiceEnac = codiceEnac;
        this.scadenza = scadenza;
    }

    public void salvaDati(){
        PropertiesWriter writer = new PropertiesWriter(Principale.getController().getProfilo().getCodice() + OPERATORE_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("proprietario");
        keys.add("codiceEnac");
        keys.add("scadenza");

        ArrayList<String> values = new ArrayList<>();
        values.add(proprietario);
        values.add(codiceEnac);
        values.add(scadenza);

        writer.write(keys, values);

        //lo aggiungo al profilo
        writer = new PropertiesWriter(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());

        keys.clear();
        values.clear();

        keys.add("fileOperatoreNormale");
        values.add(Principale.getController().getProfilo().getCodice() + OPERATORE_EXT);

        writer.write(keys, values);
    }

}
