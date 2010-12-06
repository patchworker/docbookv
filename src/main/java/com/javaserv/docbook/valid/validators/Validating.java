package com.javaserv.docbook.valid.validators;

import com.javaserv.docbook.valid.JobData;

/**
 * This interface makes adding more validators easy
 * 
 * that methods on JobData can be used for the results:
 * - jobData.setError(JobData.MISSING_JOB_DATA);
 * - jobData.setError(JobData.VALIDATION_ERROR);
 * - jobData.setErrorMessage("ERROR: missing node abcdef in line 123");
 * - jobData.addStatistics("default-SAX/Java"+System.getProperty("java.version"));
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.6.0
 */
public interface Validating {

	public abstract boolean validate(String xmlFileName, JobData jobData);

}