package com.hirvorn.qtb_test.Utente;

public class Brevetto {

	private String codice;
	private String data_rilascio;
	private String data_scadenza;
	
	
	/**
	 * Costruttori
	 */
	public Brevetto(String codice, String data_rilascio, String data_scadenza){
		this.setCodice(codice);
		this.setData_rilascio(data_rilascio);
		this.setData_scadenza(data_scadenza);
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
