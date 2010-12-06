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

import com.javaserv.docbook.valid.JobData;

/**
 * This validates the XML against the DTD on top of the XML-file
 * 
 * The internal xerces-implementation of Java6 is in use
 * (e.g. org.apache.xerces.xni.parser, org.apache.xerces.parsers.DOMParser)
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.5.0
 */
@SuppressWarnings("unused")
public class DtdJavaInternalValidator implements Validating {

	/* (non-Javadoc)
	 * @see com.javaserv.docbook.valid.validators.Validating#validate(java.lang.String, com.javaserv.docbook.valid.JobData)
	 */
	@Override
	public boolean validate(String xmlFileName, JobData jobData) {
		if(jobData == null) {
			jobData = new JobData();
			jobData.setError(JobData.MISSING_JOB_DATA);
			return false;
		}
		if (xmlFileName == null || xmlFileName.length() == 0) {
			jobData.setError(JobData.VALIDATION_ERROR);
			jobData.setErrorMessage("ERROR: missing the XML file");
		}
		jobData.addStatistics("default-SAX/Java-" + System.getProperty("java.vm.vendor") + "/" + System.getProperty("sun.arch.data.model") + "bit/" + System.getProperty("java.version"));
		boolean isValid = true;
		String errors = "";
		try {
			File xmlFile = new File(xmlFileName);
			jobData.addStatisticsRounded(xmlFile.length() / 1000, 1, "kB");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder documentBuilder = factory.newDocumentBuilder();
			ErrorHandler errorHandler = new SaxParserErrorHandler();
			SaxParserErrorHandler.setValid(true);
			SaxParserErrorHandler.setErrors("");
			documentBuilder.setErrorHandler(errorHandler);
			Document dTdDocument = documentBuilder.parse(xmlFile);
		} catch (ParserConfigurationException ex) {
			isValid = false;
			errors += "[Fatal Error] ParserConfiguration not correct. " + ex.toString() + SaxParserErrorHandler.LF + SaxParserErrorHandler.LF;
		} catch (SAXException ex) {
			isValid = false;
			errors += "[Fatal Error] The SAX-parser cannot parse the document. " + ex.toString() + SaxParserErrorHandler.LF + SaxParserErrorHandler.LF;
		} catch (IOException ex) {
			isValid = false;
			errors += "[Fatal Error] Filesystem problem, the file is not accessable. " + ex.toString() + SaxParserErrorHandler.LF + SaxParserErrorHandler.LF;
		}
		if(!isValid || !SaxParserErrorHandler.isValid) {
			jobData.setError(JobData.VALIDATION_ERROR);
			jobData.setErrorMessage(errors + SaxParserErrorHandler.getErrors());
			return false;
		}
		
		return true;

	}
}
