package com.sungness.code.generate.util;

import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;

public class XmlUtil {
    public static String validXmlBySchema(String paramString1,
            String paramString2) {
        SchemaFactory localSchemaFactory = SchemaFactory
                .newInstance("http://www.w3.org/2001/XMLSchema");
        File localFile = new File(paramString1);
        Schema localSchema = null;
        try {
            localSchema = localSchemaFactory.newSchema(localFile);
        } catch (SAXException localSAXException) {
            localSAXException.printStackTrace();
        }
        Validator localValidator = localSchema.newValidator();
        StreamSource localStreamSource = new StreamSource(
                FileHelper.getInputStream(paramString2));
        try {
            localValidator.validate(localStreamSource);
        } catch (Exception localException) {
            return localException.getMessage();
        }
        return "";
    }
}
