package com.example.countdocs.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

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
    void getCountPagesFromPDF(@Value("${test.path.pdf}") String path, @Value("${pages.count.pdf}") int countDocs) throws IOException {
        int pagesCount =  getCountPagesFromDocument(new File(path));
        assertEquals(countDocs,pagesCount);
    }

    @Test
    @DisplayName(" Проверка получения количества страниц из docx документа.")
    void getCountPagesFromDOCX(@Value("${test.path.docx}") String path, @Value("${pages.count.docx}") int countDocs) throws IOException {
        int pagesCount =  getCountPagesFromDocument(new File("./src/test/resources/testData/1.docx"));
        assertEquals(countDocs,pagesCount);
    }

    @Test
    @DisplayName(" Проверка получения количества страниц из txt документа.")
    void getCountPagesFromTXT(@Value("${test.path.txt}") String path, @Value("${pages.count.txt}") int countDocs) throws IOException {
        int pagesCount =  getCountPagesFromDocument(new File("./src/test/resources/testData/1.txt"));
        assertEquals(countDocs,pagesCount);
    }

    int getCountPagesFromDocument(File file) throws IOException {
        return utils.getCountPagesFromDocument(file);
    }
    @Test
    @DisplayName("1")
    void getFiles(@Value("${test.path.txt}") String path) throws IOException {
        byte [] bytes = FileUtils.readFileToByteArray(new File(path));
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
  OutputStream out = new FileOutputStream("src/test/resources/testData/2.doc");

// Transfer bytes from in to out
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
        XWPFDocument document = new XWPFDocument(new FileInputStream("src/test/resources/testData/2.doc"));
     //  XWPFDocument document = new XWPFDocument(fileIn);
        int pages = document.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        assertEquals(1,pages );


    }

    @Test
    @DisplayName("1")
    void DocPrintJob(@Value("${test.path.docx}") String path) throws IOException {

        BufferedReader input = null;
        Document output = null;

        try {

            input =
                    new BufferedReader (new FileReader("src/test/resources/testData/1.fb2"));
            // letter 8.5x11
            //    see com.lowagie.text.PageSize for a complete list of page-size constants.
            output = new Document(PageSize.LETTER, 40, 40, 40, 40);
            // pdf file as args[1]
            PdfWriter.getInstance(output, new FileOutputStream ("src/test/resources/testData/2.pdf"));

            output.open();
          //  output.addAuthor("RealHowTo");
            output.addSubject(path);
         //   output.addTitle(path);

            String line = "";
            while(null != (line = input.readLine())) {
               // System.out.println(line);
                Paragraph p = new Paragraph(line);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                output.add(p);
            }
            System.out.println("Done.");
            output.close();
            input.close();
            System.out.println(getCountPagesFromDocument(new File("src/test/resources/testData/2.pdf" )));
         //   System.exit(0);
        }
        catch (Exception e) {
            e.printStackTrace();
         //   System.exit(1);
        }
        System.out.println(getCountPagesFromDocument(new File("src/test/resources/testData/2.pdf" )));
    }

    @Test
    @DisplayName("1")
    void getFilesScanner(@Value("${test.path.txt}") String path) throws IOException {
        FileWriter writer = new FileWriter("src/test/resources/testData/2.pdf");
        Scanner sc = new Scanner(new File("src/test/resources/testData/1.txt"));


        while(sc.hasNext()){
            writer.write(sc.nextLine());
        }


     /*   for(Neews news : list {
            String name = news.getName();
            String family = news.getFamily();
            writer.write(name + " " + family + System.getProperty("line.separator"));
        }*/
        writer.close();
        System.out.println(getCountPagesFromDocument(new File("src/test/resources/testData/2.doc" )));
    }

}