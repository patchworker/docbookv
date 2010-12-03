package com.javaserv.docbook.manualvalidator;

/**
 * DocBook versions: http://www.oasis-open.org/docbook/xml/
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.3.0
 */
public class Controller {

	public Controller(String[] args) {
		JobData jobData = parseArguments(args);
		
		if (!jobData.isJsonMode()) {
			//VerboseInfo printInfo = new VerboseInfo(jobData);
			//return;
		}
		JsonResponse renderer = new JsonResponse();
		String output = renderer.render(jobData);
		if(output != null && output.length() > 0) {
			System.out.print(output);
			return;
		}
		System.out.println("{'ERROR':'the JSON library is not available'}");
	}
	
	public JobData parseArguments(String[] args) {
		JobData jobData = new JobData();
		
		// command e.g "docbookv.sh"
		int pointer = 0;
		if (args == null || args.length == 0) {
			jobData.setError(JobData.NO_ARGUMENTS);
			return jobData;
		}
		jobData.setCommand(args[0]);
		
		// help or --json
		pointer++;
		if (pointer > args.length -1) {
			jobData.setError(JobData.NO_FILENAME);
			return jobData;
		}
		if ("help".equals(args[pointer])) {
			jobData.setError(JobData.HELP);
			return jobData;
		}
		else if ("--json".equals(args[pointer])) {
			jobData.setJsonMode(true);
			pointer++;
		}
		
		// filename
		if (pointer > args.length -1) {
			jobData.setError(JobData.NO_FILENAME);
			return jobData;
		} else{
			jobData.setManualFilename(args[pointer]);
		}
		
		// type
		pointer++;
		if (pointer == args.length -1) {
			jobData.setValidateType(args[pointer]);
		}
		
		return jobData;
	}
}
