package com.hirvorn.qtb_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

import java.io.IOException;
import java.util.ArrayList;


public class StartPage extends AppCompatActivity {

    public static final String LOG_TAG = "QTB_Test";

    //Componenti
    private static TextView sessioneCorrente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_start_page);

        init();
    }

    public void init(){

        Principale principale = new Principale(this);

        sessioneCorrente = (TextView)findViewById(R.id.tw_sessioneCorrente);

        ReadPropertyValues readPropertyValues = new ReadPropertyValues();

        if(Principale.getController().getSessione().getCodiceUtente().equals("null")){
            sessioneCorrente.setText("Sessione corrente: null");
        }
        else {
            sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "name"));
        }
    }

    public static void setSessioneCorrente(String text){
        sessioneCorrente.setText(text);
    }
}
