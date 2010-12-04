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
 * This validates the XML against the DTD on top of the XML-file
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.5.0
 */
@SuppressWarnings("unused")
public class DtdValidator {

	public String validate(String xmlFileName) {
		if (xmlFileName == null || xmlFileName.length() == 0) {
			return ("ERROR: missing the XML file");
		}
		boolean isValid = false;
		try {
			File x = new File(xmlFileName);
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			f.setValidating(true); // Default is false
			DocumentBuilder b = f.newDocumentBuilder();
			// ErrorHandler h = new DefaultHandler();
			ErrorHandler h = new DtdErrorHandler();
			b.setErrorHandler(h);
			Document d = b.parse(x);
			isValid = true;
		} catch (ParserConfigurationException e) {
			System.out.println(e.toString());
		} catch (SAXException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		if (!isValid) {
			return ("The manual is NOT valid.");
		}
		return ("OK, no error!");

	}
}
