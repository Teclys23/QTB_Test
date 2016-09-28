package com.hirvorn.qtb_test.CreaProfilo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

/**
 * Created by laboratorio on 23/09/2016.
 */

public class Fragment_SeiOperatore extends Fragment {

    private static RadioButton operatore_si;
    private static RadioButton operatore_no;
    private static TextView tv_operatore_per;
    private static RadioButton operatore_per_si;
    private static RadioButton operatore_per_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sei_operatore, container, false);

        operatore_si = (RadioButton)view.findViewById(R.id.radioButton_sei_operatore_si);
        operatore_no = (RadioButton)view.findViewById(R.id.radioButton_sei_operatore_no);
        tv_operatore_per = (TextView)view.findViewById(R.id.textView_sei_operatore_lavori_per);
        operatore_per_si = (RadioButton)view.findViewById(R.id.radioButton_sei_operatore_per_si);
        operatore_per_no = (RadioButton)view.findViewById(R.id.radioButton_sei_operatore_per_no);

        return view;
    }

    public static void setVisibilitaOperatorePer(boolean visibile){
        if(visibile){
            tv_operatore_per.setVisibility(View.VISIBLE);
            operatore_per_si.setVisibility(View.VISIBLE);
            operatore_per_no.setVisibility(View.VISIBLE);
        }else{
            tv_operatore_per.setVisibility(View.INVISIBLE);
            operatore_per_si.setVisibility(View.INVISIBLE);
            operatore_per_no.setVisibility(View.INVISIBLE);
        }
    }
}
