package com.hirvorn.qtb_test.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import com.hirvorn.qtb_test.StartPage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PropertiesWriter {

	private Context context;
	private Properties prop;
	private InputStream inputStream;
	private FileOutputStream output;
	private String fileName;
	
	
	public PropertiesWriter(String fileName, Context context){
		this.fileName = fileName;
		this.prop = new Properties();
		this.context = context;
		
	}
	
	public void write(ArrayList<String> keys, ArrayList<String> values){

		SharedPreferences.Editor editor = this.context.getSharedPreferences(fileName, this.context.MODE_PRIVATE).edit();
		for(String key : keys){
			editor.putString(key, values.get(keys.indexOf(key)));
			Log.v(StartPage.LOG_TAG, "Pref Settata: " + key + " | " + values.get(keys.indexOf(key)));
		}
		editor.commit();
	}
}
