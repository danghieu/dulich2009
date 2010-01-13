/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.moduleJDBC.Model;
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
 *
 * @author HaiYen
 */
public class Database {
    
	static String databaseName = "";
			
	public static final String DB_URL         = "jdbc:mysql://localhost/hotel" ;
	public static final String DB_USER        = "root";
	public static final String DB_PASSWD      = "1";
	public static final String DB = "MySQL";	
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static String s_dbURL = DB_URL;
	private static String s_dbUser = DB_USER;
	private static String s_dbPw = DB_PASSWD;
	private static String s_dbType = DB;
	private static String s_dbDriver = DB_DRIVER;

	// if true,Lấy lại dữ liệu ban dau
	private static boolean s_reload = false;

	// duong dan den file dat owl.
	private static String s_source = "C:/apache-tomcat-6.0.18/webapps/MyOntology/hotel_yen10.owl";
	private static ModelMaker maker;
	private static Model base;

	/**
	 * load du lieu tu ontology vao csdl
	 * s_reload =true tuc la xoa het roi them moi, lay toan bo owl chuyen vao database
	 */
	public static boolean loadData(boolean b_reload) {		
		ModelMaker maker = getRDBModelMaker(b_reload);			
		ModelRDB model = (ModelRDB) maker.openModel(s_source);
		
                // tao ra 1 mode tu file s_source
		 FileManager.get().readModel(model,s_source);         
                 model.commit();
                 System.out.print("commit xong");
		 model.close();		
		return true;
	}
/**
 * Lay ra mot doi tuong ModelMaker chua cac mo hinh duoc lay ra tu doi tuong ket noi IDBConnection
 * @param cleanDB
 * @return
 */
	private static ModelMaker getRDBModelMaker(boolean cleanDB) {
		return ModelFactory.createModelRDBMaker(getConnection(cleanDB));
	}

    /**
	 * Tra ve ket noi csdl
	 * @param cleanDB = true  xoa tat ca cac thong tin RDF tu csdl. tat ca cac bang RDF duoc xoa
	 * @return
	 */
	private static IDBConnection getConnection(boolean cleanDB) {
		try {
			Class.forName(s_dbDriver);			
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Failed to load DB driver " + s_dbDriver, e);
		}
		
		try{
			// tao 1 ket noi csdl
			IDBConnection conn=new DBConnection(s_dbURL,s_dbUser,s_dbPw,s_dbType);
		
			
			if(cleanDB){
				 conn.cleanDB();
			}
			return conn;
		}
		catch(Exception e){
			
		}
		return null;
	}

/**
 * Muc dich lay ra modelMaker tu csdl hoac tạo 1 model trong
 */
	public static void LoadOnt2Database() {
		// OntologyModel.load();
		try {
			
	//load driver
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
              
                // s_source la ten cua model. false: mo hinh da ton tai
                base = maker.createModel(s_source, false);
			}

	
  /**
         * Dug de lay 1ontModel 
         * @return
         */
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


	private static void loadDB(ModelMaker maker, String source) {
		
		// tao mot Model moi lien ket voi source
		Model base = maker.createModel(source, false); 
		
		// tao 1 mo hinh ontology theo dac the, chua ModelMaker
		OntModel m = ModelFactory
				.createOntologyModel(getModelSpec(maker), base);
		m.read(source);
	}
	
	private static OntModelSpec getModelSpec(ModelMaker maker) {
		OntModelSpec spec = new OntModelSpec(OntModelSpec.OWL_MEM);
                spec.setImportModelMaker(maker);
		return spec;
	}
        public static void main(String args[]){
                System.out.print("Bat dau anh xa:");
                Database db=new Database(); 
	        boolean result = db.loadData(true);

                 System.out.println("KET QUA:"+result);
    }
}