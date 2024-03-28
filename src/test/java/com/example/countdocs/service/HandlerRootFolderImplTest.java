package com.example.countdocs.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class CountDocsServiceImplTest  for testing class CountDocsServiceImpl
 * */
@SpringBootTest
class HandlerRootFolderImplTest {
    @Autowired
    private HandlerRootFolder handler;
    @Value("${test.path}")
    private String path;
    @Value("${number.docs}")
    private int numberDocs;
    @Value("${number.pages}")
    private int numberPages;

    @Test
    @DisplayName(" Проверка получения количества документов из тестовой папки.")
    void testCountDocsFromPath(@Value("${test.count.docs}") int countDocs) throws IOException {
        int countDocsFromService = handler.getDataFromPath(path).get(numberDocs);
        assertEquals(countDocs,countDocsFromService);
    }
    @Test
    @DisplayName(" Проверка получения количества страниц из документов тестовой папки.")
    void testCountPagesFromPath(@Value("${test.count.pages}") int count) throws IOException {
        int countPages = handler.getDataFromPath(path).get(numberPages);
        assertEquals(count,countPages);
    }

    @Test
    @DisplayName("Проверка поиска папки по адресу с двойным слешем.")
    void testCheckPathWithDoubleSlash(@Value("${path.double.slash}") String pathDoubleSlash, @Value("${test.count.docs}") int countDocs) throws IOException {
        int countDocsFromService = handler.getDataFromPath(pathDoubleSlash).get(numberDocs);
        assertEquals(countDocs,countDocsFromService);
    }
    @Test
    @DisplayName("Проверка поиска папки по неправильному адресу.")
    void testCheckWrongPath(@Value("${test.wrong.path}") String pathWrong, @Value("${wrong.path.docs}") int count) throws IOException {
        int countDocsFromService = handler.getDataFromPath(pathWrong).get(numberDocs);
        assertEquals(count,countDocsFromService);
    }
    @Test
    @DisplayName("Проверка поиска папки по абсолютному адресу.")
    void testCountDocsFromAbsolutePath(@Value("${test.absolute.path}") String path, @Value("${test.count.docs}") int countDocs) throws IOException {
        int countDocsFromService  = handler.getDataFromPath(path).get(numberDocs);
        assertEquals(countDocs,countDocsFromService);
    }

    @Test
    @DisplayName("Проверка получения текстовой информации о файлах в тестовой папке.")
    void getInfoAboutFiles(@Value("${test.info}") String info) throws IOException {
        String response = handler.getInfoAboutFilesByPath(path);
        assertEquals(response,info);
    }
}