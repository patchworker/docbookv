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
	static public String VERSION = "0.4.0";
	static public String VERSION_DESCRIPTION = "in pom.xml maven-compiler-plugin update so that JDK6 is in use instead of 1.3 and jar-dependencies are really in use";

	public static void main(String[] args){
		Controller userInfo = new Controller(args, false);
	}
}
