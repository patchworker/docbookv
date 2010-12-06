package com.javaserv.docbook.valid;

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
	static public String VERSION = "0.6.0";
	static public String VERSION_DESCRIPTION = "Refactoring for multiple validators, still just DTD-validator against a DOCTYPE-declaration";

	public static void main(String[] args){
		Controller userInfo = new Controller(args, false);
	}
}
