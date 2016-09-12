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
import android.telephony.PhoneNumberUtils;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Brevetto.BrevettoTeoria;
import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.CreaBatteria.Fragment_CreaBatteria;
import com.hirvorn.qtb_test.CreaBrevetto.BrevettoPratica;
import com.hirvorn.qtb_test.CreaBrevetto.BrevettoVisitaMedica;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Main;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Pratica;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Teoria;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Visita_Medica;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_CreaBrevetto;
import com.hirvorn.qtb_test.CreaDrone.Fragment_CreaDrone;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Main.Fragment_Main;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Utente.Fragment_Profilo;
import com.hirvorn.qtb_test.Utils.CustomViewPager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StartPage extends AppCompatActivity {

    public static final String LOG_TAG = "QTB_Test";

    //Componenti
    private static TextView tw_sessioneCorrente;

    //Profilo
    private static EditText crea_profilo_nome;
    private static EditText crea_profilo_cognome;
    private static EditText crea_profilo_mail;
    private static EditText crea_profilo_telefono;
    private static EditText crea_profilo_codice_fiscale;
    private static EditText crea_profilo_residenza;
    private static EditText crea_profilo_via;
    private static EditText crea_profilo_cap;
    private static EditText crea_profilo_numero_civico;

    //Brevetto
    private static EditText crea_brevetto_codice;
    private static EditText crea_brevetto_data_rilascio;
    private static EditText crea_brevetto_data_scadenza;

    // Brevetto Teoria
    private static EditText brevetto_teoria_luogo;
    private static EditText brevetto_teoria_data;
    private static EditText brevetto_teoria_numero;

    // Brevetto Pratica
    private static EditText brevetto_pratica_luogo;
    private static EditText brevetto_pratica_data;
    private static EditText brevetto_pratica_numero;

    //Brevetto Visita Medica
    private static EditText brevetto_visita_medica_luogo;
    private static EditText brevetto_visita_medica_data;
    private static EditText brevetto_visita_medica_scadenza;

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
    private static final int NUM_PAGES = 8;

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


        tw_sessioneCorrente = (TextView)findViewById(R.id.tw_sessioneCorrente);


        ReadPropertyValues readPropertyValues = new ReadPropertyValues();


        //Controllo sessione corrente
        //if(!Principale.getController().isValidSession()){
        //---------------------------------------------------------- SOLO PER DEBUG
        if(Principale.getController().isValidSession()){
            Toast.makeText(this, "Non hai ancora un profilo!",Toast.LENGTH_SHORT).show();
            mPager.setCurrentItem(1);
            iniziaCreaProfilo();

        }
        else {
            Log.v(StartPage.LOG_TAG, "ECCOCI " + Principale.getController().getSessione().getCodiceUtente());
            if (Principale.getController().getSessione().getCodiceUtente().equals("null")) {
                tw_sessioneCorrente.setText("Sessione corrente: null");
            } else {

                tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "name"));
            }
        }
    }

    public static void setSessioneCorrente(String text){
        tw_sessioneCorrente.setText(text);
    }

    private void iniziaCreaProfilo(){
        mPager.setCurrentItem(1);

    }

    /**
     * Creazione di un nuovo profilo
     * @param view
     */
    public void confermaCreaProfilo(View view){
        Login login = new Login();

        // Nome
        crea_profilo_nome = (EditText)findViewById(R.id.editText_nome);
        String nome = crea_profilo_nome.getText().toString();

        if(TextUtils.isEmpty(nome)){
            crea_profilo_nome.setError("Nome mancante");
        }

        if(nome.length() < 3){
            crea_profilo_nome.setError("Il nome deve avere almeno tre caratteri");
        }

        String expression = "^[a-zA-Z]*$";

        CharSequence inputStr = nome;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        boolean nome_soloLettere = false;
        if(matcher.matches()) {
            nome_soloLettere = true;
        }else{
            crea_profilo_nome.setError("Il nome deve contenere solo lettere");
            crea_profilo_nome.setText("");
        }

        // Cognome
        crea_profilo_cognome = (EditText)findViewById(R.id.editText_cognome);
        String cognome = crea_profilo_cognome.getText().toString();

        if(TextUtils.isEmpty(cognome)){
            crea_profilo_cognome.setError("Cognome mancante");
        }

        if(cognome.length() < 3){
            crea_profilo_cognome.setError("Il cognome deve avere almeno tre caratteri");
        }

        inputStr = cognome;
        pattern = Pattern.compile(expression);
        matcher = pattern.matcher(inputStr);
        boolean cognome_soloLettere = false;
        if(matcher.matches()) {
            cognome_soloLettere = true;
        }else{
            crea_profilo_cognome.setError("Il cognome deve contenere solo lettere");
            crea_profilo_cognome.setText("");
        }

        // Mail
        crea_profilo_mail = (EditText)findViewById(R.id.editText_mail);
        String mail = crea_profilo_mail.getText().toString();

        if(TextUtils.isEmpty(mail)){
            crea_profilo_mail.setError("Mail mancante");
        }

        if(!Principale.getController().isValidEmail(mail)){
            crea_profilo_mail.setError("Inserire e-mail valida");
            crea_profilo_mail.setText("");
        }

        // Telefono
        crea_profilo_telefono = (EditText)findViewById(R.id.editText_telefono);
        String telefono = crea_profilo_telefono.getText().toString();

        if(TextUtils.isEmpty(telefono)){
            crea_profilo_telefono.setError("Telefono mancante");
        }

        if(!Principale.getController().isValidPhone(telefono)){
            crea_profilo_telefono.setError("Numero di telefono non valido");
            crea_profilo_telefono.setText("");
        }

        // Codice Fiscale
        crea_profilo_codice_fiscale = (EditText)findViewById(R.id.editText_codice_fiscale);
        String codiceFiscale = crea_profilo_codice_fiscale.getText().toString();

        if(TextUtils.isEmpty(codiceFiscale)){
            crea_profilo_codice_fiscale.setError("Codice fiscale mancante");
        }

        if(codiceFiscale.length() != 16){
            crea_profilo_codice_fiscale.setError("Il codice fiscale deve essere di 11 caratteri");
            crea_profilo_codice_fiscale.setText("");
        }

        // Residenza
        crea_profilo_residenza = (EditText)findViewById(R.id.editText_residenza);
        String residenza = crea_profilo_residenza.getText().toString();

        if(TextUtils.isEmpty(residenza)){
            crea_profilo_residenza.setError("Residenza mancante");
        }

        // Via
        crea_profilo_via = (EditText)findViewById(R.id.editText_via);
        String via = crea_profilo_via.getText().toString();

        if(TextUtils.isEmpty(via)){
            crea_profilo_via.setError("Via mancante");
        }

        // Numero civico
        crea_profilo_numero_civico = (EditText)findViewById(R.id.editText_numero_civico);
        String numeroCivico = crea_profilo_numero_civico.getText().toString();

        if(TextUtils.isEmpty(numeroCivico)){
            crea_profilo_numero_civico.setError("Nunmero civico mancante");
        }

        // CAP
        crea_profilo_cap = (EditText)findViewById(R.id.editText_cap);
        String cap = crea_profilo_cap.getText().toString();

        if(TextUtils.isEmpty(cap)){
            crea_profilo_cap.setError("C.A.P. mancante");
        }

        if(cap.length() != 5){
            crea_profilo_cap.setError("Inserire C.A.P. valido");
            crea_profilo_cap.setText("");
        }

        // Controllo completo
        if(!TextUtils.isEmpty(nome) && (nome.length() >= 3) && nome_soloLettere
                && !TextUtils.isEmpty(cognome) && (cognome.length() >= 3) && cognome_soloLettere
                && !TextUtils.isEmpty(mail) && Principale.getController().isValidEmail(mail)
                && !TextUtils.isEmpty(telefono) && Principale.getController().isValidPhone(telefono)
                && !TextUtils.isEmpty(codiceFiscale) && (codiceFiscale.length() == 16)
                && !TextUtils.isEmpty(residenza)
                && !TextUtils.isEmpty(via)
                && !TextUtils.isEmpty(numeroCivico)
                && !TextUtils.isEmpty(cap) && (cap.length() == 5)
                ){
            login.creaNuovoProfilo(nome, cognome, mail, telefono, codiceFiscale, residenza, via, numeroCivico, cap);
            setSessioneCorrente(Principale.getController().getProfilo().getNome());
            mPager.setCurrentItem(2, true);
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
            mPager.setCurrentItem(4, true);
        }
    }

    /**
     * Brevetto teoria
     * @param view
     */
    public void inserisciBrevettoTeoria(View view){
        mPager.setCurrentItem(3, true);
    }

    public void confermaBrevettoTeoria(View view){
        brevetto_teoria_luogo = (EditText)findViewById(R.id.editText_brevetto_teoria_luogo);
        String luogo = brevetto_teoria_luogo.getText().toString();

        if(TextUtils.isEmpty(luogo)){
            brevetto_teoria_luogo.setError("Luogo mancante");
        }

        brevetto_teoria_data = (EditText)findViewById(R.id.editText_brevetto_teoria_data);
        String data = brevetto_teoria_data.getText().toString();

        if(TextUtils.isEmpty(data)){
            brevetto_teoria_data.setError("Data mancante");
        }

        brevetto_teoria_numero = (EditText)findViewById(R.id.editText_brevetto_teoria_numero);
        String numero = brevetto_teoria_numero.getText().toString();

        if(TextUtils.isEmpty(numero)){
            brevetto_teoria_numero.setError("Numero mancante");
        }

        if(!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(numero)){

            BrevettoTeoria teoria = new BrevettoTeoria(Principale.getController().getSessione().getCodiceUtente(), luogo, data, numero);
            teoria.salvaBrevettoTeoria();

            mPager.setCurrentItem(2, true);
        }
    }

    public void inserisciBrevettoPratica(View view){
        mPager.setCurrentItem(4, true);
    }

    public void confermaBrevettoPratica(View view){
        brevetto_pratica_luogo = (EditText)findViewById(R.id.editText_brevetto_pratica_luogo);
        String luogo = brevetto_pratica_luogo.getText().toString();

        if(TextUtils.isEmpty(luogo)){
            brevetto_pratica_luogo.setError("Luogo mancante");
        }

        brevetto_pratica_data = (EditText)findViewById(R.id.editText_brevetto_pratica_data);
        String data = brevetto_pratica_data.getText().toString();

        if(TextUtils.isEmpty(data)){
            brevetto_pratica_data.setError("Data mancante");
        }

        brevetto_pratica_numero = (EditText)findViewById(R.id.editText_brevetto_pratica_numero);
        String numero = brevetto_pratica_numero.getText().toString();

        if(TextUtils.isEmpty(numero)){
            brevetto_pratica_numero.setError("Numero mancante");
        }

        if(!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(numero)){

            BrevettoPratica teoria = new BrevettoPratica(Principale.getController().getSessione().getCodiceUtente(), luogo, data, numero);
            teoria.salvaBrevettoPratica();

            mPager.setCurrentItem(2, true);
        }
    }

    public void inserisciBrevettoVisitaMedica(View view){
        mPager.setCurrentItem(5, true);
    }

    public void confermaBrevettoVisitaMedica(View view){
        brevetto_visita_medica_luogo = (EditText)findViewById(R.id.editText_brevetto_visita_medica_luogo);
        String luogo = brevetto_visita_medica_luogo.getText().toString();

        if(TextUtils.isEmpty(luogo)){
            brevetto_visita_medica_luogo.setError("Luogo mancante");
        }

        brevetto_visita_medica_data = (EditText)findViewById(R.id.editText_brevetto_visita_medica_data);
        String data = brevetto_visita_medica_data.getText().toString();

        if(TextUtils.isEmpty(data)){
            brevetto_visita_medica_data.setError("Data mancante");
        }

        brevetto_visita_medica_scadenza = (EditText)findViewById(R.id.editText_brevetto_visita_medica_scadenza);
        String scadenza = brevetto_visita_medica_scadenza.getText().toString();

        if(TextUtils.isEmpty(scadenza)){
            brevetto_visita_medica_scadenza.setError("Numero mancante");
        }

        if(!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(scadenza)){

            BrevettoVisitaMedica teoria = new BrevettoVisitaMedica(Principale.getController().getSessione().getCodiceUtente(), luogo, data, scadenza);
            teoria.salvaBrevettoVisitaMedica();

            mPager.setCurrentItem(2, true);
        }
    }

    public void tornaABrevettoMain(View view){
        mPager.setCurrentItem(2, true);
    }

    public void aggiungiDrone(View view){
        mPager.setCurrentItem(3, true);
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
            mPager.setCurrentItem(4, true);

        }
    }

    public void aggiungiBatteria(View view){
        mPager.setCurrentItem(5, true);
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
            mPager.setCurrentItem(4, true);
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
                case 0: return new Fragment_Main();

                case 1: return new Fragment_CreaProfilo();

                case 2: return new Fragment_Brevetto_Main();

                case 3: return new Fragment_Brevetto_Teoria();

                case 4: return new Fragment_Brevetto_Pratica();

                case 5: return new Fragment_Brevetto_Visita_Medica();

                case 6: return new Fragment_CreaDrone();

                case 7: return Fragment_Profilo.nuovaIstanza(Principale.getController().getProfilo().getNome(),
                                                            Principale.getController().getProfilo().getCognome(),
                                                            Principale.getController().getBrevetto().getCodice());

                case 8: return new Fragment_CreaBatteria();

                default: return new Fragment_CreaBrevetto();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }


    }

}
