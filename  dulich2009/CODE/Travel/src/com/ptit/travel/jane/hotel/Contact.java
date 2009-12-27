

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
import java.util.*;

import com.ptit.travel.agent.communication.Message;
/**
 *
 * @author HaiYen
 */
public class Contact {

    /**
     * 
     * @param input: Email - hasAddress - hasName - PhoneNumber
     * 
     * @return
     */
    public boolean insertContact(String input) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
                ArrayList<String> arr = new ArrayList<String>();        
                arr = Message.split(input, Message.FIELD_SEPARATE);
        
		try {
			System.out.print("a1");
			OntClass oc = om.createClass(Hotel.getURI() + "Contact"); // dinh danh ten lop
			
			
			Individual ind= om.createIndividual(Hotel.getURI() + "Contact_"+ arr.get(0) , oc);
		
			
			ind.addProperty(Hotel.Email, arr.get(0));
			ind.addProperty(Hotel.PhoneNumer,arr.get(3) );			
			
			// Neu Object la 1 doi tuong, thi phai lay ca the can
			Individual add = om.getIndividual(Hotel.getURI() + "Address_"+ arr.get(1));
			
                        // co doi tuong dia chi nay roi
			if(add!=null){
				System.out.print("khac rong");
				ind.addProperty(Hotel.hasAddress, add);
				add.addProperty(Hotel.isAdressOf, ind.getLocalName());
				
			}
			else{
				String address = null;
                                System.out.print("Nhap dia chi");
                                Scanner scan = new Scanner(System.in);
                                System.out.print("City:");
                                address = address+scan.nextLine()+Message.FIELD_SEPARATE;
                                System.out.print("number:");
                                address = address+scan.nextLine()+Message.FIELD_SEPARATE;
                                System.out.print("street:");
                                address = address+scan.nextLine()+Message.FIELD_SEPARATE;
                                Address.insertAddress(input);
                                
			}			
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
		


    
  /**
     * @param input: Email cu - : Email moil -- PhoneNumber
     */ 
	public static boolean updateContact(String input ) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
                ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
                arr = Message.split(input, Message.FIELD_SEPARATE);
		try {
                    
                    // lay ra cai the hien cua cai doi tuong can thay doi ay
			Individual ind = om.getIndividual(Hotel.getURI() + "Contact_"+ arr.get(0));
		
			
			// xoa cai cu them cai moi vao
			if (ind != null) {
				
				ind.removeAll(Hotel.Email);
				ind.addProperty(Hotel.Email, arr.get(1));
				
				ind.removeAll(Hotel.PhoneNumer);
				ind.addProperty(Hotel.PhoneNumer, arr.get(2));
				
				
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
			e.printStackTrace();
		}
		return isOk;

	}

        
        public static boolean deleteContact(String input ) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
                ArrayList<String> arr = new ArrayList<String>();

        // Ham phan tach thog tin dua vao
                arr = Message.split(input, Message.FIELD_SEPARATE);
		try {
                    
                    // lay ra cai the hien cua cai doi tuong can thay doi ay
			Individual ind = om.getIndividual(Hotel.getURI() + "Contact_"+ arr.get(0));
			ind.remove();
			isOk = true;
			
			// xoa cai cu them cai moi vao
			
		} catch (Exception e) {
			isOk = false;
			e.printStackTrace();
		}
		return isOk;

	}
        
		
	
	
    
}
