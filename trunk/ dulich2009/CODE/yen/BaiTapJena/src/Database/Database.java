// lop anh xa csdl quan he va ontology

package Database;

import com.hp.hpl.jena.db.DBConnection;
import com.hp.hpl.jena.db.IDBConnection;
import com.hp.hpl.jena.db.ModelRDB;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.ModelMaker;// ModelMaker ????
import com.hp.hpl.jena.util.FileManager;
import java.io.*;
import java.sql.*;

/**
 * Ket noi Ontology voi MsSQL server 2005
 * 
 * @author TrungHieu
 * 
 */
public class Database {
private static java.sql.Connection con;
private java.sql.Statement pst; 
	
			 
			/* Class.forName("com.mysql.jdbc.Driver");
			 String s1="jdbc:mysql://localhost/nhom";
			 String usename="root";
			 String pass="1";
			*/
		 	
//	public static final String DB_URL = "jdbc:sqlserver://EVSOFT2;port=1433;database=Jena";
	
	public static final String DB_URL         = "jdbc:mysql://localhost/jena";

//User name

	public static final String DB_USER        = "root";

//Password

	public static final String DB_PASSWD      = "1";

//Database engine name

	public static final String DB = "MySQL";

//JDBC driver

	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";

	private static String s_dbURL = DB_URL;

	private static String s_dbUser = DB_USER;

	private static String s_dbPw = DB_PASSWD;

	private static String s_dbType = DB;

	private static String s_dbDriver = DB_DRIVER;


	// if true, reload the data
	private static boolean s_reload = false;

	private static String s_source = "http://localhost:8080/MyOntology ";

	private static ModelMaker maker;

	private static Model base;

	/**
	 * Dung Ham nay cho lan dau thoi , canh bao nguy hiem
	 * s_reload =true tuc la xoa het roi them moi
	 */
	public static void loadData(boolean b_reload) {
		
		ModelMaker maker = getRDBModelMaker(b_reload);
		ModelRDB model = (ModelRDB) maker.openModel(s_source);
		model.setDoDuplicateCheck(true);
		model.begin();
		FileManager.get().readModel(model,"http://localhost:8080/MyOntology/ChiaSe.owl");
		//FileManager.get().readModel(model,"http://localhost:8080/Ontology/Sharing/Sharing.owl#");
		model.commit();
		model.close();
	}
	/**
	 * Neu cleanDB==true thi se xoa het database va import lai file owl vao database
	 * @param cleanDB
	 * @return
	 */
	private static ModelMaker getRDBModelMaker(boolean cleanDB) {
		return ModelFactory.createModelRDBMaker(getConnection(cleanDB));
	}

	private static IDBConnection getConnection(boolean cleanDB) {
		try {
			Class.forName(s_dbDriver);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Failed to load DB driver " + s_dbDriver, e);
		}
		// return new DBConnection(s_dbURL, s_dbUser, s_dbPw, s_dbType);
		try{
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
		if (s_reload) {
			
			maker = getRDBMaker(s_dbURL, s_dbUser, s_dbPw, s_dbType, true);
			loadDB(maker, s_source);
		
		}
		maker = getRDBMaker(s_dbURL, s_dbUser, s_dbPw, s_dbType, false);
		// Dong nay tao Database trong SQLServer
		base = maker.createModel(s_source, false);
			}

	public static OntModel getOntologyModel() {
		OntModel m = ModelFactory
				.createOntologyModel(getModelSpec(maker), base);
		return m;
	}

	private static ModelMaker getRDBMaker(String dbURL, String dbUser,
			String dbPw, String dbType, boolean cleanDB) {
		try {
			IDBConnection conn = new DBConnection(dbURL, dbUser, dbPw, dbType);
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
		Model base = maker.createModel(source, false); // taoj ra moo hinhf co nguon la source, truong hop flase
		OntModel m = ModelFactory
				.createOntologyModel(getModelSpec(maker), base);
		m.read(source);
	}

	private static OntModelSpec getModelSpec(ModelMaker maker) {
		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
		spec.setImportModelMaker(maker);
		return spec;
	}
	public static void main(String[] args) {
		Database db=new Database(); 
		//db.loadData(true);
	}
}
