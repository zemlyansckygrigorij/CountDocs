package com.example.countdocs.service;


import com.example.countdocs.service.handler.Handler;
import com.example.countdocs.service.handler.HandlerPDF;
import com.example.countdocs.service.handler.HandlerNotPDF;
import com.itextpdf.text.*;
import java.io.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtils for handle documents by extension
 * */
@Component
public class ExtensionUtils {

    public int getCountPagesByDocument(File file) throws IOException, DocumentException {
        Handler handler = createHandler(file);
        handler.close();
        return handler.getCountPages();
    }

   public Handler createHandler(File file) throws IOException, DocumentException {
        return getExtensionByApacheCommonLib(file.getName())
                .equals("pdf") ? new HandlerPDF(file): new HandlerNotPDF(file);
   }

    public String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
}
