package com.example.countdocs;

import com.example.countdocs.service.HandlerRootFolder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class CountDocsApplication for run application
 * */
@SpringBootApplication
public class CountDocsApplication {
    private static HandlerRootFolder handlerStatic;
    @Autowired
    HandlerRootFolder handler;
    @PostConstruct
    public void init() {
        CountDocsApplication.handlerStatic = handler;
    }
    public static void main(String[] args) throws IOException {
        SpringApplication.run(CountDocsApplication.class, args);

        if(args.length>0){
            System.out.println(handlerStatic.getInfoAboutFilesByPath(args[0]));
        }
    }
}
