package com.hirvorn.qtb_test.QTB;

import java.util.Date;

import com.hirvorn.qtb_test.Drone.Drone;

public class QTB {

    private static final int CICLI_BATTERIA_MAX = 6;

	private int num_volo;
	private Date data_volo;
	private Date ora_TO;
	private Date ora_LDG;
	private int cod_sistema;
	private int cod_batteria;
	private int tipo_operazione;
    private int codice_batteria;
    private int cicli_batteria;
	private int durata_volo;
    private int prograssivo_ore_volo;
	
	private Drone drone;
	//da analizzare
	private int cod_batteria_cicliGG;
	//luogo operazione: coord gps
	//firme?
	
	
	/**
	 * Costruttori
	 */
	public QTB(){
		
	}


	/**
	 * Metodi
	 */
	public int getNum_volo() {
		return num_volo;
	}


	public void setNum_volo(int num_volo) {
		this.num_volo = num_volo;
	}


	public Date getData_volo() {
		return data_volo;
	}


	public void setData_volo(Date data_volo) {
		this.data_volo = data_volo;
	}


	public Date getOra_TO() {
		return ora_TO;
	}


	public void setOra_TO(Date ora_TO) {
		this.ora_TO = ora_TO;
	}


	public Date getOra_LDG() {
		return ora_LDG;
	}


	public void setOra_LDG(Date ora_LDG) {
		this.ora_LDG = ora_LDG;
	}


	public int getCod_sistema() {
		return cod_sistema;
	}


	public void setCod_sistema(int cod_sistema) {
		this.cod_sistema = cod_sistema;
	}


	public int getCod_batteria() {
		return cod_batteria;
	}


	public void setCod_batteria(int cod_batteria) {
		this.cod_batteria = cod_batteria;
	}


	public int getTipo_operazione() {
		return tipo_operazione;
	}


	public void setTipo_operazione(int tipo_operazione) {
		this.tipo_operazione = tipo_operazione;
	}


	public int getDurata_volo() {
		return durata_volo;
	}


	public void setDurata_volo(int durata_volo) {
		this.durata_volo = durata_volo;
	}


	public int getCod_batteria_cicliGG() {
		return cod_batteria_cicliGG;
	}


	public void setCod_batteria_cicliGG(int cod_batteria_cicliGG) {
		this.cod_batteria_cicliGG = cod_batteria_cicliGG;
	}


	public Drone getDrone() {
		return drone;
	}


	public void setDrone(Drone drone) {
		this.drone = drone;
	}
}
