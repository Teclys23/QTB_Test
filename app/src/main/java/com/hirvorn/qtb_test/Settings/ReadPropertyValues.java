package com.hirvorn.qtb_test.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.StartPage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class ReadPropertyValues {
	
	private ArrayList<String> result;
	private InputStream inputStream;
	private Context context;
	
	/**
	 * Metodi
	 * @throws IOException 
	 */
	public ArrayList<String> getPropertyValues(String file, ArrayList<String> campi, Context context, boolean useSharedPrferences) throws IOException{

		this.context = context;

		result = new ArrayList<String>();

		if(useSharedPrferences) {

			SharedPreferences reader = this.context.getSharedPreferences(file, this.context.MODE_PRIVATE);
			for (String campo : campi) {

				result.add(reader.getString(campo, null));
			}

			Log.v(StartPage.LOG_TAG, "Pref Letta: " + result.toString());
		}
		else {
			try {

				//creo l'oggetto properties
				Properties properties = new Properties();


				inputStream = this.context.getAssets().open(file);
				Log.v(StartPage.LOG_TAG, "InputStream: " + inputStream);

				if (inputStream != null) {
					properties.load(inputStream);
				} else {
					throw new FileNotFoundException("Property file '" + file + "' not found in classpath.");
				}

				//Ottieni i valori delle propriet� e li aggiunge all'array risultato
				for (String campo : campi) {
					result.add(properties.getProperty(campo));
					Log.v(StartPage.LOG_TAG, "Campo: " + campo);
				}

			} catch (Exception exc) {
				exc.printStackTrace();
			} finally {
				inputStream.close();
			}
		}
		return result;
	}

    public String getPropValue(String file, String key){

        SharedPreferences reader = Principale.getController().getContext().getSharedPreferences(file, Context.MODE_PRIVATE);
        return reader.getString(key, null);
    }

}
