package com.javaserv.docbook.manualvalidator;

/**
 * Encapsulates the info for the validation job
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.3.0
 */
public class JobData {
	static public final int HELP = 10;
	static public final int NO_ARGUMENTS = 20;
	static public final int NO_FILENAME = 30;
	static public final int VALIDATION_ERROR = 100;

	String command = "";
	String manualFilename = "";
	String validateType = "analyse";
	boolean jsonMode = false;
	int error = 0;
	String errorMessage = "";
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public String getManualFilename() {
		return manualFilename;
	}
	public void setManualFilename(String manualFilename) {
		this.manualFilename = manualFilename;
	}
	public String getValidateType() {
		return validateType;
	}
	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}
	public boolean isJsonMode() {
		return jsonMode;
	}
	public void setJsonMode(boolean jsonMode) {
		this.jsonMode = jsonMode;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
