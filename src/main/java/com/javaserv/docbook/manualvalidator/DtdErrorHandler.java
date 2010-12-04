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
	static public final String LF = " +++ ";
	
	public DtdErrorHandler(){
	}

	public void warning(SAXParseException ex) throws SAXException {
		System.out.println ("[Warning]"
				+ getDetails(ex));
	}

	public void error(SAXParseException ex) throws SAXException {
		System.out.println("[Error]"
				+ getDetails(ex));
	}

	public void fatalError(SAXParseException ex) throws SAXException {
		System.out.println("[FatalError]"
				+ getDetails(ex));
	}

	private String getDetails(SAXParseException ex) {
		StringBuffer content = new StringBuffer();
		content.append("Line number=");
		content.append(ex.getLineNumber());
		content.append(LF);

		content.append("Column number=");
		content.append(ex.getColumnNumber());
		content.append(LF);

		content.append("Message=");
		content.append(ex.getMessage());
		content.append(LF);

		content.append("Localized Message=");
		content.append(ex.getLocalizedMessage());
		content.append(LF);
		
		content.append("Public ID=");
		content.append(ex.getPublicId());
		content.append(LF);

		content.append("System ID=");
		content.append(ex.getSystemId());
		content.append(LF);

		return content.toString();

	}

}
