package Hotel;
import java.util.ArrayList;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.core.ResultBinding;
import view.Address;
import view.Contact;




public class TaiNguyen {
	public TaiNguyen() {
	}

	
	
	
	/**
	 * Them 1 the hien tai lieu
	 * @param TheLoaiId
	 * @param NhanDe
	 * @param BanQuyen
	 * @param SoTrang
	 * @param NhaXuatBan
	 * @param DeMuc
	 * @param NgayThang
	 * @param DinhDang
	 * @param NgonNgu
	 * @param MoTaNoiDung
	 * @param NguonGoc
	 * @param CoTacGia
	 * @param CongTac
	 * @param ThuocLinhVuc
	 * @param TaiLieuCoPhuongThuc
	 * @param totalplus
	 * @return
	 */
	
	
	public Individual insertAddressProcess(Address add) {
		boolean isOk = false;
		Database.LoadOnt2Database(); // ket noi csdl
		OntModel om = Database.getOntologyModel();
		Individual ind = null;
		try {
			OntClass oc = om.createClass(Hotel.getURI() + add.getCountry()); // dinh danh ten lop
			
		
			 ind = om.createIndividual(Hotel.getURI() + "Address_"+ add.getCountry(), oc);
			
			
			
			// them cac thuoc tinh vao ca the can tao
			if (add.getCountry() != null) {
				ind.addProperty(Hotel.country, add.getCountry()); 
			}
			if (add.getStreet()!= null) {
				ind.addProperty(Hotel.street,add.getStreet());
			}
			
			if (add.getZipcode() != null) {
				ind.addProperty(Hotel.zipCode,add.getZipcode());
			}
			
			isOk = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			isOk = false;
		}
		return ind;
	}

	public boolean updateTaiLieu(Address add) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();

		try {
			Individual ind = om.getIndividual(Hotel.getURI() + "Address_"+ add.getCountry());
			System.out.print(Hotel.getURI() + "Address_"+ add.getCountry());
			System.out.println("Hien thi cac the hien:");
			System.out.println("country:"+ ind.getProperty(Hotel.country));
			
			if (ind != null) {
				ind.removeAll(Hotel.country);
				ind.addProperty(Hotel.country, add.getCountry());
				ind.removeAll(Hotel.street);
				ind.addProperty(Hotel.street, add.getStreet());
				ind.removeAll(Hotel.zipCode);
				ind.addProperty(Hotel.zipCode, add.getZipcode());
				
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
			e.printStackTrace();
		}
		return isOk;

	}

	public boolean insertContact(Contact contact) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			System.out.print("a1");
			OntClass oc = om.createClass(Hotel.getURI() + contact.getEmail()); // dinh danh ten lop
			
			
			Individual ind= om.createIndividual(Hotel.getURI() + "Contact_"+ contact.getEmail(), oc);
		
			if (contact.getEmail() != null) {
				ind.addProperty(Hotel.Email, contact.getEmail());
			}
			System.out.print("a2");
			
			if (contact.getPhoneNumber() != 0) {
				ind.addProperty(Hotel.PhoneNumer, contact.getPhoneNumber());
			}
			
			
			// Neu Object la 1 doi tuong, thi phai lay ca the can
			Individual add = om.getIndividual(Hotel.getURI() + "Address_"+ contact.getHasAddress());
			// 
			if(add!=null){
				System.out.print("khac rong");
				ind.addProperty(Hotel.hasAdress,add);
				add.addProperty(Hotel.isAdressOf, ind.getLocalName());
				
			}
			else{
				System.out.print("Nhap dia chi");
			}			
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
			isOk = false;
		}
		return isOk;
	}
		// search

	public void searchbyId(String ontclass) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		
		// Query query=QueryFactory.create("SELECT ?y  WHERE { ?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
		String x = "<" + Hotel.NS + ontclass + ">";
		System.out.print("x= "+x);
		//Query query=QueryFactory.create("SELECT ?email ?PhoneNumber ?Fax ?hasAddress  WHERE { "+x+"?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
		
	/*	String queryString = "PREFIX Hotel: <http://www.owl-ontologies.com/Travel.owl#> " +
	            
	            "SELECT ?email ?PhoneNumber WHERE {"+x+"Hotel:Email ?email."+ x +" Hotel:PhoneNumber ?PhoneNumber}";
*/

		Query query = QueryFactory.create("PREFIX Hotel: <http://www.owl-ontologies.com/Travel.owl#>"
				+ "SELECT ?email ?PhoneNumber ?Fax ?hasAddress WHERE { " + x
				+ " Hotel:Email ?email" + x
				+ " Hotel:PhoneNumber ?PhoneNumber " + x
				+ " Hotel:Fax ?Fax" + x
				
				+ " Hotel:hasAddress ?hasAddress }");
				
		// System.out.println(queryString);
		//Query query = QueryFactory.create(queryString);
		System.out.println("query");
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				System.out.println("xua ly khi co du lieu");
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				Contact contact = new Contact();
								
				if (binding.get("Email").isLiteral()) {
					contact.setEmail(binding.getLiteral("email").getString());
							
				}

				if (binding.get("Fax").isLiteral()) {
					contact.setFax((Integer)binding.getLiteral("Fax")
							.getValue());
				} 
				
				if (binding.get("PhoneNumber").isLiteral()) {
					contact.setPhoneNumber((Integer)binding.getLiteral("PhoneNumber")
							.getValue());
				}
								
				if(binding.get("hasAddress").isLiteral()){
					contact.setHasAddress(binding.getLiteral("hasAddress").getString());
				}
				list.add(contact);
				System.out.print("email"+contact.getEmail());

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			om.close();
		}
	/*
		
		 try {

     		System.out.println("in nao");
         ResultSet rs = queryexec.execSelect(); 

         for ( ; rs.hasNext() ; )  {
        		System.out.println("b3");
               QuerySolution rb = rs.nextSolution() ;
        
                System.out.println( "email:  "+rb.getResource("email").getLocalName());//toString());
                System.out.println( "phonenumber:  "+rb.getResource("PhoneNumber").getLocalName());

             }

               
     }
    catch(Exception e)
       {
               e.printStackTrace();
       }     
         */
		
	}
	
	
public static void main(String s[]){
	TaiNguyen tn =  new TaiNguyen();
//String[] s1=tn.searchAdvanced("CongNgheThongTin", ".PDF", "BaiGiang", "Hoang Minh Thuc", "Bach Khoa Ha Noi", "Tieng anh");
	Address add = new Address("English", "NamDinh", "085");
	
	//Address add = new Address("a", "b", "c");
	Contact contact = new Contact();
//	contact.setAddress(add);
	contact.setEmail("yennh235@yahoo.com");
	contact.setPhoneNumber(01);
	contact.setFax(01);
	contact.setHasAddress("VietNam");
	
	//Boolean a = tn.insertContact(contact);
//	Individual ad = tn.insertAddressProcess(add);
	Boolean a = tn.insertContact(contact);
	System.out.println("ket qua:"+a);
	ArrayList list = new ArrayList();
	 tn.searchbyId("Contact_yennh235@yahoo.com");

	
	 
}
}

