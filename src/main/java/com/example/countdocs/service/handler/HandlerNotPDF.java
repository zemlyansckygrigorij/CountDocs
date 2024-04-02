package com.example.countdocs.service.handler;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Value;
import java.io.*;

public class HandlerNotPDF implements Handler{
    @Value("${margin.left}")
    private float marginLeft;
    @Value("${margin.right}")
    private float marginReft;
    @Value("${margin.top}")
    private float marginTop;
    @Value("${margin.bottom}")
    private float marginBottom;
    private final BufferedReader reader ;
    private int countPages = 0;
    private final String fileName;
    private final Document output;
    private final
    PdfWriter writer;
    @Value("${path.temp}")  String pathTemp;
    public  HandlerNotPDF(File file) throws IOException, DocumentException {
        this.reader =  new BufferedReader(new FileReader(file));
        this.fileName = pathTemp + file.getName() + ".pdf";
        this.output = new Document(PageSize.LETTER, marginLeft, marginReft, marginTop, marginBottom);
        this.writer = PdfWriter.getInstance(output, new BufferedOutputStream(new FileOutputStream(fileName)));
        createTempFile();
    }
    private void createTempFile() throws IOException, DocumentException {
        output.open();
        // letter 8.5x11
        //    see com.lowagie.text.PageSize for a complete list of page-size constants.
        output.addSubject(fileName);
        String line ;
        while (null != (line = reader.readLine())) {
            Paragraph p = new Paragraph(line);
            p.setAlignment(Element.ALIGN_JUSTIFIED);
            output.add(p);
        }
        countPages = writer.getPageNumber();
    }

    @Override
    public int getCountPages(){
        return countPages;
    }

    @Override
    public void close() throws IOException {
        output.close();
        reader.close();
        writer.close();
        File fileTemp = new File(fileName);
        fileTemp.delete();
    }
}
