package com.ptit.travel.moduleJDBC.Model;

//lop anh xa csdl quan he va ontology


import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;// ModelMaker ????
import com.hp.hpl.jena.util.FileManager;
import java.sql.*;

/**
 * Ket noi Ontology voi MsSQL server 2005
 * 
 * @author TrungHieu
 * 
 */
public class Database {

 
	static String databaseName = "";
			
	public static final String DB_URL         = "jdbc:mysql://localhost/yen" ;

//User name

	public static final String DB_USER        = "root";

//Password

	public static final String DB_PASSWD      = "root";

//Database engine name

	public static final String DB = "MySQL";
	static Connection connection;
	private Statement statement; 

//JDBC driver

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	private static String s_dbURL = DB_URL;

	private static String s_dbUser = DB_USER;

	private static String s_dbPw = DB_PASSWD;

	private static String s_dbType = DB;

	private static String s_dbDriver = DB_DRIVER;


	// if true, reload the data
	private static boolean s_reload = false;

	// duong dan den file dat owl.
	private static String s_source = "http://localhost:8080/MyOntology ";

	private static ModelMaker maker;

	private static Model base;

	/**
	 * Dung Ham nay cho lan dau thoi , canh bao nguy hiem
	 * s_reload =true tuc la xoa het roi them moi, lay toan bo owl chuyen vao database
	 */
	public static boolean loadData(boolean b_reload) {
		
		// neu la true, xoa toan bo csdl, tra ve 1 model macker lay tu csdl
		ModelMaker maker = getRDBModelMaker(b_reload);
		
		// anh xa sag csdl quan he
		
		//Tim mot model theo source
		ModelRDB model = (ModelRDB) maker.openModel(s_source);
		
		// tap cac gia tri cua doDuplicateCheck
		model.setDoDuplicateCheck(true);
		model.begin();
		
		// duong dan den file owl
		
		FileManager.get().readModel(model,"http://localhost:8080/MyOntology/train.owl");
		
		// cap nhat vao csdl
		model.commit();
		model.close();
		System.out.print("commit xong");
		
		return true;
	}
	/**
	 * Neu cleanDB==true thi se xoa het database va import lai file owl vao database
	 * @param cleanDB
	 * @return
	 * 
	 * ModelMaker:interface chua mot tap model duoc dinh danh, phuong thuc tao mot model moi va mo model theo ten, xoa model
	 * truy cao 1 model mac dich
	 */
	private static ModelMaker getRDBModelMaker(boolean cleanDB) {
		return ModelFactory.createModelRDBMaker(getConnection(cleanDB));
	}

	/**
	 * Tra ve ket noi jdbc hoac null 
	 * @param cleanDB = true  xoa tat ca cac thong tin RDF tu csdl. tat ca cac bang RDF duoc xoa
	 * @return
	 */
	private static IDBConnection getConnection(boolean cleanDB) {
		try {
			Class.forName(s_dbDriver);
			/*
			 *  try {
			 
					connection = DriverManager.getConnection(s_dbURL, s_dbUser, s_dbPw);
					Statement stm=connection.createStatement();
					stm.executeUpdate("CREATE DATABASE MyDB"); 
					s_dbURL    = "jdbc:mysql://localhost/MyDB";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Failed to load DB driver " + s_dbDriver, e);
		}
		
		try{
			// tao 1 ket noi csdl
			IDBConnection conn=new DBConnection(s_dbURL,s_dbUser,s_dbPw,s_dbType);
		
			//	con=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);	
			if(cleanDB){
				 conn.cleanDB();
			}
			return conn;
		}
		catch(Exception e){
			
		}
		return null;
	}

	// /-----------------------------------Ket thuc thu
	// nghiem---------------------
	
	
	// ket noi csdl
	public static void LoadOnt2Database() {
		// OntologyModel.load();
		try {
			
	// ket noi csdl
			Class.forName(s_dbDriver); 
			
		
		} catch (Exception e) {
			System.err.println("Failed to load the driver for the database: "
					+ e.getMessage());
			System.err.println("Have you got the CLASSPATH set correctly?");
		}
		
		// lay mo hinh rdf tu owl, dua ra modelmaker
		if (s_reload) {
		
			
			
			maker = getRDBMaker(s_dbURL, s_dbUser, s_dbPw, s_dbType, true);
			loadDB(maker, s_source);
		
		}
                
		maker = getRDBMaker(s_dbURL, s_dbUser, s_dbPw, s_dbType, false);
		
	
                // tao ra 1 model tu file nguon
                base = maker.createModel(s_source, false);
			}

	
        /**
         * Dug de lay 1 model. Toạ ra 1 model tu 
         * @return
         */
	public static OntModel getOntologyModel() {
		OntModel m = ModelFactory
				.createOntologyModel(getModelSpec(maker), base);
		return m;
	}

	/**
	 * tra ve moot ModelMaker hoac null, tao ra 1 mo hinh rdb
	 * @param dbURL
	 * @param dbUser
	 * @param dbPw
	 * @param dbType
	 * @param cleanDB = true tra ve ModelMaker
	 * @return
	 */
	private static ModelMaker getRDBMaker(String dbURL, String dbUser,
			String dbPw, String dbType, boolean cleanDB) {
		try {
			
			// ket noi den csdl
			IDBConnection conn = new DBConnection(dbURL, dbUser, dbPw, dbType);
			
			// cleanDB = true, xoa tat ca thong tin rdf tu csdl.
			// tra ve 1 ModelMaker ma no truy xuat vao cac model duoc sao luu csdl theo connection conn
			if (cleanDB) {
				conn.cleanDB();
			}
			return ModelFactory.createModelRDBMaker(conn);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

	/*
	 * public static String getDefaultSource() {
	 * OntDocumentManager.getInstance().addAltEntry(ONT,
	 * "http://localhost:8080/Ontology/Book/Book.owl"); return ONT; }
	 */

	private static void loadDB(ModelMaker maker, String source) {
		
		// tao mot Model moi lien ket voi source
		Model base = maker.createModel(source, false); 
		
		// tao 1 mo hinh ontology theo dac the, chua ModelMaker
		OntModel m = ModelFactory
				.createOntologyModel(getModelSpec(maker), base);
		m.read(source);
	}

	//tra ve mot mieu ta dong goi cac thanh phan cua 1 mo hinh ontology,
	//chua ca scheme, suy dien va ho so ngon ngu
	private static OntModelSpec getModelSpec(ModelMaker maker) {
		// owl_mem dac ta cac mo hinh owl ma duoc lu tru trong bo nho va
		// ko them suy dien ke thua
            //   A specification for OWL models that are stored in memory and do no additional entailment reasoning
		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
                
                // Set the model maker that will be used when the ontology model needs
                //to create an additional container for an imported ontology
                
		spec.setImportModelMaker(maker);
		return spec;
	}
	
	/**
	 * Tao mot csdl theo owl
	 * @param args
	 */
	public static void main(String[] args) {
		Database db=new Database(); 
                
		//IDBConnection db1 = db.getConnection(true);
		boolean result = db.loadData(true);
		System.out.println("KET QUA:"+result);
	}
}

