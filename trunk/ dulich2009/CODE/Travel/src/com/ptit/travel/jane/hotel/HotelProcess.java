/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.jane.hotel;
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
import com.hp.hpl.jena.rdf.model.*;
import java.io.FileOutputStream;
import java.lang.*;
/**
 *
 * @author Administrator
 */
public class HotelProcess {

    
    public Model searchbyId (boolean bar, boolean fitnescenter,boolean gardenCafe, boolean karaoke, boolean nightClub, String location,boolean meetingRoom, boolean restaurant ) {
	
		Database.LoadOnt2Database();
		Model om = Database.getOntologyModel();		
		Database.LoadOnt2Database();
		
		String queryString = null;
                        
                      queryString  = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
			+ "SELECT DISTINCT * \n "
			+ "WHERE \n"
                        + "{ \n"
                        +"?x hotel:hasHotelRoom ?meetingRoom. \n"
                        
                      
                        + "?x hotel:hasRestaurant ?restaurant. \n"
                        + "?restaurant hotel:RestaurantName ?ten.\n";
                       
			
			
			
                
                if (bar == true){
			queryString = queryString + "?x hotel:hotelActivities ?bar. \n"
                               + " FILTER regex(?bar,\""
					+ "Bar" + "\", \"i\")\n";
                }
                   
               if (fitnescenter == true){
			queryString = queryString + "?x hotel:hotelFacilities ?hotelFacilities1. \n"
                                +" FILTER regex(?hotelFacilities1,\""
					+ "FitnessCenter" + "\", \"i\")\n";
                }
                 
                if (gardenCafe == true){
			queryString = queryString + "?x hotel:hotelFacilities ?garden. \n"
                                +" FILTER regex(?garden,\""
					+ "GardenCafe" + "\", \"i\")\n";
                }
                 if (gardenCafe == true){
			queryString = queryString + "?x hotel:hotelActivities ?karaoke. \n"
                                +" FILTER regex(?karaoke,\""
					+ "Karaoke" + "\", \"i\")\n";
                }
                           
                if (location != null){
			queryString = queryString + "?x hotel:HotelLocation ?location. \n"
                                +" FILTER regex(?location,\""
					+ location + "\", \"i\")\n";
                }  
                      
               if (nightClub == true){
			queryString = queryString + "?x hotel:hotelActivities ?sauna. \n"
                                +" FILTER regex(?sauna,\""
					+ "Sauna" + "\", \"i\")\n";
                }        
         
           //    queryString = queryString + "?x hotel:hasMeetingRoom, ?meetingRoom. \n" ;                   
                         
            //    queryString = queryString + "?x hotel:hasRestaurant ?restaurant. \n";                    
                      
                queryString = queryString + "}";
           //     System.out.println("truy van:"+queryString);
                Query query = QueryFactory.create(queryString);	
                QueryExecution queryexec = QueryExecutionFactory.create(query, om);
                Model model = ModelFactory.createDefaultModel();
                try {
                    ResultSet rs = queryexec.execSelect();			
                    while (rs.hasNext()) {
                         model = rs.getResourceModel(); 
			Object obj = rs.next();						
				 ResultBinding binding = (ResultBinding) obj;
				System.out.println("truy 1:"+binding.toString());
                               
                                 String meeting = binding.getResource("restaurant").getLocalName().toString();
                                 if(meeting != null)
                                     System.out.print("dung" + meeting);                               

				
		}	
				
			
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
           return model;
    }
    
    
    
    
    public Model search2(String HotelName, String HotelName2){
           Database.LoadOnt2Database();
           Model om = Database.getOntologyModel();		
		Database.LoadOnt2Database();
		
		String queryString = null;
                        
                      queryString  = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
			+ "SELECT DISTINCT * \n "
			+ "WHERE \n"
                        + "{ \n"
                        +"?x hotel:HotelName ?hotelname. \n"                        
                        +" FILTER regex(?hotelname,\""
					+ HotelName + "\", \"i\")}";
               
                 Query query = QueryFactory.create(queryString);	
                QueryExecution queryexec = QueryExecutionFactory.create(query, om);
                   Model model1 = ModelFactory.createDefaultModel();
                try {
                    ResultSet rs = queryexec.execSelect();			
                    while (rs.hasNext()) {
                         model1 = rs.getResourceModel(); 
			Object obj = rs.next();						
				 ResultBinding binding = (ResultBinding) obj;
				System.out.println("truy2:"+binding.toString());
                    }
                }catch(Exception e1){
                    e1.printStackTrace();
                }
                   HotelProcess hotelprocess = new HotelProcess();
                   
                   Model model = ModelFactory.createDefaultModel();
                         model  = hotelprocess.searchbyId(false, true, false, false, false, "inside",false, true );
                         
                  model1.add(model);
                   
                   
                        
                   String queryString1  = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
			+ "SELECT DISTINCT * \n "
			+ "WHERE \n"
                        + "{ \n"
                        +"?x hotel:HotelName ?hotelname. \n"                        
                        +" FILTER ( regex(?hotelname,\""
					+ HotelName + "\", \"i\")||regex(?hotelname,\""
					+ HotelName2 + "\", \"i\")) }";
			
                 Query query2 = QueryFactory.create(queryString1);	
                QueryExecution queryexec2 = QueryExecutionFactory.create(query2, model1);
                   
                try {
                    ResultSet rs2 = queryexec2.execSelect();			
                    while (rs2.hasNext()) {
                        
			Object obj = rs2.next();						
				 ResultBinding binding = (ResultBinding) obj;
				System.out.println("truy 3"+binding.toString());
                    }
                }catch(Exception e1){
                    e1.printStackTrace();
                }
                   
                   
                   return model1;
	}
    
    public static void main(String s[])throws Exception{
        HotelProcess hotelprocess = new HotelProcess();
    //    hotelprocess.searchbyId(false, true, false, false, false, "inside",false, true );
        hotelprocess.search2("HaiYen", "HotelSofitel");
    }
   
}
