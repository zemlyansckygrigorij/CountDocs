package com.example.countdocs.service.handler;

import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.IOException;

public class HandlerPDF implements Handler{

    private final PdfReader reader;
    private int countPages;
    public  HandlerPDF(File file) throws IOException {
        this.reader = new PdfReader(file.getPath());
        this.countPages = reader.getNumberOfPages();
    }
    public int getCountPages() {
        return countPages;
    }
    public void close(){
        reader.close();
    }
}
