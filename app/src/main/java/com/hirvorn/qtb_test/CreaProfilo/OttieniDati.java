package com.hirvorn.qtb_test.CreaProfilo;

import java.util.Scanner;

public class OttieniDati {

	//Variabili per la creazione del profilo
	private String nome;
	private String cognome;
	private String mail;
	private String telefono;
	
	
	
	/**
	 * Costruttore
	 */
	public OttieniDati(){
		
		richiediDati();
		
	}
	
	
	/**
	 * Metodi
	 */
	//per richiedere i dati
	private void richiediDati(){
		/*
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Nome: ");
		this.nome = sc.nextLine();
		if(this.nome.length() < 3){
			System.out.print("Inserire un nome > 3: ");
			this.nome = sc.nextLine();
		}
		
		System.out.print("Cognome: ");
		this.cognome = sc.nextLine();
		if(this.cognome.length() < 3){
			System.out.print("Inserire un cognome > 3: ");
			this.cognome = sc.nextLine();
		}
		
		System.out.print("Mail: ");
		this.mail = sc.nextLine();
		//controllo mail ---------------------------------------------------------------------------------------!!
		
		System.out.print("Telefono: ");
		this.telefono = sc.nextLine();
		//controllo telefono ---------------------------------------------------------------------------------------!!
		*/
		this.nome = "carlo";
		this.cognome = "pierangelo";
		this.mail = "asdasd@asd.it";
		this.telefono = "1234123412";
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getCognome(){
		return this.cognome;
	}
	
	public String getMail(){
		return this.mail;
	}
	
	public String getTelefono(){
		return this.telefono;
	}
}
