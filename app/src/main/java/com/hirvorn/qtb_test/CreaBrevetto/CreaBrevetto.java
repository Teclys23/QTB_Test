package com.hirvorn.qtb_test.CreaBrevetto;

import android.content.Context;

import java.util.ArrayList;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Utente.Brevetto;

public class CreaBrevetto {

	private Brevetto brevetto;
	
	/**
	 * Costruttore
	 */
	public CreaBrevetto(){
		this.brevetto = null;
	}

	
	/**
	 * Metodi
	 */
	public void init(){
		OttieniDati dati = new OttieniDati();
		this.setBrevetto(new Brevetto(dati.getCodice(), dati.getDataRilascio(), dati.getDataScadenza()));
	}
	
	public void salvaBrevetto(){
		PropertiesWriter writer = new PropertiesWriter(Principale.getController().getSessione().getCodiceUtente() + ".properties", Principale.getController().getContext());
		
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
