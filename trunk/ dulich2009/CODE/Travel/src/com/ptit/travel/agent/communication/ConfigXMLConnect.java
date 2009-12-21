/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ptit.travel.agent.communication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author D05CNPM
 */
public class ConfigXMLConnect {

    private static Logger log = Logger.getLogger(ConfigXMLConnect.class.getName());
    /**
     *  Properties file 
     */
    static java.util.Properties config = null;
    static String propertyFile = "c:/ConfigXMLConnect.properties";
    /**
     *  Operators file name.
     */
//*
    

    static {
        log.info("Trying to initiate config");

        log.debug("Trying to initiate config XMLRPC connect constants");
        try {

            log.debug("Trying to initiate config constants");
            File file = new File(propertyFile);
            FileInputStream inputStream = new FileInputStream(file);


            config = new java.util.Properties();
            config.load(inputStream);

            inputStream.close();

//            config.load(Class.forName("com.ptit.travel.agent.onto.ConfigXMLConnect").getResourceAsStream(propertyFile));
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

    public static int nextPort(String key) {
        int port = 0;
        try {            
            int span = Integer.parseInt(config.getProperty("SPAN"));
            port = Integer.parseInt(config.getProperty(key)) + span;
            if (port > 65000) {
                int base = Integer.parseInt(config.getProperty("BASE_PORT"));
                port = base + (port - base) % span;
            }
            config.setProperty(key, "" + port);
            FileOutputStream outputStream = new FileOutputStream(new File(propertyFile));

            config.store(outputStream, "New " + key);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return port;
    }

    public static void main(String[] a) {
        System.out.println(nextPort("PORT_USER"));
        System.out.println(PORT);
    }
    /**
     * default HOST
     */
    public static final String HOST = config.getProperty("HOST");
    /**
     * hotel host
     */
    public static final String HOST_HOTEL = config.getProperty("HOST_HOTEL");
    public static final String HOST_TRAIN = config.getProperty("HOST_TRAIN");
    public static final String HOST_CAR = config.getProperty("HOST_CAR");
    public static final String HOST_FLIGHT = config.getProperty("HOST_FLIGHT");
    public static final String HOST_USER = config.getProperty("HOST_USER");
    public static final String HOST_BROKER = config.getProperty("HOST_BROKER");
    /**
     * DEFAULT  PORT
     */
    public static final int PORT = Integer.parseInt(config.getProperty("BASE_PORT"));
    public static final int PORT_HOTEL = Integer.parseInt(config.getProperty("PORT_HOTEL"));
    public static final int PORT_TRAIN = Integer.parseInt(config.getProperty("PORT_TRAIN"));
    public static final int PORT_CAR = Integer.parseInt(config.getProperty("PORT_CAR"));
    public static final int PORT_FLIGHT = Integer.parseInt(config.getProperty("PORT_FLIGHT"));
    public static final int PORT_USER = Integer.parseInt(config.getProperty("PORT_USER"));
    public static final int PORT_BROKER = Integer.parseInt(config.getProperty("PORT_BROKER"));
}
