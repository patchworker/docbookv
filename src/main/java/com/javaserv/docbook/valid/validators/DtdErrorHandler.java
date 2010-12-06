package com.javaserv.docbook.valid.validators;

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
	static public final String LF = "\r\n";
	static public final String SEPARATOR = " +++ ";
	static protected boolean isValid = false;
	static protected String errors = "";
	
	public DtdErrorHandler(){
	}

	protected static boolean isValid() {
		return isValid;
	}

	protected static void setValid(boolean isValid) {
		DtdErrorHandler.isValid = isValid;
	}

	protected static String getErrors() {
		return errors;
	}

	protected static void setErrors(String errors) {
		DtdErrorHandler.errors = errors;
	}

	public void warning(SAXParseException ex) throws SAXException {
		setValid(false);
		errors += "[Warning]"
				+ getDetails(ex);
	}

	public void error(SAXParseException ex) throws SAXException {
		setValid(false);
		errors += "[Error]"
				+ getDetails(ex);
	}

	public void fatalError(SAXParseException ex) throws SAXException {
		setValid(false);
		errors += "[FatalError]"
				+ getDetails(ex);
	}

	protected String getDetails(SAXParseException ex) {
		StringBuffer content = new StringBuffer();
		content.append("Line number=");
		content.append(ex.getLineNumber());
		content.append(SEPARATOR);

		content.append("Column number=");
		content.append(ex.getColumnNumber());
		content.append(SEPARATOR);

		content.append("Message=");
		content.append(ex.getMessage());
		content.append(SEPARATOR);
		
		content.append("Public ID=");
		content.append(ex.getPublicId());
		content.append(SEPARATOR);

		content.append("Filename=");
		content.append(ex.getSystemId());
		
		content.append(LF + LF);

		return content.toString();

	}

}
