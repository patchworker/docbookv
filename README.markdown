docbookv -- DocBook Validator 0.5.5
===================================

The aim of this tool is to provide detailed information of docbook-validation-errors. The tool will be used in a PHP 
online editor for docbook files, for that reason it has a mode to use JSON format. "YGWYM" means "You get what you mean".

Why Java for PHP
----------------

The build-in validator of PHP 5.x doesn't tell in which line an error occurs. The validators of Java give very detailed information about errors.

What can it validate, what is planned
-----------------------------------

- DocBook XML files until 4.x (because that have a DOCTYPE declaration)
- Every other XML or HTML file what has a DOCTYPE declaration in the first line
- planned: XML Schema validation for DocBook files 5.x
- planned: Relax NG validation for a subset of DocBook

Performance
-----------

In the moment the focus is not on performance but features, the validation-process will be much faster in 1.0.0

Usage examples
--------------

        /usr/bin/docbookv.sh help

        /usr/bin/docbookv.sh manual.xml

        /usr/bin/docbookv.sh --json manual.xml

