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


public class SimpleQuery extends Object{

	   static final String inputFileName  = "family.swrl.owl";
	  /*  public static final String DB_URL = "jdbc:mysql://localhost/hai";
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
	    private static String s_source;                       
	    public static void main (String args[])throws Exception {
	  //	  Property hasFather = null;
	        // create an empty model
	         * 
	         
	        Model model = ModelFactory.createDefaultModel();

	        InputStream in = FileManager.get().open( inputFileName );
	        
	        if (in == null) {
	            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
	        }
	        
	        // read the RDF/XML file
	        model.read(in, "");
	                    
	        // write it to standard out
	        model.write(System.out);   
	        
	        
	      /*  
	        try {
	            Class.forName( s_dbDriver );
	        }
	        catch (Exception e) {
	            System.err.println( "Failed to load the driver for the database: " + e.getMessage() );
	            System.err.println( "Have you got the CLASSPATH set correctly?" );
	        }
	        	      
    //	Class.forName("com.mysql.jdbc.Driver");

    	DBConnection connection = new DBConnection(s_dbURL, s_dbUser, s_dbPw, s_dbType);
   

        // now we plug that base model into an ontology model that also uses
        // the given model maker to create storage for imported models
      
    	ModelMaker maker = ModelFactory.createModelRDBMaker(connection);

    	// Create a new model named "wordnet." Setting the second parameter to "true" causes an
    	// AlreadyExistsException to be thrown if the db already has a model with this name
    	Model wordnetModel = maker.createModel("wordnet",true);
    	model.begin();

    	// For each wordnet model . . .
    //	InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("family.swrl.owl");
    	model.read(in,null);

    	// Commit the database transaction
    	model.commit();
	        
	        
	        
	     
	        
	        
	        
	        
	         Query query = QueryFactory.create( "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +           
	                  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	                  "SELECT ?title from  ?x dc:description ?y.?y dc:title ?title ");//FILTER(?title = Jena - an RDF framework for Java)}");// . ?y dc:description ?z . ?z dc:title ?title }" );

	          Query query1 = QueryFactory.create( "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +           
	                  "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
	                  "SELECT ?title ?description WHERE {?a dc:title ?title }");// . ?y dc:description ?z . ?z dc:title ?title }" );
	          
	          QueryExecution qexec = QueryExecutionFactory.create( query, model );  

	          System.out.println( "Titles: " ) ;

	          try {

	                  ResultSet rs = qexec.execSelect(); 

	                  for ( ; rs.hasNext() ; )  {

	                        QuerySolution rb = rs.nextSolution() ;

	                     RDFNode x = rb.get( "title" ) ;
	                     RDFNode y = rb.get( "Description" ) ;
	                   

	                          if ( x.isLiteral())  {

	                              Literal titleStr = ( Literal )x  ;
	                              Literal decription = ( Literal )y  ;

	                                       System.out.println( " title:   " + titleStr +"     description: "+decription) ;

	                      }

	                        }

	          }

	          finally
	          {
	                  qexec.close() ;
	          }       

	        
	        
	        // kieu truy van khi biet gia tri
	     //  Query query1 = QueryFactory.create( "SELECT ?Father WHERE{?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasConsort>  'ConYeu'}");
	        
	     //   Query query=QueryFactory.create("PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#> SELECT ?y ?givenName WHERE { ?y vcard:Family 'Smith' . ?y vcard:Given  ?givenName }");
	  //   Query query=QueryFactory.create("SELECT ?y  WHERE { ?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
*/

    public static void main(String s[])throws Exception{

        // create an empty model
        
       
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
      /*  model.read( in, "");
        model.write(System.out);
        OntModel ont = ModelFactory.createOntologyModel();
        ont.read(in, ""); */
        
       // OntClass ontclass = ont.createClass("http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Father");
     
        
       Individual ind = family.getOntModel().createIndividual("http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#haiyen",family.Mother);
       ind.addProperty(family.hasSibling,"Th");
       ind.addProperty(family.hasChild, "Huong");
       ind.addProperty(family.hasNephew, "Handu");
       Query query=QueryFactory.create("SELECT ?y  WHERE { ?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasChild> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#Huong> }");
	   
	   System.out.println("b 1:");
	         QueryExecution qexec = QueryExecutionFactory.create( query, family.getOntModel() );
	         
	         try {

	        		System.out.println("in nao");
                ResultSet rs = qexec.execSelect(); 

                for ( ; rs.hasNext() ; )  {
               		System.out.println("b3");
                      QuerySolution rb = rs.nextSolution() ;
               
                       System.out.println( "Con:  "+rb.getResource("y").getLocalName());//toString());

                    }

                      
	        }
	       catch(Exception e)
	          {
	                  e.printStackTrace();
	          }     
                
	    


	    }
}

	        