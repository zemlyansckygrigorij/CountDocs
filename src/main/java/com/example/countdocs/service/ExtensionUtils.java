package com.example.countdocs.service;

import com.itextpdf.text.*;
import java.io.*;
import java.util.Optional;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtils for handle documents by extension
 * */
@Component
public class ExtensionUtils {
    @Value("${margin.left}")
    private float marginLeft;
    @Value("${margin.right}")
    private float marginReft;
    @Value("${margin.top}")
    private float marginTop;
    @Value("${margin.bottom}")
    private float marginBottom;

    private final String pathTemp;
    public ExtensionUtils(@Value("${path.temp}")  String pathTemp){
        this.pathTemp= pathTemp;
    }
    public int getCountPagesFromTempDocument(File file) throws IOException{
        int result = 0;
        if(getExtensionByApacheCommonLib(file.getName()).equals("pdf")){
            PdfReader reader = new PdfReader(file.getPath());
            result = reader.getNumberOfPages();
            reader.close();
            return result;
        }

        Document output = null;
        PdfWriter writer = null;

        try(
                BufferedReader input = new BufferedReader(new FileReader(file))
        ) {
            String fileName = pathTemp + file.getName().replace(getExtensionByApacheCommonLib(file.getName()), "") + "pdf";
            output = new Document(PageSize.LETTER, marginLeft, marginReft, marginTop, marginBottom);
            writer = PdfWriter.getInstance(output, new FileOutputStream(fileName));
            output.open();


            // letter 8.5x11
            //    see com.lowagie.text.PageSize for a complete list of page-size constants.

            output.addSubject(file.getPath());

            String line ;
            while (null != (line = input.readLine())) {
                Paragraph p = new Paragraph(line);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                output.add(p);
            }
            result = writer.getPageNumber();
            output.close();
            input.close();
            writer.close();
            File fileTemp = new File(fileName);
            fileTemp.delete();
        }catch (DocumentException ex){
            ex.printStackTrace();
        }finally{
            assert output != null;
            Optional.of(output).orElseThrow().close();
            assert writer != null;
            Optional.of(writer).orElseThrow().close();
        }
        return result;
    }

    public String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
}
