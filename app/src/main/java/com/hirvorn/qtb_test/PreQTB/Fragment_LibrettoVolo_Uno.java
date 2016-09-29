package com.hirvorn.qtb_test.PreQTB;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hirvorn.qtb_test.Enum.Meteo;
import com.hirvorn.qtb_test.Enum.Vento;
import com.hirvorn.qtb_test.Enum.VolumeDiVolo;
import com.hirvorn.qtb_test.GPS.GPSTracker;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.StartPage;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class Fragment_LibrettoVolo_Uno extends Fragment{

    private static Spinner volume_di_volo;
    private static Spinner meteo;
    private static Spinner vento;
    private static TextView data;
    private static Button button;
    private static View view;
    private static EditText luogo;

    private static TextView lat;
    private static TextView lon;
    private static Button button1;
    private static Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_logbook_uno, container, false);

        luogo = (EditText)view.findViewById(R.id.editText_logbook_uno_luogo);

        volume_di_volo = (Spinner) view.findViewById(R.id.spinner_volume_di_volo);
        volume_di_volo.setAdapter(new ArrayAdapter<VolumeDiVolo>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, VolumeDiVolo.values()));

        meteo = (Spinner) view.findViewById(R.id.spinner_meteo);
        meteo.setAdapter(new ArrayAdapter<Meteo>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, Meteo.values()));

        vento = (Spinner) view.findViewById(R.id.spinner_vento);
        vento.setAdapter(new ArrayAdapter<Vento>(Principale.getController().getContext(), android.R.layout.simple_spinner_item, Vento.values()));

        data = (TextView) view.findViewById(R.id.textView_logbook_uno_data);

        Calendar calendar = Calendar.getInstance();
        int giorno = calendar.get(Calendar.DAY_OF_MONTH);
        int mese = calendar.get(Calendar.MONTH) + 1;
        int anno = calendar.get(Calendar.YEAR);

        data.setText(giorno + "/" + mese + "/" + anno + " (premi per cambiare)");

        return view;
    }

    public static void settaCoordinate(double latitudine, double longitudine, GPSTracker gps){
        RelativeLayout layout = (RelativeLayout)view.findViewById(R.id.layout_coordinate);

        LinearLayout layout1 = new LinearLayout(Principale.getController().getContext());
        layout1.setOrientation(LinearLayout.VERTICAL);
        lat = new TextView(Principale.getController().getContext());
        lat.setText("Latitudine: " + latitudine);

        lon = new TextView(Principale.getController().getContext());
        lon.setText("Longitudine: " + longitudine);

        layout1.addView(lat);
        layout1.addView(lon);



        button1 = (Button)view.findViewById(R.id.button_inserisci_gps);
        button1.setEnabled(false);
        button1.setVisibility(View.INVISIBLE);

        button2 = new Button(Principale.getController().getContext());
        button2.setText("Annulla coordinate");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                luogo.setText("");

                lat.setText("");
                lat.setVisibility(View.INVISIBLE);

                lon.setText("");
                lon.setVisibility(View.INVISIBLE);

                button1.setEnabled(true);
                button1.setVisibility(View.VISIBLE);

                button2.setVisibility(View.INVISIBLE);
            }
        });

        layout1.addView(button2);
        layout.addView(layout1);

        gps.stopUsingGPS();
    }

    public static void settaLocation(Address location){

        luogo.setText(location.getLocality());
    }

    public static void salvaDati(){
        ArrayList<String> keys = new ArrayList<>();
        keys.add("data");
        keys.add("volumeDiVolo");
        keys.add("luogo");
        keys.add("latitudine");
        keys.add("longitudine");
        keys.add("meteo");
        keys.add("vento");

        ArrayList<String> values = new ArrayList<>();
        values.add(new ArrayList<>(Arrays.asList(data.getText().toString().split(" "))).get(0));
        values.add(volume_di_volo.getSelectedItem().toString());
        values.add(luogo.getText().toString());
        if(lat != null && lon != null){
            values.add(lat.getText().toString());
            values.add(lon.getText().toString());
        }else{
            values.add("null");
            values.add("null");
        }
        values.add(meteo.getSelectedItem().toString());
        values.add(vento.getSelectedItem().toString());

        Principale.getController().getLibrettoDiVolo().salvaDati(keys, values);

    }

}
