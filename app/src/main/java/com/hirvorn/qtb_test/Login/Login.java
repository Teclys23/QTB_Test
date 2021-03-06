package com.hirvorn.qtb_test.Login;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import com.hirvorn.qtb_test.Batteria.Batteria;
import com.hirvorn.qtb_test.CreaBrevetto.CreaBrevetto;
import com.hirvorn.qtb_test.Drone.Drone;
import com.hirvorn.qtb_test.Eccezioni.LastSessionNotFoundException;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.Configuration;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Utente.Profilo;
import com.hirvorn.qtb_test.Objects.Sessione;

public class Login {

	private Profilo profilo;
	private Context context;
	
	
	/**
	 * Costruttore
	 */
	public Login(){
		this.profilo = null;
	}
	
	
	/**
	 * Metodi
	 */
	//Controlla l'esistenza di un'ultima sessione
	public Sessione ultimaSessione(String lastSessionFile, Context context){

		this.context = context;

		//creo array di sostegno
		ArrayList<String> campiDaCercare = new ArrayList<String>();
		ArrayList<String> result = new ArrayList<String>();
		
		//cerca il campo "LastSession"
		campiDaCercare.add("LastSession");

		//creo oggetto Configuration e ottengo le chiavi
		Configuration lastSession = new Configuration(Principale.LASTSESSION_FILE, this.context);
		result = lastSession.ottieniProp(campiDaCercare, this.context);

		Sessione sessione = null;

		//controllo del risultato
		if(result.get(0).equals("") || result.size() > 1 || result.size() < 1){
			try {

				sessione = new Sessione("LastSessionNotFoundException");
				throw new LastSessionNotFoundException();
			} catch (LastSessionNotFoundException e) {
				//e.printStackTrace();
			}
		}else{
			sessione = new Sessione(result.get(0));
		}
		Log.v(StartPage.LOG_TAG, "Fine ultimaSessione()");
		return sessione;
	}
	
	//crea nuovo profilo
	public void creaNuovoProfilo(String nome, String cognome, String mail, String telefono, String codiceFiscale, String residenza, String via, String numeroCivico, String cap){

		Profilo profilo = new Profilo(nome, cognome, mail, telefono, codiceFiscale, residenza, via, numeroCivico, cap, "0");
        profilo.configCodiceProfilo();
		profilo.salvaProfilo();
		this.profilo = profilo;
		
		//imposto il profilo come sessione attuale nel controller
		//---------------
        Principale.getController().setSessione(new Sessione(profilo.getCodice()));
		Principale.getController().setProfilo(profilo);

		//---------------
	}
	
	//crea un nuovo brevetto e lo aggancia al profilo
	public void creaNuovoBrevetto(String codiceUtente, String codice, String data_rilascio, String data_scadenza){
		CreaBrevetto creaB = new CreaBrevetto(codiceUtente);
		creaB.init(codice, data_rilascio, data_scadenza);
		creaB.salvaBrevetto();

		//Imposta il brevetto nel controllore
		Principale.getController().setBrevetto(creaB.getBrevetto());
	}

    public void creaNuovoDrone(String categoria, String marca, String apr, String spr, String numero_motori){
        Drone drone = new Drone(categoria, marca, apr, spr, numero_motori, Principale.getController().getSessione().getCodiceUtente());
        drone.salvaNuovoDrone();
    }
	
	public Profilo getProfilo() {
		return profilo;
	}
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
}
