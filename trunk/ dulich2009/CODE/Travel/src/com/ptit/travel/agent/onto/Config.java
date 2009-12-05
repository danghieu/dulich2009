package  com.ptit.travel.agent.onto;

import org.apache.log4j.Logger;





/**
 * <br>Configuration for Ontology<br>
 * Properties can be edited in Config.properties<br>
 *
 */
public class Config {

	private static Logger log = Logger.getLogger( Config.class.getName() );
    /**
     *  Properties file
     */
    static java.util.Properties config = null;

    /**
     *  Operators file name.
     */

    static {
        log.info("Trying to initiate config");
        String propertyFile = "Config.properties";
        log.debug("Trying to initiate config constants");
        try {

            log.debug("Trying to initiate config constants");


            config = new java.util.Properties();
            config.load(Class.forName("com.ptit.travel.agent.onto.Config").getResourceAsStream(propertyFile));
            // Test
            log.debug("property file loaded from " + propertyFile);
            //////

        } catch (Exception e) {
            // TEST
            log.error("Cannot load properties from " + propertyFile, e);
            log.debug("Cannot load properties from " + propertyFile);
            /////////
        }
    }

	/**
	 * BASE resurns agent ontology uri without "#" at the end
	 * same string with # can be returned by  {@link agent.core.memory.Memory#getBase() Memory.getBase()} method
	 */    

    public static final String BASE = config.getProperty("BASE");
	/**
	 * Source file defines path to OWL file containing of agent ontology definitions<br>
	 * This can be loaded when OM Config {@link agent.core.memory.Config#CREATE_MODEL CREATE_MODEL} is set True 
	 */    
    public static final String SOURCE_FILE = config.getProperty("SOURCE_FILE");
    
}
