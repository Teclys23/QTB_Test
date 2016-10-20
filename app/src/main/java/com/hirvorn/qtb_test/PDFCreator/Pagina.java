package com.hirvorn.qtb_test.PDFCreator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;

/**
 * Created by Jhonny on 12/10/2016.
 */

public class Pagina {

    private static final String TAG = "PaginaPDF";
    private static final int CM = 100;
    private static final int MM = 10;

    public static void disegnaPagina(PdfDocument.Page page,
                                     int pagenumber){

        Canvas canvas = page.getCanvas();

        pagenumber++; // Make sure page numbers start at 1
        Log.v(TAG, "Dimensioni: " + canvas.getWidth() + " x " + canvas.getHeight());

        //ISO A4 = 21 cm x 29,7 cm

        //int titleBaseLine = 72;
        //int leftMargin = 54;
        int density = 254; // = 100 pixel / centimetro

        int griglia_top = 5*CM;
        int griglia_left = 1 * CM;
        int griglia_width = 19 * CM;
        int spessoreLinea = 1;

        int griglia_H_cursore_precedente;
        int griglia_V_cursore;

        int griglia_riga_1 = 14 * MM;
        int griglia_riga_2 = 24*MM;
        int griglia_riga_3 = 14*MM;
        int griglia_riga_4 = 14*MM;

        //contenuto prima riga
        int text_left_pad = 5*MM;
        int text1_V = griglia_top + spessoreLinea + 8*MM;
        int text1_H = griglia_left + spessoreLinea + text_left_pad;
        int text1_interlinea = 2*MM;

        int larghezza_cella_1 = 2*CM;
        int larghezza_cella_2 = 4*CM;
        int larghezza_cella_3 = 4*CM;
        int larghezza_cella_4 = 4*CM;
        int larghezza_cella_5 = 2*CM;

        //contenuto terza riga
        int text3_V = griglia_top + spessoreLinea + griglia_riga_1 + spessoreLinea + griglia_riga_2 + spessoreLinea + 8*MM;
        int text3_H = griglia_left + spessoreLinea + text_left_pad;
        int text3_interlinea = 2*MM;


        canvas.setDensity(density);
        //-------
        Log.v(TAG, "Densità pagina: " + density);
        Log.v(TAG, "Larghezza pagina: " + canvas.getWidth());
        Log.v(TAG, "Altezza pagina: " + canvas.getHeight());
        //-------


        Paint paint = new Paint();

        //griglia
        paint.setColor(Color.BLACK);

        Rect lineaGriglia_width = new Rect(griglia_left, griglia_top, griglia_left + griglia_width, griglia_top + spessoreLinea);
        canvas.drawRect(lineaGriglia_width, paint);

        Rect lineaGriglia_riga_1_inizio = new Rect(griglia_left, griglia_top + spessoreLinea, griglia_left + spessoreLinea, griglia_top + griglia_riga_1);
        canvas.drawRect(lineaGriglia_riga_1_inizio, paint);

        Rect lineaGriglia_riga_1_fine = new Rect(griglia_left + griglia_width - spessoreLinea,
                                                    griglia_top + spessoreLinea,
                                                    griglia_left + griglia_width,
                                                    griglia_top + griglia_riga_1);
        canvas.drawRect(lineaGriglia_riga_1_fine, paint);

        Rect lineaGriglia_width_2 = new Rect(griglia_left,
                                                griglia_top + griglia_riga_1 + spessoreLinea,
                                                griglia_left + griglia_width,
                                                griglia_top + griglia_riga_1 + spessoreLinea + spessoreLinea);
        canvas.drawRect(lineaGriglia_width_2, paint);


        //cella 1
        paint.setTextSize(36);
        canvas.drawText("N°", text1_H, text1_V - text1_interlinea, paint);
        canvas.drawText("volo", text1_H, text1_V + text1_interlinea, paint);

        int griglia_H_cursore = griglia_left + spessoreLinea + larghezza_cella_1;

        canvas.drawRect(griglia_H_cursore,
                griglia_top + spessoreLinea,
                griglia_H_cursore + spessoreLinea,
                griglia_top + griglia_riga_1 + spessoreLinea,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 2
        text1_H = griglia_H_cursore + text_left_pad;
        canvas.drawText("Ora di Take Off", text1_H, text1_V - text1_interlinea, paint);
        canvas.drawText("Ora di Landing", text1_H, text1_V + text1_interlinea, paint);

        griglia_H_cursore += larghezza_cella_2;
        canvas.drawRect(griglia_H_cursore,
                griglia_top + spessoreLinea,
                griglia_H_cursore + spessoreLinea,
                griglia_top + griglia_riga_1 + spessoreLinea,
                paint);

        griglia_H_cursore += spessoreLinea;
        //spazio occupato: 6 cm + spessori

        //cella 3
        text1_H = griglia_H_cursore + text_left_pad;
        canvas.drawText("Tipo Operazioni", text1_H, text1_V - text1_interlinea, paint);

        griglia_H_cursore += larghezza_cella_3;
        canvas.drawRect(griglia_H_cursore,
                griglia_top + spessoreLinea,
                griglia_H_cursore + spessoreLinea,
                griglia_top + griglia_riga_1 + spessoreLinea,
                paint);

        griglia_H_cursore += spessoreLinea;
        //spazio occupato: 10cm + spessori

        //cella 4
        text1_H = griglia_H_cursore + text_left_pad;
        canvas.drawText("Luogo operazioni", text1_H, text1_V - text1_interlinea, paint);
        canvas.drawText("Coordinate gps", text1_H, text1_V + text1_interlinea, paint);

        griglia_H_cursore += larghezza_cella_4;
        canvas.drawRect(griglia_H_cursore,
                griglia_top + spessoreLinea,
                griglia_H_cursore + spessoreLinea,
                griglia_top + griglia_riga_1 + spessoreLinea,
                paint);

        griglia_H_cursore += spessoreLinea;
        //spazio occupato: 14 cm + spessori

        //cella 5
        text1_H = griglia_H_cursore + text_left_pad;
        canvas.drawText("Durata", text1_H, text1_V - text1_interlinea, paint);
        canvas.drawText("Volo", text1_H, text1_V + text1_interlinea, paint);

        griglia_H_cursore += larghezza_cella_5;
        canvas.drawRect(griglia_H_cursore,
                griglia_top + spessoreLinea,
                griglia_H_cursore + spessoreLinea,
                griglia_top + griglia_riga_1 + spessoreLinea,
                paint);

        griglia_H_cursore += spessoreLinea;
        //spazio occupato: 16.5 cm + spessori

        //cella 6
        text1_H = griglia_H_cursore + text_left_pad;
        canvas.drawText("Progressivo", text1_H, text1_V - text1_interlinea, paint);
        canvas.drawText("ore di Volo", text1_H, text1_V + text1_interlinea, paint);

        //Riga 2
        //riga inizio
        griglia_H_cursore = griglia_left;
        griglia_V_cursore = griglia_top + 2*spessoreLinea + griglia_riga_1;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        // cella 1
        griglia_H_cursore += spessoreLinea + larghezza_cella_1;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 2
        griglia_H_cursore += larghezza_cella_2;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 3
        griglia_H_cursore += larghezza_cella_3;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 4
        griglia_H_cursore += larghezza_cella_4;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 5
        griglia_H_cursore += larghezza_cella_5;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //riga fine
        griglia_H_cursore = griglia_left + griglia_width -  spessoreLinea;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_2,
                paint);

        griglia_H_cursore += spessoreLinea;

        //linea separazione riga 2/3
        griglia_V_cursore += griglia_riga_2;
        griglia_H_cursore = griglia_left;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + griglia_width,
                griglia_V_cursore + spessoreLinea,
                paint);

        griglia_V_cursore += spessoreLinea;

        //------------------------------------------------------------------------------------------
        //Riga 3
        //riga inizio
        griglia_H_cursore = griglia_left;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;
        // cella 1
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore += larghezza_cella_1;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);

        canvas.drawText("Note", text3_H, text3_V - text3_interlinea, paint);

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 2
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore += larghezza_cella_2;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 3
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore += larghezza_cella_3;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);


        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 4
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore += larghezza_cella_4;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 5
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore += larghezza_cella_5;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //riga fine
        griglia_H_cursore_precedente = griglia_H_cursore;
        griglia_H_cursore = griglia_left + griglia_width -  spessoreLinea;

        coloraCella(canvas,
                griglia_H_cursore_precedente,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                spessoreLinea);

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_3,
                paint);

        griglia_H_cursore += spessoreLinea;

        //linea separazione riga 3/4
        griglia_V_cursore += griglia_riga_3;
        griglia_H_cursore = griglia_left;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + griglia_width,
                griglia_V_cursore + spessoreLinea,
                paint);

        griglia_V_cursore += spessoreLinea;

        //Riga 4
        //riga inizio
        griglia_H_cursore = griglia_left;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        // cella 1
        griglia_H_cursore += spessoreLinea + larghezza_cella_1;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 2
        griglia_H_cursore += larghezza_cella_2;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 3
        griglia_H_cursore += larghezza_cella_3;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 4
        griglia_H_cursore += larghezza_cella_4;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //cella 5
        griglia_H_cursore += larghezza_cella_5;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //riga fine
        griglia_H_cursore = griglia_left + griglia_width -  spessoreLinea;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + spessoreLinea,
                griglia_V_cursore + griglia_riga_4,
                paint);

        griglia_H_cursore += spessoreLinea;

        //linea separazione riga 4/5
        griglia_V_cursore += griglia_riga_4;
        griglia_H_cursore = griglia_left;

        canvas.drawRect(griglia_H_cursore,
                griglia_V_cursore,
                griglia_H_cursore + griglia_width,
                griglia_V_cursore + spessoreLinea,
                paint);

        griglia_V_cursore += spessoreLinea;


    }

    private static void coloraCella(Canvas canvas, int left, int top, int right, int bot, int spessoreLinea){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(Principale.getController().getContext(), R.color.color_PDFDoc_Note));

        canvas.drawRect(left, top, right - spessoreLinea, bot, paint);
    }
}
