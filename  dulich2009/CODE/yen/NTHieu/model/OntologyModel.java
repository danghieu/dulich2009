package model;

import java.util.ArrayList;

import view.IndividualResult;
import view.KetQuaAdvanced;
import view.KetQuaResult;

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
 * @author TrungHieu
 * 
 */
public class OntologyModel {
	public String prefix = "PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n";
	public String namespace = "http://eduportal.vn/chiase/myontology#";
	public OntModel om;
	public String path = "C:/Program Files/Apache Software Foundation/Tomcat 5.5/webapps/MyOntology/ChiaSe.owl";

	/**
	 * Load Ontology tu file .owl
	 * 
	 */
	public void load() {
		String keySource = "http://chiase.vn";
		boolean isDAML = keySource.endsWith(".daml");
		om = ModelFactory.createOntologyModel(isDAML ? OntModelSpec.DAML_MEM
				: OntModelSpec.OWL_MEM, null);

		om.getDocumentManager()
				.addAltEntry("http://chiase.vn", "file:/" + path);
		om.read(keySource);
	}

	public void builtOnt2Database() {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		builtClass(om);
	}

	
// dua ra cac lop va quan hej cha con
private void builtClass(OntModel om) {
		OntClass ConNguoi = om.createClass(namespace + "ConNguoi");
		OntClass NguoiDung = om.createClass(namespace + "NguoiDung");
		OntClass TacGia = om.createClass(namespace + "TacGia");
		OntClass QuanTri = om.createClass(namespace + "QuanTri");
		OntClass ThanhVien = om.createClass(namespace + "ThanhVien");
		OntClass Khach = om.createClass(namespace + "Khach");

		ConNguoi.addSubClass(NguoiDung);
		NguoiDung.addSubClass(QuanTri);
		NguoiDung.addSubClass(ThanhVien);
		NguoiDung.addSubClass(Khach);

		ConNguoi.addSubClass(TacGia);
	}


	public String[] searchbyPrefix(String prefix) {
		ArrayList<String> list = new ArrayList<String>();

		String queryString = "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "SELECT ?tukhoa \n" + "WHERE \n" + "{ \n"
				+ " ?x chiase:TuKhoa ?tukhoa. FILTER regex(?tukhoa,\"" + prefix
				+ "\",\"i\")  \n" + "} \n" + "ORDER BY ?tukhoa";
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

	private ArrayList<KetQuaResult> searchbyKeywordAdvanced(String keyword) {
		ArrayList<KetQuaResult> list = new ArrayList<KetQuaResult>();
		String queryString = "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "SELECT ?kq ?timdung ?tukhoa ?tailieuid ?nhande ?dinhdang ?tentacgia ?motanoidung ?anhminhhoa ?totaldownload \n "
				+ "WHERE \n" + "{ \n"
				+ " ?kq chiase:TuKhoa ?tukhoa. FILTER regex(?tukhoa,\""
				+ keyword + "\"). \n" + " ?kq chiase:TimDung ?timdung. \n"
				+ " ?kq chiase:KetQuaLaTaiLieu ?tailieuid. \n"
				+ " ?tailieuid chiase:NhanDe ?nhande. \n"
				+ " ?tailieuid chiase:DinhDang ?dinhdang. \n"
				+ " ?tailieuid chiase:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia chiase:TenGoi ?tentacgia. \n"
				+ " ?tailieuid chiase:MoTaNoiDung ?motanoidung. \n"
				+ " OPTIONAL { \n"
				+ " ?tailieuid chiase:AnhMinhHoa ?anhminhhoa. \n" + " } \n"
				+ " OPTIONAL { \n"
				+ " ?tailieuid chiase:TotalDownload ?totaldownload. \n"
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
				if (al.get(i).get("x").isLiteral()) {
					strList[totalField * i + 0] = al.get(i).getLiteral("x")
							.getValue().toString();
				} else {
					strList[totalField * i + 0] = al.get(i).getResource("x")
							.getLocalName().toString();
				}

				if (al.get(i).get("nhande").isLiteral()) {
					strList[totalField * i + 1] = al.get(i)
							.getLiteral("nhande").getValue().toString();
				} else {
					strList[totalField * i + 1] = al.get(i).getResource(
							"nhande").getLocalName().toString();
				}
				if (al.get(i).get("tentacgia").isLiteral()) {
					strList[totalField * i + 2] = al.get(i).getLiteral(
							"tentacgia").getValue().toString();
				} else {
					strList[totalField * i + 2] = al.get(i).getResource(
							"tentacgia").getLocalName().toString();
				}

				if (al.get(i).get("nhaxuatban").isLiteral()) {
					strList[totalField * i + 3] = al.get(i).getLiteral(
							"nhaxuatban").getValue().toString();
				} else {
					strList[totalField * i + 3] = al.get(i).getResource(
							"nhaxuatban").getLocalName().toString();
				}
				if (al.get(i).get("ngonngu").isLiteral()) {
					strList[totalField * i + 4] = al.get(i).getLiteral(
							"ngonngu").getValue().toString();
				} else {
					strList[totalField * i + 4] = al.get(i).getResource(
							"ngonngu").getLocalName().toString();
				}
				if (totalField > 5) {
					if (al.get(i).get("dinhdang").isLiteral()) {
						strList[totalField * i + 5] = al.get(i).getLiteral(
								"dinhdang").getValue().toString();
					} else {
						strList[totalField * i + 5] = al.get(i).getResource(
								"dinhdang").getLocalName().toString();
					}
					if (al.get(i).get("motanoidung").isLiteral()) {
						strList[totalField * i + 6] = al.get(i).getLiteral(
								"motanoidung").getValue().toString();
					} else {
						strList[totalField * i + 6] = al.get(i).getResource(
								"motanoidung").getLocalName().toString();
					}
					if (al.get(i).get("anhminhhoa").isLiteral()) {
						strList[totalField * i + 7] = al.get(i).getLiteral(
								"anhminhhoa").getValue().toString();
					} else {
						strList[totalField * i + 7] = al.get(i).getResource(
								"anhminhhoa").getLocalName().toString();
					}
					if (al.get(i).get("totaldownload") != null) {
						if (al.get(i).get("totaldownload").isLiteral()) {
							strList[totalField * i + 8] = al.get(i).getLiteral(
									"totaldownload").getValue().toString();
						} else {
							strList[totalField * i + 8] = al.get(i)
									.getResource("totaldownload")
									.getLocalName().toString();
						}
					} else {
						strList[totalField * i + 8] = "0";
					}
				}
			}
		} catch (Exception e) {

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
				+ " PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
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

	public long maxIdPlus(String oClass) {
		long total = 0;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = "" + "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "PREFIX rdf: <" + Sharing.RDF + "> \n" + "PREFIX rdfs: <"
				+ Sharing.RDFS + "> \n" + "SELECT ?x \n" + "WHERE \n" + "{ \n"
				+ " ?x rdf:type <" + Sharing.NS + oClass + "> \n" + "} \n" + "";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object oj = rs.next();
				total = total + 1;
			}
		} catch (Exception ex) {

		}
		om.close();
		return total + 1;
	}

	public static String KetQuaDaLuu(String tukhoa, String tailieuid) {
		String s = "";
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = "" + "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "PREFIX rdf: <" + Sharing.RDF + "> \n" + "PREFIX rdfs: <"
				+ Sharing.RDFS + "> \n" + "SELECT ?x ?tukhoa \n" + "WHERE \n"
				+ "{ \n" + " ?x chiase:TuKhoa ?tukhoa. \n"
				+ " ?x chiase:KetQuaLaTaiLieu <" + Sharing.NS + tailieuid
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

	public static ArrayList<IndividualResult> GetInd(String strClass) {
		ArrayList<IndividualResult> al = new ArrayList<IndividualResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
				+ " SELECT ?ind ?oclass ?tengoi \n"
				+ " WHERE \n"
				+ " { \n"
				+ "  ?ind rdf:type ?oclass. \n"
				+ "  ?ind chiase:TenGoi ?tengoi. \n"
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

	public static String insertIndividual(String OntClass, String TenGoi) {
		String Instance = "";
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
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
			} else {
				OntClass oc = om.createClass(Sharing.getURI() + OntClass);
				int total = list.size() + 1;
				Individual ind = om.createIndividual(Sharing.getURI()
						+ OntClass + "_" + total, oc);
				if (TenGoi != null) {
					ind.addProperty(Sharing.TenGoi, TenGoi);
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

	public static Individual GetbyInstance(String Instance) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om.getIndividual(Sharing.getURI() + Instance);
		return ind;
	}

	public static void removePropertyData(String Ind, Property pro) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om.getIndividual(Sharing.getURI() + Ind);
		ind.removeAll(pro);
		om.commit();
	}
}
