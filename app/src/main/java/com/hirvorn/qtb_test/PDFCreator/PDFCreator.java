package com.hirvorn.qtb_test.PDFCreator;

/**
 * Created by Jhonny on 12/10/2016.
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.content.Context;
import android.util.Log;

import com.hirvorn.qtb_test.StartPage;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFCreator  extends AppCompatActivity {

    public class MyPrintDocumentAdapter extends PrintDocumentAdapter
    {
        Context context;
        private int pageHeight;
        private int pageWidth;
        public PdfDocument myPdfDocument;
        public int totalpages = 1;

        public MyPrintDocumentAdapter(Context context)
        {
            this.context = context;
        }

        @Override
        public void onLayout(PrintAttributes oldAttributes,
                             PrintAttributes newAttributes,
                             CancellationSignal cancellationSignal,
                             LayoutResultCallback callback,
                             Bundle metadata) {

            myPdfDocument = new PrintedPdfDocument(context, newAttributes);

            //ISO_A4 = 210mm x 297mm
            //pageHeight = newAttributes.getMediaSize().getHeightMils()/1000 * 72;
            //pageHeight = (int)((newAttributes.getMediaSize().ISO_A4.getHeightMils() / 1000 * 72) / 0.039370);
            Log.v(StartPage.LOG_TAG, "ISO A4: " + newAttributes.getMediaSize().ISO_A4.getWidthMils() + " x "
                    + newAttributes.getMediaSize().ISO_A4.getHeightMils()
                    + " - " + (int)((newAttributes.getMediaSize().ISO_A4.getHeightMils() * 0.0254)));
            pageHeight = 2970;

            //pageWidth = newAttributes.getMediaSize().getWidthMils()/1000 * 72;
            //pageWidth = newAttributes.getMediaSize().ISO_A4.getWidthMils();
            pageWidth = 2100;


            if (cancellationSignal.isCanceled() ) {
                callback.onLayoutCancelled();
                return;
            }

            if (totalpages > 0) {
                PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                        .Builder("print_output.pdf")
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(totalpages);


                PrintDocumentInfo info = builder.build();
                callback.onLayoutFinished(info, true);
            } else {
                callback.onLayoutFailed("Page count is zero.");
            }
        }


        @Override
        public void onWrite(final PageRange[] pageRanges,
                            final ParcelFileDescriptor destination,
                            final CancellationSignal cancellationSignal,
                            final WriteResultCallback callback) {

            for (int i = 0; i < totalpages; i++) {
                if (pageInRange(pageRanges, i))
                {
                    PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                            pageHeight, i).create();

                    PdfDocument.Page page =
                            myPdfDocument.startPage(newPage);

                    if (cancellationSignal.isCanceled()) {
                        callback.onWriteCancelled();
                        myPdfDocument.close();
                        myPdfDocument = null;
                        return;
                    }
                    drawPage(page, i);
                    myPdfDocument.finishPage(page);
                }
            }

            try {
                myPdfDocument.writeTo(new FileOutputStream(
                        destination.getFileDescriptor()));
            } catch (IOException e) {
                callback.onWriteFailed(e.toString());
                return;
            } finally {
                myPdfDocument.close();
                myPdfDocument = null;
            }

            callback.onWriteFinished(pageRanges);
        }

    }

    private boolean pageInRange(PageRange[] pageRanges, int page)
    {
        for (int i = 0; i<pageRanges.length; i++)
        {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }

    private void drawPage(PdfDocument.Page page,
                          int pagenumber) {
        Pagina.disegnaPagina(page, pagenumber);
    }

}