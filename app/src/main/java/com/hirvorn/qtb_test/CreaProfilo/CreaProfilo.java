package com.hirvorn.qtb_test.CreaProfilo;

import android.content.Context;

import java.util.ArrayList;

import com.hirvorn.qtb_test.CreaBrevetto.*;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Utente.Profilo;

public class CreaProfilo {

	private Profilo profilo;
	
	
	public CreaProfilo(){
		this.profilo = null;
	}
	
	
	public void init(){
		OttieniDati ottieniDati = new OttieniDati();
		this.profilo = new Profilo(ottieniDati.getNome(), ottieniDati.getCognome(), ottieniDati.getMail(), ottieniDati.getTelefono());
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
