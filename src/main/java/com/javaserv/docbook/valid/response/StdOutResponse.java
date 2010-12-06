package com.javaserv.docbook.valid.response;

import java.util.Calendar;

import com.javaserv.docbook.valid.Docbookv;
import com.javaserv.docbook.valid.JobData;

/**
 * Encapsulates the direct text-output to stdout
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.2.0
 */
public class StdOutResponse extends AbstractResponse {
	private StringBuffer content;
	
	public StdOutResponse() {
		super();
	}
	
	public String render(JobData jobData) {
		content = new StringBuffer();
		add(HEADER, jobData);
		if(jobData == null) {
			jobData = new JobData();
			jobData.setTimeMillisStart(Calendar.getInstance().getTimeInMillis());
			jobData.setError(JobData.MISSING_JOB_DATA);
			add(JobData.MISSING_JOB_DATA, null);
			return content.toString();
		}
		if(jobData.getError() == 0) {
			add(JobData.VALID_STATUS, jobData);
			add(JobData.STATISTICS_INFO, jobData);
			return content.toString();
		}
		else if(jobData.getError() == JobData.HELP) {
			add(JobData.HELP, jobData);
			return content.toString();
		}
		content.append(" ");
		content.append(LF);
		add(JobData.VALID_STATUS, jobData);
		add(JobData.STATISTICS_INFO, jobData);
		add(jobData.getError(), jobData);
		if(jobData.getError() != JobData.VALIDATION_ERROR) {
			add(JobData.HELP, jobData);
		}
		return content.toString();
		
	}

	/**
	 * additional modes:
	 * - 200 show the header with version-info
	 * - 210 show the info about filename and co
	 * @param mode
	 * @param args
	 */
	public void add(int mode, JobData jobData) {
		switch (mode) {
			case HEADER: {
				content.append("DocBook validator " + Docbookv.VERSION);
				content.append(LF);
				content.append("("+'"' + Docbookv.VERSION_DESCRIPTION + '"'+")");
				content.append(LF);
				content.append("Contact: Daniel Bruessler <mail@danielbruessler.de>");
				content.append(LF);
				content.append("Issues:  https://github.com/patchworker/docbookv");
				content.append(LF);
				content.append("verbose-mode");
				content.append(LF);
				break;
			}
			case JobData.NO_ARGUMENTS: {
				content.append(" ");
				content.append(LF);
				content.append("No file given. usage as a command-script with these arguments:");
				content.append(LF);
				content.append("java -jar docbookv.jar $0 $1 $2");
				content.append(LF);
				break;
			}
			case JobData.NO_FILENAME: {
				content.append(" ");
				content.append(LF);
				content.append("No file given.");
				content.append(LF);
				break;
			}
			case FILES_INFO: {
				content.append(" ");
				content.append(LF);
				content.append("DocBook manual: " + jobData.getManualFilename());
				if (jobData.getValidateType().length() > 0) {
					content.append("Validate type:  " + jobData.getValidateType());
					content.append(LF);
				}
				break;
			}
			case JobData.MISSING_JOB_DATA: {
				content.append("ERROR: StdOutResponse needs jobData");
				content.append(LF);
				break;
			}
			case JobData.VALIDATION_ERROR: {
				content.append(jobData.getErrorMessage());
				content.append(LF);
				break;
			}
			case JobData.VALID_STATUS: {
				if(jobData.getError() == 0) {
					content.append("OK, the manual is valid!");
					content.append(LF);
					return;
				}
				content.append("FAILED. The manual contains errors (or there are warnings).");
				content.append(LF);
				break;
			}
			case JobData.STATISTICS_INFO: {
				content.append("(statistics: " + jobData.getStatistics() + ")");
				content.append(LF);
				break;
			}
			case JobData.HELP:
			default: {
				String command = jobData.getCommand();
				if(command == null || command.length() == 0) command = "docbookv.sh/docbook.bat";
				content.append(" ");
				content.append(LF);
				content.append("== usage examples ==");
				content.append(LF);
				content.append(command + " help");
				content.append(LF);
				content.append(command + " manual.xml");
				content.append(LF);
				content.append(command);
				content.append(" --json manual.xml");
				content.append(LF);
				content.append(" ");
				content.append(LF);
			}
		}
	}
}
