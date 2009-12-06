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

import model.Database;
import model.FrameWork;
import model.OntologyModel;
import model.Sharing;


import view.KetQuaAdvanced;
import view.TaiLieuResult;

public class TaiNguyen {
	public TaiNguyen() {
	}

	public String[] searchTaiLieubyChuyenNganh(String tenchuyennganh) {

		String queryString = "" + "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "PREFIX rdf: <"
				+ Sharing.RDF
				+ "> \n"
				+ "PREFIX rdfs: <"
				+ Sharing.RDFS
				+ "> \n"
				+ "SELECT DISTINCT  ?x ?nhande ?tentacgia ?nhaxuatban ?ngonngu ?dinhdang ?motanoidung ?anhminhhoa ?totaldownload \n"
				+ "WHERE \n" + "{ \n" + " ?x chiase:NhanDe ?nhande. \n"
				+ " ?x chiase:CoTacGia ?TacGia. \n"
				+ " ?TacGia chiase:TenGoi ?tentacgia. \n"
				+ " ?x chiase:NhaXuatBan ?nhaxuatban. \n"
				+ " ?x chiase:NgonNgu ?ngonngu. \n"
				+ " ?x chiase:MoTaNoiDung ?motanoidung. \n"
				+ " ?x chiase:DinhDang ?dinhdang. \n"
				+ " ?x chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
				+ " ?thuoclinhvuc chiase:TenGoi ?tenchuyennganh. \n"
				+ " OPTIONAL { \n" + " ?x chiase:AnhMinhHoa ?anhminhhoa. \n"
				+ " } \n" + " OPTIONAL { \n"
				+ " ?x chiase:TotalDownload ?totaldownload. \n" + " } \n"
				+ " FILTER regex(?tenchuyennganh,\"" + tenchuyennganh
				+ "\", \"i\")\n" + "} \n" + "";
		// System.out.println(queryString);
		OntologyModel OM = new OntologyModel();
		String[] strList = OM.queryCommand(queryString, 9);
		return strList;
	}

	public String[] searchAdvanced(String linhvuc, String dinhdang,
			String theloai, String tacgia, String nhaxuatban, String ngonngu) {
		System.out.println("trong ham advance tai nguyen");
		String queryString = "" + "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "PREFIX rdf: <"
				+ Sharing.RDF
				+ "> \n"
				+ "PREFIX rdfs: <"
				+ Sharing.RDFS
				+ "> \n"
				+ "SELECT DISTINCT  ?x ?nhande ?tentacgia ?nhaxuatban ?ngonngu ?dinhdang ?anhminhhoa ?totaldownload ?theloai ?label \n"
				+ "WHERE \n" + "{ \n" + " ?x chiase:NhanDe ?nhande. \n"
				+ " ?x chiase:BanQuyen ?banquyen. \n"
				+ " ?x chiase:DinhDang ?dinhdang. \n"

				+ " ?x chiase:NguonGoc ?nguongoc. \n"
				+ " ?x chiase:NhaXuatBan ?nhaxuatban. \n"

				+ " ?x chiase:NoiChua ?noichua. \n"
				+ " ?x chiase:NgonNgu ?ngonngu. \n" + " OPTIONAL { \n"
				+ "  ?x chiase:CoTacGia ?cotacgia. \n"
				+ "  ?cotacgia chiase:TenGoi ?tentacgia. \n" + " } \n"
				+ " OPTIONAL { \n" + "  ?x chiase:AnhMinhHoa ?anhminhhoa. \n"
				+ " } \n" + " OPTIONAL { \n"
				+ "  ?x chiase:TotalDownload ?totaldownload. \n" + " } \n";

		if (!linhvuc.equals("")) {
			queryString = queryString
					+ " ?x chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
					+ " ?thuoclinhvuc rdf:type <" + Sharing.NS + linhvuc
			
					
					+ ">. \n";
		}
		if (!theloai.equals("")) {
			queryString = queryString + " ?x rdf:type <" + Sharing.NS + theloai
					+ ">.\n";
		} else {
			queryString = queryString + " ?x rdf:type ?theloai.\n"
					+ " ?theloai rdfs:label ?label. \n";
		}
		if (!dinhdang.equals("")) {
			queryString = queryString + " FILTER regex(?dinhdang,\"" + dinhdang
					+ "\", \"i\")\n";
		}
		if (!nhaxuatban.equals("")) {
			queryString = queryString + " FILTER regex(?nhaxuatban,\""
					+ nhaxuatban + "\", \"i\")\n";
		}
		if (!ngonngu.equals("")) {
			queryString = queryString + " FILTER regex(?ngonngu,\"" + ngonngu
					+ "\", \"i\")\n";
		}
		if (!tacgia.equals("")) {
			queryString = queryString + " FILTER regex(?tentacgia,\"" + tacgia
					+ "\", \"i\")\n";
		}
		queryString = queryString + "}";
		//System.out.println(queryString);
		ArrayList<TaiLieuResult> list = new ArrayList<TaiLieuResult>();
		Database db = new Database();
		db.LoadOnt2Database();
		
		System.out.println("lmjtrong ham advance tai nguyen1");
		
		OntModel om = Database.getOntologyModel();
		System.out.println("lau OntoModel"+om.expandPrefix(nhaxuatban));
		Query query = QueryFactory.create(queryString);
		
		System.out.println("tao cau truy van"+query.toString());
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		System.out.println("Thuc hien tyruy van");
		try {
			ResultSet rs = queryexec.execSelect();
			if(rs.hasNext()==false)System.out.println("ko cos gi tri");
			else{
				
			
			while (rs.hasNext()) {
				System.out.println("thucj thi truy van");
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				TaiLieuResult dtTaiNguyen = new TaiLieuResult();
				if (binding.get("x").isLiteral()) {
					dtTaiNguyen.setId(binding.getLiteral("x").getValue()
							.toString());
				} else {
					dtTaiNguyen.setId(binding.getResource("x").getLocalName()
							.toString());
				}
				if (binding.get("nhande").isLiteral()) {
					dtTaiNguyen.setNhanDe(binding.getLiteral("nhande")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNhanDe(binding.getResource("nhande")
							.getLocalName().toString());
				}
				if (binding.get("totaldownload") != null) {
					if (binding.get("totaldownload").isLiteral()) {
						dtTaiNguyen.setTotalDownLoad(binding.getLiteral(
								"totaldownload").getValue().toString());
					} else {
						dtTaiNguyen.setTotalDownLoad(binding.getResource(
								"totaldownload").getLocalName().toString());
					}
				} else {
					dtTaiNguyen.setTotalDownLoad("0");
				}
				if (binding.get("anhminhhoa") != null) {
					if (binding.get("anhminhhoa").isLiteral()) {
						dtTaiNguyen.setAnhMinhHoa(binding.getLiteral(
								"anhminhhoa").getValue().toString());
					} else {
						dtTaiNguyen.setAnhMinhHoa(binding.getResource(
								"anhminhhoa").getLocalName().toString());
					}
				}
				if (dinhdang.equals("")) {
					if (binding.get("dinhdang").isLiteral()) {
						dtTaiNguyen.setDinhDang(binding.getLiteral("dinhdang")
								.getValue().toString());
					} else {
						dtTaiNguyen.setDinhDang(binding.getResource("dinhdang")
								.getLocalName().toString());
					}
				} else {
					dtTaiNguyen.setDinhDang(dinhdang);
				}
				if (nhaxuatban.equals("")) {
					if (binding.get("nhaxuatban").isLiteral()) {
						dtTaiNguyen.setXuatBan(binding.getLiteral("nhaxuatban")
								.getValue().toString());
					} else {
						dtTaiNguyen.setXuatBan(binding
								.getResource("nhaxuatban").getLocalName()
								.toString());
					}
				} else {
					dtTaiNguyen.setXuatBan(nhaxuatban);
				}
				if(ngonngu.equals("")){
					if (binding.get("ngonngu").isLiteral()) {
						dtTaiNguyen.setNgonNgu(binding.getLiteral("ngonngu")
								.getValue().toString());
					} else {
						dtTaiNguyen.setNgonNgu(binding.getResource("ngonngu")
								.getLocalName().toString());
					}
				} else{
					dtTaiNguyen.setNgonNgu(ngonngu);
				}
				if(tacgia.equals("")){
					if (binding.get("tentacgia").isLiteral()) {
						dtTaiNguyen.setTenTacGia(binding.getLiteral("tentacgia")
								.getValue().toString());
					} else {
						dtTaiNguyen.setTenTacGia(binding.getResource("tentacgia")
								.getLocalName().toString());
					}
				} else{
					dtTaiNguyen.setTenTacGia(tacgia);
				}
				if(theloai.equals("")){
					if (binding.get("theloai").isLiteral()) {
						dtTaiNguyen.setTheLoai(binding.getLiteral("theloai")
								.getValue().toString());
					} else {
						dtTaiNguyen.setTheLoai(binding.getResource("theloai")
								.getLocalName().toString());
					}
					
					if (binding.get("label").isLiteral()) {
						dtTaiNguyen.setTheLoaiLabel(binding.getLiteral("label")
								.getValue().toString());
					} else {
						dtTaiNguyen.setTheLoaiLabel(binding.getResource("label")
								.getLocalName().toString());
					}		
				}
				list.add(dtTaiNguyen);
			}
		} 
		}catch (Exception e) {

			System.out.println("bi loi: "+e);
		}
		int totalField=11;
		String[] strList = new String[list.size()*totalField];
		for (int i = 0; i < list.size(); i++) {
			TaiLieuResult dtTaiLieu = (TaiLieuResult) list.get(i);
			strList[i * totalField + 0] = dtTaiLieu.getId();
			strList[i * totalField + 1] = dtTaiLieu.getNhanDe();
			strList[i * totalField + 2] = dtTaiLieu.getXuatBan();
			strList[i * totalField + 3] = dtTaiLieu.getNgonNgu();
			strList[i * totalField + 4] = dtTaiLieu.getDinhDang();
			strList[i * totalField + 5] = dtTaiLieu.getTenTacGia();
			strList[i * totalField + 6] = dtTaiLieu.getMoTa();
			strList[i * totalField + 7] = dtTaiLieu.getAnhMinhHoa();
			strList[i * totalField + 8] = dtTaiLieu.getTotalDownLoad();
			strList[i * totalField + 9] = dtTaiLieu.getTheLoai();
			strList[i * totalField + 10] = dtTaiLieu.getTheLoaiLabel();
			
		}
		return strList;
	}

	public String[] searchTaiLieubyTheLoai(String theloaiid) {
		String queryString = "" + "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "PREFIX rdf: <"
				+ Sharing.RDF
				+ "> \n"
				+ "PREFIX rdfs: <"
				+ Sharing.RDFS
				+ "> \n"
				+ "SELECT DISTINCT  ?x ?nhande ?tentacgia ?nhaxuatban ?ngonngu \n"
				+ "WHERE \n" + "{ \n" + "?x chiase:NhanDe ?nhande. \n"
				+ "?x chiase:CoTacGia ?TacGia. \n"
				+ "?TacGia chiase:TenGoi ?tentacgia. \n"
				+ "?x chiase:NhaXuatBan ?nhaxuatban. \n"
				+ "?x chiase:NgonNgu ?ngonngu. \n" + "?x rdf:type <"
				+ Sharing.NS + theloaiid + ">" + "} \n" + "";
		System.out.println(queryString);
		OntologyModel OM = new OntologyModel();
		String[] strList = OM.queryCommand(queryString, 5);
		return strList;
	}

	public String[] searchTaiLieuTopDownload(int top, int offset) {
		ArrayList<TaiLieuResult> list = new ArrayList<TaiLieuResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String queryString = "" + "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "PREFIX rdf: <" + Sharing.RDF + "> \n" + "PREFIX rdfs: <"
				+ Sharing.RDFS + "> \n" + "SELECT * \n" + "WHERE \n" + "{ \n"
				+ " ?x chiase:NhanDe ?nhande. \n"
				+ " ?x chiase:NhaXuatBan ?nhaxuatban. \n"
				+ " ?x chiase:NgonNgu ?ngonngu. \n"
				+ " ?x chiase:BanQuyen ?banquyen. \n"
				+ " ?x chiase:AnhMinhHoa ?anhminhhoa. \n"
				+ " ?x chiase:DinhDang ?dinhdang. \n"
				+ " ?x chiase:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia chiase:TenGoi ?tentacgia. \n"
				+ " ?x chiase:TotalDownload ?totaldownload. \n"
				+ " ?x chiase:MoTaNoiDung ?motanoidung. \n" + "} \n"
				+ " Order by DESC(?totaldownload) Limit " + top + "\n"
				+ " OFFSET " + offset * top + "\n" + "";
		System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				TaiLieuResult dtTaiNguyen = new TaiLieuResult();
				if (binding.get("x").isLiteral()) {
					dtTaiNguyen.setId(binding.getLiteral("x").getValue()
							.toString());
				} else {
					dtTaiNguyen.setId(binding.getResource("x").getLocalName()
							.toString());
				}

				if (binding.get("nhande").isLiteral()) {
					dtTaiNguyen.setNhanDe(binding.getLiteral("nhande")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNhanDe(binding.getResource("nhande")
							.getLocalName().toString());
				}
				if (binding.get("banquyen").isLiteral()) {
					dtTaiNguyen.setBanQuyen(binding.getLiteral("banquyen")
							.getValue().toString());
				} else {
					dtTaiNguyen.setBanQuyen(binding.getResource("banquyen")
							.getLocalName().toString());							
						}
				if (binding.get("nhaxuatban").isLiteral()) {
					dtTaiNguyen.setXuatBan(binding.getLiteral("nhaxuatban")
							.getValue().toString());
				} else {
					dtTaiNguyen.setXuatBan(binding.getResource("nhaxuatban")
							.getLocalName().toString());
				}
				if (binding.get("dinhdang").isLiteral()) {
					dtTaiNguyen.setDinhDang(binding.getLiteral("dinhdang")
							.getValue().toString());
				} else {
					dtTaiNguyen.setDinhDang(binding.getResource("dinhdang")
							.getLocalName().toString());
				}
				if (binding.get("ngonngu").isLiteral()) {
					dtTaiNguyen.setNgonNgu(binding.getLiteral("ngonngu")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNgonNgu(binding.getResource("ngonngu")
							.getLocalName().toString());
				}
				if (binding.get("anhminhhoa").isLiteral()) {
					dtTaiNguyen.setAnhMinhHoa(binding.getLiteral("anhminhhoa")
							.getValue().toString());
				} else {
					dtTaiNguyen.setAnhMinhHoa(binding.getResource("anhminhhoa")
							.getLocalName().toString());
				}
				if (binding.get("motanoidung").isLiteral()) {
					dtTaiNguyen.setMoTa(binding.getLiteral("motanoidung")
							.getValue().toString());
				} else {
					dtTaiNguyen.setMoTa(binding.getResource("motanoidung")
							.getLocalName().toString());
				}

				if (binding.get("tentacgia").isLiteral()) {
					dtTaiNguyen.setTenTacGia(binding.getLiteral("tentacgia")
							.getValue().toString());
				} else {
					dtTaiNguyen.setTenTacGia(binding.getResource("tentacgia")
							.getLocalName().toString());
				}

				if (binding.get("totaldownload").isLiteral()) {
					dtTaiNguyen.setTotalDownLoad(binding.getLiteral(
							"totaldownload").getValue().toString());
				} else {
					dtTaiNguyen.setTotalDownLoad(binding.getResource(
							"totaldownload").getLocalName().toString());
				}

				list.add(dtTaiNguyen);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			om.close();
		}
		FrameWork fw = new FrameWork();
		String[] strList = fw.convertArrayListTaiLieu2StringArray(list);
		return strList;
	}

	public String[] searchTopNew(int top, int offset) {
		String[] strList = null;
		int totalField = 8;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String queryString = "" + "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "PREFIX rdf: <"
				+ Sharing.RDF
				+ "> \n"
				+ "PREFIX rdfs: <"
				+ Sharing.RDFS
				+ "> \n"
				+ "SELECT DISTINCT  ?x ?nhande ?ngonngu ?tentacgia ?nhaxuatban ?theloai ?tenchuyennganh ?anhminhhoa \n"
				+ "WHERE \n" + "{ \n" + " ?x chiase:NhanDe ?nhande. \n"
				+ " ?x chiase:NhaXuatBan ?nhaxuatban. \n"
				+ " ?x chiase:NgonNgu ?ngonngu. \n"
				+ " ?x chiase:AnhMinhHoa ?anhminhhoa. \n"
				+ " ?x chiase:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia chiase:TenGoi ?tentacgia. \n"

				+ " ?x chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
				+ " ?thuoclinhvuc chiase:TenGoi ?tenchuyennganh. \n"

				+ " OPTIONAL \n" + " { \n" + " ?x rdf:type ?thuoctheloai.\n"
				+ " ?thuoctheloai rdfs:label ?theloai. \n" + " } \n" + "} \n"
				+ " Order by DESC(?x) Limit " + top + "\n" + " OFFSET "
				+ offset * top + "\n" + "";
		System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			ArrayList<ResultBinding> al = new ArrayList<ResultBinding>();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				al.add(binding);
			}
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

				if (al.get(i).get("ngonngu").isLiteral()) {
					strList[totalField * i + 2] = al.get(i).getLiteral(
							"ngonngu").getValue().toString();
				} else {
					strList[totalField * i + 2] = al.get(i).getResource(
							"ngonngu").getLocalName().toString();
				}

				if (al.get(i).get("tentacgia").isLiteral()) {
					strList[totalField * i + 3] = al.get(i).getLiteral(
							"tentacgia").getValue().toString();
				} else {
					strList[totalField * i + 3] = al.get(i).getResource(
							"tentacgia").getLocalName().toString();
				}

				if (al.get(i).get("nhaxuatban").isLiteral()) {
					strList[totalField * i + 4] = al.get(i).getLiteral(
							"nhaxuatban").getValue().toString();
				} else {
					strList[totalField * i + 4] = al.get(i).getResource(
							"nhaxuatban").getLocalName().toString();
				}

				if (al.get(i).get("theloai").isLiteral()) {
					strList[totalField * i + 5] = al.get(i).getLiteral(
							"theloai").getValue().toString();
				} else {
					strList[totalField * i + 5] = al.get(i).getResource(
							"theloai").getLocalName().toString();
				}

				if (al.get(i).get("tenchuyennganh").isLiteral()) {
					strList[totalField * i + 6] = al.get(i).getLiteral(
							"tenchuyennganh").getValue().toString();
				} else {
					strList[totalField * i + 6] = al.get(i).getResource(
							"tenchuyennganh").getLocalName().toString();
				}
				if (al.get(i).get("anhminhhoa").isLiteral()) {
					strList[totalField * i + 7] = al.get(i).getLiteral(
							"anhminhhoa").getValue().toString();
				} else {
					strList[totalField * i + 7] = al.get(i).getResource(
							"anhminhhoa").getLocalName().toString();
				}
			}
		} catch (Exception e) {

		} finally {
			queryexec.close();
		}
		return strList;
	}

	public String[] searchPrefixforLinhVuc(String thuoclinhvuc, String prefix) {

		String queryString = "" + "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "PREFIX rdf: <" + Sharing.RDF + "> \n" + "PREFIX rdfs: <"
				+ Sharing.RDFS + "> \n" + "SELECT DISTINCT ?tunhac \n" + "{ \n"
				+ " ?x chiase:NhanDe ?tunhac. \n"
				+ " ?x chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
				+ " ?thuoclinhvuc chiase:TenGoi ?tenchuyennganh. \n"
				+ " FILTER regex(?tenchuyennganh,\"" + thuoclinhvuc
				+ "\", \"i\").\n" + " FILTER regex(?tunhac,\"" + prefix
				+ "\", \"i\")\n" + "}";
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		System.out.println(queryString);
		ArrayList<String> list = new ArrayList<String>();
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				String s = "";
				if (binding.get("tunhac").isLiteral()) {
					s = binding.getLiteral("tunhac").getValue().toString();
				} else {
					s = binding.getResource("tunhac").getLocalName().toString();
				}
				list.add(s);
			}
			om.close();
		} catch (Exception e) {
			om.close();
		}
		String[] strList = new String[list.size()];
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				strList[i] = (String) list.get(i);
			}
		}
		return strList;
	}

	public String[] searchBestTaiLieu(String tentacgia, String thuoclinhvuc,
			String tugoiy) {
		String[] strList = null;
		int totalField = 8;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String queryString = "" + "PREFIX chiase: <"
				+ Sharing.NS
				+ "> \n"
				+ "PREFIX rdf: <"
				+ Sharing.RDF
				+ "> \n"
				+ "PREFIX rdfs: <"
				+ Sharing.RDFS
				+ "> \n"
				+ "SELECT DISTINCT  ?x ?nhande ?ngonngu ?tentacgia ?nhaxuatban ?theloai ?tenchuyennganh_thuc ?linhvuc \n"
				+ "WHERE \n" + "{ \n" + "?x chiase:NhanDe ?nhande. \n"
				+ "?x chiase:NhaXuatBan ?nhaxuatban. \n"
				+ "?x chiase:NgonNgu ?ngonngu. \n"

				+ " ?x chiase:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia chiase:TenGoi ?tentacgia. \n" + "{ \n"
				+ " ?x chiase:CoTacGia ?cotacgia. \n"
				+ " ?cotacgia chiase:TenGoi ?tacgia. \n" + "} \n" + "UNION \n"
				+ "{ \n" + " ?x chiase:CongTac ?congtac."
				+ " ?congtac chiase:TenGoi ?tacgia." + "} \n"

				+ " ?x chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
				+ " ?thuoclinhvuc chiase:TenGoi ?tenchuyennganh_thuc. \n"
				+ " ?thuoclinhvuc rdf:type ?linhvuc.\n"

				+ " ?allthuoclinhvuc rdf:type ?linhvuc.\n"
				+ " ?allthuoclinhvuc chiase:TenGoi ?tenchuyennganh.\n"

				+ " ?x chiase:TaiLieuCoPhuongThuc ?phuongthuc. \n"
				+ " ?phuongthuc chiase:TenGoi ?tenphuongthuc. \n"

				+ "OPTIONAL \n" + "{ \n" + " ?x rdf:type ?theloai.\n" + "} \n"

				+ " FILTER regex(?tacgia,\"" + tentacgia + "\", \"i\")\n"
				+ " FILTER regex(?tenchuyennganh,\"" + thuoclinhvuc
				+ "\", \"i\")\n" + " FILTER (regex(?tenphuongthuc,\"" + tugoiy
				+ "\", \"i\") || regex(?nhande,\"" + tugoiy + "\", \"i\"))\n"
				+ "} \n" + "";
		System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			ArrayList<ResultBinding> al = new ArrayList<ResultBinding>();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				al.add(binding);
			}
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

				if (al.get(i).get("ngonngu").isLiteral()) {
					strList[totalField * i + 2] = al.get(i).getLiteral(
							"ngonngu").getValue().toString();
				} else {
					strList[totalField * i + 2] = al.get(i).getResource(
							"ngonngu").getLocalName().toString();
				}

				if (al.get(i).get("tentacgia").isLiteral()) {
					strList[totalField * i + 3] = al.get(i).getLiteral(
							"tentacgia").getValue().toString();
				} else {
					strList[totalField * i + 3] = al.get(i).getResource(
							"tentacgia").getLocalName().toString();
				}

				if (al.get(i).get("nhaxuatban").isLiteral()) {
					strList[totalField * i + 4] = al.get(i).getLiteral(
							"nhaxuatban").getValue().toString();
				} else {
					strList[totalField * i + 4] = al.get(i).getResource(
							"nhaxuatban").getLocalName().toString();
				}

				if (al.get(i).get("theloai").isLiteral()) {
					strList[totalField * i + 5] = al.get(i).getLiteral(
							"theloai").getValue().toString();
				} else {
					strList[totalField * i + 5] = al.get(i).getResource(
							"theloai").getLocalName().toString();
				}

				if (al.get(i).get("tenchuyennganh_thuc").isLiteral()) {
					strList[totalField * i + 6] = al.get(i).getLiteral(
							"tenchuyennganh_thuc").getValue().toString();
				} else {
					strList[totalField * i + 6] = al.get(i).getResource(
							"tenchuyennganh_thuc").getLocalName().toString();
				}

				if (al.get(i).get("linhvuc").isLiteral()) {
					strList[totalField * i + 7] = al.get(i).getLiteral(
							"linhvuc").getValue().toString();
				} else {
					strList[totalField * i + 7] = al.get(i).getResource(
							"linhvuc").getLocalName().toString();
				}
			}
		} catch (Exception e) {

		} finally {
			queryexec.close();
		}
		return strList;
	}

	public String[] searchbyId(String Id) {
		ArrayList<TaiLieuResult> list = new ArrayList<TaiLieuResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String x = "<" + Sharing.NS + Id + ">";
		String queryString = "PREFIX chiase: <" + Sharing.NS + "> \n"
				+ "SELECT * \n " + "WHERE \n" + "{ \n" + x
				+ " chiase:NhanDe ?nhande. \n" + x
				+ " chiase:BanQuyen ?banquyen. \n" + x
				+ " chiase:SoTrang ?sotrang. \n" + x
				+ " chiase:NhaXuatBan ?nhaxuatban. \n" + x
				+ " chiase:DeMuc ?demuc. \n" + x
				+ " chiase:NgayThang ?ngaythang. \n" + x
				+ " chiase:NoiChua ?noichua. \n" + x
				+ " chiase:DinhDang ?dinhdang. \n" + x
				+ " chiase:NgonNgu ?ngonngu. \n" + x
				+ " chiase:AnhMinhHoa ?anhminhhoa. \n" + x
				+ " chiase:MoTaNoiDung ?motanoidung. \n" + x
				+ " chiase:NguonGoc ?nguongoc. \n" + "OPTIONAL { \n" + x
				+ " chiase:CoTacGia ?cotacgia. \n"
				+ "?cotacgia chiase:TenGoi ?tentacgia. \n" + " } \n"
				+ "OPTIONAL { \n" + x + " chiase:CongTac ?congtac. \n"
				+ "?congtac chiase:TenGoi ?tencongtac. \n" + " } \n"
				+ "OPTIONAL { \n" + x
				+ " chiase:ThuocLinhVuc ?thuoclinhvuc. \n"
				+ "?thuoclinhvuc chiase:TenGoi ?tenlinhvuc. \n" + " } \n"
				+ "OPTIONAL { \n" + x
				+ " chiase:TaiLieuCoPhuongThuc ?phuongthuc. \n"
				+ "?phuongthuc chiase:TenGoi ?tenphuongthuc. \n" + " } \n"
				+ "OPTIONAL { \n" + x
				+ " chiase:TotalDownload ?totaldownload. } \n" + " }";
		// System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				TaiLieuResult dtTaiNguyen = new TaiLieuResult();
				dtTaiNguyen.setId(Id);

				if (binding.get("nhande").isLiteral()) {
					dtTaiNguyen.setNhanDe(binding.getLiteral("nhande")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNhanDe(binding.getResource("nhande")
							.getLocalName().toString());
				}
				if (binding.get("banquyen").isLiteral()) {
					dtTaiNguyen.setBanQuyen(binding.getLiteral("banquyen")
							.getValue().toString());
				} else {
					dtTaiNguyen.setBanQuyen(binding.getResource("banquyen")
							.getLocalName().toString());
				}
				if (binding.get("sotrang").isLiteral()) {
					dtTaiNguyen.setSoTrang(binding.getLiteral("sotrang")
							.getValue().toString());
				} else {
					dtTaiNguyen.setSoTrang(binding.getResource("sotrang")
							.getLocalName().toString());
				}
				if (binding.get("nhaxuatban").isLiteral()) {
					dtTaiNguyen.setXuatBan(binding.getLiteral("nhaxuatban")
							.getValue().toString());
				} else {
					dtTaiNguyen.setXuatBan(binding.getResource("nhaxuatban")
							.getLocalName().toString());
				}
				if (binding.get("demuc").isLiteral()) {
					dtTaiNguyen.setDeMuc(binding.getLiteral("demuc").getValue()
							.toString());
				} else {
					dtTaiNguyen.setDeMuc(binding.getResource("demuc")
							.getLocalName().toString());
				}
				if (binding.get("ngaythang").isLiteral()) {
					dtTaiNguyen.setNgayThang(binding.getLiteral("ngaythang")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNgayThang(binding.getResource("ngaythang")
							.getLocalName().toString());
				}
				if (binding.get("noichua").isLiteral()) {
					dtTaiNguyen.setNoiChua(binding.getLiteral("noichua")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNoiChua(binding.getResource("noichua")
							.getLocalName().toString());
				}
				if (binding.get("dinhdang").isLiteral()) {
					dtTaiNguyen.setDinhDang(binding.getLiteral("dinhdang")
							.getValue().toString());
				} else {
					dtTaiNguyen.setDinhDang(binding.getResource("dinhdang")
							.getLocalName().toString());
				}
				if (binding.get("ngonngu").isLiteral()) {
					dtTaiNguyen.setNgonNgu(binding.getLiteral("ngonngu")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNgonNgu(binding.getResource("ngonngu")
							.getLocalName().toString());
				}
				if (binding.get("anhminhhoa").isLiteral()) {
					dtTaiNguyen.setAnhMinhHoa(binding.getLiteral("anhminhhoa")
							.getValue().toString());
				} else {
					dtTaiNguyen.setAnhMinhHoa(binding.getResource("anhminhhoa")
							.getLocalName().toString());
				}
				if (binding.get("motanoidung").isLiteral()) {
					dtTaiNguyen.setMoTa(binding.getLiteral("motanoidung")
							.getValue().toString());
				} else {
					dtTaiNguyen.setMoTa(binding.getResource("motanoidung")
							.getLocalName().toString());
				}
				if (binding.get("nguongoc").isLiteral()) {
					dtTaiNguyen.setNguonGoc(binding.getLiteral("nguongoc")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNguonGoc(binding.getResource("nguongoc")
							.getLocalName().toString());
				}
				if (binding.get("tentacgia") != null) {
					if (binding.get("tentacgia").isLiteral()) {
						dtTaiNguyen.setTenTacGia(binding
								.getLiteral("tentacgia").getValue().toString());
					} else {
						dtTaiNguyen.setTenTacGia(binding.getResource(
								"tentacgia").getLocalName().toString());
					}
				}
				if (binding.get("tencongtac") != null) {
					if (binding.get("tencongtac").isLiteral()) {
						dtTaiNguyen.setNguoiCongTac(binding.getLiteral(
								"tencongtac").getValue().toString());
					} else {
						dtTaiNguyen.setNguoiCongTac(binding.getResource(
								"tencongtac").getLocalName().toString());
					}
				}
				if (binding.get("tenlinhvuc") != null) {
					if (binding.get("tenlinhvuc").isLiteral()) {
						dtTaiNguyen.setTenLinhVuc(binding.getLiteral(
								"tenlinhvuc").getValue().toString());
					} else {
						dtTaiNguyen.setTenLinhVuc(binding.getResource(
								"tenlinhvuc").getLocalName().toString());
					}
				}
				if (binding.get("tenphuongthuc") != null) {
					if (binding.get("tenphuongthuc").isLiteral()) {
						dtTaiNguyen.setCoPhuongThuc(binding.getLiteral(
								"tenphuongthuc").getValue().toString());
					} else {
						dtTaiNguyen.setCoPhuongThuc(binding.getResource(
								"tenphuongthuc").getLocalName().toString());
					}
				}
				if (binding.get("totaldownload") != null) {
					if (binding.get("tenphuongthuc").isLiteral()) {
						dtTaiNguyen.setTotalDownLoad(binding.getLiteral(
								"totaldownload").getValue().toString());
					} else {
						dtTaiNguyen.setTotalDownLoad(binding.getResource(
								"totaldownload").getLocalName().toString());
					}
				}
				list.add(dtTaiNguyen);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			om.close();
		}
		FrameWork fw = new FrameWork();
		String[] strList = fw.convertArrayListTaiLieu2StringArray(list);
		return strList;
	}

	public String[] searchbyKeywordAdvanced(String keyword) {
		OntologyModel OM = new OntologyModel();
		ArrayList<KetQuaAdvanced> list = OM.searchbyKeyword(keyword);
		FrameWork fw = new FrameWork();
		String[] strList = fw.convertArrayListKetQua2StringArray(list);
		return strList;
	}

	public String[] searchbyPrefix(String prefix) {
		OntologyModel OM = new OntologyModel();
		return OM.searchbyPrefix(prefix);
	}

	public String[] searchbyKeyword(String keyword) {
		ArrayList<TaiLieuResult> list = new ArrayList<TaiLieuResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String queryString = "PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
				+ "SELECT DISTINCT ?x ?nhande ?banquyen ?dinhdang ?nguongoc ?nhaxuatban ?noichua ?ngonngu ?tentacgia ?anhminhhoa ?totaldownload \n "
				+ "WHERE \n"
				+ "{ \n"
				+ "?x chiase:NhanDe ?nhande. \n"
				+ "?x chiase:BanQuyen ?banquyen. \n"
				+ "?x chiase:DinhDang ?dinhdang. \n"

				+ "?x chiase:NguonGoc ?nguongoc. \n"
				+ "?x chiase:NhaXuatBan ?nhaxuatban. \n"

				+ "?x chiase:NoiChua ?noichua. \n"
				+ "?x chiase:NgonNgu ?ngonngu. \n"
				+ "OPTIONAL { \n"
				+ "?x chiase:CoTacGia ?cotacgia. \n"
				+ "?cotacgia chiase:TenGoi ?tentacgia. \n"
				+ "} \n"
				+ "OPTIONAL { \n"
				+ "?x chiase:AnhMinhHoa ?anhminhhoa. \n"
				+ "} \n"
				+ "OPTIONAL { \n"
				+ "?x chiase:TotalDownload ?totaldownload. \n"
				+ "} \n"
				+ "FILTER (regex(?nhande,'"
				+ keyword
				+ "',\"i\") || regex(?banquyen,'"
				+ keyword
				+ "',\"i\") || regex(?nguongoc,'"
				+ keyword
				+ "',\"i\") || regex(?nhaxuatban,'"
				+ keyword
				+ "',\"i\") || regex(?tentacgia,'" + keyword + "',\"i\") )  \n"

				+ "}";
		System.out.println(queryString);
		Query query = QueryFactory.create(queryString);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				TaiLieuResult dtTaiNguyen = new TaiLieuResult();
				if (binding.get("x").isLiteral()) {
					dtTaiNguyen.setId(binding.getLiteral("x").getValue()
							.toString());
				} else {
					dtTaiNguyen.setId(binding.getResource("x").getLocalName()
							.toString());
				}
				if (binding.get("nhande").isLiteral()) {
					dtTaiNguyen.setNhanDe(binding.getLiteral("nhande")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNhanDe(binding.getResource("nhande")
							.getLocalName().toString());
				}
				if (binding.get("banquyen").isLiteral()) {
					dtTaiNguyen.setBanQuyen(binding.getLiteral("banquyen")
							.getValue().toString());
				} else {
					dtTaiNguyen.setBanQuyen(binding.getResource("banquyen")
							.getLocalName().toString());
				}

				if (binding.get("dinhdang").isLiteral()) {
					dtTaiNguyen.setDinhDang(binding.getLiteral("dinhdang")
							.getValue().toString());
				} else {
					dtTaiNguyen.setDinhDang(binding.getResource("dinhdang")
							.getLocalName().toString());
				}
				if (binding.get("nguongoc").isLiteral()) {
					dtTaiNguyen.setNguonGoc(binding.getLiteral("nguongoc")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNguonGoc(binding.getResource("nguongoc")
							.getLocalName().toString());
				}
				if (binding.get("nhaxuatban").isLiteral()) {
					dtTaiNguyen.setXuatBan(binding.getLiteral("nhaxuatban")
							.getValue().toString());
				} else {
					dtTaiNguyen.setXuatBan(binding.getResource("nhaxuatban")
							.getLocalName().toString());
				}
				if (binding.get("noichua").isLiteral()) {
					dtTaiNguyen.setNoiChua(binding.getLiteral("noichua")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNoiChua(binding.getResource("noichua")
							.getLocalName().toString());
				}
				if (binding.get("ngonngu").isLiteral()) {
					dtTaiNguyen.setNgonNgu(binding.getLiteral("ngonngu")
							.getValue().toString());
				} else {
					dtTaiNguyen.setNgonNgu(binding.getResource("ngonngu")
							.getLocalName().toString());
				}
				if (binding.get("tentacgia").isLiteral()) {
					dtTaiNguyen.setTenTacGia(binding.getLiteral("tentacgia")
							.getValue().toString());
				} else {
					dtTaiNguyen.setTenTacGia(binding.getResource("tentacgia")
							.getLocalName().toString());
				}
				if (binding.get("totaldownload") != null) {
					if (binding.get("totaldownload").isLiteral()) {
						dtTaiNguyen.setTotalDownLoad(binding.getLiteral(
								"totaldownload").getValue().toString());
					} else {
						dtTaiNguyen.setTotalDownLoad(binding.getResource(
								"totaldownload").getLocalName().toString());
					}
				} else {
					dtTaiNguyen.setTotalDownLoad("0");
				}
				if (binding.get("anhminhhoa") != null) {
					if (binding.get("anhminhhoa").isLiteral()) {
						dtTaiNguyen.setAnhMinhHoa(binding.getLiteral(
								"anhminhhoa").getValue().toString());
					} else {
						dtTaiNguyen.setAnhMinhHoa(binding.getResource(
								"anhminhhoa").getLocalName().toString());
					}
				}
				list.add(dtTaiNguyen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			om.close();
		}
		FrameWork fw = new FrameWork();
		String[] strList = fw.convertArrayListTaiLieu2StringArray(list);
		return strList;
	}

	
	// tinh xem 1 lop co bao nhieu the hien
	public long total(String parentClass) {
		long total = 0;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String strQuery = ""
				+ " PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
				+ " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
				+ " PREFIX chiase: <http://eduportal.vn/chiase/myontology#> \n"
				+ " SELECT ?tainguyen ?oclassParent \n" + " WHERE \n" + " { \n"
				+ "  ?tainguyen rdf:type ?oclass. \n"
				+ "  ?oclass rdfs:subClassOf ?oclassParent. \n" + " } \n" + "";
		Query query = QueryFactory.create(strQuery);
		QueryExecution queryexec = QueryExecutionFactory.create(query, om);
		try {
			ResultSet rs = queryexec.execSelect();
			while (rs.hasNext()) {
				String s = "";
				Object obj = rs.next();
				ResultBinding binding = (ResultBinding) obj;
				if (binding.get("oclassParent").isLiteral()) {
					s = binding.getLiteral("oclassParent").getValue()
							.toString();
				} else {
					s = binding.getResource("oclassParent").getLocalName()
							.toString();
				}
				if (s.equals(parentClass)) {
					total = total + 1;
				}
			}
		} catch (Exception ex) {

		}
		return total;
	}
	public boolean insertTaiLieuPhp(String TheLoaiId, String NhanDe,
			String BanQuyen, String SoTrang, String NhaXuatBan, String DeMuc,
			String NgayThang, String DinhDang, String NgonNgu,
			String MoTaNoiDung, String NguonGoc, String CoTacGia,
			String CongTac, String ThuocLinhVuc, String TaiLieuCoPhuongThuc,
			String totalplus,String anhminhhoa,String noichua){
		boolean b=false;
		long ttp=Long.parseLong(totalplus);
		b=insertTaiLieu(TheLoaiId, NhanDe, BanQuyen, SoTrang, NhaXuatBan, DeMuc, NgayThang, DinhDang, NgonNgu, MoTaNoiDung, NguonGoc, CoTacGia, CongTac, ThuocLinhVuc, TaiLieuCoPhuongThuc, ttp);
		b=b && uploadTaiNguyenImage(anhminhhoa, ttp);
		b=b && uploadTaiNguyenFile(noichua, ttp);
		return b;	
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
	public boolean insertTaiLieu(String TheLoaiId, String NhanDe,
			String BanQuyen, String SoTrang, String NhaXuatBan, String DeMuc,
			String NgayThang, String DinhDang, String NgonNgu,
			String MoTaNoiDung, String NguonGoc, String CoTacGia,
			String CongTac, String ThuocLinhVuc, String TaiLieuCoPhuongThuc,
			long totalplus) {
		boolean isOk = false;
		Database.LoadOnt2Database(); // ket noi csdl
		OntModel om = Database.getOntologyModel();
		try {
			OntClass oc = om.createClass(Sharing.getURI() + TheLoaiId); // dinh danh ten lop
			
		/*	oc =om.getOntClass(Sharing.getURI()+TheLoaiId);
			TaiLieuResult tl=new TaiLieuResult();
			tl.setNhanDe(Sharing.getURI()+NhanDe);
			oc.createIndividual().addLiteral(tl);
			// tao 1 individual (lop thuoc jena ho tro)
			// createIndividual (totalplus, class_name)
			*/
			Individual ind = om.createIndividual(Sharing.getURI() + "TaiNguyen_"
					+ totalplus, oc);
			
			
			
			// them cac thuoc tinh vao ca the can tao
			if (NhanDe != null) {
				ind.addProperty(Sharing.NhanDe, NhanDe); 
			}
			if (BanQuyen != null) {
				ind.addProperty(Sharing.BanQuyen, BanQuyen);
			}
			if (SoTrang != null) {
				ind.addProperty(Sharing.SoTrang, SoTrang);
			}
			if (NhaXuatBan != null) {
				ind.addProperty(Sharing.NhaXuatBan, NhaXuatBan);
			}
			if (DeMuc != null) {
				ind.addProperty(Sharing.DeMuc, DeMuc);
			}
			if (NgayThang != null) {
				ind.addProperty(Sharing.NgayThang, NgayThang);
			}
			if (DinhDang != null) {
				ind.addProperty(Sharing.DinhDang, DinhDang);
			}
			if (NgonNgu != null) {
				ind.addProperty(Sharing.NgonNgu, NgonNgu);
			}
			if (MoTaNoiDung != null) {
				ind.addProperty(Sharing.MoTaNoiDung, MoTaNoiDung);
			}
			if (NguonGoc != null) {
				ind.addProperty(Sharing.NguonGoc, NguonGoc);
			}
			
			// Du lieu la 1 lop (range)
			if ((CoTacGia != null) && (!CoTacGia.equals(""))) {
				Individual TacGiaInd = OntologyModel
						.GetbyInstance(OntologyModel.insertIndividual(
								"TacGiaChinh", CoTacGia));
				ind.addProperty(Sharing.CoTacGia, TacGiaInd);
				TacGiaInd.addProperty(Sharing.CoTacPham, ind);
			}
			if ((CongTac != null) && (!CongTac.equals(""))) {
				Individual CongTacInd = OntologyModel
						.GetbyInstance(OntologyModel.insertIndividual(
								"CongTacVien", CongTac));
				ind.addProperty(Sharing.CongTac, CongTacInd);
				CongTacInd.addProperty(Sharing.DaHoTro, ind);
			}
		
			
			if ((!ThuocLinhVuc.equals("")) && (ThuocLinhVuc != null)) {
				Individual LinhVucInd = OntologyModel
						.GetbyInstance(OntologyModel.insertIndividual("ThuocLinhVuc",ThuocLinhVuc));
				ind.addProperty(Sharing.ThuocLinhVuc, LinhVucInd);
				LinhVucInd.addProperty(Sharing.CoTaiLieu, ind);
			}
						
			
			if ((!TaiLieuCoPhuongThuc.equals(""))
					&& (TaiLieuCoPhuongThuc != null)) {
				Individual PhuongThuocInd = OntologyModel
						.GetbyInstance(OntologyModel.insertIndividual(
								"PhuongThuc", TaiLieuCoPhuongThuc));
				ind.addProperty(Sharing.TaiLieuCoPhuongThuc, PhuongThuocInd);
				PhuongThuocInd.addProperty(Sharing.PhuongThucThuocTaiLieu, ind);
			}
			isOk = true;
		} catch (Exception e) {
			System.out.println(e.toString());
			isOk = false;
		}
		return isOk;
	}

	public boolean updateTaiLieu(String tailieuid, String NhanDe,
			String BanQuyen, String SoTrang, String NhaXuatBan, String DeMuc,
			String NgayThang, String DinhDang, String NgonNgu,
			String MoTaNoiDung, String NguonGoc, String AnhMinhHoa,
			String NoiChua) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();

		try {
			Individual ind = om.getIndividual(Sharing.getURI() + tailieuid);
			if (ind != null) {
				ind.removeAll(Sharing.NhanDe);
				ind.addProperty(Sharing.NhanDe, NhanDe);
				ind.removeAll(Sharing.BanQuyen);
				ind.addProperty(Sharing.BanQuyen, BanQuyen);
				ind.removeAll(Sharing.SoTrang);
				ind.addProperty(Sharing.SoTrang, SoTrang);
				ind.removeAll(Sharing.NhaXuatBan);
				ind.addProperty(Sharing.NhaXuatBan, NhaXuatBan);
				ind.removeAll(Sharing.DeMuc);
				ind.addProperty(Sharing.DeMuc, DeMuc);
				ind.removeAll(Sharing.NgayThang);
				ind.addProperty(Sharing.NgayThang, NgayThang);
				ind.removeAll(Sharing.DinhDang);
				ind.addProperty(Sharing.DinhDang, DinhDang);
				ind.removeAll(Sharing.NgonNgu);
				ind.addProperty(Sharing.NgonNgu, NgonNgu);
				ind.removeAll(Sharing.MoTaNoiDung);
				ind.addProperty(Sharing.MoTaNoiDung, MoTaNoiDung);
				ind.removeAll(Sharing.NguonGoc);
				ind.addProperty(Sharing.NguonGoc, NguonGoc);
				ind.removeAll(Sharing.AnhMinhHoa);
				ind.addProperty(Sharing.AnhMinhHoa, AnhMinhHoa);
				ind.removeAll(Sharing.NoiChua);
				ind.addProperty(Sharing.NoiChua, NoiChua);
				isOk = true;
			}
		} catch (Exception e) {
			isOk = false;
		} finally {
			om.close();
			isOk = false;
		}
		return isOk;

	}

	public boolean saveKetQua(String tukhoa, String timdung, String oclass,
			String tailieuid) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			String s = OntologyModel.KetQuaDaLuu(tukhoa, tailieuid);
			if (s.equals("")) {
				OntologyModel OM = new OntologyModel();
				OntClass oc = om.createClass(Sharing.NS + oclass);
				Individual ind = om.createIndividual(Sharing.getURI() + oclass
						+ "_" + OM.maxIdPlus(oclass), oc);
				ind.addProperty(Sharing.TuKhoa, tukhoa);
				ind.addProperty(Sharing.TimDung, timdung);
				Individual resource = OntologyModel.GetbyInstance(tailieuid);
				ind.addProperty(Sharing.KetQuaLaTaiLieu, resource);
				isOk = true;
			} else {
				Individual ind = om.getIndividual(Sharing.getURI() + s);
				ind.addProperty(Sharing.TimDung, timdung);
				isOk = true;
			}
			om.commit();
		} catch (Exception e) {
			om.close();
			isOk = false;
		}
		return isOk;
	}

	public boolean uploadTaiNguyenImage(String strAnhMinhHoa, long totalplus) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			OntResource ors = om.createOntResource(Sharing.getURI()
					+ "Tailieu_" + totalplus);
			if (strAnhMinhHoa != null) {
				ors.addProperty(Sharing.AnhMinhHoa, strAnhMinhHoa);
			} else {
				ors.addProperty(Sharing.AnhMinhHoa, "");
			}
			om.commit();
			isOk = true;
		} catch (Exception ex) {
			isOk = false;
		}
		return isOk;
	}
	public boolean uploadTaiNguyenFile(String strNoiChua, long totalplus) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			OntResource ors = om.createOntResource(Sharing.getURI()
					+ "Tailieu_" + totalplus);
			if (strNoiChua != null) {
				ors.addProperty(Sharing.NoiChua, strNoiChua);
			} else {
				ors.addProperty(Sharing.NoiChua, "");
			}
			om.commit();
			isOk = true;
		} catch (Exception ex) {
			isOk = false;
		}
		return isOk;
	}

	public void updateUpload(String tailieuid, String noichua) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();

		try {
			Individual ind = om.getIndividual(Sharing.getURI() + tailieuid);
			if (ind != null) {
				ind.removeAll(Sharing.NoiChua);
				ind.addProperty(Sharing.NoiChua, noichua);
			}
		} catch (Exception e) {

		} finally {
			om.close();
		}
	}

	public void updateTotalDownload(String tailieuid, int newtotal) {
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		String total = Integer.toString(newtotal);
		try {
			Individual ind = om.getIndividual(Sharing.getURI() + tailieuid);
			if (ind != null) {
				ind.removeAll(Sharing.TotalDownload);
				ind.addProperty(Sharing.TotalDownload, total);
			}
		} catch (Exception e) {

		} finally {
			om.close();
		}
	}

	public boolean addOntResource2OntClass(OntResource ors, OntClass oc) {
		boolean isOk = false;
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		try {
			Individual ind = oc.createIndividual(ors.getURI());
			ind.commit();
			om.commit();
			isOk = true;
		} catch (Exception e) {
			isOk = false;
		}
		return isOk;
	}
	public void searchbyId1(String keyword) {
		System.out.println("vao search");
	// day la ben ong hieu
		
		
		ArrayList<TaiLieuResult> list = new ArrayList<TaiLieuResult>();
		Database.LoadOnt2Database();
		OntModel om = Database.getOntologyModel();
		System.out.println("om: "+ om.toString());
		  om.write(System.out);
		System.out.println("model:"+om.toString());
		String queryString = "PREFIX hotel: <http://eduportal.vn/chiase/myontology#> \n"
				+ "SELECT DISTINCT ?x ?nhande ?banquyen ?nguongoc\n "
				+ "WHERE \n"
				+ "{ \n"
				+ "?x hotel:NhanDe ?nhande. \n"				
				
				+ "FILTER (regex(?nhande,'"
				+ keyword
				+ "',\"i\") || regex(?banquyen,'"
				+ keyword
				+ "',\"i\") || regex(?nguongoc,'"
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
				ResultBinding binding = (ResultBinding) obj;
				TaiLieuResult dtTaiNguyen = new TaiLieuResult();
				

				if (binding.get("nhande").isLiteral()) {
					dtTaiNguyen.setNhanDe(binding.getLiteral("nhande")
							.getValue().toString());
					System.out.println("nhan de:"+dtTaiNguyen.getNhanDe());	
				} 
				
				
			}
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	}
public static void main(String s[]){
	TaiNguyen tn =  new TaiNguyen();
//String[] s1=tn.searchAdvanced("CongNgheThongTin", ".PDF", "BaiGiang", "Hoang Minh Thuc", "Bach Khoa Ha Noi", "Tieng anh");
	
	String[] s1=tn.searchTaiLieubyTheLoai("04");
	for(int i=0;i<s1.length;i++){
		System.out.println(i);
		System.out.println(s1[i]);
	}
/*Boolean a= tn.insertTaiLieu("04", "co tich","ban quyen: yen","100","giao duc", "truyen tho",
			"01-01-2005", "yen", "tieng viet","cham ngon", "viet nam", "nguyen yen",
			"khong", "Thuoc linh vuc: Giai Tri", "tai lieu: yen",6);
	System.out.println("ket qua:"+a);
	*/
	
	tn.searchbyId1("co tich");
	System.out.println("ket thuc");
	
/*	String s2[]=new String[10];
	         s2= tn.searchTaiLieubyTheLoai("04");
	System.out.println("1:"+s2[0]);
	*/
}
}

