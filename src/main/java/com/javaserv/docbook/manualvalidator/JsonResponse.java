package com.javaserv.docbook.manualvalidator;

import org.json.JSONStringer;
import org.json.JSONException;

/**
 * Encapsulates the output of JSON data
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.3.0
 */
public class JsonResponse {
	static public final int HEADER = 200;
	static public final int FILES_INFO = 210;

	static public final String LF = " +++ "; 
	
	private JSONStringer content = null;

	public JsonResponse() {
	}
	
	public String render(JobData jobData) {
		try {
			content = new JSONStringer();
			content.object();
			
			add(HEADER, jobData);
			if(jobData == null) {
				jobData = new JobData();
				jobData.setError(JobData.MISSING_JOB_DATA);
				add(JobData.VALID_STATUS, jobData);
				content.endObject();
				return content.toString();
			}
			if(jobData.getError() == 0) {
				add(JobData.VALID_STATUS, jobData);
				add(FILES_INFO, jobData); // as debug-info
				content.endObject();
				return content.toString();
			}
			else if(jobData.getError() == JobData.HELP) {
				add(JobData.HELP, jobData);
				content.endObject();
				return content.toString();
			}
			add(JobData.VALID_STATUS, jobData);
			add(jobData.getError(), jobData);
			if(jobData.getError() != JobData.VALIDATION_ERROR) {
				add(JobData.HELP, jobData);
			}
			
			content.endObject();
			return content.toString();
		} catch (JSONException ex) {
			return "{'ERROR':'JSON-Rendering is not possible. " + ex.getMessage() + "'}";
		}
		
	}

	/**
	 * additional modes:
	 * - 200 show the header with version-info
	 * - 210 show the info about filename and co
	 * @param mode
	 * @param args
	 * @throws JSONException 
	 */
	public void add(int mode, JobData jobData) throws JSONException {
		switch (mode) {
			case HEADER: {
				content.key("version").value(Docbookv.VERSION);
				break;
			}
			case JobData.NO_ARGUMENTS: {				
				content.key("ERROR").value("No data given. The XMl must be compressed as gzip and sent by POST");
				break;
			}
			case FILES_INFO: {
				String debugInfo = "DocBook manual="
					  + jobData.getManualFilename();
				if (jobData.getValidateType().length() > 0) {
					debugInfo += LF + "Validate type=" + jobData.getValidateType();
				}
				content.key("debug-info").value(debugInfo);
				break;
			}
			case JobData.MISSING_JOB_DATA: {
				content.key("ERROR").value("VerboseInfo needs jobData");
				break;
			}
			case JobData.VALIDATION_ERROR: {
				content.key("ERROR").value(jobData.getErrorMessage());
				break;
			}
			case JobData.VALID_STATUS: {
				if(jobData.getError() == 0) {
					content.key("valid").value(true);
					return;
				}
				content.key("valid").value(false);
				break;
			}
			case JobData.HELP:
			default: {
				StringBuffer help = new StringBuffer();
				String command = jobData.getCommand();
				if(command == null || command.length() == 0) command = "docbookv";
				help.append("usage examples: " + LF);
				help.append(command + " help" + LF);
				help.append(command + " manual.xml" + LF);
				help.append(command
						+ " --json manual.xml" + LF);
				help.append(command
						+ " manual.xml docbook-5.0" + LF);
				content.key("HELP").value(help.toString());
			}
		}
	}
}
