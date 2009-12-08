/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.hotel;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import com.ptit.travel.moduleJDBC.Model.*;
import java.lang.*;
/**
 *
 * @author Administrator
 */
public class HotelProcess {

    
    public void searchbyId(String keyword, String keyword2) {
	
		
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();		
		Database.LoadOnt2Database();
		
		String queryString = null;
                        
                      queryString  = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
			+ "SELECT DISTINCT * \n "
			+ "WHERE \n"
			+ "{ \n"
			+ "?x hotel:hotelFacilities ?hotelFacilities. \n"
                        + "?x hotel:hotelFacilities ?hotelFacilities1. \n";
                  
                        
			                     
			

			
                
                if (keyword !=null){
			queryString = queryString + " FILTER regex(?hotelFacilities,\""
					+ keyword + "\", \"i\")\n";
                }
                   
               if (keyword2 !=null){
			queryString = queryString + " FILTER regex(?hotelFacilities1,\""
					+ keyword2 + "\", \"i\")\n";
                }
                    
                      
                      queryString = queryString + "}";
                      System.out.println("truy van:"+queryString);
	Query query = QueryFactory.create(queryString);	
	QueryExecution queryexec = QueryExecutionFactory.create(query, om);
	
	try {
		ResultSet rs = queryexec.execSelect();			
		while (rs.hasNext()) {
			Object obj = rs.next();						
				 ResultBinding binding = (ResultBinding) obj;
				System.out.println(binding.toString());
				

				
		}	
				
			
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
    
    public static void main(String s[]){
        HotelProcess hotelprocess = new HotelProcess();
        hotelprocess.searchbyId("Parking", "SwimmingPool");
    }
   
}
