import java.util.ArrayList;

import view.IndividualResult;
import view.ChuyenNganhResult;
import view.LopResult;
import view.SubMainResult;
import view.TinhChatResult;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.core.ResultBinding;

import model.Database;
import model.FrameWork;
import model.OntologyModel;
import model.Sharing;

public class PhanLoai {
	/**
	 * Phan loai dua tren du lieu cua file Ontology(.owl)
	 * 
	 * @param strClass
	 *            ten cua lop cha
	 * @return mang cac lop con
	 */
	public String[] subClass(String strClass) {
		ArrayList<SubMainResult> list = new ArrayList<SubMainResult>();
		OntologyModel OM=new OntologyModel();
		OM.load();
		// System.out.println(OntologyModel.om.toString());
		String strQuery = " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
				+ " SELECT ?subject ?Object \n"
				+ " WHERE \n"
				+ " { \n"
				+ " ?subject rdfs:subClassOf ?Object. \n" + " }";
		OntologyModel OTM=new OntologyModel();
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query,
				OTM.om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				SubMainResult dtPhanLoai = new SubMainResult();
				Object obj = rs.next();
				// System.out.println(obj.toString());
				ResultBinding binding = (ResultBinding) obj;
				// System.out.println(binding.toString());
				if (binding.get("subject").isLiteral()) {
					dtPhanLoai.setStrSubClass(binding.getLiteral("subject")
							.getValue().toString());
				} else {
					dtPhanLoai.setStrSubClass(binding.getResource("subject")
							.getLocalName().toString());
				}
				if (binding.get("Object").isLiteral()) {
					dtPhanLoai.setStrMainClass(binding.getLiteral("Object")
							.getValue().toString());
				} else {
					dtPhanLoai.setStrMainClass(binding.getResource("Object")
							.getLocalName().toString());
				}
				if (dtPhanLoai.getStrMainClass().equals(strClass)) {
					list.add(dtPhanLoai);
				}
			}
		} catch (Exception e) {
		}
		String[] strList = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			SubMainResult dtPhanLoai = (SubMainResult) list.get(i);
			strList[i] = dtPhanLoai.getStrSubClass();
		}
		return strList;
	}

	/**
	 * Phan loai dua tren du lieu tren Database
	 * 
	 * @param strClass
	 *            ten cua lop cha
	 * @return mang cac lop con
	 */
	public String[] subClassDB(String strClass) {
		ArrayList<LopResult> list = new ArrayList<LopResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		// /////
		// OntologyModel.load();
		// System.out.println(om.toString());
		String strQuery = "" + " PREFIX rdfs: <" + Sharing.RDFS + "> \n"
				+ " SELECT ?subject ?label ?comment ?Object \n" + " WHERE \n"
				+ " { \n" 
				+ " ?subject rdfs:subClassOf ?Object. \n"
				+ " ?subject rdfs:label ?label. \n"
				+ " ?subject rdfs:comment ?comment. \n" + " }"
				+ " ORDER by ?subject";
		// System.out.println(strQuery);
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				String strObject = null;
				if (binding.get("Object").isLiteral()) {
					strObject = binding.getLiteral("Object").getValue()
							.toString();
				} else {
					strObject = binding.getResource("Object").getLocalName()
							.toString();
				}
				if (strObject.equals(strClass)) {
					LopResult dtLop = new LopResult();
					if (binding.get("subject").isLiteral()) {
						dtLop.setLopId(binding.getLiteral("subject").getValue()
								.toString());
					} else {
						dtLop.setLopId(binding.getResource("subject")
								.getLocalName().toString());
					}
					if (binding.get("label").isLiteral()) {
						dtLop.setLabel(binding.getLiteral("label").getValue()
								.toString());
					} else {
						dtLop.setLabel(binding.getResource("label")
								.getLocalName().toString());
					}
					if (binding.get("comment").isLiteral()) {
						dtLop.setComment(binding.getLiteral("comment")
								.getValue().toString());
					} else {
						dtLop.setComment(binding.getResource("comment")
								.getLocalName().toString());
					}
					list.add(dtLop);
				}
			}
		} catch (Exception e) {
		}
		FrameWork fw=new FrameWork();
		String[] strList = fw.convertArrayListLop2StringArray(list);
		return strList;
	}

	/**
	 * Tim tat ca cac individual cua lop strClass va cua ca cac lop con cua no
	 * 
	 * @param strClass
	 * @return
	 */
	public String[] searchAllIndividual(String strClass) {
		String[] subClass = subClassDB(strClass);
		ArrayList<IndividualResult> allInd = new ArrayList<IndividualResult>();
		for (int i = 0; i < subClass.length / 3; i++) {
			ArrayList<IndividualResult> ind = OntologyModel
					.GetInd(subClass[i * 3 + 0]);
			for (int j = 0; j < ind.size(); j++) {
				IndividualResult ir = (IndividualResult) ind.get(j);
				allInd.add(ir);
			}
		}
		ArrayList<IndividualResult> indP = OntologyModel.GetInd(strClass);
		for (int j = 0; j < indP.size(); j++) {
			IndividualResult ir = (IndividualResult) indP.get(j);
			allInd.add(ir);
		}
		String[] ketqua = new String[allInd.size() * 2];
		for (int i = 0; i < allInd.size(); i++) {
			IndividualResult dtIndividual = (IndividualResult) allInd.get(i);
			ketqua[i * 2 + 0] = dtIndividual.getIndividualName();
			ketqua[i * 2 + 1] = dtIndividual.getTenGoi();
		}
		return ketqua;
	}

	/**
	 * Kiem tra xem lop strClassNganh da co the hien co ten goi
	 * strIndividualName hay khong
	 * 
	 * @param strClassNganh
	 * @param strIndividualName
	 * @return boolean
	 */
	public boolean ExistsIndividualName(String strClassNganh,
			String strIndividualName) {
		boolean exist = false;
		ArrayList<ChuyenNganhResult> list = new ArrayList<ChuyenNganhResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = ""
				+ " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
				+ " SELECT ?ind ?oclass ?tengoi \n" + " WHERE \n" + " { \n"
				+ "  ?ind rdf:type ?oclass. \n"
				+ "  ?ind chiase:TenGoi ?tengoi. \n" + " } \n"
				+ " ORDER by ?ind";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				ChuyenNganhResult dtLinhVuc = new ChuyenNganhResult();
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("ind").isLiteral()) {
					dtLinhVuc.setStrIndividualName(binding.getLiteral("ind")
							.getValue().toString());
				} else {
					dtLinhVuc.setStrIndividualName(binding.getResource("ind")
							.getLocalName().toString());
				}
				if (binding.get("tengoi").isLiteral()) {
					dtLinhVuc.setStrTenGoi(binding.getLiteral("tengoi")
							.getValue().toString());
				} else {
					dtLinhVuc.setStrTenGoi(binding.getResource("tengoi")
							.getLocalName().toString());
				}
				if (binding.get("oclass").isLiteral()) {
					dtLinhVuc.setStrClassName(binding.getLiteral("oclass")
							.getValue().toString());
				} else {
					dtLinhVuc.setStrClassName(binding.getResource("oclass")
							.getLocalName().toString());
				}
				if (dtLinhVuc.getStrClassName().equals(strClassNganh)
						&& dtLinhVuc.getStrTenGoi().equals(strIndividualName)) {
					list.add(dtLinhVuc);
				}
			}
		} catch (Exception e) {

		}
		if (list.size() > 0) {
			exist = true;
		}
		return exist;
	}
	public boolean insertTinhChat(String ClassName,String strTenGoi,String strMoTaNoiDung){
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		if (!ExistsIndividualName(ClassName, strTenGoi)) {
			try {
				OntClass oc = om.createClass(Sharing.getURI() + ClassName);
				int total = (listTinhChat(ClassName).length / 3) + 1;
				Individual ind = om.createIndividual(Sharing.getURI()
						+ ClassName + "_" + total, oc);
				if (strTenGoi != null) {
					ind.addProperty(Sharing.TenGoi, strTenGoi);
				}
				if (strMoTaNoiDung != null) {
					ind.addProperty(Sharing.MoTaNoiDung, strMoTaNoiDung);
				}
				om.commit();
				isOk = true;
			} catch (Exception e) {
				isOk = false;
			}
		}
		return isOk;
	}
	public boolean updateTinhChat(String strIndividualTinhChat,String strTenGoi,String strMoTaNoiDung){
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			Individual ind = om.getIndividual(Sharing.getURI()
					+ strIndividualTinhChat);
			if (ind != null) {
				if (strTenGoi != null) {
					ind.removeAll(Sharing.TenGoi);
					ind.addProperty(Sharing.TenGoi, strTenGoi);
				}
				if (strMoTaNoiDung != null) {
					ind.removeAll(Sharing.MoTaNoiDung);
					ind.addProperty(Sharing.MoTaNoiDung, strMoTaNoiDung);
				}
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
		} finally {
			om.close();
		}
		return isOk;
	}
	/**
	 * 
	 * @param strClassNganh
	 * @param strTenGoi
	 * @param strMoTaNoiDung
	 * @return
	 */
	public boolean insertNganh(String strClassNganh, String strTenGoi,
			String strMoTaNoiDung, String[] strThuocTinh) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		if (!ExistsIndividualName(strClassNganh, strTenGoi)) {
			try {
				OntClass oc = om.createClass(Sharing.getURI() + strClassNganh);
				int total = (searchIndividual(strClassNganh).length / 5) + 1;
				Individual ind = om.createIndividual(Sharing.getURI()
						+ strClassNganh + "_" + total, oc);
				if (strTenGoi != null) {
					ind.addProperty(Sharing.TenGoi, strTenGoi);
				}
				if (strMoTaNoiDung != null) {
					ind.addProperty(Sharing.MoTaNoiDung, strMoTaNoiDung);
				}
				if (strThuocTinh != null) {
					for (int i = 0; i < strThuocTinh.length; i++) {
						Individual resource = OntologyModel
								.GetbyInstance(strThuocTinh[i]);
						ind.addProperty(Sharing.LinhVucCoThuocTinh, resource);
						resource.addProperty(Sharing.ThuocTinhThuocLinhVuc, ind);
					}
				}
				om.commit();
				isOk = true;
			} catch (Exception e) {
				isOk = false;
			}
		}
		return isOk;
	}

	/**
	 * Cap nhat individual, neu gia tri tham so la null thi khong cap nhat
	 * 
	 * @param strIndividualNganh
	 * @param strTenGoi
	 * @param strMoTaNoiDung
	 * @param strCoTaiNguyen
	 */
	public boolean updateNganh(String strIndividualNganh, String strTenGoi,
			String strMoTaNoiDung, String[] strThuocTinh) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			Individual ind = om.getIndividual(Sharing.getURI()
					+ strIndividualNganh);
			if (ind != null) {
				if (strTenGoi != null) {
					ind.removeAll(Sharing.TenGoi);
					ind.addProperty(Sharing.TenGoi, strTenGoi);
				}
				if (strMoTaNoiDung != null) {
					ind.removeAll(Sharing.MoTaNoiDung);
					ind.addProperty(Sharing.MoTaNoiDung, strMoTaNoiDung);
				}
				if (strThuocTinh.length > 0) {
					ind.removeAll(Sharing.LinhVucCoThuocTinh);
				}
				
				// thuoc tinh co the da co. loai thuoc tinh trung
				if (strThuocTinh != null) {
					for (int i = 0; i < strThuocTinh.length; i++) {
						Individual resource = OntologyModel
								.GetbyInstance(strThuocTinh[i]);
						ind.addProperty(Sharing.LinhVucCoThuocTinh, resource);
						resource.addProperty(Sharing.ThuocTinhThuocLinhVuc, ind);
					}
				}
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
		} finally {
			om.close();
		}
		return isOk;
	}

	public void removePropertyFromNganh(String strIndividualNganh,
			String[] strCoTaiLieu) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om
				.getIndividual(Sharing.getURI() + strIndividualNganh);
		for (int i = 0; i < strCoTaiLieu.length; i++) {
			Individual resource = OntologyModel.GetbyInstance(strCoTaiLieu[i]);
			ind.removeProperty(Sharing.CoTaiLieu, resource);
		}
		om.commit();
	}

	public void deleteNganh(String strIndividualNganh) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Individual ind = om
				.getIndividual(Sharing.getURI() + strIndividualNganh);
		if (ind != null) {
			ind.removeAll(Sharing.TenGoi);
			ind.removeAll(Sharing.MoTaNoiDung);
			ind.removeAll(Sharing.CoTaiLieu);
			ind.removeAll(Sharing.LinhVucCoThuocTinh);
			ind.removeAll(Sharing.TaiLieuCoPhuongThuc);
		}
	}

	public String[] listTinhChat(String strClassName) {
		ArrayList<TinhChatResult> list = new ArrayList<TinhChatResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = "" + " PREFIX chiase: <" + Sharing.NS + "> \n"
				+ " PREFIX rdf: <" + Sharing.RDF + "> \n"
				+ " SELECT ?x ?tengoi ?oclass ?motanoidung \n" + " WHERE { \n"
				+ " ?x rdf:type ?oclass. \n" + " ?x chiase:TenGoi ?tengoi. \n"
				+ " ?x chiase:MoTaNoiDung ?motanoidung. \n" + " } \n"
				+ " ORDER by ?tengoi";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				TinhChatResult dtTinhChat = new TinhChatResult();
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("x").isLiteral()) {
					dtTinhChat.setStrIndividualName(binding.getLiteral("x")
							.getValue().toString());
				} else {
					dtTinhChat.setStrIndividualName(binding.getResource("x")
							.getLocalName().toString());
				}
				if (binding.get("tengoi").isLiteral()) {
					dtTinhChat.setStrTenGoi(binding.getLiteral("tengoi")
							.getValue().toString());
				} else {
					dtTinhChat.setStrTenGoi(binding.getResource("tengoi")
							.getLocalName().toString());
				}
				if (binding.get("oclass").isLiteral()) {
					dtTinhChat.setStrClassName(binding.getLiteral("oclass")
							.getValue().toString());
				} else {
					dtTinhChat.setStrClassName(binding.getResource("oclass")
							.getLocalName().toString());
				}
				if (binding.get("motanoidung").isLiteral()) {
					dtTinhChat.setStrMoTaNoiDung(binding.getLiteral(
							"motanoidung").getValue().toString());
				} else {
					dtTinhChat.setStrMoTaNoiDung(binding.getResource(
							"motanoidung").getLocalName().toString());
				}
				if (dtTinhChat.getStrClassName().equals(strClassName)) {
					list.add(dtTinhChat);
				}
			}
		} catch (Exception e) {
		} finally {
			queryexec.close();
		}
		FrameWork fw=new FrameWork();
		String[] strList = fw.convertArrayListTinhChat2StringArray(list);
		return strList;
	}

	public String[] searchIndividual(String strClassName) {
		ArrayList<ChuyenNganhResult> list = new ArrayList<ChuyenNganhResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
				+ " SELECT ?ind ?oclass ?tengoi ?motanoidung ?tenthuoctinh ?thuoctinh  \n"
				+ " WHERE \n"
				+ " { \n"
				+ "  ?ind rdf:type <"+Sharing.NS+strClassName+">. \n"
				+ "  ?ind chiase:TenGoi ?tengoi. \n"
				+ "  ?ind chiase:MoTaNoiDung ?motanoidung. \n"
				+ " OPTIONAL { \n"
				+ "  ?ind chiase:LinhVucCoThuocTinh ?thuoctinh. \n"
				+ "  ?thuoctinh chiase:TenGoi ?tenthuoctinh. \n"
				+ " } \n"
				+ " } \n" + " ORDER by ?ind";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				ChuyenNganhResult dtChuyenNganh = new ChuyenNganhResult();
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				//System.out.println(binding.toString());
				if (binding.get("ind").isLiteral()) {
					dtChuyenNganh.setStrIndividualName(binding
							.getLiteral("ind").getValue().toString());
				} else {
					dtChuyenNganh.setStrIndividualName(binding.getResource(
							"ind").getLocalName().toString());
				}
				if (binding.get("tengoi").isLiteral()) {
					dtChuyenNganh.setStrTenGoi(binding.getLiteral("tengoi")
							.getValue().toString());
				} else {
					dtChuyenNganh.setStrTenGoi(binding.getResource("tengoi")
							.getLocalName().toString());
				}
				dtChuyenNganh.setStrClassName(strClassName);
				if (binding.get("motanoidung").isLiteral()) {
					dtChuyenNganh.setStrMoTaNoiDung(binding.getLiteral(
							"motanoidung").getValue().toString());
				} else {
					dtChuyenNganh.setStrMoTaNoiDung(binding.getResource(
							"motanoidung").getLocalName().toString());
				}
				if (binding.get("thuoctinh").isLiteral()) {
					dtChuyenNganh.setStrLinhVucCoThuocTinh(binding.getLiteral(
							"thuoctinh").getValue().toString());
				} else {
					dtChuyenNganh.setStrLinhVucCoThuocTinh(binding.getResource(
							"thuoctinh").getLocalName().toString());
				}
				if (binding.get("tenthuoctinh").isLiteral()) {
					dtChuyenNganh.setstrTenThuocTinh(binding.getLiteral(
							"tenthuoctinh").getValue().toString());
				} else {
					dtChuyenNganh.setstrTenThuocTinh(binding.getResource(
							"tenthuoctinh").getLocalName().toString());
				}
				list.add(dtChuyenNganh);
			}
		} catch (Exception e) {
		}
		FrameWork fw=new FrameWork();
		String[] strList = fw.convertArrayListInd2StringArray(list);
		return strList;
	}
}
