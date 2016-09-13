package com.hirvorn.qtb_test.CreaBrevetto;

import android.util.Log;

import java.util.ArrayList;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Brevetto.Brevetto;

public class CreaBrevetto {

	private Brevetto brevetto;
	private String codiceUtente;
	
	/**
	 * Costruttore
	 */
	public CreaBrevetto(String codiceUtente){
		this.brevetto = null;
		this.codiceUtente = codiceUtente;
	}

	
	/**
	 * Metodi
	 */
	public void init(String codice, String dataRilascio, String dataScadenza){
		this.brevetto = new Brevetto(codice, dataRilascio, dataScadenza);
		Log.v(StartPage.LOG_TAG, "Nuovo Brevetto -- cod: " + this.brevetto.getCodice() + " dataR: " + this.brevetto.getData_rilascio() + " dataS: " + this.brevetto.getData_scadenza());
	}
	
	public void salvaBrevetto(){
		PropertiesWriter writer = new PropertiesWriter(this.codiceUtente + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
		
		//Creo l'elenco di chiavi
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("brevetto_codice");
		keys.add("brevetto_dataRilascio");
		keys.add("brevetto_dataScadenza");

		
		//creo l'elenco dei valori
		ArrayList<String> values = new ArrayList<String>();
		values.add(this.brevetto.getCodice());
		values.add(this.brevetto.getData_rilascio());
		values.add(this.brevetto.getData_scadenza());

		
		writer.write(keys, values);
	}
	
	public Brevetto getBrevetto() {
		return brevetto;
	}

	public void setBrevetto(Brevetto brevetto) {
		this.brevetto = brevetto;
	}
	
	
}
