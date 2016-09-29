package com.hirvorn.qtb_test.PreQTB;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hirvorn.qtb_test.R;

import java.util.Calendar;

/**
 * Created by laboratorio on 29/09/2016.
 */

public class Fragment_Volo extends Fragment {

    private static Button btn_take_off;
    private static Button btn_landing;
    private static TextView tv_take_off;
    private static TextView tv_landing;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volo, container, false);

        btn_take_off = (Button)view.findViewById(R.id.btn_take_off);
        btn_landing = (Button)view.findViewById(R.id.btn_landing);
        tv_take_off = (TextView)view.findViewById(R.id.textView_volo_orario_take_off);
        tv_landing = (TextView)view.findViewById(R.id.textView_volo_orario_landing);

        return view;
    }

    public static void takeOff(){
        Calendar calendar = Calendar.getInstance();
        String ora = String.valueOf(calendar.get(Calendar.HOUR));
        String minuti = String.valueOf(calendar.get(Calendar.MINUTE));

        tv_take_off.setText(ora + " : " + minuti);

        btn_take_off.setVisibility(View.INVISIBLE);
        btn_landing.setVisibility(View.VISIBLE);

    }

    public static void landing(){
        Calendar calendar = Calendar.getInstance();
        String ora = String.valueOf(calendar.get(Calendar.HOUR));
        String minuti = String.valueOf(calendar.get(Calendar.MINUTE));

        tv_landing.setText(ora + " : " + minuti);
    }
}
