package com.hirvorn.qtb_test.Operatore;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;

import java.util.ArrayList;

/**
 * Created by laboratorio on 26/09/2016.
 */

public class OperatoreCritico {

    public static final String OPERATORE_CRITICO_EXT = ".opc";

    private String proprietario;
    private String codiceEnacCritico;
    private String scadenzaCritico;

    public OperatoreCritico(String proprietario, String codiceEnacCritico, String scadenzaCritico){
        this.proprietario = proprietario;
        this.codiceEnacCritico = codiceEnacCritico;
        this.scadenzaCritico = scadenzaCritico;
    }

    public void salvaDati(){
        PropertiesWriter writer = new PropertiesWriter(Principale.getController().getProfilo().getCodice() + OPERATORE_CRITICO_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("proprietario");
        keys.add("codiceEnacCritico");
        keys.add("scadenzaCritico");

        ArrayList<String> values = new ArrayList<>();
        values.add(proprietario);
        values.add(codiceEnacCritico);
        values.add(scadenzaCritico);

        writer.write(keys, values);

        //lo aggiungo al profilo
        writer = new PropertiesWriter(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());

        keys.clear();
        values.clear();

        keys.add("fileOperatoreCritico");
        values.add(Principale.getController().getProfilo().getCodice() + OPERATORE_CRITICO_EXT);

        writer.write(keys, values);
    }
}
