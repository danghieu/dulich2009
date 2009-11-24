import jena.query;

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
public class TruyVan extends Object{

	 
    static final String inputFileName = "family.swrl.owl";
    
    public static void main (String args[]) {
        // create an empty model
        Model model = ModelFactory.createDefaultModel();
       
        // use the FileManager to find the input file
        InputStream in = FileManager.get().open(inputFileName);
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read( in, "");  
        model.write(System.out);   	        
	       /*   Query query = QueryFactory.create( "PREFIX dc: <http://purl.org/dc/elements/1.1/> " +           
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
	       Query query1 = QueryFactory.create( "SELECT ?givenName WHERE{ ?y  <http://www.w3.org/2001/vcard-rdf/3.0#Family>  'Smith' .?y  <http://www.w3.org/2001/vcard-rdf/3.0#Given>  ?givenName }");
	        

	        Query query=QueryFactory.create("PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#> SELECT ?y ?givenName WHERE { ?y vcard:Family 'Smith' . ?y vcard:Given  ?givenName }");
	        
	         QueryExecution qexec = QueryExecutionFactory.create( query, model );
	        try {

                ResultSet rs = qexec.execSelect(); 

                for ( ; rs.hasNext() ; )  {

                      QuerySolution rb = rs.nextSolution() ;

                  
                   RDFNode x = rb.get( "givenName" ) ;
                  
                 

                        if ( x.isLiteral())  {

                            Literal titleStr = ( Literal )x  ;
                     

                                     System.out.println( " family:   " + titleStr+"  "+rb.getLiteral("givenName"));
                                     System.out.println( " family:   " + titleStr+"  "+rb.getResource("y").toString());

                    }

                      }
	        }
	        finally
	          {
	                  qexec.close() ;
	          }     
        
	        
	        
	        
	        
*/

        
        //Query query=QueryFactory.create("PREFIX vcard:<http://www.w3.org/2001/vcard-rdf/3.0#> SELECT ?y ?givenName WHERE { ?y vcard:Family 'Smith' . ?y vcard:Given  ?givenName }");
  	     //Query query=QueryFactory.create("SELECT ?y  WHERE { ?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
        
        model.getProperty("http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece").
	    }
}

	        