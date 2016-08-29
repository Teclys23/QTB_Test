package com.hirvorn.qtb_test.Login;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import com.hirvorn.qtb_test.CreaBrevetto.CreaBrevetto;
import com.hirvorn.qtb_test.CreaProfilo.CreaProfilo;
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
		Configuration lastSession = new Configuration(lastSessionFile, this.context);
		result = lastSession.ottieniProp(campiDaCercare, this.context);

		Sessione sessione = null;

		//controllo del risultato
		if(result.get(0).equals("") || result.size() > 1 || result.size() < 1){
			try {

				sessione = new Sessione("LastSessionNotFoundException");
				sessione.setValidSession(false);
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
	public void creaNuovoProfilo(String nome, String cognome, String mail, String telefono){
		CreaProfilo creaProfilo = new CreaProfilo();
		creaProfilo.init(nome, cognome, mail, telefono);
		creaProfilo.salvaProfilo();
		this.profilo = creaProfilo.getProfilo();
		
		//imposto il profilo come sessione attuale nel controller
		//---------------
		Log.v(StartPage.LOG_TAG, "QUAAAAAAAAAAAAAAAAAA");
		Principale.getController().setSessione(new Sessione(this.getProfilo().getCodice()));
		Log.v(StartPage.LOG_TAG, "QUAAAAAAAAAAAAAAAAAA 2");
		//---------------
	}
	
	//crea un nuovo brevetto e lo aggancia al profilo
	public void creaNuovoBrevetto(){
		CreaBrevetto creaB = new CreaBrevetto();
		creaB.init();
		creaB.salvaBrevetto();
	}
	
	public Profilo getProfilo() {
		return profilo;
	}
	public void setProfilo(Profilo profilo) {
		this.profilo = profilo;
	}
}
