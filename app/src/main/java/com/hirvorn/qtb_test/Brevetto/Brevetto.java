package com.hirvorn.qtb_test.Brevetto;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;

import java.util.ArrayList;

public class Brevetto {

    public static final String BREVETTO_EXT = ".brev";

	private String codice = "000000";
	private String data_rilascio;
	private String data_scadenza;

    private String teoria;
    private String pratica;
    private String visita_medica;
    private String enac;
	
	/**
	 * Costruttori
	 */
	public Brevetto(String codice, String data_rilascio, String data_scadenza){
		this.setCodice(codice);
		this.setData_rilascio(data_rilascio);
		this.setData_scadenza(data_scadenza);
	}

    public Brevetto(String teoria, String pratica, String visita_medica, String enac){
        this.teoria = teoria;
        this.pratica = pratica;
        this.visita_medica = visita_medica;
        this.enac = enac;
    }

    public Brevetto(){}

    public static void creaBrevettoVuoto(String codiceUtente){
        PropertiesWriter writer = new PropertiesWriter(codiceUtente + BREVETTO_EXT, Principale.getController().getContext());

        ArrayList<String> keys = new ArrayList<>();
        keys.add("enac");
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");

        ArrayList<String> values = new ArrayList<>();
        values.add("null");
        values.add("#");
        values.add("#");
        values.add("#");

        writer.write(keys, values);

        // Salva brevetto nel profilo
        writer = new PropertiesWriter(codiceUtente + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
        keys.clear();
        values.clear();
        keys.add("brevetto");
        values.add(codiceUtente + BREVETTO_EXT);
        writer.write(keys,values);
    }

    public void salvaEnac(String enac){
        PropertiesWriter writer = new PropertiesWriter(Principale.getController().getSessione().getCodiceUtente() + BREVETTO_EXT, Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();

        keys.add("enac");
        values.add(enac);

        writer.write(keys, values);
    }
	
	
	/**
	 * Metodi
	 */
	public String getCodice() {
		return codice;
	}

    public String getEnac(){ return this.enac; }

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
