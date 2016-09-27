package com.hirvorn.qtb_test.Operatore;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.Settings.ReadPropertyValues;

/**
 * Created by laboratorio on 26/09/2016.
 */

public class Fragment_CreaOperatore extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crea_operatore, container, false);

        return view;
    }

    public static void controllaButtonOperatoreCritico(View view){
        ReadPropertyValues reader = new ReadPropertyValues();
        if(reader.getPropValue(Principale.getController().getProfilo().getCodice() + Principale.getConfig().getUserExtension(), "fileOperatoreNormale") != null){
            Button button = (Button)view.findViewById(R.id.button_crea_operatore_critico);
            button.setEnabled(true);
        }
    }
}
