package com.ptit.travel.common;

import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClientLite;
import org.apache.xmlrpc.XmlRpcException;

public class CallAgent {

    public static final String SERVER = "http://localhost:8006";
    //"http://" + ConfigXMLConnect.HOST_USER + ":" + ConfigXMLConnect.PORT_USER;     
    XmlRpcClientLite myClient;
    private String host;
    private int port;

    public CallAgent() {
        try {
            myClient = new XmlRpcClientLite(SERVER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CallAgent(String address) {
        try {
            myClient = new XmlRpcClientLite(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public CallAgent(String host, int port) {
        try {
            myClient = new XmlRpcClientLite(host, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public String callTheAgentViaXmlRpc(String function, String param) {
        String result = null;
        //the parameters are inserted in a vector
        Vector v = new Vector();
        v.addElement(param);
        System.out.println("method is going to be called");

        try {

            result = (String) myClient.execute(function, v);
        } catch (XmlRpcException e) {
            System.out.println("exception while transmitting message " + e);
            e.printStackTrace();
        } catch (java.io.IOException e) {
            System.out.println("exception while transmitting message " + e);
            e.printStackTrace();
        }
        return result;
    }
    /**
     * used for calling function with more than one param
     * @param function
     * @param params
     * @return
     */
    public String callTheAgentViaXmlRpc(String function, String[] params) {
        String result = null;
        //the parameters are inserted in a vector
        Vector v = new Vector();
        for(int i = 0; i < params.length; i ++){
            v.addElement(params[i]);
        }
        
        System.out.println("method is going to be called");

        try {

            result = (String) myClient.execute(function, v);
        } catch (XmlRpcException e) {
            System.out.println("exception while transmitting message " + e);
            e.printStackTrace();
        } catch (java.io.IOException e) {
            System.out.println("exception while transmitting message " + e);
            e.printStackTrace();
        }
        return result;
    }
}
