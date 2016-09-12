package com.hirvorn.qtb_test.Utente;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;

import java.util.ArrayList;

public class Brevetto {

    public static final String BREVETTO_EXT = ".brev";

	private String codice = "000000";
	private String data_rilascio;
	private String data_scadenza;

    private String brev_teoria;
	
	/**
	 * Costruttori
	 */
	public Brevetto(String codice, String data_rilascio, String data_scadenza){
		this.setCodice(codice);
		this.setData_rilascio(data_rilascio);
		this.setData_scadenza(data_scadenza);
	}

    public static void creaBrevettoVuoto(String codiceUtente){
        PropertiesWriter writer = new PropertiesWriter(codiceUtente + BREVETTO_EXT, Principale.getController().getContext());

        ArrayList<String> keys = new ArrayList<>();
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");

        ArrayList<String> values = new ArrayList<>();
        values.add("#");
        values.add("#");
        values.add("#");

        writer.write(keys, values);
    }


	
	
	/**
	 * Metodi
	 */
	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}


	public String getData_rilascio() {
		return data_rilascio;
	}


	public void setData_rilascio(String data_rilascio) {
		this.data_rilascio = data_rilascio;
	}


	public String getData_scadenza() {
		return data_scadenza;
	}


	public void setData_scadenza(String data_scadenza) {
		this.data_scadenza = data_scadenza;
	}
}
