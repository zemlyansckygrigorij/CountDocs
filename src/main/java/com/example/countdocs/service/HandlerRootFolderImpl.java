package com.example.countdocs.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


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
    private final Set<String> extensions;
    public HandlerRootFolderImpl(@Value("${kind.docs}")  String extensions){
         this.extensions = new HashSet<>(Arrays.asList(extensions.split(";")));
    }
    @Override
    public List<Integer> getDataFromPath(String pathS) throws IOException, DocumentException {
        List<Integer> listData = new ArrayList<>();
        handlerDocsFromPath(getPath(pathS));
        listData.add(countDocs);
        listData.add(countPages);
        countDocs = 0;
        countPages = 0;
        return listData;
    }

    @Override
    public String getInfoAboutFilesByPath(String path)  {
        try{
            List<Integer> listData = getDataFromPath(path);
            return "Documents: "+ listData.get(numberDocs)+"\n" +
                    "Pages: "+ listData.get(numberPages)+"\n";
        }catch(IOException ex){
            return "Невозможно обработать данные.";
        } catch (DocumentException e) {
            return "Невозможно обработать документы";
        } catch (Exception e) {
            return "Проверьте введеные данные";
        }
    }

    private void handlerDocsFromPath(Path path) throws IOException, DocumentException {
        File dir = new File(path.toString());
        if(dir.isDirectory()&& Optional.of(dir.listFiles()).isPresent()) {
            for(File item : Objects.requireNonNull(dir.listFiles())){
                if(item.isDirectory()){
                    handlerDocsFromPath(item.toPath());
                }
                else{
                    countDocs++;
                    String extension = utils.getExtensionByApacheCommonLib(item.getName());
                    if(extensions.contains(extension)){
                       countPages = countPages + utils.getCountPagesByDocument(item);
                    }
                }
            }
        }
    }

    private Path getPath(String pathS){
        String homePath = FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");
        String path = pathS.replaceAll("\\\\","/");
        if(path.contains(homePath))  return Paths.get(path);
        return Paths.get(homePath +"/"+ path);
    }
}
