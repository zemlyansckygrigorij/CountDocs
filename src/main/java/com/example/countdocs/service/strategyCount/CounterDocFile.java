package com.example.countdocs.service.strategyCount;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class CounterDocFile  for count pages  into word documents
 * */
public class CounterDocFile implements Counter {
    @Override
    public int getCountPages(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file.getPath());
        XWPFDocument document = new XWPFDocument(fis);
        return document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
    }
}
