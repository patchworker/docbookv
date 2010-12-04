package com.javaserv.docbook.manualvalidator;

/**
 * DocBook versions: http://www.oasis-open.org/docbook/xml/
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.2.0
 */
public class VerboseInfo {
	
	static public final int HEADER = 200;
	static public final int FILES_INFO = 210;
	
	public VerboseInfo() {
	}
	
	public void render(JobData jobData) {
		printHelpText(HEADER, jobData);
		if(jobData == null) {
			jobData = new JobData();
			jobData.setError(JobData.MISSING_JOB_DATA);
			printHelpText(JobData.MISSING_JOB_DATA, null);
			return;
		}
		if(jobData.getError() == 0) {
			printHelpText(JobData.VALID_STATUS, jobData);
			printHelpText(FILES_INFO, jobData);
			return;
		}
		else if(jobData.getError() == JobData.HELP) {
			printHelpText(JobData.HELP, jobData);
			return;
		}
		System.out.println(" ");
		printHelpText(JobData.VALID_STATUS, jobData);
		printHelpText(jobData.getError(), jobData);
		if(jobData.getError() != JobData.VALIDATION_ERROR) {
			printHelpText(JobData.HELP, jobData);
		}
		
	}

	/**
	 * additional modes:
	 * - 200 show the header with version-info
	 * - 210 show the info about filename and co
	 * @param mode
	 * @param args
	 */
	public void printHelpText(int mode, JobData jobData) {
		switch (mode) {
			case HEADER: {
				System.out.println("DocBook validator " + Docbookv.VERSION);
				System.out.println("("+'"' + Docbookv.VERSION_DESCRIPTION + '"'+")");
				System.out.println("Contact (feature-requests, bug-reports): Daniel Bruessler <mail@danielbruessler.de>");
				System.out.println("verbose-mode");
				break;
			}
			case JobData.NO_ARGUMENTS: {
				System.out.println(" ");
				System.out
						.println("No file given. usage as a command-script with these arguments:");
				System.out.println("java -jar docbookv.jar $0 $1 $2");
				break;
			}
			case JobData.NO_FILENAME: {
				System.out.println(" ");
				System.out.println("No file given.");
				break;
			}
			case FILES_INFO: {
				System.out.println(" ");
				System.out.println("DocBook manual: " + jobData.getManualFilename());
				if (jobData.getValidateType().length() > 0) {
					System.out.println("Validate type:  " + jobData.getValidateType());
				}
				break;
			}
			case JobData.MISSING_JOB_DATA: {
				System.out.println("ERROR: VerboseInfo needs jobData");
				break;
			}
			case JobData.VALIDATION_ERROR: {
				System.out.println(jobData.getErrorMessage());
				break;
			}
			case JobData.VALID_STATUS: {
				if(jobData.getError() == 0) {
					System.out.println("OK, the manual is valid!");
					return;
				}
				System.out.println("FAILED. The manual contains errors (or there are warnings).");
				break;
			}
			case JobData.HELP:
			default: {
				String command = jobData.getCommand();
				if(command == null || command.length() == 0) command = "docbookv.sh/docbook.bat";
				System.out.println(" ");
				System.out.println("== usage examples ==");
				System.out.println(command + " help");
				System.out.println(command + " manual.xml");
				System.out.println(command
						+ " --json manual.xml");
				System.out.println(command
						+ " manual.xml docbook-5.0");
				System.out.println(" ");
			}
		}
	}
}
