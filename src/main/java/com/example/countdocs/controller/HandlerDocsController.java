package com.example.countdocs.controller;

import com.example.countdocs.service.HandlerRootFolder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class HandlerDocsController for work with web part
 * */
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class HandlerDocsController {
    private final HandlerRootFolder handler;

    @PostMapping()
    public String getInfoAboutFilesByPath(@RequestBody String path) {
       return handler.getInfoAboutFilesByPath(path);
    }
}
