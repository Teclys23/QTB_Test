package com.hirvorn.qtb_test.Controller;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.PropertiesWriter;
import com.hirvorn.qtb_test.Objects.Sessione;
import com.hirvorn.qtb_test.Brevetto.Brevetto;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utente.Profilo;

public class Controller {

	private Context context;
	private Sessione sessione;
	private String confiFileName = "config.properties";
	private Profilo profilo;
	private Brevetto brevetto;
	private String drone_attuale;
    private int tot_ore_volo;
	
	
	public Controller(Context context){
		this.sessione = null;
		this.context = context;
		this.profilo = null;
        this.drone_attuale = null;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;

		this.profilo = Profilo.getProfilo(this.sessione.getCodiceUtente());
		
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

    public boolean isValidBrevetto(){
        ReadPropertyValues reader = new ReadPropertyValues();
        if(!(reader.getPropValue(getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_teoria")).equals("#")
            && !(reader.getPropValue(getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_pratica")).equals("#")
                && !(reader.getPropValue(getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_visita_medica")).equals("#")
                ){
            return true;
        }
        else {
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

    public void setDroneAttuale(String drone_attuale){
        this.drone_attuale = drone_attuale;
    }

    public String getDroneAttuale(){
        return this.drone_attuale;
    }

    public void initTotOreVolo(){
        ReadPropertyValues reader = new ReadPropertyValues();
        tot_ore_volo = Integer.parseInt(reader.getPropValue(profilo.getCodice() + Principale.getConfig().getUserExtension(), "totOreVolo"));
    }

    public void addTotOreVolo(int minuti){
        tot_ore_volo  += minuti;

        PropertiesWriter writer = new PropertiesWriter(profilo.getCodice() + Principale.getConfig().getUserExtension(), Principale.getController().getContext());
        ArrayList<String> keys = new ArrayList<>();
        keys.add("totOreVolo");

        ArrayList<String> values = new ArrayList<>();
        values.add(String.valueOf(tot_ore_volo));

        writer.write(keys, values);
    }

    public int getTotOreVolo(){
        return tot_ore_volo;
    }
	
}
