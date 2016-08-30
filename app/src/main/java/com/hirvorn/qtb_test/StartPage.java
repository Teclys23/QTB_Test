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
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_CreaBrevetto;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utils.CustomViewPager;

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
        /*
        Fragment fragment = new Fragment_CreaProfilo();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main, fragment);
        fragmentTransaction.commit();*/

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

    }

    private void transizioneFragment(int vecchioFrag, int nuovoFrag){

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

                default: return new Fragment_CreaBrevetto();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


    }

}
