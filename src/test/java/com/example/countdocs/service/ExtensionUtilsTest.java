package com.example.countdocs.service;

import com.itextpdf.text.DocumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtilsTest for testing class ExtensionUtils
 * */
@SpringBootTest
class ExtensionUtilsTest {
    @Autowired
    ExtensionUtils utils;

    @Test
    @DisplayName(" Проверка получения количества страниц из pdf документа.")
    void getCountPagesFromPDF(@Value("${test.path.pdf}") String path, @Value("${pages.count.pdf}") int countDocs) throws IOException, DocumentException {
        int pagesCount =  getCountPagesFromDocument(new File(path));
        assertEquals(countDocs,pagesCount);
    }

    @Test
    @DisplayName(" Проверка получения количества страниц из txt документа.")
    void getCountPagesFromTXT(@Value("${test.path.txt}") String path, @Value("${pages.count.txt}") int countDocs) throws IOException, DocumentException {
        int pagesCount =  getCountPagesFromDocument(new File(path));
        assertEquals(countDocs,pagesCount);
    }
    @Test
    @DisplayName(" Проверка получения количества страниц из docx документа.")
    void getCountPagesFromDOCX(@Value("${test.path.docx}") String path, @Value("${pages.count.docx}") int countDocs) throws IOException, DocumentException {
        int pagesCount =  getCountPagesFromDocument(new File(path));
        assertEquals(countDocs,pagesCount);
    }

    int getCountPagesFromDocument(File file) throws IOException, DocumentException {
        return utils.getCountPagesByDocument(file);
    }
}