package com.javaserv.docbook.valid.response;

import com.javaserv.docbook.valid.JobData;

/**
 * This abstract class is the base for the response-formats
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.6.0
 */
public abstract class AbstractResponse {

	public static final int HEADER = 200;
	public static final int FILES_INFO = 210;
	public static final String LF = System.getProperty("line.separator");
	public static final String SEPARATOR = " -- ";

	public AbstractResponse() {
	}

	public abstract String render(JobData jobData);

	public abstract void add(int mode, JobData jobData) throws Exception;
	
}