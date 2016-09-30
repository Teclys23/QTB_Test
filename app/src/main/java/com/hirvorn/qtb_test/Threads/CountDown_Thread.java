package com.hirvorn.qtb_test.Threads;

import android.app.Activity;
import android.util.Log;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.PreQTB.Fragment_Volo;
import com.hirvorn.qtb_test.StartPage;

/**
 * Created by laboratorio on 30/09/2016.
 */

public class CountDown_Thread extends Thread {

    private static final int INTERVALLO = 1000;
    private static final int MINUTI = 5;

    private boolean running;
    private int i;

    public CountDown_Thread(){
        running = true;
        i = 0;
    }

    public void run(){
        while (this.running) {
            if(i < MINUTI) {

                if (Principale.getController().isMissioneChiusa()) {
                    Log.v(StartPage.LOG_TAG, "MISSIONE CHIUSA");
                    stopThread();


                } else
                    Log.v(StartPage.LOG_TAG, "MISSIONE APERTA");

                try {
                    sleep(INTERVALLO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;

            }else{

                if (!Principale.getController().isMissioneChiusa()) {
                    ((Activity) (Principale.getController().getContext())).runOnUiThread(new Runnable() {

                        boolean fatto = false;
                        @Override
                        public void run() {
                            while (running) {
                                if (!fatto) {
                                    Fragment_Volo.chiudiMissione();
                                    fatto = true;
                                    stopThread();
                                }
                                try {
                                    sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }else {
                    Log.v(StartPage.LOG_TAG, "THREAD MORTO");

                    stopThread();
                }




            }
        }
    }

    public void stopThread(){
        this.running = false;
        interrupt();
    }
}
