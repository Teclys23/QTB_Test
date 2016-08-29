package com.hirvorn.qtb_test.CreaProfilo;

import android.support.v4.app.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import com.hirvorn.qtb_test.CreaBrevetto.*;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.StartPage;
import com.hirvorn.qtb_test.Utente.Profilo;

public class CreaProfilo extends AppCompatActivity{

	private Profilo profilo;
	private Fragment fragment;
	
	
	public CreaProfilo(){
		this.profilo = null;
	}
	
	
	public void init(String nome, String cognome, String mail, String telefono){

		Log.v(StartPage.LOG_TAG, "-------------***** ---------------------- **********-----------");

		/*
		if (!isFinishing()) {
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();
			ft.replace(R.id.fragment_main, fragment);
			ft.commit();
		}

/*
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_main, fragment);

		fragmentTransaction.commit();*/

		//OttieniDati ottieniDati = new OttieniDati();
		this.profilo = new Profilo(nome, cognome, mail, telefono);
		Log.v(StartPage.LOG_TAG, "NUOVO PROFILO " + nome + " " + cognome + " " + mail + " " + telefono);
		profilo.configCodiceProfilo();
	}
	
	public void salvaProfilo(){
		PropertiesWriter writer = new PropertiesWriter(this.profilo.getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
		//Creo l'elenco di chiavi
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("code");
		keys.add("name");
		keys.add("surname");
		keys.add("mail");
		keys.add("telephone");
		
		//creo l'elenco dei valori
		ArrayList<String> values = new ArrayList<String>();
		values.add(this.profilo.getCodice());
		values.add(this.profilo.getNome());
		values.add(this.profilo.getCognome());
		values.add(this.profilo.getMail());
		values.add(this.profilo.getTelefono());
		
		writer.write(keys, values);
	}
	
	public Profilo getProfilo(){
		return this.profilo;
	}
}
