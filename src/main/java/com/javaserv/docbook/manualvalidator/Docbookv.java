package com.javaserv.docbook.manualvalidator;

import org.json.JSONException;

/**
 * The class what contains the main method for the console run
 * DocBook versions: http://www.oasis-open.org/docbook/xml/
 * 
 * @author Daniel Bruessler <mail@danielbruessler.de>
 * @since 0.1.0
 */
@SuppressWarnings("unused")
public class Docbookv {
	static public String VERSION = "0.5.2";
	static public String VERSION_DESCRIPTION = "Validation of XML-manuals against dtd, what is declared as DOCTYPE in the file";

	public static void main(String[] args){
		Controller userInfo = new Controller(args, false);
	}
}
