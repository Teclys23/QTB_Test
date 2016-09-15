package com.hirvorn.qtb_test;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Brevetto.BrevettoTeoria;
import com.hirvorn.qtb_test.Controller.Controller;
import com.hirvorn.qtb_test.CreaBatteria.Fragment_CreaBatteria;
import com.hirvorn.qtb_test.Brevetto.BrevettoPratica;
import com.hirvorn.qtb_test.Brevetto.BrevettoVisitaMedica;
import com.hirvorn.qtb_test.CreaBrevetto.DatePickerFragment;
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
import com.hirvorn.qtb_test.Brevetto.Brevetto;
import com.hirvorn.qtb_test.Utente.Fragment_Profilo;
import com.hirvorn.qtb_test.Utente.Profilo;
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

    // Brevetto Main
    private static EditText brevetto_main_enac;

    // Brevetto Teoria
    private static EditText brevetto_teoria_luogo;
    private static TextView brevetto_teoria_data;
    private static EditText brevetto_teoria_numero;

    // Brevetto Pratica
    private static EditText brevetto_pratica_luogo;
    private static TextView brevetto_pratica_data;
    private static EditText brevetto_pratica_numero;

    //Brevetto Visita Medica
    private static EditText brevetto_visita_medica_luogo;
    private static TextView brevetto_visita_medica_data;
    private static TextView brevetto_visita_medica_scadenza;

    //Drone
    private static Spinner crea_drone_categoria;
    private static Spinner crea_drone_marca_modello;
    private static EditText crea_drone_apr;
    private static EditText crea_drone_spr_text;
    private static ListView crea_drone_spr;
    private static Spinner crea_drone_numero_motori;

    //Batteria
    private static EditText crea_batteria_drone;
    private static EditText crea_batteria_codice;

    /**
     * Controlli
     */
    private boolean sessioneValida;
    private boolean brevettoValido;

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
        setSessioneValida(false);
        setBrevettoValido(false);

        Principale principale = new Principale(this);

        tw_sessioneCorrente = (TextView)findViewById(R.id.tw_sessioneCorrente);

        //Controllo sessione corrente
        //if(!Principale.getController().isValidSession()){
        //---------------------------------------------------------- SOLO PER DEBUG
        if(!Principale.getController().isValidSession()){
            Toast.makeText(this, "Non hai ancora un profilo!",Toast.LENGTH_SHORT).show();
            mPager.setCurrentItem(1);
            iniziaCreaProfilo();
        }
        else{
            caricaProfilo();
            setSessioneValida(true);
            continuaDopoProfilo();
        }

    }

    /**
     * Metodi
     *
     */

    // Getter e Setter

    public static void setSessioneCorrente(String text){
        tw_sessioneCorrente.setText(text);
    }

    private void setSessioneValida(boolean valida){ this.sessioneValida = valida; }

    private boolean getSessioneValida(){ return this.sessioneValida; }

    private void setBrevettoValido(boolean valido){ this.brevettoValido = valido; }

    private boolean getBrevettoValido(){ return this.brevettoValido; }

    //----------------------------------------------------------------------------------------------

    // Crea Profilo

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
            setSessioneValida(true);
            continuaDopoProfilo();
            //mPager.setCurrentItem(2, true);
        }
    }

    private void caricaProfilo(){
        ReadPropertyValues reader = new ReadPropertyValues();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("codice");
        keys.add("nome");
        keys.add("cognome");
        keys.add("mail");
        keys.add("telefono");
        keys.add("codiceFiscale");
        keys.add("residenza");
        keys.add("via");
        keys.add("codiceCivico");
        keys.add("cap");
        keys.add("drones");

        ArrayList<String> values = new ArrayList<>();
        try {
            values = reader.getPropertyValues(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), keys, Principale.getController().getContext(), true);

            Profilo profilo = new Profilo(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7), values.get(8), values.get(9), values.get(10));
            Principale.getController().setProfilo(profilo);
            Log.v(StartPage.LOG_TAG, "Profilo settato nel controller.");
            System.out.println(profilo.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void continuaDopoProfilo(){

        ReadPropertyValues readPropertyValues = new ReadPropertyValues();

        if(sessioneValida && !Principale.getController().isValidBrevetto()){
            tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "nome"));
            Toast.makeText(this, "Non hai ancora settato il brevetto!", Toast.LENGTH_SHORT).show();

            mPager.setCurrentItem(2, true);
        }
        else{

            tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "nome"));
            caricaBrevetto();
            setBrevettoValido(true);
            mPager.setCurrentItem(6, true);
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

        brevetto_teoria_data = (TextView)findViewById(R.id.textView_dataView);
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

        brevetto_pratica_data = (TextView) findViewById(R.id.textView_dataView_pratica);
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

        brevetto_visita_medica_data = (TextView)findViewById(R.id.textView_dataView_visita_medica_data);
        String data = brevetto_visita_medica_data.getText().toString();

        if(TextUtils.isEmpty(data)){
            brevetto_visita_medica_data.setError("Data mancante");
        }

        brevetto_visita_medica_scadenza = (TextView) findViewById(R.id.textView_dataView_visita_medica_scadenza);
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

    public void confermaBrevettoCompleto(View view){

        ReadPropertyValues reader = new ReadPropertyValues();

        // Salvo la pratica, la teoria e la visita medica
        String brevetto_pratica = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_pratica");
        String visita_medica = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente()  + Brevetto.BREVETTO_EXT, "brevetto_visita_medica");
        String brevetto_teoria = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_teoria");

        brevetto_main_enac = (EditText)findViewById(R.id.editText_codice_enac);
        String enac = brevetto_main_enac.getText().toString();

        if(TextUtils.isEmpty(enac)){
            brevetto_main_enac.setError("Codice mancante");
        }

        if(brevetto_teoria.equals("#")){
            Toast.makeText(this, "Brevetto Teoria mancante!", Toast.LENGTH_SHORT).show();
        }

        if(brevetto_pratica.equals("#")){
            Toast.makeText(this, "Brevetto Pratica mancante!", Toast.LENGTH_SHORT).show();
        }

        if(visita_medica.equals("#")){
            Toast.makeText(this, "Visita Medica mancante!", Toast.LENGTH_SHORT).show();
        }

        if(!brevetto_teoria.equals("#") && !brevetto_pratica.equals("#") && !visita_medica.equals("#")
                && !TextUtils.isEmpty(enac)){
            Brevetto brevetto = new Brevetto();
            brevetto.salvaEnac(enac);
            setBrevettoValido(true);
            Principale.getController().setBrevetto(brevetto);
            continuaDopoBrevetto();
        }
    }

    private void caricaBrevetto(){
        ReadPropertyValues reader = new ReadPropertyValues();
        ArrayList<String> keys = new ArrayList<>();
        keys.add("brevetto_teoria");
        keys.add("brevetto_pratica");
        keys.add("brevetto_visita_medica");
        keys.add("enac");

        ArrayList<String> values = new ArrayList<>();

        try {
            values = reader.getPropertyValues(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, keys, Principale.getController().getContext(), true);

            Brevetto brevetto = new Brevetto(values.get(0), values.get(1), values.get(2), values.get(3));
            Principale.getController().setBrevetto(brevetto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void continuaDopoBrevetto(){
        if(getSessioneValida() && getBrevettoValido()){
            Log.v(StartPage.LOG_TAG, "ECCOCI " + Principale.getController().getSessione().getCodiceUtente());
            if (Principale.getController().getSessione().getCodiceUtente().equals("null")) {
                tw_sessioneCorrente.setText("Sessione corrente: null");
            } else {
                mPager.setCurrentItem(6);
                //tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "name"));
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    public void aggiungiDrone(View view){
        mPager.setCurrentItem(7, true);
    }

    /**
     * Crea un drone
     * @param view
     */
    public void confermaCreaDrone(View view){

        crea_drone_categoria = (Spinner) findViewById(R.id.spinner_categorie);
        String categoria = crea_drone_categoria.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(categoria)){
            crea_drone_categoria;
        }*/

        crea_drone_marca_modello = (Spinner)findViewById(R.id.spinner_marca_modello);
        String marca = crea_drone_marca_modello.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(marca)){
            crea_drone_marca.setError("Marca mancante");
        }*/

        crea_drone_apr = (EditText)findViewById(R.id.editText_crea_drone_apr);
        String apr = crea_drone_apr.getText().toString();

        if(TextUtils.isEmpty(apr)){
            crea_drone_apr.setError("APR mancante");
        }

        if(apr.length() < 6 || apr.length() > 12){
            crea_drone_apr.setError("L'APR deve essere compreso tra i 6 e 12 caratteri");
            crea_drone_apr.setText("");
        }

        crea_drone_spr = (ListView) findViewById(R.id.listView_spr);
        StringBuilder lista_spr = new StringBuilder();
        int i = 0;
        for(i = 0; i < crea_drone_spr.getCount(); i++){
            lista_spr.append(crea_drone_spr.getItemAtPosition(i));
            lista_spr.append("#");
        }

        boolean sprValidi = false;
        if(i > 0){
            sprValidi = true;
        }
        else{
            Toast.makeText(Principale.getController().getContext(), "Ogni APR deve avere almeno un SPR", Toast.LENGTH_SHORT).show();
        }

        crea_drone_numero_motori = (Spinner)findViewById(R.id.spinner_numero_motori);
        String numero_motori = crea_drone_numero_motori.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(numero_motori)){
            crea_drone_numero_motori.setError("Numero motori mancante");
        }*/

        if(!TextUtils.isEmpty(categoria)
                && !TextUtils.isEmpty(marca)
                && !TextUtils.isEmpty(apr) && !(apr.length() < 6) && !(apr.length() > 12)
                && !TextUtils.isEmpty(lista_spr) && sprValidi
                && !TextUtils.isEmpty(numero_motori)){
            //salva drone
            Login login = new Login();
            login.creaNuovoDrone(categoria, marca, apr, lista_spr.toString(), numero_motori);

            Fragment_Profilo.aggiungiCodiceDrone(apr);
            mPager.setCurrentItem(7, true);

        }
    }

    public void aggiungiSpr(View view){
        crea_drone_spr_text = (EditText)findViewById(R.id.editText_crea_drone_spr);
        String spr = crea_drone_spr_text.getText().toString();

        if(!TextUtils.isEmpty(spr)){
            Fragment_CreaDrone.addItem(spr);
            crea_drone_spr_text.setText("");
        }else{
            crea_drone_spr_text.setError("SPR mancante");
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

    public void settaData(View view, int risorsa){
        Bundle bundle = new Bundle();
        bundle.putInt("textView", risorsa);
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(),"Date Picker");
    }

    public void settaDataTeoria(View view){
        settaData(view, R.id.textView_dataView);
    }

    public void settaDataPratica(View view){
        settaData(view, R.id.textView_dataView_pratica);
    }

    public void settaDataVisitaMedicaData(View view){
        settaData(view, R.id.textView_dataView_visita_medica_data);
    }

    public void settaDataVisitaMedicaScadenza(View view){
        settaData(view, R.id.textView_dataView_visita_medica_scadenza);
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

                case 6: return Fragment_Profilo.nuovaIstanza(Principale.getController().getProfilo().getNome(),
                        Principale.getController().getProfilo().getCognome(),
                        "000000");

                case 7: return new Fragment_CreaDrone();

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
