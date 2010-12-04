package com.javaserv.docbook.manualvalidator;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;
import org.xml.sax.helpers.DefaultHandler;

/**
 * This handles the DTD errors (SAX parser)
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.5.0
 */
@SuppressWarnings("unused")
public class DtdErrorHandler implements ErrorHandler {
	
	public void warning(SAXParseException e) throws SAXException {
        System.out.println("[Warning] "); 
        printInfo(e);
     }
     public void error(SAXParseException e) throws SAXException {
        System.out.println("[Error]"); 
        printInfo(e);
     }
     public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("Fattal error: "); 
        printInfo(e);
     }
     private void printInfo(SAXParseException e) {
     	 System.out.println("   Public ID: "+e.getPublicId());
     	 System.out.println("   System ID: "+e.getSystemId());
     	 System.out.println("   Line number: "+e.getLineNumber());
     	 System.out.println("   Column number: "+e.getColumnNumber());
     	 System.out.println("   Message: "+e.getMessage());
     }

}
