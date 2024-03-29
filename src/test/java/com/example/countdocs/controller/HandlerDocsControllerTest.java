package com.example.countdocs.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.nio.file.FileSystems;
import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class HandlerDocsControllerTest  for testing class HandlerDocsController
 * */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HandlerDocsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${test.info}")
    private String info;

    @Test
    @DisplayName(" Проверка получения web-страницы содержащей информацию о документах в тестовой папке. По относительному пути.")
    void testPath(@Value("${test.path}") String path) throws Exception{
        assertEquals(getInfoAboutFilesByPath(path),info);
    }

    @Test
    @DisplayName(" Проверка получения web-страницы содержащей информацию о документах в тестовой папке. По абсолютному пути.")
    void testAbsolutePath(@Value("${test.path}") String path) throws Exception{
        String homePath = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
        assertEquals(getInfoAboutFilesByPath(homePath+path),info);
    }

    @Test
    @DisplayName(" Проверка получения web-страницы содержащей информацию о документах в тестовой папке. По относительному пути с двойным слэшем.")
    void testPathWithDoubleSlash(@Value("${path.double.slash}") String path) throws Exception{
        assertEquals(getInfoAboutFilesByPath(path),info);
    }

    @Test
    @DisplayName(" Проверка получения web-страницы содержащей информацию о документах в тестовой папке. По неверному пути.")
    void testWrongPath(@Value("${test.wrong.path}") String path, @Value("${wrong.path.info}") String info) throws Exception{
        assertEquals(getInfoAboutFilesByPath(path),info);
    }

    private String getInfoAboutFilesByPath(String path) throws Exception {
          MvcResult result =  mockMvc.perform(MockMvcRequestBuilders
                        .post("/")
                        .content(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                  .andReturn();
        return result.getResponse().getContentAsString();
    }
}