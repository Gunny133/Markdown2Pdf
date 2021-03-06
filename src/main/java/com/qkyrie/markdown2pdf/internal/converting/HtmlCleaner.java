package com.qkyrie.markdown2pdf.internal.converting;

import com.qkyrie.markdown2pdf.internal.exceptions.ConversionException;
import org.w3c.tidy.Tidy;

import java.io.*;

/**
 * User: Quinten
 * Date: 31-3-2014
 * Time: 09:44
 *
 * @author Quinten De Swaef
 */
public class HtmlCleaner {
    public String clean(String input) throws ConversionException {
        InputStream stringAsStream;
        try {
            stringAsStream = new ByteArrayInputStream(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw ConversionException.HTML_TO_PDF_EXCEPTION;
        }
        ByteArrayOutputStream outputAsStream = new ByteArrayOutputStream();
        Tidy htmlCleaner = new Tidy();
        htmlCleaner.setXHTML(true);
        htmlCleaner.parse(stringAsStream, outputAsStream);

        try {
            String result = outputAsStream.toString("UTF-8");
            return result.trim();
        } catch (UnsupportedEncodingException e) {
            throw ConversionException.HTML_TO_PDF_EXCEPTION;
        }
    }
}
