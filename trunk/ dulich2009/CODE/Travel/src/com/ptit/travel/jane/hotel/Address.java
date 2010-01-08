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
import com.ptit.travel.moduleJDBC.Model.Database;

import com.ptit.travel.agent.communication.Message;

public class Address {
	public Address() {
	}

        
        // ham nay t dung de insert 1 the hien address vao csdl jena vua roi ay
	public static boolean insertAddress(String input) // vi ko dung Address nua, mai trung truyen cho 1 String, to e phai tach de lay thong tin
        {
	  ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
        arr = Message.split(input, Message.FIELD_SEPARATE);
            boolean isOk = false;
		Database.LoadOnt2Database(); // ket noi csdl
		OntModel om = Database.getOntologyModel();// lay model csdl
                om.write(System.out);                   
                        
                
		Individual ind = null;
		try {
                      OntClass oc = om.createClass(Hotel.getURI() + "Address");
                      ind = om.createIndividual(Hotel.getURI() + "Address_" + arr.get(1)+"_"+arr.get(2)+"_"+arr.get(0), oc);
        	
	
				ind.addProperty(Hotel.country, "Viet Nam");
                        
                                
                                // them tung thuoc tinh la cac datatypeProperty ay
			if (arr.get(2)!= null) 
				ind.addProperty(Hotel.street,arr.get(2));
                               // Hotel.street thuc ra la de co cai the
			
			
			if (arr.get(1) != null) {
				ind.addProperty(Hotel.number,arr.get(1));
			}
                                ind.addProperty(Hotel.city, arr.get(0));
			
			isOk = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			isOk = false;
		}
		return isOk;
	}

        
        
        /**
         * input = city, number, street (cu -> moi)
         * @param input
         * @return
         */
	public static boolean updateAddress(String input ) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
                ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
                arr = Message.split(input, Message.FIELD_SEPARATE);
		try {
                    
                    // lay ra cai the hien cua cai doi tuong can thay doi ay
			Individual ind = om.getIndividual(Hotel.getURI() + "Address_"+ arr.get(1)+"_"+arr.get(2)+"_"+arr.get(0));
		
			
			// xoa cai cu them cai moi vao
			if (ind != null) {
				ind.remove();
				
				
				
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
			e.printStackTrace();
		}
		return isOk;

	}

        
        public static boolean deleteAddress(String input ) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
                ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
                arr = Message.split(input, Message.FIELD_SEPARATE);
		try {
                    
                    // lay ra cai the hien cua cai doi tuong can thay doi ay
			Individual ind = om.getIndividual(Hotel.getURI() + "Address_"+ arr.get(1)+"_"+arr.get(2)+"_"+arr.get(0));
			ind.remove();
			isOk = true;
			
			// xoa cai cu them cai moi vao
			
		} catch (Exception e) {
			isOk = false;
			e.printStackTrace();
		}
		return isOk;

	}
        
        
	
	
public static void main(String s[]) throws Exception{
	
 Address add = new Address();
	String input = "km10"+Message.FIELD_SEPARATE +"Nguyen Trai" + Message.FIELD_SEPARATE +"Thanh xuan_ Ha Noi";
        String input1 = "km8"+Message.FIELD_SEPARATE +"Nguyen Trai" + Message.FIELD_SEPARATE +"Thanh xuan_ Ha Noi";
        // day la 1 doi tuong dia chi, to se cho vao csdl
	boolean a = add.insertAddress(input);
      //  boolean b = add.deleteAddress(input);

	
	 
}
}

