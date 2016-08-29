package com.hirvorn.qtb_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

import java.io.IOException;
import java.util.ArrayList;


public class StartPage extends AppCompatActivity {

    public static final String LOG_TAG = "QTB_Test";

    //Componenti
    private static TextView sessioneCorrente;

    private static EditText crea_profilo_nome;
    private static EditText crea_profilo_cognome;
    private static EditText crea_profilo_mail;
    private static EditText crea_profilo_telefono;

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

    public void confermaCreaProfilo(View view){
        Login login = new Login();

        crea_profilo_nome = (EditText)findViewById(R.id.editText_nome);
        String nome = crea_profilo_nome.getText().toString();

        if(TextUtils.isEmpty(nome)){
            crea_profilo_nome.setError("Nome mancante");
        }

        crea_profilo_cognome = (EditText)findViewById(R.id.editText_cognome);
        String cognome = crea_profilo_cognome.getText().toString();

        if(TextUtils.isEmpty(cognome)){
            crea_profilo_cognome.setError("Cognome mancante");
        }

        crea_profilo_mail = (EditText)findViewById(R.id.editText_mail);
        String mail = crea_profilo_mail.getText().toString();

        if(TextUtils.isEmpty(mail)){
            crea_profilo_mail.setError("Mail mancante");
        }

        crea_profilo_telefono = (EditText)findViewById(R.id.editText_telefono);
        String telefono = crea_profilo_telefono.getText().toString();

        if(TextUtils.isEmpty(telefono)){
            crea_profilo_telefono.setError("Telefono mancante");
        }

        if(!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(cognome) && !TextUtils.isEmpty(mail) && !TextUtils.isEmpty(telefono)){
            login.creaNuovoProfilo(nome, cognome, mail, telefono);
        }
    }
}
