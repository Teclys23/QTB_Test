package com.hirvorn.qtb_test.CreaBrevetto;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.StartPage;

import java.util.Calendar;

/**
 * Created by laboratorio on 12/09/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK, this, year, month, day);
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        //Do something with the date chosen by the user
        Bundle bundle = this.getArguments();
        int risorsa = bundle.getInt("textView");
        TextView tv = (TextView) getActivity().findViewById(risorsa);
       /*
        tv.setText("Date changed...");
        tv.setText(tv.getText() + "\nYear: " + year);
        tv.setText(tv.getText() + "\nMonth: " + month);
        tv.setText(tv.getText() + "\nDay of Month: " + day);

        String stringOfDate = day + "/" + month + "/" + year;
        tv.setText(tv.getText() + "\n\nFormatted date: " + stringOfDate);*/
        tv.setText(new StringBuilder().append(day).append("/").append(month+1).append("/").append(year));
    }
}