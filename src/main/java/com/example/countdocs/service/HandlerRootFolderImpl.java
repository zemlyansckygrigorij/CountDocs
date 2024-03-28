package com.example.countdocs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class HandlerRootFolderImpl for implements HandlerRootFolder
 * */
@Service
public class HandlerRootFolderImpl implements HandlerRootFolder{
    @Autowired
    private ExtensionUtils utils ;
    @Value("${number.docs}")
    private int numberDocs;
    @Value("${number.pages}")
    private int numberPages;
    private int countDocs = 0;
    private int countPages = 0;

    @Override
    public List<Integer> getDataFromPath(String pathS) throws IOException {
        List<Integer> listData = new ArrayList<>();
        handlerDocsFromPath(getPathByOS(pathS));
        listData.add(countDocs);
        listData.add(countPages);
        countDocs = 0;
        countPages = 0;
        return listData;
    }

    @Override
    public String getInfoAboutFilesByPath(String path) throws IOException {
        List<Integer> listData = getDataFromPath(path);
        return "Documents: "+ listData.get(numberDocs)+"\n" +
                "Pages: "+ listData.get(numberPages)+"\n";
    }

    private void handlerDocsFromPath(Path path) throws IOException {
        File dir = new File(path.toString());
        if(dir.isDirectory()&& Optional.of(dir.listFiles()).isPresent()) {
            for(File item : Objects.requireNonNull(dir.listFiles())){
                if(item.isDirectory()){
                    handlerDocsFromPath(item.toPath());
                }
                else{
                    countDocs++;
                    countPages = countPages + utils.getCountPagesFromDocument(item);
                }
            }
        }
    }

    private Path getPathByOS(String pathS){
        String os = System.getProperty("os.name");
        return switch(os){
            case("Windows 10")->  Paths.get(pathS.trim().replaceAll("\t", "/"));
            default -> Paths.get(pathS);
        };
    }
}
