package com.example.countdocs.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfReader;

import java.io.*;

import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtils for handle documents by extension
 * */
@Component
public class ExtensionUtils {
    @Value("${margin.left}")
    private float marginLeft;
    @Value("${margin.right}")
    private float marginReft;
    @Value("${margin.top}")
    private float marginTop;
    @Value("${margin.bottom}")
    private float marginBottom;
    @Value("${path.temp}")
    private String pathTemp;
    public int getCountPagesFromDocument(File file) throws IOException {
        String extension = getExtensionByApacheCommonLib(file.getName());
        return switch (extension) {
            case "pdf" -> getCountPagesFromPDF(file);
            case "docx" -> getCountPagesFromDOCX(file);
            default -> 0;
        };
    }

    private int getCountPagesFromPDF(File file) throws IOException {
        PdfReader reader = new PdfReader(file.getPath());
        return reader.getNumberOfPages();
    }

    private int getCountPagesFromDOCX(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file.getPath());
        XWPFDocument document = new XWPFDocument(fis);
        return document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
    }

    private String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }

    public void convertToPDFInTempFolder(File file) throws IOException {
        BufferedReader input = null;
        Document output = null;

        try {
            input =
                    new BufferedReader (new FileReader( file));

            // letter 8.5x11
            //    see com.lowagie.text.PageSize for a complete list of page-size constants.
            output = new Document(PageSize.LETTER, marginLeft, marginReft, marginTop, marginBottom);
            PdfWriter.getInstance(output, new FileOutputStream (pathTemp+file.getName()+".pdf"));
            output.open();
            output.addSubject(file.getPath());

            String line = "";
            while(null != (line = input.readLine())) {
                Paragraph p = new Paragraph(line);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                output.add(p);
            }
            output.close();
            input.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
