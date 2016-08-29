package com.hirvorn.qtb_test.CreaBrevetto;

import java.util.Scanner;

public class OttieniDati {

	private String codice;
	private String data_rilascio;
	private String data_scadenza;
	
	
	/**
	 * Costruttore
	 */
	public OttieniDati(){
		
		richiediDati();
	}
	
	
	/**
	 * Metodi
	 */
	public void richiediDati(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("-- Brevetto --\n");
		/*
		System.out.print("Codice: ");
		this.codice = sc.next();
		
		System.out.print("Data rilascio: ");
		this.data_rilascio = sc.next();
		
		System.out.print("Data scadenza: ");
		this.data_scadenza = sc.next();
		*/
		this.codice = "1234123412";
		this.data_rilascio = "01/01/1993";
		this.data_scadenza = "23/01/2993";
	}
	
	public String getCodice(){
		return this.codice;
	}
	
	public String getDataRilascio(){
		return this.data_rilascio;
	}
	
	public String getDataScadenza(){
		return this.data_scadenza;
	}
}
