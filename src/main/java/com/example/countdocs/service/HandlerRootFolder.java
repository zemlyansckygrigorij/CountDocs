package com.example.countdocs.service;

import java.io.IOException;
import java.util.List;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface HandlerRootFolder for handle root folder
 * */
public interface HandlerRootFolder {
    List<Integer> getDataFromPath(String path) throws IOException;
    String getInfoAboutFilesByPath(String path) throws IOException;
}
