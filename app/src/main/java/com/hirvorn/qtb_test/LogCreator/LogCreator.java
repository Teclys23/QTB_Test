package com.hirvorn.qtb_test.LogCreator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Environment;
import android.widget.Toast;

import com.hirvorn.qtb_test.LibrettoDiVolo.LibrettoDiVolo;
import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Jhonny on 17/10/2016.
 */

public class LogCreator {

    private static FileOutputStream out;

    public static void creaLog(String name){

        String logPath = Environment.getExternalStorageDirectory().toString();
        //File logFile = Environment.getExternalStorageDirectory();

        File logFile = new File(logPath + "/" + Principale.getController().getContext().getResources().getString(R.string.app_name) + "/logs");

        if(!logFile.exists()){
            logFile.mkdirs();
        }

        File log = new File(logFile, name + ".log");

        if(log.exists())
            log.delete();

        try {
            out = new FileOutputStream(log);

            scriviDati(name);

            out.flush();
            out.close();
            System.out.println("Log creato");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void scriviDati(String name){
        System.out.println("Nome: " + name + LibrettoDiVolo.LOGBOOK_EXTENSION);

        try {

            String s = Principale.getController().ottieniDatiPerLog(name + LibrettoDiVolo.LOGBOOK_EXTENSION);
            System.out.println("Prova dati: " + s);
            out.write(s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
