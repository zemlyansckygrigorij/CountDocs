package com.example.countdocs.service;

import com.itextpdf.text.pdf.PdfReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtils for handle documents by extension
 * */
@Component
public class ExtensionUtils {
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
}
