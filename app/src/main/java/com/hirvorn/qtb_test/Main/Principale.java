package com.hirvorn.qtb_test.Main;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hirvorn.qtb_test.Controller.Controller;

import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Settings.Config;
import com.hirvorn.qtb_test.Settings.Configuration;
import com.hirvorn.qtb_test.Objects.Sessione;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.StartPage;

import java.util.ArrayList;
import java.util.Map;


public class Principale {

	//ConfigFile
	private static Config config;
	private static Controller controller;
	
	//utils
	public static final String CONFIG_FILE = "config.properties";
	public static final String LASTSESSION_FILE = "lastsession";
	private Context context;
	private Sessione sessioneCorrente;
	
	//public static void main(String[] args) {
	public Principale(Context context){

		this.context = context;

		//leggo il file di configurazione
		Configuration configuration = new Configuration(CONFIG_FILE, this.context);

        //crea file lastsession.properties se non esistente
        if(!configuration.esisteLastSession()){
            PropertiesWriter writer = new PropertiesWriter(Principale.LASTSESSION_FILE, this.context);
            ArrayList<String> keys = new ArrayList();
            keys.add("LastSession");
            ArrayList<String> values = new ArrayList<>();
            values.add("null");
            writer.write(keys, values);
        }

		config = configuration.creaConfig(this.context);

		//creo il controller
		controller = new Controller(this.context);
		
		//Controllo sessione
		
		Login login = new Login();


		//lastSession = login.ultimaSessione(lastSessionFile);
		Log.v(StartPage.LOG_TAG, "Ultima sessione: " + login.ultimaSessione(LASTSESSION_FILE, this.context).getCodiceUtente());
		Log.v(StartPage.LOG_TAG, "============================================================");
        //se l'ultima sessione è diversa da null
        if(!login.ultimaSessione(LASTSESSION_FILE, Principale.getController().getContext()).getCodiceUtente().equals("null")){
            controller.setSessione(login.ultimaSessione(LASTSESSION_FILE, this.context));
        }
        else{
            controller.setSessione(new Sessione("null"));
        }
		//controller.setSessione(login.ultimaSessione(LASTSESSION_FILE, this.context));
        Log.v(StartPage.LOG_TAG, "============================================================2");
		Log.v(StartPage.LOG_TAG, "controller - sessione: " + controller.getSessione());


		//se esiste l'ultima sessione, la apre
		//if(controller.getSessione().isValidSession()){

        if(controller.isValidSession()){
			//System.out.println("Sessione attiva: " + controller.getSessione().getCodiceUtente())
			this.sessioneCorrente = controller.getSessione();
			Log.v(StartPage.LOG_TAG, "Sessione valida: " + this.sessioneCorrente);
			
		}//altrimenti..
		else{
			Log.v(StartPage.LOG_TAG, "Sessione non valida. Avvio creazione profilo.");

/*
			login.creaNuovoProfilo();
			login.creaNuovoBrevetto();

			this.sessioneCorrente = controller.getSessione();

*/
		}


	}

	public static Config getConfig(){
		return Principale.config;
	}
	
	public static Controller getController(){
		return Principale.controller;
	}

	public Sessione getSessioneCorrente(){
		return this.sessioneCorrente;
	}

	private void creaLastSessionFile(){
        Log.v(StartPage.LOG_TAG, "CREA LAST SESSION FILE");
        SharedPreferences values = this.context.getSharedPreferences(LASTSESSION_FILE, Context.MODE_PRIVATE);
        Map campi = values.getAll();
        if(campi.isEmpty()){
            ArrayList<String> campi_da_settare = new ArrayList<>();
            ArrayList<String> valori_da_settare = new ArrayList<>();

            campi_da_settare.add("LastSession");
            valori_da_settare.add("null");

            PropertiesWriter writer = new PropertiesWriter(LASTSESSION_FILE, this.context);
            writer.write(campi_da_settare, valori_da_settare);
        }
    }

}
