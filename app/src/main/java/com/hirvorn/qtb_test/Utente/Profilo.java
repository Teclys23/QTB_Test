package com.hirvorn.qtb_test.Utente;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.hirvorn.qtb_test.Drone.Drone;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.StartPage;

public class Profilo {
	
	private String codice;
	private String nome;
	private String cognome;
	private String mail;
	private String telefono;
	private Brevetto brevetto;
	private ArrayList<Drone> droni_posseduti;
	
	//utils
	private static final int SUB_BEGIN_INDEX = 0;
	private static final int SUB_END_INDEX = 3;
	
	
	/**
	 * Costruttori
	 */
	public Profilo(String nome, String cognome, String mail, String telefono){
		this.nome = nome;
		this.cognome = cognome;
		this.mail = mail;
		this.telefono = telefono;
        droni_posseduti = new ArrayList<>();

	}


	/**
	 * Metodi
	 */
	//per configurare il profilo
	public void configCodiceProfilo(){
        Log.v(StartPage.LOG_TAG, "CONFIG CODICE PROFILO" + this.getNome() + this.getCognome());
        String subcode = getNome().substring(SUB_BEGIN_INDEX, SUB_END_INDEX).toUpperCase() + "-" +
							getCognome().substring(SUB_BEGIN_INDEX, SUB_END_INDEX).toUpperCase();
		setCodice(Principale.getConfig().getLastCode() + "-" + subcode);
		System.out.println("codice: " + getCodice());
	}

    public static Profilo getProfilo(String codiceUtente){

        ReadPropertyValues reader = new ReadPropertyValues();

        ArrayList<String> keys = new ArrayList<>();
        keys.add("codice");
        keys.add("nome");
        keys.add("cognome");
        keys.add("mail");
        keys.add("telefono");

        ArrayList<String> values = null;
        try {
            values = reader.getPropertyValues(codiceUtente + Principale.getConfig().getUserExtension(), keys, Principale.getController().getContext(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Profilo p = new Profilo(values.get(1), values.get(2), values.get(3), values.get(4));
        p.setCodice(values.get(0));

        return p;
    }

    /**
     * Metodo per ottenere un ArrayList contenente i codici dei droni posseduti
     * @return
     */
    public ArrayList<String> getDroniPosseduti(){

        ReadPropertyValues reader = new ReadPropertyValues();

        String droni = reader.getPropValue(this.getCodice() + Principale.getConfig().getUserExtension(), "drones");

        ArrayList<String> droni_posseduti = new ArrayList<String>(Arrays.asList(droni.split("#")));

        return droni_posseduti;
    }

    public void salvaProfilo(){
        PropertiesWriter writer = new PropertiesWriter(getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
        //Creo l'elenco di chiavi
        ArrayList<String> keys = new ArrayList<String>();
        keys.add("code");
        keys.add("name");
        keys.add("surname");
        keys.add("mail");
        keys.add("telephone");
        keys.add("drones");

        //creo l'elenco dei valori
        ArrayList<String> values = new ArrayList<String>();
        values.add(getCodice());
        values.add(getNome());
        values.add(getCognome());
        values.add(getMail());
        values.add(getTelefono());
        values.add("#");

        writer.write(keys, values);
    }
	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public ArrayList<Drone> getDroni_posseduti() {
		return droni_posseduti;
	}


	public void setDroni_posseduti(ArrayList<Drone> droni_posseduti) {
		this.droni_posseduti = droni_posseduti;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public Brevetto getBrevetto() {
		return brevetto;
	}


	public void setBrevetto(Brevetto brevetto) {
		this.brevetto = brevetto;
	}


	public String getCodice() {
		return codice;
	}


	public void setCodice(String codice) {
		this.codice = codice;
	}



}
