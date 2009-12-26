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
		// to tim kiem theo tu khoa nao day

	
		
	
	
    
}
