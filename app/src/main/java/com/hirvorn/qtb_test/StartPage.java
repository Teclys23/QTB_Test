package com.hirvorn.qtb_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.CreaBatteria.Fragment_CreaBatteria;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_CreaBrevetto;
import com.hirvorn.qtb_test.CreaDrone.Fragment_CreaDrone;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utente.Fragment_Profilo;
import com.hirvorn.qtb_test.Utils.CustomViewPager;

import java.io.IOException;
import java.util.ArrayList;


public class StartPage extends AppCompatActivity {

    public static final String LOG_TAG = "QTB_Test";

    //Componenti
    private static TextView sessioneCorrente;

    //Profilo
    private static EditText crea_profilo_nome;
    private static EditText crea_profilo_cognome;
    private static EditText crea_profilo_mail;
    private static EditText crea_profilo_telefono;

    //Brevetto
    private static EditText crea_brevetto_codice;
    private static EditText crea_brevetto_data_rilascio;
    private static EditText crea_brevetto_data_scadenza;

    //Drone
    private static EditText crea_drone_data;
    private static EditText crea_drone_categoria;
    private static EditText crea_drone_marca;
    private static EditText crea_drone_apr;
    private static EditText crea_drone_spr;
    private static EditText crea_drone_numero_motori;

    //Batteria
    private static EditText crea_batteria_drone;
    private static EditText crea_batteria_codice;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private CustomViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_page);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (CustomViewPager) findViewById(R.id.pager);


        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPagingEnabled(false);
        mPager.setAdapter(mPagerAdapter);

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
        mPager.setCurrentItem(0);

    }

    /**
     * Creazione di un nuovo profilo
     * @param view
     */
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
            mPager.setCurrentItem(1, true);
        }
    }

    /**
     * Creazione di un nuovo brevetto
     * @param view
     */
    public void confermaBrevetto(View view){

        Login login = new Login();

        crea_brevetto_codice = (EditText)findViewById(R.id.editText_codice);
        String codice = crea_brevetto_codice.getText().toString();

        if(TextUtils.isEmpty(codice)){
            crea_brevetto_codice.setError("Codice mancante");
        }

        crea_brevetto_data_rilascio = (EditText)findViewById(R.id.editText_data_rilascio);
        String data_rilascio = crea_brevetto_data_rilascio.getText().toString();

        if(TextUtils.isEmpty(data_rilascio)){
            crea_brevetto_data_rilascio.setError("Data rilascio mancante");
        }

        crea_brevetto_data_scadenza = (EditText)findViewById(R.id.editText_data_scadenza);
        String data_scadenza = crea_brevetto_data_scadenza.getText().toString();

        if(TextUtils.isEmpty(data_scadenza)){
            crea_brevetto_data_scadenza.setError("Data scadenza mancancete");
        }

        if(!TextUtils.isEmpty(codice) && !TextUtils.isEmpty(data_rilascio) && !TextUtils.isEmpty(data_scadenza)){
            login.creaNuovoBrevetto(Principale.getController().getSessione().getCodiceUtente(), codice, data_rilascio, data_scadenza);
            mPager.setCurrentItem(3, true);
        }
    }

    public void aggiungiDrone(View view){
        mPager.setCurrentItem(2, true);
    }

    /**
     * Crea un drone
     * @param view
     */
    public void confermaCreaDrone(View view){
        crea_drone_data = (EditText)findViewById(R.id.editText_crea_drone_data);
        String data = crea_drone_data.getText().toString();

        if(TextUtils.isEmpty(data)){
            crea_drone_data.setError("Data mancante");
        }

        crea_drone_categoria = (EditText)findViewById(R.id.editText_crea_drone_categoria);
        String categoria = crea_drone_categoria.getText().toString();

        if(TextUtils.isEmpty(categoria)){
            crea_drone_categoria.setError("Categoria mancante");
        }

        crea_drone_marca = (EditText)findViewById(R.id.editText_crea_drone_marca);
        String marca = crea_drone_marca.getText().toString();

        if(TextUtils.isEmpty(marca)){
            crea_drone_marca.setError("Marca mancante");
        }

        crea_drone_apr = (EditText)findViewById(R.id.editText_crea_drone_apr);
        String apr = crea_drone_apr.getText().toString();

        if(TextUtils.isEmpty(apr)){
            crea_drone_apr.setError("APR mancante");
        }

        crea_drone_spr = (EditText)findViewById(R.id.editText_crea_drone_spr);
        String spr = crea_drone_spr.getText().toString();

        if(TextUtils.isEmpty(spr)){
            crea_drone_spr.setError("SPR mancante");
        }

        crea_drone_numero_motori = (EditText)findViewById(R.id.editText_crea_drone_numero_motori);
        String numero_motori = crea_drone_numero_motori.getText().toString();

        if(TextUtils.isEmpty(numero_motori)){
            crea_drone_numero_motori.setError("Numero motori mancante");
        }

        if(!TextUtils.isEmpty(data) && !TextUtils.isEmpty(categoria) && !TextUtils.isEmpty(marca) && !TextUtils.isEmpty(apr) && !TextUtils.isEmpty(spr) && !TextUtils.isEmpty(numero_motori)){
            //salva drone
            Login login = new Login();
            login.creaNuovoDrone(data, categoria, marca, apr, spr, numero_motori);

            Fragment_Profilo.aggiungiCodiceDrone(apr + spr);
            mPager.setCurrentItem(3, true);

        }
    }

    public void aggiungiBatteria(View view){
        mPager.setCurrentItem(4, true);
    }

    public void confermaCreaBatteria(View view){
        crea_batteria_drone = (EditText)findViewById(R.id.crea_batteria_drone);
        String nomeDrone = crea_batteria_drone.getText().toString();

        if(TextUtils.isEmpty(nomeDrone)){
            crea_batteria_drone.setError("Drone mancante");
        }

        crea_batteria_codice = (EditText)findViewById(R.id.crea_batteria_codice);
        String codiceBatteria = crea_batteria_codice.getText().toString();

        if(TextUtils.isEmpty(codiceBatteria)){
            crea_batteria_codice.setError("Codice mancante");
        }

        if(!TextUtils.isEmpty(nomeDrone) && !TextUtils.isEmpty(codiceBatteria)){
            Login login = new Login();
            login.creaNuovaBatteria(nomeDrone, codiceBatteria);
            mPager.setCurrentItem(3, true);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.v(StartPage.LOG_TAG, "getItem Position: " + position);
            switch (position){
                case 0: return new Fragment_CreaProfilo();

                case 1: return new Fragment_CreaBrevetto();

                case 2: return new Fragment_CreaDrone();

                case 3: return Fragment_Profilo.nuovaIstanza(Principale.getController().getProfilo().getNome(),
                                                            Principale.getController().getProfilo().getCognome(),
                                                            Principale.getController().getBrevetto().getCodice());

                case 4: return new Fragment_CreaBatteria();

                default: return new Fragment_CreaBrevetto();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


    }

}
