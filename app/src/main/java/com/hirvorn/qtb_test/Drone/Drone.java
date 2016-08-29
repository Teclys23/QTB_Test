package com.hirvorn.qtb_test.Drone;

import java.util.Date;

public class Drone {

	private Date data;
	private int num_serie_APR_SPR;
	private int num_motori;
	
	//da analizzare
	private int categoria;
	private int marca;
	private int modello;
	private String note;
	//da vedere
	//- timbro
	//- firma
	
	
	/**
	 * Costruttori
	 */
	public Drone(){
		
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


	public int getNum_serie_APR_SPR() {
		return num_serie_APR_SPR;
	}


	public void setNum_serie_APR_SPR(int num_serie_APR_SPR) {
		this.num_serie_APR_SPR = num_serie_APR_SPR;
	}


	public int getNum_motori() {
		return num_motori;
	}


	public void setNum_motori(int num_motori) {
		this.num_motori = num_motori;
	}


	public int getCategoria() {
		return categoria;
	}


	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}


	public int getMarca() {
		return marca;
	}


	public void setMarca(int marca) {
		this.marca = marca;
	}


	public int getModello() {
		return modello;
	}


	public void setModello(int modello) {
		this.modello = modello;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
}
