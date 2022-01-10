
/**
 * This module exports packages used by the main application class
 * to construct the graphics of the user interface.
 * 
 * @author David Bogdan
 * @version 1.0, 15 Nov 2021
 */
module graphics {
	exports graphics.window;
	
	requires transitive java.desktop;
	requires functionals;
	requires java.sql;
	requires org.json;
	requires itextpdf;
}