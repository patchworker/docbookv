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
		boolean isValid = true;
		String errors = "";
		try {
			File xmlFile = new File(xmlFileName);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			ErrorHandler errorHandler = new DtdErrorHandler();
			DtdErrorHandler.setValid(true);
			DtdErrorHandler.setErrors("");
			documentBuilder.setErrorHandler(errorHandler);
			Document dTdDocument = documentBuilder.parse(xmlFile);
		} catch (ParserConfigurationException ex) {
			isValid = false;
			errors += "[Fatal Error] ParserConfiguration not correct. " + ex.toString();
		} catch (SAXException ex) {
			isValid = false;
			errors += "[Fatal Error] The SAX-parser cannot parse the document. " + ex.toString();
		} catch (IOException ex) {
			isValid = false;
			errors += "[Fatal Error] Filesystem problem, the file is not accessable. " + ex.toString();
		}
		
		if (!isValid || !DtdErrorHandler.isValid) {
			return ("\r\n" + errors + DtdErrorHandler.getErrors() + "\r\n" + "The manual is NOT valid.");
		}
		return ("OK");

	}
}
