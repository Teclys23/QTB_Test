package com.hirvorn.qtb_test.PDFCreator2;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.hirvorn.qtb_test.Main.Principale;
import com.hirvorn.qtb_test.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

/**
 * Created by Jhonny on 18/10/2016.
 */

public class Pagina {

    private static final int GRID_FONT_SIZE = 8;
    private static final int CELL_PADDING = 5;

    private static PdfPTable table, table2;
    private static PdfPCell cell;

    public static Document disegnaPagina(Document document) throws DocumentException {



        document.setMargins(20, 20, 20, 20);
        Font font = new Font(Font.FontFamily.HELVETICA, GRID_FONT_SIZE, Font.NORMAL);
        float[] columnWidths = {2, 2, 4, 5, 4, 5, 2, 3, 4, 3, 2};
        float[] columnWidths2 = {4, 4, 5, 4, 2, 3, 5, 4, 5};


        //Header
        Paragraph paragraph = new Paragraph("Questo");
        paragraph.add("\nsarà");
        paragraph.add("\nla zona con logo ed info");
        paragraph.add("\n ");


        document.add(paragraph);

        for(int i = 0; i < 3; i++) {
            //disegno le celle nell'header della griglia
            ArrayList<String> testi = new ArrayList<>();
            testi.add("N°\nVolo");
            testi.add("Data di Volo");
            testi.add("Ora di Take Off\nOra di Landing");
            testi.add("Codice Sistema\nCodice Batteria");
            testi.add("Tipo Operazioni");
            testi.add("Luogo Operazioni\nCoordinate GPS");
            testi.add("Durata\nVolo");
            testi.add("Progressivo\nore di Volo");
            testi.add("Firma P.I.C.\\Allievo");
            testi.add("Firma F.I.");
            testi.add("Note");

            disegnaCelleHeader(font, columnWidths, testi);

            //disegno le cella della riga dati
            disegnaCelleRigaDati(font);

            //disegno le celle dell'header secondario
            testi.clear();
            testi.add("Firma P.I.C. dopo LDG");
            testi.add("Totale uso\nda ultimo C.N.");
            testi.add("Note");
            testi.add("N° riferimento\nsegnalazione difetti");
            testi.add("Difetti\nrilevati");
            testi.add("Firma");
            testi.add("N° riferimento\nsegnalazione difetti");
            testi.add("Eliminazione\nDifetti");
            testi.add("Firma");
            disegnaCelleHeaderSecondario(font, columnWidths2, testi);

            //disegno le celle della riga dati secondaria
            disegnaCelleRigaDatiSecondaria(font);

            document.add(table);
            document.add(table2);
            document.add(new Paragraph(" "));
        }


        return document;
    }

    private static void disegnaCelleHeader(Font font, float[] columnWidths, ArrayList<String> testi){
        //Griglia
        table = new PdfPTable(columnWidths);
        table.setWidthPercentage(100);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);

        //Header griglia
        Phrase phrase = null;

        for(String testo : testi){
            phrase = new Phrase();
            phrase.setFont(font);
            phrase.add(testo);
            cell = new PdfPCell(phrase);
            cell.setPadding(CELL_PADDING);
            table.addCell(cell);
        }
    }

    private static void disegnaCelleRigaDati(Font font){
        //contenuto celle
        Phrase phrase = null;

        //n° volo
        for(int i = 0; i < 11; i++) {
            phrase = new Phrase("\n\n\n\n");
            phrase.setFont(font);
            cell = new PdfPCell(phrase);
            cell.setPadding(CELL_PADDING);
            table.addCell(cell);
        }
        //data di volo

    }

    private static void disegnaCelleHeaderSecondario(Font font, float[] columnWidths, ArrayList<String> testi){
        //Griglia
        table2 = new PdfPTable(columnWidths);
        table2.setWidthPercentage(100);
        table2.setHorizontalAlignment(Element.ALIGN_MIDDLE);

        //Header griglia
        Phrase phrase = null;

        for(String testo : testi){
            phrase = new Phrase();
            phrase.setFont(font);
            phrase.add(testo);
            cell = new PdfPCell(phrase);
            cell.setPadding(CELL_PADDING);
            BaseColor baseColor = new BaseColor(ContextCompat.getColor(Principale.getController().getContext(), R.color.color_PDFDoc_Note));

            cell.setBackgroundColor(baseColor);
            table2.addCell(cell);
        }
    }

    private static void disegnaCelleRigaDatiSecondaria(Font font){
        //contenuto celle
        Phrase phrase = null;

        //n° volo
        for(int i = 0; i < 9; i++) {
            phrase = new Phrase("\n");
            phrase.setFont(font);
            cell = new PdfPCell(phrase);
            cell.setPadding(CELL_PADDING);
            table2.addCell(cell);
        }
        //data di volo

    }
}
