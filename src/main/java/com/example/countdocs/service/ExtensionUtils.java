package com.example.countdocs.service;

import com.example.countdocs.service.strategyCount.*;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
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
        CounterFile counter = new CounterFile(switch (extension) {
            case "pdf" -> new CounterPDFFile();
            case "docx","doc" -> new CounterDocFile();
            default -> null;
        });
        return counter.count(file);
    }

    public String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
}
