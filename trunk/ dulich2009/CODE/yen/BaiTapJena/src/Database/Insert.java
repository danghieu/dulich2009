package Database;
import jena.query;
import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;
import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;

import java.io.*;
public class Insert extends Object {
		 //  static final String inputFileName  = "family.swrl.owl";
		    public static final String DB_URL = "jdbc:mysql://localhost/hai";
		    public static final String DB_USER = "root";
		    public static final String DB_PASSWD = "1";
		    public static final String DB = "MySQL";
		    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

		    // Static variables
		    //////////////////////////////////

		    // database connection parameters, with defaults
		    private static String s_dbURL = DB_URL;
		    private static String s_dbUser = DB_USER;
		    private static String s_dbPw = DB_PASSWD;
		    private static String s_dbType = DB;
		    private static String s_dbDriver = DB_DRIVER;

		    // if true, reload the data
		    private static boolean s_reload = false;

		    // source URL to load data from; if null, use default
		    static String s_source;                       
		    public static void main (String args[])throws Exception {
		  //	  Property hasFather = null;
		        // create an empty model   	
    Model model = ModelFactory.createDefaultModel();

    InputStream in = FileManager.get().open( inputFileName );
    
    if (in == null) {
        throw new IllegalArgumentException( "File: " + inputFileName + " not found");
    }
    
    // read the RDF/XML file
    model.read(in, "");
                
    // write it to standard out
    model.write(System.out);   
    
    
  