package com.hirvorn.qtb_test.PDFCreator2;

import android.os.Environment;
import android.util.Log;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.hirvorn.qtb_test.StartPage.LOG_TAG;

/**
 * Created by Jhonny on 18/10/2016.
 */

public class PDFCreator2 {

    public PDFCreator2(){

    }

    public static void creaPDF() throws FileNotFoundException, DocumentException {
        //creo la directory qualora non esistesse
        //File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
        //        Environment.DIRECTORY_DOCUMENTS), "pdfdemo");

        File pdfFolder = new File(Environment.getExternalStorageDirectory() + "/" + Principale.getController().getContext().getResources().getString(R.string.app_name) + "/logs");

        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();
            Log.i(LOG_TAG, "Pdf Directory created");
        }

        //creazione del nome del pdf
        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

        File myFile = new File(pdfFolder + timeStamp + ".pdf");

        FileOutputStream output = new FileOutputStream(myFile);

        Document document = new Document(PageSize.A4.rotate());

        PdfWriter.getInstance(document, output);

        //inizializzazione
        document.open();

        document = Pagina.disegnaPagina(document);

        //chiusura del file
        document.close();


    }

}
