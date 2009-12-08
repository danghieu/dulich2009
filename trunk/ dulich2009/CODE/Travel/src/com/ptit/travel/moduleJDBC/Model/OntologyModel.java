package com.ptit.travel.moduleJDBC.Model;

import java.util.ArrayList;

//import CommonClass.IndividualResult;
/*import view.KetQuaAdvanced;
import view.KetQuaResult;
*/
import com.ptit.travel.jane.hotel.Hotel;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.sparql.core.ResultBinding;

/**
 * 
 * @author Administrator
 *
 */
public class OntologyModel {

	// Duong dan file ontology, dat trong webapps cuaTomcat
	
	public String prefix = "PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n";
	//public String namespace = "http://localhost:8080/Ontology/Sharing/Sharing.owl#";
	public String namespace = "http://www.owl-ontologies.com/Travel.owl#";
	public OntModel om;
	public String path = "C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/MyOntology/hotel.owl";

	/**
	 * Load Ontology tu file .owl
	 * 
	 */
	
	/*	public void load() {
		String keySource = "http://chiase.vn";
		boolean isDAML = keySource.endsWith(".daml");
		om = ModelFactory.createOntologyModel(isDAML ? OntModelSpec.DAML_MEM
				: OntModelSpec.OWL_MEM, null);

		om.getDocumentManager()
				.addAltEntry("http://chiase.vn", "file:/" + path);
		om.read(keySource);
	}
*/
	
	public void builtOnt2Database() {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
	//	builtClass(om);
		//System.out.println("in:"+om.toString());
	}

	
// dua ra cac lop va quan hej cha con

	private void builtClass(OntModel om) {
		OntClass Customer = om.createClass(namespace + "Customer");
		OntClass Address = om.createClass(namespace + "Address");
		OntClass Name = om.createClass(namespace + "Name");
		OntClass LivingRoom = om.createClass(namespace + "LivingRoom");
		OntClass Contact = om.createClass(namespace + "Contact");
		
		
	}

	
	/**
	 * Tim kiem theo tien to, ket qua tra ve la 1 mang cac chuoi
	 */
	

	/*public String[] searchbyPrefix(String prefix) {
		ArrayList<String> list = new ArrayList<String>();

		String queryString = "PREFIX hotel: <" + Hotel.NS + "> \n"
				+ "SELECT ?tukhoa \n" + "WHERE \n" + "{ \n"
				+ " ?x hotel:TuKhoa ?tukhoa. FILTER regex(?tukhoa,\"" + prefix
				+ "\",\"i\")  \n" + "} \n" + "ORDER BY ?tukhoa";
		// System.out.println(queryString);


		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		// lay mo hinh tu csdl
		
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				String s = "";
				if (binding.get("tukhoa").isLiteral()) {
					s = binding.getLiteral("tukhoa").getValue().toString();
				} else {
					s = binding.getResource("tukhoa").getLocalName().toString();
				}
				list.add(s);
			}
		} catch (Exception e) {

		}
		FrameWork fw = new FrameWork();
		return fw.convertArrayListStr2StringArray(list);
	}	
	
	*/
	
	
/*
	private ArrayList<KetQuaResult> searchbyKeywordAdvanced(String keyword) {
		ArrayList<KetQuaResult> list = new ArrayList<KetQuaResult>();
		String queryString = "PREFIX hotel: <"
				+ Sharing.NS
				+ "> \n"
				+ "SELECT ?kq ?timdung ?tukhoa ?tailieuid ?nhande ?dinhdang ?tentacgia ?motanoidung ?anhminhhoa ?totaldownload \n "
				+ "WHERE \n" + "{ \n"
				+ " ?kq hotel:TuKhoa ?tukhoa. FILTER regex(?tukhoa,\""
				+ keyword + "\"). \n" + " ?kq hotel:TimDung ?timdung. \n"
				+ " ?kq hotel:KetQuaLaTaiLieu ?tailieuid. \n"
				+ " ?tailieuid hotel:NhanDe ?nhande. \n"
				+ " ?tailieuid hotel:DinhDang ?dinhdang. \n"
				+ " ?tailieuid hotel:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia hotel:TenGoi ?tentacgia. \n"
				+ " ?tailieuid hotel:MoTaNoiDung ?motanoidung. \n"
				+ " OPTIONAL { \n"
				+ " ?tailieuid hotel:AnhMinhHoa ?anhminhhoa. \n" + " } \n"
				+ " OPTIONAL { \n"
				+ " ?tailieuid hotel:TotalDownload ?totaldownload. \n"
				+ " } \n" + "} \n" + "ORDER BY ?kq";
		// System.out.println(queryString);
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				// System.out.println(binding.toString());
				KetQuaResult kqr = new KetQuaResult();
				if (binding.get("kq").isLiteral()) {
					kqr.setKetquaid(binding.getLiteral("kq").getValue()
							.toString());
				} else {
					kqr.setKetquaid(binding.getResource("kq").getLocalName()
							.toString());
				}
				if (binding.get("tukhoa").isLiteral()) {
					kqr.setKeyword(binding.getLiteral("tukhoa").getValue()
							.toString());
				} else {
					kqr.setKeyword(binding.getResource("tukhoa").getLocalName()
							.toString());
				}
				if (binding.get("tailieuid").isLiteral()) {
					kqr.setTailieuid(binding.getLiteral("tailieuid").getValue()
							.toString());
				} else {
					kqr.setTailieuid(binding.getResource("tailieuid")
							.getLocalName().toString());
				}
				if (binding.get("timdung").isLiteral()) {
					kqr.setTimdung(Float.parseFloat(binding.getLiteral(
							"timdung").getValue().toString()));
				} else {
					kqr.setTimdung(Float.parseFloat(binding.getResource(
							"timdung").getLocalName().toString()));
				}

				if (binding.get("nhande").isLiteral()) {
					kqr.setNhande(binding.getLiteral("nhande").getValue()
							.toString());
				} else {
					kqr.setNhande(binding.getResource("nhande").getLocalName()
							.toString());
				}
				if (binding.get("anhminhhoa").isLiteral()) {
					kqr.setAnhminhhoa(binding.getLiteral("anhminhhoa")
							.getValue().toString());
				} else {
					kqr.setAnhminhhoa(binding.getResource("anhminhhoa")
							.getLocalName().toString());
				}
				if (binding.get("dinhdang").isLiteral()) {
					kqr.setDinhdang(binding.getLiteral("dinhdang").getValue()
							.toString());
				} else {
					kqr.setDinhdang(binding.getResource("dinhdang")
							.getLocalName().toString());
				}
				if (binding.get("tentacgia").isLiteral()) {
					kqr.setTentacgia(binding.getLiteral("tentacgia").getValue()
							.toString());
				} else {
					kqr.setTentacgia(binding.getResource("tentacgia")
							.getLocalName().toString());
				}
				if (binding.get("motanoidung").isLiteral()) {
					kqr.setMotanoidung(binding.getLiteral("motanoidung")
							.getValue().toString());
				} else {
					kqr.setMotanoidung(binding.getResource("motanoidung")
							.getLocalName().toString());
				}
				if (binding.get("totaldownload") != null) {
					if (binding.get("totaldownload").isLiteral()) {
						kqr.setTotaldownload(binding
								.getLiteral("totaldownload").getValue()
								.toString());
					} else {
						kqr.setTotaldownload(binding.getResource(
								"totaldownload").getLocalName().toString());
					}
				} else {
					kqr.setTotaldownload("0");
				}
				list.add(kqr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<KetQuaAdvanced> searchbyKeyword(String keyword) {
		ArrayList<KetQuaAdvanced> listOut = new ArrayList<KetQuaAdvanced>();
		OntologyModel om = new OntologyModel();
		ArrayList<KetQuaResult> listIn = om.searchbyKeywordAdvanced(keyword);
		String currId = "";
		KetQuaAdvanced kqa = new KetQuaAdvanced();
		KetQuaResult kqr;
		for (int i = 0; i < listIn.size(); i++) {
			kqr = listIn.get(i);
			if (kqr.getKetquaid().equals(currId)) {
				kqa.setValue(kqa.getValue() + kqr.getTimdung());
				kqa.setCount(kqa.getCount() + 1);
			} else {
				if (!currId.equals("")) {
					listOut.add(kqa);
					kqa = new KetQuaAdvanced();
				}

				currId = kqr.getKetquaid();

				kqa.setKetquaid(currId);
				kqa.setValue(kqr.getTimdung());
				kqa.setKeyword(kqr.getKeyword());
				kqa.setTailieuid(kqr.getTailieuid());
				kqa.setCount(1);
				kqa.setAnhminhhoa(kqr.getAnhminhhoa());
				kqa.setNhande(kqr.getNhande());
				kqa.setMotanoidung(kqr.getMotanoidung());
				kqa.setTentacgia(kqr.getTentacgia());
				kqa.setTotaldownload(kqr.getTotaldownload());
				kqa.setDinhdang(kqr.getDinhdang());
			}
		}
		if (listIn.size() > 0)
			listOut.add(kqa);
		return listOut;
	}
*/
	
	/**
	 * Them du lieu vao cuoi cua csdl, theo totalField, them vao address
	 */
	
	public String[] queryCommand(String strQuery, int totalField) {
		String[] strList = null;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			ArrayList<ResultBinding> al = new ArrayList<ResultBinding>();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				al.add(binding);
			}
			// System.out.println(al.size());
			strList = new String[totalField * al.size()];
			for (int i = 0; i < al.size(); i++) {
				if (al.get(i).get("country").isLiteral()) {
					strList[totalField * i + 0] = al.get(i).getLiteral("country")
							.getValue().toString();
				} else {
					strList[totalField * i + 0] = al.get(i).getResource("country")
							.getLocalName().toString();
				}

				if (al.get(i).get("street").isLiteral()) {
					strList[totalField * i + 1] = al.get(i)
							.getLiteral("street").getValue().toString();
				} else {
					strList[totalField * i + 1] = al.get(i).getResource(
							"street").getLocalName().toString();
				}
				if (al.get(i).get("zipcode").isLiteral()) {
					strList[totalField * i + 2] = al.get(i).getLiteral(
							"zipcode").getValue().toString();
				} else {
					strList[totalField * i + 2] = al.get(i).getResource(
							"zipcode").getLocalName().toString();
				}
				
					
				}
			}
		 catch (Exception e) {
			 e.printStackTrace();
		} finally {
			queryexec.close();
		}
		return strList;
	}

	/**
	 * tra lai gia tri la true neu ton tai the hien
	 * 
	 * @param strIndividual
	 *            ma the hien
	 * @return boolean
	 */
	public static boolean hasIndividual(String strIndividual) {
		boolean bHas = false;
		ArrayList<String> ListStr = new ArrayList<String>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
				+ " SELECT ?ind \n"
				+ " WHERE \n"
				+ " { \n"
				+ "  ?ind rdf:type ?oclass. \n" + " } \n" + " ORDER by ?ind";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				String str = "";
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("ind").isLiteral()) {
					str = binding.getLiteral("ind").getValue().toString();
				} else {
					str = binding.getResource("ind").getLocalName().toString();
				}
				if (str.equals(strIndividual)) {
					ListStr.add(str);
				}
			}
		} catch (Exception e) {

		}
		if (ListStr.size() > 0) {
			bHas = true;
		}
		return bHas;
	}

	
/**
 * Tra ve tong so class can kiem tra
 * @param oClass
 * @return
 */
	public long maxIdPlus(String oClass) {
		long total = 0;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		System.out.print("tinh Max");
		String strQuery = "" + "PREFIX hotel: <" + Hotel.NS + "> \n"
				+ "PREFIX rdf: <" + Hotel.RDF + "> \n" + "PREFIX rdfs: <"
				+ Hotel.RDFS + "> \n" + "SELECT ?x \n" + "WHERE \n" + "{ \n"
				+ " ?x rdf:type <" + Hotel.NS + oClass + "> \n" + "} \n" + "";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object oj = rs.next();
				total = total + 1;
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		om.close();
		return total + 1;
	}

	/*
	public static String KetQuaDaLuu(String tukhoa, String tailieuid) {
		String s = "";
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = "" + "PREFIX hotel: <" + Hotel.NS + "> \n"
				+ "PREFIX rdf: <" + Hotel.RDF + "> \n" + "PREFIX rdfs: <"
				+ Hotel.RDFS + "> \n" + "SELECT ?x ?tukhoa \n" + "WHERE \n"
				+ "{ \n" + " ?x hotel:TuKhoa ?tukhoa. \n"
				+ " ?x hotel:KetQuaLaTaiLieu <" + Hotel.NS + tailieuid
				+ ">. \n" + " FILTER regex(?tukhoa,\"" + tukhoa + "\")\n"
				+ "} \n" + "";
		// System.out.println(strQuery);
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("x").isLiteral()) {
					s = binding.getLiteral("x").getValue().toString();
				} else {
					s = binding.getResource("x").getLocalName().toString();
				}
			}
		} catch (Exception ex) {
			// om.close();
		}
		om.close();
		return s;
	}
*/
	

/**
 * Tra ve danh sach cac the hien cua 1 lop nao day
 */
	/*public static ArrayList<IndividualResult> GetInd(String strClass) {
		ArrayList<IndividualResult> al = new ArrayList<IndividualResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX hotel: <http://www.owl-ontologies.com/Travel.owl#> \n"
				+ " SELECT ?ind ?oclass ?tengoi \n"
				+ " WHERE \n"
				+ " { \n"
				+ "  ?ind rdf:type ?oclass. \n"
				+ "  ?ind hotel:TenGoi ?tengoi. \n"
				+ " } \n"
				+ " ORDER by ?ind";
		Query query = QueryFactory.create(strQuery);
		// System.out.println(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			// System.out.println(rs.toString());
			while (rs.hasNext()) {
				IndividualResult ind = new IndividualResult();
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("ind").isLiteral()) {
					ind.setIndividualName(binding.getLiteral("ind").getValue()
							.toString());
				} else {
					ind.setIndividualName(binding.getResource("ind")
							.getLocalName().toString());
				}
				if (binding.get("oclass").isLiteral()) {
					ind.setClassName(binding.getLiteral("oclass").getValue()
							.toString());
				} else {
					ind.setClassName(binding.getResource("oclass")
							.getLocalName().toString());
				}
				if (binding.get("tengoi").isLiteral()) {
					ind.setTenGoi(binding.getLiteral("tengoi").getValue()
							.toString());
				} else {
					ind.setTenGoi(binding.getResource("tengoi").getLocalName()
							.toString());
				}
				if (ind.getClassName().equals(strClass)) {
					al.add(ind);
				}
			}
		} catch (Exception e) {

		}
		return al;
	}

	
	/**
	 * Them 1 the hien vao model
	 * @param OntClass
	 * @param TenGoi
	 * @return
	 */
	/*public static String insertIndividual(String OntClass, String TenGoi) {
		String Instance = "";
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
				
		
	//	Hotel.MessageHotel.getSubClass().listProperties().nextStatement().getString();
		
		
		
		
		
		try {
			ArrayList<IndividualResult> list = GetInd(OntClass);
			boolean b = false;
			for (int i = 0; i < list.size(); i++) {
				IndividualResult ir = (IndividualResult) list.get(i);
				if (ir.getTenGoi().equals(TenGoi)) {
					b = true;
					Instance = ir.getIndividualName();
					break;
				}
			}
			if (b) {
			} 
			// chua co lop nay thi tao ra 1 lop moi
			else {
				OntClass oc = om.createClass(Hotel.getURI() + OntClass);
				int total = list.size() + 1;
				Individual ind = om.createIndividual(Hotel.getURI()
						+ OntClass + "_" + total, oc);
				
				// Ten lop, chu y tat ca deu phai co namespace
				if (TenGoi != null) {
					ind.addProperty(Hotel.name, TenGoi);
				}
				Instance = OntClass + "_" + total;
				ind.commit();
			}
			om.commit();
		} catch (Exception e) {
			Instance = "";
		}
		return Instance;
	}

	//Ten 1 the hien
	public static Individual GetbyInstance(String Instance) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om.getIndividual(Hotel.getURI() + Instance);
		return ind;
	}
         * 

	// xoa that ca cac thuoc tinh lien quan den 1 the hien
	public static void removePropertyData(String Ind, Property pro) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om.getIndividual(Hotel.getURI() + Ind);
		ind.removeAll(pro);
		om.commit();
	}
         * */
	public static void main(String s[]){
		OntologyModel on=new OntologyModel();
		on.builtOnt2Database();
		//long a = on.maxIdPlus("Address");
		//System.out.print("tong so:"+a);
		
	}
}
