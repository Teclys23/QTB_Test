package com.hirvorn.qtb_test.Objects;

import java.util.ArrayList;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;

public class Sessione {

	private String codiceUtente;

	
	
	/**
	 * Costruttore
	 */
	public Sessione(String codiceUtente){
		this.codiceUtente = codiceUtente;
	}
	
	
	/**
	 * Metodi
	 */	
	//scrive la LastSession
	public void salvaUltimaSessione(String codiceUtente){
		//possibile che ci siano gi� altri campi? ----------------------------------------------------------!!
		
		PropertiesWriter writer = new PropertiesWriter(codiceUtente + ".properties", Principale.getController().getContext());
		
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("lastSession");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(codiceUtente);
	}
	
	public String getCodiceUtente() {
		return codiceUtente;
	}

	public void setCodiceUtente(String sessione) {
		this.codiceUtente = sessione;
	}

}
