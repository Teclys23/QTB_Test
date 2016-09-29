package com.hirvorn.qtb_test.Utils;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hirvorn.qtb_test.PreQTB.Fragment_LibrettoVolo_Quattro;
import com.hirvorn.qtb_test.R;
import com.hirvorn.qtb_test.StartPage;

import java.util.Calendar;


public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        //Use the current time as the default values for the time picker
        final Calendar c = Calendar.getInstance();
        Bundle bundle = this.getArguments();
        int hour, minute;
        if(bundle.getInt("ora") == 0 && bundle.getInt("minuti") == 0){
            hour = Fragment_LibrettoVolo_Quattro.oraLandingUno();
            minute = Fragment_LibrettoVolo_Quattro.minutiLandingUno();
        }else {
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        }

        //Create and return a new instance of TimePickerDialog
        return new TimePickerDialog(getActivity(),this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    //onTimeSet() callback method
    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
        Bundle bundle = this.getArguments();
        int risorsa = bundle.getInt("risorsa");
        //Do something with the user chosen time
        //Get reference of host activity (XML Layout File) TextView widget
        TextView tv = (TextView) getActivity().findViewById(risorsa);
        //Set a message for user
        //Display the user changed time on TextView
        tv.setText(String.valueOf(hourOfDay) + " : " + String.valueOf(minute));

        if(risorsa == R.id.textView_logbook_quattro_ora_take_off)
            StartPage.impostaTextView();
        else
            StartPage.impostaTextViewDue();
    }

}