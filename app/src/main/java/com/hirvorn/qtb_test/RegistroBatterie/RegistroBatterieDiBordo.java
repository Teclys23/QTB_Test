package com.hirvorn.qtb_test.RegistroBatterie;

import java.util.Date;

public class RegistroBatterieDiBordo {

	private Date data;
	private int tipo_operazione;
	private int tipo_esito;
	private String note;
	private Date ultimo_utilizzo;
	
	//firma?
	
	/**
	 * Costruttori
	 */
	public RegistroBatterieDiBordo(){
		
	}

	
	/**
	 * Metodi
	 */

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getTipo_operazione() {
		return tipo_operazione;
	}

	public void setTipo_operazione(int tipo_operazione) {
		this.tipo_operazione = tipo_operazione;
	}

	public int getTipo_esito() {
		return tipo_esito;
	}

	public void setTipo_esito(int tipo_esito) {
		this.tipo_esito = tipo_esito;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getUltimo_utilizzo() {
		return ultimo_utilizzo;
	}

	public void setUltimo_utilizzo(Date ultimo_utilizzo) {
		this.ultimo_utilizzo = ultimo_utilizzo;
	}
}
