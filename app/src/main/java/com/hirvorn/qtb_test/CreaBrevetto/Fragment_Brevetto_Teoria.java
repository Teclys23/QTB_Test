package com.hirvorn.qtb_test.CreaBrevetto;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

import java.util.Calendar;

/**
 * Created by laboratorio on 12/09/2016.
 */
public class Fragment_Brevetto_Teoria extends Fragment{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_brevetto_teoria, container, false);

        return view;
    }
}
