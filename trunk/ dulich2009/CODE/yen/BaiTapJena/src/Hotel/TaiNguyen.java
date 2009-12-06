package Hotel;
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
	
	
	public boolean insertAddressProcess(Address add) {
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
		return isOk;
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

	public void searchbyId1(String keyword)throws Exception {
		System.out.println("vao search");
	//	ArrayList<Contact> list = new ArrayList<Contact>();
		
		// Query query=QueryFactory.create("SELECT ?y  WHERE { ?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
	//	String x = "<" + Hotel.NS + ontclass + ">";
	//	System.out.print("x= "+x);
		//Query query=QueryFactory.create("SELECT ?email ?PhoneNumber ?Fax ?hasAddress  WHERE { "+x+"?y <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#hasNiece> <http://swrl.stanford.edu/ontologies/examples/family.swrl.owl#ConYen> }");
		
	/*	String queryString = "PREFIX Hotel: <http://www.owl-ontologies.com/Travel.owl#> " +
	            
	            "SELECT ?email ?PhoneNumber WHERE {"+x+"Hotel:Email ?email."+ x +" Hotel:PhoneNumber ?PhoneNumber}";
*/

		ArrayList<Address> list = new ArrayList<Address>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		System.out.println("om: "+ om.toString());
		FileOutputStream out = new FileOutputStream("D:\\yen.txt");
		  om.write(out);
		Database.LoadOnt2Database();
		
		String queryString = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
			+ "SELECT DISTINCT ?x ?country ?street ?zipcode\n "
			+ "WHERE \n"
			+ "{ \n"
			+ "?x hotel:zipCode ?zipcode. \n"				
			
			+ "FILTER (regex(?zipcode,'"
			+ keyword
			+ "',\"i\") || regex(?country,'"
			+ keyword
			+ "',\"i\") || regex(?street,'"
			+ keyword
			+ "',\"i\") )  \n"

			+ "}";
	System.out.println(queryString);
	Query query = QueryFactory.create(queryString);
	System.out.println("create");
	QueryExecution queryexec = QueryExecutionFactory.create(query, om);
	System.out.println("create2");
	try {
		ResultSet rs = queryexec.execSelect();
		 System.out.println("rs:"+rs.toString());	
		while (rs.hasNext()) {
			Object obj = rs.next();		
			 System.out.println("thuc hien truy van1");	
				 System.out.println("thuc hien truy van1");	
				 ResultBinding binding = (ResultBinding) obj;
				Address add = new Address();
				

				if (binding.get("zipcode").isLiteral()) {
					add.setZipcode(binding.getLiteral("zipcode")
							.getValue().toString());
					System.out.println("nhan de:"+add.getCountry());	
				} 
		}	
				
			
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
   
		
	
		
	
	
	
public static void main(String s[]) throws Exception{
	TaiNguyen tn =  new TaiNguyen();
//String[] s1=tn.searchAdvanced("CongNgheThongTin", ".PDF", "BaiGiang", "Hoang Minh Thuc", "Bach Khoa Ha Noi", "Tieng anh");
	Address add = new Address("vietnam", "NamDinh", "085");
	//boolean a = tn.insertAddressProcess(add);
//	Boolean a = tn.insertContact(contact);
//System.out.println("ket qua:"+a);
	ArrayList list = new ArrayList();

	Contact contact = new Contact();
//	contact.setAddress(add);
	contact.setEmail("yennh235@yahoo.com");
	contact.setPhoneNumber(01);
	contact.setFax(01);
	contact.setHasAddress("VietNam");
	//Boolean a = tn.insertContact(contact);
	
	 tn.searchbyId1("085");
//	tn.searchbyId();

	
	 
}
}

