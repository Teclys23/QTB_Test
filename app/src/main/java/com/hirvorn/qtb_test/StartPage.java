package com.hirvorn.qtb_test;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hirvorn.qtb_test.Batteria.Batteria;
import com.hirvorn.qtb_test.Brevetto.BrevettoTeoria;
import com.hirvorn.qtb_test.CreaBatteria.Fragment_CreaBatteria;
import com.hirvorn.qtb_test.Brevetto.BrevettoPratica;
import com.hirvorn.qtb_test.Brevetto.BrevettoVisitaMedica;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_SeiOperatore;
import com.hirvorn.qtb_test.LibrettoDiVolo.LibrettoDiVolo;
import com.hirvorn.qtb_test.Operatore.Fragment_CreaOperatore;
import com.hirvorn.qtb_test.Operatore.Fragment_CreaOperatore_Critico;
import com.hirvorn.qtb_test.Operatore.Fragment_CreaOperatore_Normale;
import com.hirvorn.qtb_test.Operatore.Fragment_CreaOperatore_Terzi;
import com.hirvorn.qtb_test.Operatore.Operatore;
import com.hirvorn.qtb_test.Operatore.OperatoreCritico;
import com.hirvorn.qtb_test.Operatore.OperatoreTerzi;
import com.hirvorn.qtb_test.PreQTB.Fragment_DomandaOperatore;
import com.hirvorn.qtb_test.Utils.DatePickerFragment;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Main;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Pratica;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Teoria;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_Brevetto_Visita_Medica;
import com.hirvorn.qtb_test.CreaBrevetto.Fragment_CreaBrevetto;
import com.hirvorn.qtb_test.CreaDrone.Fragment_CreaDrone;
import com.hirvorn.qtb_test.CreaProfilo.Fragment_CreaProfilo;
import com.hirvorn.qtb_test.GPS.GPSTracker;
import com.hirvorn.qtb_test.LibrettoDiVolo.Fragment_LibrettoVolo_Due;
import com.hirvorn.qtb_test.LibrettoDiVolo.Fragment_LibrettoVolo_Quattro;
import com.hirvorn.qtb_test.LibrettoDiVolo.Fragment_LibrettoVolo_Tre;
import com.hirvorn.qtb_test.LibrettoDiVolo.Fragment_LibrettoVolo_Uno;
import com.hirvorn.qtb_test.Login.Login;
import com.hirvorn.qtb_test.Main.Fragment_Main;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;
import com.hirvorn.qtb_test.Brevetto.Brevetto;
import com.hirvorn.qtb_test.Utente.Fragment_Profilo;
import com.hirvorn.qtb_test.Utente.Profilo;
import com.hirvorn.qtb_test.Utils.CustomViewPager;
import com.hirvorn.qtb_test.Utils.TimePickerFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
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
    private static EditText crea_batteria_codice;
    private static LinearLayout layout_batteria;
    private ArrayList<EditText> celle;
    private static EditText crea_batteria_numero_celle;
    private static EditText crea_batteria_amperaggio;
    private static EditText crea_batteria_moltiplicatore_scarica;
    private static EditText crea_batteria_moltiplicatore_carica;
    private static EditText crea_batteria_valore_batteria_carica;
    private static EditText crea_batteria_valore_batteria_scarica;
    private static EditText crea_batteria_valore_batteria_storage;
    private static EditText crea_batteria_valore_tensione_batteria_carica;
    private static EditText crea_batteria_valore_percentuale_efficienza;

    //Logbook uno
    private static EditText luogo;

    //Logbook quattro
    private static EditText durata_missione_uno;
    private static EditText durata_missione_due;
    private static TextView landing_uno;
    private static TextView landing_due;
    private static TextView take_off_uno;
    private static TextView take_off_due;

    //CreaOperatore
    private static Button creaOperatoreCritico;

    //CreaOperatoreNormale
    private static EditText codiceEnac;
    private static TextView scadenzaNormale;

    //CreaOperatoreCritico
    private static EditText codiceEnacCritico;
    private static TextView scadenzaCritico;

    //CreaOperatoreTerzi
    private static EditText nomeOperatoreTerzi;
    private static EditText codiceTerzi;
    private static EditText aprUtilizzatoTerzi;
    private static CheckBox criticoTerzi;

    /**
     * Controlli
     */
    private boolean sessioneValida;
    private boolean brevettoValido;

    private static View view;
    private GPSTracker gps;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 13;
    private static final int NUM_PAGES_APP = 5;

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

    public void init() {
        setSessioneValida(false);
        setBrevettoValido(false);

        Principale principale = new Principale(this);

        tw_sessioneCorrente = (TextView) findViewById(R.id.tw_sessioneCorrente);

        //Controllo sessione corrente
        //if(!Principale.getController().isValidSession()){
        //---------------------------------------------------------- SOLO PER DEBUG
        if (!Principale.getController().isValidSession()) {
            Toast.makeText(this, "Non hai ancora un profilo!", Toast.LENGTH_SHORT).show();
            mPager.setCurrentItem(0);
            iniziaCreaProfilo();
        } else {
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
    public static void setSessioneCorrente(String text) {
        tw_sessioneCorrente.setText(text);
    }

    private void setSessioneValida(boolean valida) {
        this.sessioneValida = valida;
    }

    private boolean getSessioneValida() {
        return this.sessioneValida;
    }

    private void setBrevettoValido(boolean valido) {
        this.brevettoValido = valido;
    }

    private boolean getBrevettoValido() {
        return this.brevettoValido;
    }

    //----------------------------------------------------------------------------------------------

    // Crea Profilo

    private void iniziaCreaProfilo() {
        mPager.setCurrentItem(0);

    }

    /**
     * Creazione di un nuovo profilo
     * @param view
     */
    public void confermaCreaProfilo(View view) {
        Login login = new Login();

        // Nome
        crea_profilo_nome = (EditText) findViewById(R.id.editText_nome);
        String nome = crea_profilo_nome.getText().toString();

        if (TextUtils.isEmpty(nome)) {
            crea_profilo_nome.setError("Nome mancante");
        }

        if (nome.length() < 3) {
            crea_profilo_nome.setError("Il nome deve avere almeno tre caratteri");
        }

        String expression = "^[a-zA-Z]*$";

        CharSequence inputStr = nome;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        boolean nome_soloLettere = false;
        if (matcher.matches()) {
            nome_soloLettere = true;
        } else {
            crea_profilo_nome.setError("Il nome deve contenere solo lettere");
            crea_profilo_nome.setText("");
        }

        // Cognome
        crea_profilo_cognome = (EditText) findViewById(R.id.editText_cognome);
        String cognome = crea_profilo_cognome.getText().toString();

        if (TextUtils.isEmpty(cognome)) {
            crea_profilo_cognome.setError("Cognome mancante");
        }

        if (cognome.length() < 3) {
            crea_profilo_cognome.setError("Il cognome deve avere almeno tre caratteri");
        }

        inputStr = cognome;
        pattern = Pattern.compile(expression);
        matcher = pattern.matcher(inputStr);
        boolean cognome_soloLettere = false;
        if (matcher.matches()) {
            cognome_soloLettere = true;
        } else {
            crea_profilo_cognome.setError("Il cognome deve contenere solo lettere");
            crea_profilo_cognome.setText("");
        }

        // Mail
        crea_profilo_mail = (EditText) findViewById(R.id.editText_mail);
        String mail = crea_profilo_mail.getText().toString();

        if (TextUtils.isEmpty(mail)) {
            crea_profilo_mail.setError("Mail mancante");
        }

        if (!Principale.getController().isValidEmail(mail)) {
            crea_profilo_mail.setError("Inserire e-mail valida");
            crea_profilo_mail.setText("");
        }

        // Telefono
        crea_profilo_telefono = (EditText) findViewById(R.id.editText_telefono);
        String telefono = crea_profilo_telefono.getText().toString();

        if (TextUtils.isEmpty(telefono)) {
            crea_profilo_telefono.setError("Telefono mancante");
        }

        if (!Principale.getController().isValidPhone(telefono)) {
            crea_profilo_telefono.setError("Numero di telefono non valido");
            crea_profilo_telefono.setText("");
        }

        // Codice Fiscale
        crea_profilo_codice_fiscale = (EditText) findViewById(R.id.editText_codice_fiscale);
        String codiceFiscale = crea_profilo_codice_fiscale.getText().toString();

        if (TextUtils.isEmpty(codiceFiscale)) {
            crea_profilo_codice_fiscale.setError("Codice fiscale mancante");
        }

        if (codiceFiscale.length() != 16) {
            crea_profilo_codice_fiscale.setError("Il codice fiscale deve essere di 11 caratteri");
            crea_profilo_codice_fiscale.setText("");
        }

        // Residenza
        crea_profilo_residenza = (EditText) findViewById(R.id.editText_residenza);
        String residenza = crea_profilo_residenza.getText().toString();

        if (TextUtils.isEmpty(residenza)) {
            crea_profilo_residenza.setError("Residenza mancante");
        }

        // Via
        crea_profilo_via = (EditText) findViewById(R.id.editText_via);
        String via = crea_profilo_via.getText().toString();

        if (TextUtils.isEmpty(via)) {
            crea_profilo_via.setError("Via mancante");
        }

        // Numero civico
        crea_profilo_numero_civico = (EditText) findViewById(R.id.editText_numero_civico);
        String numeroCivico = crea_profilo_numero_civico.getText().toString();

        if (TextUtils.isEmpty(numeroCivico)) {
            crea_profilo_numero_civico.setError("Nunmero civico mancante");
        }

        // CAP
        crea_profilo_cap = (EditText) findViewById(R.id.editText_cap);
        String cap = crea_profilo_cap.getText().toString();

        if (TextUtils.isEmpty(cap)) {
            crea_profilo_cap.setError("C.A.P. mancante");
        }

        if (cap.length() != 5) {
            crea_profilo_cap.setError("Inserire C.A.P. valido");
            crea_profilo_cap.setText("");
        }

        // Controllo completo
        if (!TextUtils.isEmpty(nome) && (nome.length() >= 3) && nome_soloLettere
                && !TextUtils.isEmpty(cognome) && (cognome.length() >= 3) && cognome_soloLettere
                && !TextUtils.isEmpty(mail) && Principale.getController().isValidEmail(mail)
                && !TextUtils.isEmpty(telefono) && Principale.getController().isValidPhone(telefono)
                && !TextUtils.isEmpty(codiceFiscale) && (codiceFiscale.length() == 16)
                && !TextUtils.isEmpty(residenza)
                && !TextUtils.isEmpty(via)
                && !TextUtils.isEmpty(numeroCivico)
                && !TextUtils.isEmpty(cap) && (cap.length() == 5)
                ) {
            login.creaNuovoProfilo(nome, cognome, mail, telefono, codiceFiscale, residenza, via, numeroCivico, cap);
            setSessioneCorrente(Principale.getController().getProfilo().getNome());
            setSessioneValida(true);
            Principale.getController().initTotOreVolo();
            continuaDopoProfilo();
            //mPager.setCurrentItem(2, true);
        }
    }

    private void caricaProfilo() {
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
        keys.add("totOreVolo");

        ArrayList<String> values = new ArrayList<>();
        try {
            values = reader.getPropertyValues(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), keys, Principale.getController().getContext(), true);

            Profilo profilo = new Profilo(values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7), values.get(8), values.get(9), values.get(10), values.get(11));
            Principale.getController().setProfilo(profilo);
            Log.v(StartPage.LOG_TAG, "Profilo settato nel controller.");
            System.out.println(profilo.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void continuaDopoProfilo() {

        ReadPropertyValues readPropertyValues = new ReadPropertyValues();

        if (sessioneValida && !Principale.getController().isValidBrevetto()) {
            tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "nome"));
            Toast.makeText(this, "Non hai ancora settato il brevetto!", Toast.LENGTH_SHORT).show();

            mPager.setCurrentItem(1, true);
        } else {

            tw_sessioneCorrente.setText(readPropertyValues.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Principale.getConfig().getUserExtension(), "nome"));
            caricaBrevetto();
            setBrevettoValido(true);
            //mPager.setCurrentItem(6, true);
            continuaDopoBrevetto();
        }

    }

    //----------------------------------------------------------------------------------------------
    // domanda operatore

    public void domandaOperatore_SeiOperatore(View view){
        Fragment_DomandaOperatore.seiOperatore();
    }

    public void creaOperatoreNormale(View view){
        Principale.getController().setCritico(false);
        creaOperatoreCritico = (Button)findViewById(R.id.button_crea_operatore_critico);
        mPager.setCurrentItem(3, true);
    }

    public void creaOperatoreCritico(View view){
        Principale.getController().setCritico(true);
        mPager.setCurrentItem(4, true);
    }

    public void creaOperatoreTerzi(View view){
        Principale.getController().setCritico(false);
        mPager.setCurrentItem(5, true);
    }

    public void domandaOperatore_Conferma (View view){
        mPager.setCurrentItem(8, true);
    }

    //----------------------------------------------------------------------------------------------
    // Crea Operatore Normale

    public void scadenzaOperatoreNormale(View view){
        settaData(view, R.id.textView_crea_operatore_normale_data_scadenza);
    }

    public void aggiungiDroneOperatoreNormale(View view){
        mPager.setCurrentItem(6, true);
    }

    public void confermaOperatoreNormale(View view){

        codiceEnac = (EditText)findViewById(R.id.editText_crea_operatore_normale_codice_enac);
        scadenzaNormale = (TextView)findViewById(R.id.textView_crea_operatore_normale_data_scadenza);

        String codiceEnacNormale = codiceEnac.getText().toString();

        if(TextUtils.isEmpty(codiceEnacNormale)){
            codiceEnac.setError("Codice mancante");
        }

        String scadenza = scadenzaNormale.getText().toString();

        if(scadenza.equals("Premi qui per inserire")){
            scadenzaNormale.setError("Data mancante");
            scadenzaNormale.setText(R.string.crea_operatore_normale_data_scadenza);
        }

        if(!TextUtils.isEmpty(codiceEnacNormale)
                && !scadenza.equals("Premi qui per inserire")){
            Operatore operatore = new Operatore(Principale.getController().getProfilo().getCodice(), codiceEnacNormale, scadenza);
            operatore.salvaDati();
            creaOperatoreCritico.setEnabled(true);
            mPager.setCurrentItem(2, true);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Crea Operatore Critico

    public void scadenzaOperatoreCritico(View view){
        settaData(view, R.id.textView_crea_operatore_critico_data_scadenza);
    }

    public void aggiungiDroneOperatoreCritico(View view){
        mPager.setCurrentItem(6, true);
    }

    public void confermaOperatoreCritico(View view){

        codiceEnacCritico = (EditText)findViewById(R.id.editText_crea_operatore_critico_codice_enac);
        scadenzaCritico = (TextView)findViewById(R.id.textView_crea_operatore_critico_data_scadenza);

        String codiceEnac = codiceEnacCritico.getText().toString();

        if(TextUtils.isEmpty(codiceEnac)){
            codiceEnacCritico.setError("Codice mancante");
        }

        String scadenza = scadenzaCritico.getText().toString();

        if(scadenza.equals("Premi qui per inserire")){
            scadenzaCritico.setError("Data mancante");
            scadenzaCritico.setText(R.string.crea_operatore_critico_data_scadenza);
        }

        if(!TextUtils.isEmpty(codiceEnac)
                && !scadenza.equals("Premi qui per inserire")){
            OperatoreCritico operatore = new OperatoreCritico(Principale.getController().getProfilo().getCodice(), codiceEnac, scadenza);
            operatore.salvaDati();
            creaOperatoreCritico.setEnabled(true);
            mPager.setCurrentItem(2, true);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Crea Operatore Terzi

    public void confermaOperatoreTerzi(View view){
        nomeOperatoreTerzi = (EditText)findViewById(R.id.editText_crea_operatore_terzi_nome_operatore);
        String nomeTerzi = nomeOperatoreTerzi.getText().toString();

        if(TextUtils.isEmpty(nomeTerzi)){
            nomeOperatoreTerzi.setError("Nome operatore mancante");
        }

        codiceTerzi = (EditText)findViewById(R.id.editText_crea_operatore_terzi_codice);
        String codiceOpTerzi = codiceTerzi.getText().toString();

        if(TextUtils.isEmpty(codiceOpTerzi)){
            codiceTerzi.setError("Codice mancante");
        }

        aprUtilizzatoTerzi = (EditText)findViewById(R.id.editText_crea_operatore_terzi_apr);
        String aprTerzi = aprUtilizzatoTerzi.getText().toString();

        if(TextUtils.isEmpty(aprTerzi)){
            aprUtilizzatoTerzi.setError("APR utilizzato mancante");
        }

        criticoTerzi = (CheckBox)findViewById(R.id.checkBox_crea_operatore_terzi_critico);

        if(!TextUtils.isEmpty(nomeTerzi)
                && !TextUtils.isEmpty(codiceOpTerzi)
                && !TextUtils.isEmpty(aprTerzi)){
            OperatoreTerzi operatoreTerzi = new OperatoreTerzi(Principale.getController().getProfilo().getCodice(), nomeTerzi, codiceOpTerzi, aprTerzi, criticoTerzi.isChecked());
            operatoreTerzi.salvaDati();
            mPager.setCurrentItem(2, true);
        }
    }

    //----------------------------------------------------------------------------------------------
    // Sei operatore

    public void seiOperatore(View view){
        if(((RadioGroup)findViewById(R.id.radioGroup_sei_operatore)).getCheckedRadioButtonId() == R.id.radioButton_sei_operatore_si)
            Fragment_SeiOperatore.setVisibilitaOperatorePer(false);
        else
            Fragment_SeiOperatore.setVisibilitaOperatorePer(true);

    }

    public void seiOperatoreConferma(View view){
        int seiOperatore = ((RadioGroup)findViewById(R.id.radioGroup_sei_operatore)).getCheckedRadioButtonId();

        if(((RadioButton)findViewById(seiOperatore)).getText().toString().equals(getResources().getString(R.string.sei_operatore_no))){
            int seiOperatorePer = ((RadioGroup)findViewById(R.id.radioGroup_sei_operatore_per)).getCheckedRadioButtonId();

            if(((RadioButton)findViewById(seiOperatorePer)).getText().equals(getResources().getString(R.string.sei_operatore_no))){
                //no operatore, no lavoro per
                mPager.setCurrentItem(8, true);
            }else{
                mPager.setCurrentItem(5, true);
            }

        }else{
            //è operatore
            mPager.setCurrentItem(2, true);
        }
    }


    //----------------------------------------------------------------------------------------------

    /**
     * Brevetto teoria
     * @param view
     */
    public void inserisciBrevettoTeoria(View view) {
        mPager.setCurrentItem(9, true);
    }

    public void confermaBrevettoTeoria(View view) {
        brevetto_teoria_luogo = (EditText) findViewById(R.id.editText_brevetto_teoria_luogo);
        String luogo = brevetto_teoria_luogo.getText().toString();

        if (TextUtils.isEmpty(luogo)) {
            brevetto_teoria_luogo.setError("Luogo mancante");
        }

        brevetto_teoria_data = (TextView) findViewById(R.id.textView_dataView);
        String data = brevetto_teoria_data.getText().toString();

        if (TextUtils.isEmpty(data)) {
            brevetto_teoria_data.setError("Data mancante");
        }

        brevetto_teoria_numero = (EditText) findViewById(R.id.editText_brevetto_teoria_numero);
        String numero = brevetto_teoria_numero.getText().toString();

        if (TextUtils.isEmpty(numero)) {
            brevetto_teoria_numero.setError("Numero mancante");
        }

        if (!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(numero)) {

            BrevettoTeoria teoria = new BrevettoTeoria(Principale.getController().getSessione().getCodiceUtente(), luogo, data, numero);
            teoria.salvaBrevettoTeoria();

            mPager.setCurrentItem(8, true);
        }
    }

    public void inserisciBrevettoPratica(View view) {
        mPager.setCurrentItem(10, true);
    }

    public void confermaBrevettoPratica(View view) {
        brevetto_pratica_luogo = (EditText) findViewById(R.id.editText_brevetto_pratica_luogo);
        String luogo = brevetto_pratica_luogo.getText().toString();

        if (TextUtils.isEmpty(luogo)) {
            brevetto_pratica_luogo.setError("Luogo mancante");
        }

        brevetto_pratica_data = (TextView) findViewById(R.id.textView_dataView_pratica);
        String data = brevetto_pratica_data.getText().toString();

        if (TextUtils.isEmpty(data)) {
            brevetto_pratica_data.setError("Data mancante");
        }

        brevetto_pratica_numero = (EditText) findViewById(R.id.editText_brevetto_pratica_numero);
        String numero = brevetto_pratica_numero.getText().toString();

        if (TextUtils.isEmpty(numero)) {
            brevetto_pratica_numero.setError("Numero mancante");
        }

        if (!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(numero)) {

            BrevettoPratica teoria = new BrevettoPratica(Principale.getController().getSessione().getCodiceUtente(), luogo, data, numero);
            teoria.salvaBrevettoPratica();

            mPager.setCurrentItem(8, true);
        }
    }

    public void inserisciBrevettoVisitaMedica(View view) {
        mPager.setCurrentItem(11, true);
    }

    public void confermaBrevettoVisitaMedica(View view) {
        brevetto_visita_medica_luogo = (EditText) findViewById(R.id.editText_brevetto_visita_medica_luogo);
        String luogo = brevetto_visita_medica_luogo.getText().toString();

        if (TextUtils.isEmpty(luogo)) {
            brevetto_visita_medica_luogo.setError("Luogo mancante");
        }

        brevetto_visita_medica_data = (TextView) findViewById(R.id.textView_dataView_visita_medica_data);
        String data = brevetto_visita_medica_data.getText().toString();

        if (TextUtils.isEmpty(data)) {
            brevetto_visita_medica_data.setError("Data mancante");
        }

        brevetto_visita_medica_scadenza = (TextView) findViewById(R.id.textView_dataView_visita_medica_scadenza);
        String scadenza = brevetto_visita_medica_scadenza.getText().toString();

        if (TextUtils.isEmpty(scadenza)) {
            brevetto_visita_medica_scadenza.setError("Numero mancante");
        }

        if (!TextUtils.isEmpty(luogo)
                && !TextUtils.isEmpty(data)
                && !TextUtils.isEmpty(scadenza)) {

            BrevettoVisitaMedica teoria = new BrevettoVisitaMedica(Principale.getController().getSessione().getCodiceUtente(), luogo, data, scadenza);
            teoria.salvaBrevettoVisitaMedica();

            mPager.setCurrentItem(8, true);
        }
    }

    public void tornaABrevettoMain(View view) {
        mPager.setCurrentItem(8, true);
    }

    public void confermaBrevettoCompleto(View view) {

        ReadPropertyValues reader = new ReadPropertyValues();

        // Salvo la pratica, la teoria e la visita medica
        String brevetto_pratica = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_pratica");
        String visita_medica = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_visita_medica");
        String brevetto_teoria = reader.getPropValue(Principale.getController().getSessione().getCodiceUtente() + Brevetto.BREVETTO_EXT, "brevetto_teoria");

        brevetto_main_enac = (EditText) findViewById(R.id.editText_codice_enac);
        String enac = brevetto_main_enac.getText().toString();

        if (TextUtils.isEmpty(enac)) {
            brevetto_main_enac.setError("Codice mancante");
        }

        if (brevetto_teoria.equals("#")) {
            Toast.makeText(this, "Brevetto Teoria mancante!", Toast.LENGTH_SHORT).show();
        }

        if (brevetto_pratica.equals("#")) {
            Toast.makeText(this, "Brevetto Pratica mancante!", Toast.LENGTH_SHORT).show();
        }

        if (visita_medica.equals("#")) {
            Toast.makeText(this, "Visita Medica mancante!", Toast.LENGTH_SHORT).show();
        }

        if (!brevetto_teoria.equals("#") && !brevetto_pratica.equals("#") && !visita_medica.equals("#")
                && !TextUtils.isEmpty(enac)) {
            Brevetto brevetto = new Brevetto();
            brevetto.salvaEnac(enac);
            setBrevettoValido(true);
            Principale.getController().setBrevetto(brevetto);
            continuaDopoBrevetto();
        }
    }

    private void caricaBrevetto() {
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

    private void continuaDopoBrevetto() {
        if (getSessioneValida() && getBrevettoValido()) {
            Log.v(StartPage.LOG_TAG, "ECCOCI " + Principale.getController().getSessione().getCodiceUtente());

            if (Principale.getController().getSessione().getCodiceUtente().equals("null")) {
                tw_sessioneCorrente.setText("Sessione corrente: null");
            } else {

                String droni = Principale.getController().getProfilo().haDroniPosseduti();
                ArrayList<String> elenco_droni = new ArrayList<>(Arrays.asList(droni.split("#")));


                if (elenco_droni != null && !elenco_droni.isEmpty()) {
                    mPagerAdapter = new SreenSlidePagerAdapterMain(getSupportFragmentManager());
                    mPager.setAdapter(mPagerAdapter);
                    mPager.setCurrentItem(0, true);
                } else {
                    mPager.setCurrentItem(12);
                    Fragment_Profilo.aggiornaBrevetto();
                }
            }
        }
    }

    //----------------------------------------------------------------------------------------------

    public void aggiungiDrone(View view) {
        mPager.setCurrentItem(6, true);
    }

    /**
     * Crea un drone
     * @param view
     */
    public void confermaCreaDrone(View view) {

        crea_drone_categoria = (Spinner) findViewById(R.id.spinner_categorie);
        String categoria = crea_drone_categoria.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(categoria)){
            crea_drone_categoria;
        }*/

        crea_drone_marca_modello = (Spinner) findViewById(R.id.spinner_marca_modello);
        String marca = crea_drone_marca_modello.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(marca)){
            crea_drone_marca.setError("Marca mancante");
        }*/

        crea_drone_apr = (EditText) findViewById(R.id.editText_crea_drone_apr);
        String apr = crea_drone_apr.getText().toString();

        if (TextUtils.isEmpty(apr)) {
            crea_drone_apr.setError("APR mancante");
        }

        if (apr.length() < 6 || apr.length() > 12) {
            crea_drone_apr.setError("L'APR deve essere compreso tra i 6 e 12 caratteri");
            crea_drone_apr.setText("");
        }

        crea_drone_spr = (ListView) findViewById(R.id.listView_spr);
        StringBuilder lista_spr = new StringBuilder();
        int i = 0;
        for (i = 0; i < crea_drone_spr.getCount(); i++) {
            lista_spr.append(crea_drone_spr.getItemAtPosition(i));
            lista_spr.append("#");
        }

        boolean sprValidi = false;
        if (i > 0) {
            sprValidi = true;
        } else {
            Toast.makeText(Principale.getController().getContext(), "Ogni APR deve avere almeno un SPR", Toast.LENGTH_SHORT).show();
        }

        crea_drone_numero_motori = (Spinner) findViewById(R.id.spinner_numero_motori);
        String numero_motori = crea_drone_numero_motori.getSelectedItem().toString();
/*
        if(TextUtils.isEmpty(numero_motori)){
            crea_drone_numero_motori.setError("Numero motori mancante");
        }*/

        if (!TextUtils.isEmpty(categoria)
                && !TextUtils.isEmpty(marca)
                && !TextUtils.isEmpty(apr) && !(apr.length() < 6) && !(apr.length() > 12)
                && !TextUtils.isEmpty(lista_spr) && sprValidi
                && !TextUtils.isEmpty(numero_motori)) {
            //salva drone
            Login login = new Login();
            login.creaNuovoDrone(categoria, marca, apr, lista_spr.toString(), numero_motori);

            //Fragment_Profilo.aggiungiCodiceDrone(apr);
            mPager.setCurrentItem(2, true);

        }
    }

    public void aggiungiSpr(View view) {
        crea_drone_spr_text = (EditText) findViewById(R.id.editText_crea_drone_spr);
        String spr = crea_drone_spr_text.getText().toString();

        if (!TextUtils.isEmpty(spr)) {
            Fragment_CreaDrone.addItem(spr);
            crea_drone_spr_text.setText("");
        } else {
            crea_drone_spr_text.setError("SPR mancante");
        }
    }

    //----------------------------------------------------------------------------------------------

    // Batteria

    public void aggiungiBatteria(View view) {
        crea_drone_apr = (EditText) findViewById(R.id.editText_crea_drone_apr);
        String string = crea_drone_apr.getText().toString();

        if (!TextUtils.isEmpty(string)) {
            Principale.getController().setDroneAttuale(string);

            if (!Principale.getController().getDroneAttuale().equals("")) {
                Log.v(StartPage.LOG_TAG, "Drone qua: " + Principale.getController().getDroneAttuale());
                mPager.setCurrentItem(7, true);
            }

        } else {
            //Principale.getController().setDroneAttuale("");
        }


    }

    public void confermaCreaBatteria(View view) {


        crea_batteria_codice = (EditText) findViewById(R.id.crea_batteria_codice);
        String codiceBatteria = crea_batteria_codice.getText().toString();

        if (TextUtils.isEmpty(codiceBatteria)) {
            crea_batteria_codice.setError("Codice mancante");
        }

        crea_batteria_numero_celle = (EditText) findViewById(R.id.editText_numero_celle);
        String numero_celle = crea_batteria_numero_celle.getText().toString();

        if (TextUtils.isEmpty(numero_celle)) {
            crea_batteria_numero_celle.setError("Numero celle mancante");
        }

        crea_batteria_amperaggio = (EditText) findViewById(R.id.editText_amperaggio);
        String amperaggio = crea_batteria_amperaggio.getText().toString();

        if (TextUtils.isEmpty(amperaggio)) {
            crea_batteria_amperaggio.setError("Amperaggio mancante");
        }

        crea_batteria_moltiplicatore_scarica = (EditText) findViewById(R.id.editText_moltiplicatore_scarica);
        String moltiplicatore_scarica = crea_batteria_moltiplicatore_scarica.getText().toString();

        if (TextUtils.isEmpty(moltiplicatore_scarica)) {
            crea_batteria_moltiplicatore_scarica.setError("Moltiplicatore scarica mancante");
        }

        crea_batteria_moltiplicatore_carica = (EditText) findViewById(R.id.editText_moltiplicatore_carica);
        String moltiplicatore_carica = crea_batteria_moltiplicatore_carica.getText().toString();

        if (TextUtils.isEmpty(moltiplicatore_carica)) {
            crea_batteria_moltiplicatore_carica.setError("Moltiplicatore carica mancante");
        }

        crea_batteria_valore_batteria_carica = (EditText) findViewById(R.id.editText_valore_batteria_carica);
        String valore_batteria_carica = crea_batteria_valore_batteria_carica.getText().toString();

        if (TextUtils.isEmpty(valore_batteria_carica)) {
            crea_batteria_valore_batteria_carica.setError("Valore batteria carica mancante");
        }

        crea_batteria_valore_batteria_storage = (EditText)findViewById(R.id.editTextvalore_batteria_storage);
        String valore_batteria_storage = crea_batteria_valore_batteria_storage.getText().toString();

        if(TextUtils.isEmpty(valore_batteria_storage)){
            crea_batteria_valore_batteria_storage.setError("Valore batteria in storage mancante");
        }

        crea_batteria_valore_batteria_scarica = (EditText) findViewById(R.id.editText_valore_batteria_scarica);
        String valore_batteria_scarica = crea_batteria_valore_batteria_scarica.getText().toString();

        if (TextUtils.isEmpty(valore_batteria_scarica)) {
            crea_batteria_valore_batteria_scarica.setError("Valore batteria scarica mancante");
        }

        crea_batteria_valore_tensione_batteria_carica = (EditText) findViewById(R.id.editText_valore_tensione_carica);
        String valore_tensione_carica = crea_batteria_valore_tensione_batteria_carica.getText().toString();

        if (TextUtils.isEmpty(valore_tensione_carica)) {
            crea_batteria_valore_tensione_batteria_carica.setError("Valore tensione batteria carica mancante");
        }

        crea_batteria_valore_percentuale_efficienza = (EditText) findViewById(R.id.editText_valore_percentuale_efficienza);
        String valore_percentuale_efficienza = crea_batteria_valore_percentuale_efficienza.getText().toString();

        if (TextUtils.isEmpty(valore_percentuale_efficienza)) {
            crea_batteria_valore_percentuale_efficienza.setError("Valore percentuale efficienza mancante");
        }

        if(!numero_celle.equals("")) {
            if (Integer.parseInt(numero_celle) > 0) {
                StringBuilder lettura_celle = new StringBuilder();
                boolean celleSettate = true;
                for (int i = 0; i < Integer.parseInt(numero_celle); i++) {
                    if (TextUtils.isEmpty(celle.get(i).getText().toString())) {
                        celle.get(i).setError("Valore lettura mancante");
                        celleSettate = false;
                    }
                }

                if (celleSettate) {
                    for (int i = 0; i < Integer.parseInt(numero_celle); i++) {
                        lettura_celle.append(celle.get(0).getText().toString());
                        lettura_celle.append("#");
                    }
                }

            }
        }



        if (!TextUtils.isEmpty(codiceBatteria)
                && !TextUtils.isEmpty(numero_celle)
                && !TextUtils.isEmpty(amperaggio)
                && !TextUtils.isEmpty(moltiplicatore_carica)
                && !TextUtils.isEmpty(moltiplicatore_scarica)
                && !TextUtils.isEmpty(valore_batteria_carica)
                && !TextUtils.isEmpty(valore_batteria_scarica)
                && !TextUtils.isEmpty(valore_batteria_storage)
                && !TextUtils.isEmpty(valore_percentuale_efficienza)
                && !TextUtils.isEmpty(valore_tensione_carica)
                ) {
            Batteria batteria = new Batteria(codiceBatteria);
            Log.v(StartPage.LOG_TAG, "Drone attuale: " + Principale.getController().getDroneAttuale());
            batteria.salvaBatteria(Principale.getController().getDroneAttuale());

            mPager.setCurrentItem(6, true);
        }
    }

    public void mostraCelle(View view) {
        celle = new ArrayList<>();
        String num_celle = ((EditText) findViewById(R.id.editText_numero_celle)).getText().toString();
        layout_batteria = (LinearLayout) findViewById(R.id.linearLayout_batteria);

        Button button_celle = (Button) findViewById(R.id.button_inserisci_celle);
        button_celle.setEnabled(false);


        if (!TextUtils.isEmpty(num_celle)) {
            int numero_celle = Integer.parseInt(num_celle);

            for (int i = 0; i < numero_celle; i++) {
                LinearLayout linearLayout = new LinearLayout(Principale.getController().getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                TextView textView = new TextView(Principale.getController().getContext());
                textView.setText("Cella " + (i + 1));
                textView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                EditText editText = new EditText(Principale.getController().getContext());
                editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                linearLayout.addView(textView);
                linearLayout.addView(editText);

                celle.add(editText);

                layout_batteria.addView(linearLayout);

                final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView2);
                scrollView.post(new Runnable() {
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });

            }

        }
    }

    //----------------------------------------------------------------------------------------------

    // Profilo

    public void confermaProfilo(View view) {
        String elenco_droni = Principale.getController().getProfilo().haDroniPosseduti();

        ArrayList<String> elenco = new ArrayList<>(Arrays.asList(elenco_droni.split("#")));

        if (elenco == null || elenco.isEmpty()) {
            Toast.makeText(Principale.getController().getContext(), "Non hai ancora aggiunto un drone!", Toast.LENGTH_LONG);
        } else {
            //continua
        }
    }

    //----------------------------------------------------------------------------------------------

    public void settaData(View view, int risorsa) {
        Bundle bundle = new Bundle();
        bundle.putInt("textView", risorsa);
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "Date Picker");
    }

    public void settaDataTeoria(View view) {
        settaData(view, R.id.textView_dataView);
    }

    public void settaDataPratica(View view) {
        settaData(view, R.id.textView_dataView_pratica);
    }

    public void settaDataVisitaMedicaData(View view) {
        settaData(view, R.id.textView_dataView_visita_medica_data);
    }

    public void settaDataVisitaMedicaScadenza(View view) {
        settaData(view, R.id.textView_dataView_visita_medica_scadenza);
    }

    public void settaDataLogBook(View view) {
        settaData(view, R.id.textView_logbook_uno_data);
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
            switch (position) {
                //case 0: return new Fragment_Main();

                case 0:
                    return new Fragment_CreaProfilo();

                case 1:
                    //return new Fragment_DomandaOperatore();
                    return new Fragment_SeiOperatore();

                case 2:
                    return new Fragment_CreaOperatore();
                    //return new Fragment_Brevetto_Main();

                case 3:
                    return new Fragment_CreaOperatore_Normale();
                    //return new Fragment_Brevetto_Teoria();

                case 4:
                    return new Fragment_CreaOperatore_Critico();
                    //return new Fragment_Brevetto_Pratica();

                case 5:
                    return new Fragment_CreaOperatore_Terzi();
                    //return new Fragment_Brevetto_Visita_Medica();

                case 6:
                    return new Fragment_CreaDrone();

                case 7:
                    return new Fragment_CreaBatteria();

                case 8:
                    return new Fragment_Brevetto_Main();

                case 9:
                    return new Fragment_Brevetto_Teoria();

                case 10:
                    return new Fragment_Brevetto_Pratica();

                case 11:
                    return new Fragment_Brevetto_Visita_Medica();

                case 12:
                    return Fragment_Profilo.nuovaIstanza(Principale.getController().getProfilo().getNome(),
                            Principale.getController().getProfilo().getCognome(),
                            "000000");

                default:
                    return new Fragment_CreaProfilo();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

    }

    private class SreenSlidePagerAdapterMain extends FragmentStatePagerAdapter {
        public SreenSlidePagerAdapterMain(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new Fragment_Main();

                case 1:
                    return new Fragment_DomandaOperatore();
                    //return new Fragment_LibrettoVolo_Uno();

                case 2:
                    return new Fragment_LibrettoVolo_Due();

                case 3:
                    return new Fragment_LibrettoVolo_Tre();

                case 4:
                    return new Fragment_LibrettoVolo_Quattro();


                default:
                    return new Fragment_Main();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES_APP;
        }

    }

    //----------------------------------------------------------------------------------------------


    public void creaLibrettoDiVoloUno(View view){
        Principale.getController().setDroneAttuale(((Spinner)findViewById(R.id.spinner_droni)).getSelectedItem().toString());
        mPager.setCurrentItem(1);
    }

    public void prendiCoordinate(View view) {
        Context mContext = this;

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Toast.makeText(mContext,"You need have granted permission",Toast.LENGTH_SHORT).show();
            gps = new GPSTracker(mContext, this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();

                // \n is for new line
                Fragment_LibrettoVolo_Uno.settaCoordinate(latitude, longitude, gps);

                //------------ nome città
                final Location location = new Location("");
                location.setLatitude(latitude);
                location.setLongitude(longitude);

                getCityName(location, new OnGeocoderFinishedListener() {
                    @Override
                    public void onFinished(List<Address> results) {
                        // do something with the result
                        if(!results.isEmpty()) {
                            Fragment_LibrettoVolo_Uno.settaLocation(results.get(0));

                        }
                        else {
                            Toast.makeText(Principale.getController().getContext(), "Impossibile stabilire il luogo.", Toast.LENGTH_LONG).show();
                        }
                    }
        });


            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    // contacts-related task you need to do.

                    gps = new GPSTracker(Principale.getController().getContext(), this);

                    // Check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line
                        Fragment_LibrettoVolo_Uno.settaCoordinate(latitude, longitude, gps);
                    } else {
                        // Can't get location.
                        // GPS or network is not enabled.
                        // Ask user to enable GPS/network in settings.
                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(Principale.getController().getContext(), "You need to grant permission", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // Nome città

    public abstract class OnGeocoderFinishedListener {
        public abstract void onFinished(List<Address> results);
    }

    public void getCityName(final Location location, final OnGeocoderFinishedListener listener) {
        new AsyncTask<Void, Integer, List<Address>>() {
            @Override
            protected List<Address> doInBackground(Void... arg0) {
                Geocoder coder = new Geocoder(Principale.getController().getContext(), Locale.ENGLISH);
                List<Address> results = null;
                try {
                    results = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                } catch (IOException e) {
                    // nothing
                }
                return results;
            }

            @Override
            protected void onPostExecute(List<Address> results) {
                if (results != null && listener != null) {
                    listener.onFinished(results);
                }
            }
        }.execute();
    }

    public void confermaLogbookUno(View view){
        luogo = (EditText)findViewById(R.id.editText_logbook_uno_luogo);
        String location = luogo.getText().toString();

        if(TextUtils.isEmpty(location)){
            luogo.setError("Luogo mancante");
        }

        if(!TextUtils.isEmpty(location)) {
            Principale.getController().creaLibrettoDiVolo(Principale.getController().getProfilo().getCodice(),
                    (new ReadPropertyValues()).getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "numeroLogbook"));
            Principale.getController().salvaDati_Logbook_Uno();
            mPager.setCurrentItem(2, true);
        }
    }

    public void confermaLogbookDue(View view){
        Principale.getController().salvaDati_Logbook_Due();
        mPager.setCurrentItem(3, true);
    }

    public void confermaLogbookTre(View view){
        Principale.getController().salvaDati_Logbook_Tre();
        mPager.setCurrentItem(4, true);
    }

    public void confermaLogbookQuattro(View view){
        durata_missione_uno = (EditText)findViewById(R.id.editText_logbook_quattro_durata_missione_uno);
        String durataUno = durata_missione_uno.getText().toString();

        if(TextUtils.isEmpty(durataUno)){
            durata_missione_uno.setError("Durata missione mancante");
            durata_missione_uno.requestFocus();
        }

        durata_missione_due = (EditText)findViewById(R.id.editText_logbook_quattro_durata_missione_due);
        String durataDue = durata_missione_due.getText().toString();

        if(!TextUtils.isEmpty(durataUno)) {
            if (Fragment_LibrettoVolo_Quattro.durataMinoreCinqueMinuti(Integer.parseInt(durataUno))) {
                if (TextUtils.isEmpty(durataDue)) {
                    durata_missione_due.setError("Durata missione mancante");
                    durata_missione_due.requestFocus();
                }
            }
        }

        take_off_uno = (TextView)findViewById(R.id.textView_logbook_quattro_ora_take_off);
        take_off_due = (TextView)findViewById(R.id.textView_logbook_quattro_ora_take_off_due);

        landing_uno = (TextView)findViewById(R.id.textView_logbook_quattro_landing);
        String landingUno = landing_uno.getText().toString();

        if(TextUtils.isEmpty(landingUno)){
            take_off_uno.setError("Inserire orario di take off");
        }

        landing_due = (TextView)findViewById(R.id.textView_logbook_quattro_landing_due);
        String landingDue = landing_due.getText().toString();

        if(!TextUtils.isEmpty(durataUno)) {
            if (Fragment_LibrettoVolo_Quattro.durataMinoreCinqueMinuti(Integer.parseInt(durataUno))) {
                if (TextUtils.isEmpty(landingDue)) {
                    take_off_due.setError("Inrerire orario di take off");
                }
            }
        }

        if(!TextUtils.isEmpty(durataUno)
                && !TextUtils.isEmpty(landingUno)){
            if(Fragment_LibrettoVolo_Quattro.durataMinoreCinqueMinuti(Integer.parseInt(durataUno))){
                if(!TextUtils.isEmpty(durataDue)
                        && !TextUtils.isEmpty(landingDue)){
                    Principale.getController().salvaDati_Logbook_Quattro();
                }
            }else{
                Principale.getController().salvaDati_Logbook_Quattro();
            }
        }

    }

    //----------------------------------------------------------------------------------------------
    //set orari

    public void settaOraTakeOffUno(View view){
        settaOra(view, R.id.textView_logbook_quattro_ora_take_off, Calendar.HOUR_OF_DAY, Calendar.MINUTE);
    }


    public void settaOraTakeOffDue(View view){
        settaOra(view, R.id.textView_logbook_quattro_ora_take_off_due, 0, 0);
    }

    public static void impostaTextView(){
        Fragment_LibrettoVolo_Quattro.settaLandingUno();
    }

    public static void impostaTextViewDue(){
        Fragment_LibrettoVolo_Quattro.settaLandingDue();
    }

    public void settaOra(View view, int risorsa, int ora, int minuti){

        Bundle bundle = new Bundle();
        bundle.putInt("ora", ora);
        bundle.putInt("minuti", minuti);
        bundle.putInt("risorsa", risorsa);
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(bundle);
        newFragment.show(getSupportFragmentManager(), "TimePicker");
    }


}
