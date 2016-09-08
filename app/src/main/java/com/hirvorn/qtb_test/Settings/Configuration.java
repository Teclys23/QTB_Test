package com.hirvorn.qtb_test.Settings;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.StartPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Gestione delle configurazioni
 * @author laboratorio
 *
 */
public class Configuration {

	private ArrayList<String> values;
	private String file;
	
	//utils
	private Config configFile = null;
	private final static String CONFIG_FILE = "config.properties";
	private Context context;
	
	/**
	 * Costruttore
	 * @param file
	 */
	public Configuration(String file, Context context){
		this.file = file;
		this.context = context;
	}
	
	
	/**
	 * Dato l'array di campi, va a cercare loro e restituisce le relative chiavi
	 * @param campi
	 * @return
	 */
	public ArrayList<String> ottieniProp(ArrayList<String> campi, Context context){

		this.context = context;

		this.values = new ArrayList<String>();
		
		ReadPropertyValues prop = new ReadPropertyValues();

		
		try {

			values = prop.getPropertyValues(file, campi, this.context, true);

			//System.out.println(values);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return values;
	}
	
	public Config creaConfig(Context context){

		this.context = context;

		ArrayList<String> campi = new ArrayList<String>();
		
		//-------------------------------------------------
		//Campi per la creazione del config
		campi.add("lastCode");
		campi.add("usersPath");
		campi.add("resourcesPath");
		campi.add("lastsessionFile");
        campi.add("userExtension");
		
		//-------------------------------------------------
	
		ReadPropertyValues prop = new ReadPropertyValues();
		
		ArrayList<String> config_values = null;
		
		//provo a leggere i valori
		try{
			config_values = new ArrayList<String>();

			config_values = prop.getPropertyValues(this.file, campi, this.context, false);


		}catch(IOException exc){
			exc.printStackTrace();
		}
		System.out.println(config_values.toString());
		
		Properties properties = new Properties();
		try {
			//InputStream inputStream = new FileInputStream(CONFIG_FILE);
			AssetManager assetManager = this.context.getAssets();
			InputStream inputStream = assetManager.open(CONFIG_FILE);
			//carico il file delle properties

			Log.v(StartPage.LOG_TAG, "Configuration InputStream: " + inputStream);
			properties.load(inputStream);
			
			//carico il configFile
			this.configFile = new Config(properties.getProperty("lastCode"),		// lastCode
										properties.getProperty("usersPath"),		// usersPath
										properties.getProperty("resourcesPath"),	// resourcesPath
										properties.getProperty("lastsessionFile"),	// lastsessionFile
                                        properties.getProperty("userExtension")
										);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		Log.v(StartPage.LOG_TAG, "File config accettato");
		
		return configFile;
	}

    public boolean esisteLastSession(){

        File file = new File("/data/data/com.hirvorn.qtb_test/shared_prefs/lastsession.xml");
        if (file.exists()) {
            Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
            return true;
        }
        else {
            Log.d("TAG", "Setup default preferences");
            return false;
        }

    }
}
