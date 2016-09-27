package com.hirvorn.qtb_test.Operatore;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by laboratorio on 27/09/2016.
 */

public class OperatoreTerzi {

    public static final String OPERATORE_TERZI_EXT = ".opt";

    private String proprietario;
    private String nomeOperatoreTerzi;
    private String codiceEnacTerzi;
    private String aprUtilizzato;
    private String critico;

    public OperatoreTerzi(String proprietario, String nomeOperatoreTerzi, String codiceEnacTerzi, String aprUtilizzato, boolean critico){
        this.proprietario = proprietario;
        this.nomeOperatoreTerzi = nomeOperatoreTerzi;
        this.codiceEnacTerzi = codiceEnacTerzi;
        this.aprUtilizzato = aprUtilizzato;
        this.critico = String.valueOf(critico);
    }

    public void salvaDati(){
        PropertiesWriter writer = new PropertiesWriter(proprietario + OPERATORE_TERZI_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("proprietario");
        keys.add("nomeOperatoreTerzi");
        keys.add("codiceEnacTerzi");
        keys.add("aprUtilizzato");
        keys.add("critico");

        ArrayList<String> values = new ArrayList<>();
        values.add(proprietario);
        values.add(nomeOperatoreTerzi);
        values.add(codiceEnacTerzi);
        values.add(aprUtilizzato);
        values.add(critico);

        writer.write(keys, values);
    }
}
