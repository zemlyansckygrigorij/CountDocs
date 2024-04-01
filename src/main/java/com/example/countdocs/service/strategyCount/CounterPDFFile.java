package com.example.countdocs.service.strategyCount;

import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.IOException;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class CounterPDFFile for count pages into pdf documents
 * */
public class CounterPDFFile implements Counter {
    @Override
    public int getCountPages(File file) throws IOException {
        PdfReader reader = new PdfReader(file.getPath());
        return reader.getNumberOfPages();
    }
}
