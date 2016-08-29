package com.hirvorn.qtb_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
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


        //Controllo sessione corrente
        if(!Principale.getController().getSessione().isValidSession()){
            Toast.makeText(this, "Non hai ancora un profilo!",Toast.LENGTH_SHORT).show();
            iniziaCreaProfilo();

        }
        else {
            Log.v(StartPage.LOG_TAG, "ECCOCI " + Principale.getController().getSessione().getCodiceUtente());
            if (Principale.getController().getSessione().getCodiceUtente().equals("null")) {
                sessioneCorrente.setText("Sessione corrente: null");
            } else {

                sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "name"));
            }
        }
    }

    public static void setSessioneCorrente(String text){
        sessioneCorrente.setText(text);
    }

    private void iniziaCreaProfilo(){
        Fragment fragment = new Fragment_CreaProfilo();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.commit();
    }
}
