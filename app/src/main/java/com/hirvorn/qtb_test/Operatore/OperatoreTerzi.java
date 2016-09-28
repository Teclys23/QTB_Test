package com.hirvorn.qtb_test.Operatore;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

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
        ReadPropertyValues reader = new ReadPropertyValues();
        String apr = reader.getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "aprUtilizzati");

        PropertiesWriter writer = new PropertiesWriter(proprietario + "_" + codiceEnacTerzi + OPERATORE_TERZI_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("proprietario");
        keys.add("nomeOperatoreTerzi");
        keys.add("codiceEnacTerzi");
        keys.add("aprUtilizzati");
        keys.add("critico");

        ArrayList<String> values = new ArrayList<>();
        values.add(proprietario);
        values.add(nomeOperatoreTerzi);
        values.add(codiceEnacTerzi);
        values.add(apr + "#" + aprUtilizzato);
        values.add(critico);

        writer.write(keys, values);

        //lo aggiungo al profilo

        String operatori = reader.getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "fileOperatoreTerzi");

        writer = new PropertiesWriter(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());

        keys.clear();
        values.clear();

        keys.add("fileOperatoreTerzi");
        values.add(operatori + "#" + Principale.getController().getProfilo().getCodice() + "_" + codiceEnacTerzi + OPERATORE_TERZI_EXT);

        writer.write(keys, values);
    }
}
