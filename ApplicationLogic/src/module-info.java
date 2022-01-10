
/**
 * This module is used by the main application to provide functionality 
 * to the user interface and deal with database management.
 * 
 * @author David Bogdan
 * @version 1.0, 05 Nov 2021
 */
module functionals {
	exports functionals.validators;
	exports functionals.handlers;
	exports functionals.containers;
	
	requires java.sql;
	requires transitive java.desktop;
}