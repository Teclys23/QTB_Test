package com.hirvorn.qtb_test.Controller;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Objects.Sessione;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Utente.Brevetto;
import com.hirvorn.qtb_test.Utente.Profilo;

public class Controller {

	private Context context;
	private Sessione sessione;
	private String confiFileName = "config.properties";
	private Profilo profilo;
	private Brevetto brevetto;
	
	
	
	public Controller(Context context){
		this.sessione = null;
		this.context = context;
		this.profilo = null;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;

		this.profilo = Profilo.getProfilo(this.sessione.getCodiceUtente());
        Log.v(StartPage.LOG_TAG, "QUAQUAQUA");
		
		//scrive la nuova sessione sul file lastsession
		//PropertiesWriter writer = new PropertiesWriter(Principale.getConfig().getResourcesPath() + Principale.getConfig().getLastsessionFile());

		PropertiesWriter writer = new PropertiesWriter(Principale.LASTSESSION_FILE, this.context);


		//-----------------------------------------------------------------------
		// Array keys e values
		
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("LastSession");

		ArrayList<String> values = new ArrayList<String>();
		values.add(this.sessione.getCodiceUtente());
		//-----------------------------------------------------------------------
		
		writer.write(keys, values);

	}

    public boolean isValidSession(){
        if(this.sessione != null){
            if(!this.sessione.getCodiceUtente().equals("null")){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    public boolean isValidEmail(String email){
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public boolean isValidPhone(String telefono){
        if (TextUtils.isEmpty(telefono)) {
            return false;
        } else {
            if (telefono.length() < 6 || telefono.length() > 13) {
                return false;
            } else {
                return android.util.Patterns.PHONE.matcher(telefono).matches();
            }
        }
    }

	public Context getContext(){
		return this.context;
	}

	public String getConfigFileName(){
		return this.confiFileName;
	}

	public void setProfilo(Profilo profilo){
		this.profilo = profilo;
	}

	public Profilo getProfilo(){
		return this.profilo;
	}

	public void setBrevetto(Brevetto brevetto){
		this.brevetto = brevetto;
	}

	public Brevetto getBrevetto(){
		return this.brevetto;
	}
	
}
