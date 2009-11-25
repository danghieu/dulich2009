package com.ptit.travel.agent;

import java.util.ArrayList;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClientLite;
import org.apache.xmlrpc.XmlRpcException;

public class Test {

	  public static final String SERVER = "http://localhost:8000";
		

	  String id = "AskAgent";
	  String resourceType = "CD";
	  XmlRpcClientLite myClient;
	  public Test(){
		  try {
			  myClient = new XmlRpcClientLite (SERVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	/**
	 * @param args
	 */
	private String callTheAgentViaXmlRpc(String function, String param) {
			String result = null;
	      //the parameters are inserted in a vector
	      Vector v = new Vector ();
	      v.addElement (param);
	      System.out.println("method is going to be called");

	      try {
	    	  
	          result = (String) myClient.execute("UserAgent." + function, v);
	      } catch (XmlRpcException e ){
	          System.out.println("exception while transmitting message " + e);
	          e.printStackTrace();
	      } catch (java.io.IOException e) {
	          System.out.println("exception while transmitting message " + e);
	          e.printStackTrace();
	      }
	      return result;
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String msgId = System.currentTimeMillis() + "";
		String arr = "_aaaNONEbbb_";
		Test test = new Test();
//		while(((arr = test.callTheAgentViaXmlRpc("getSearchResults", msgId)).equals("_aaaNONEbbb_"))){
//
//		}
		arr = test.callTheAgentViaXmlRpc("getSearchResults", "102");
		System.out.println(arr.toString());
	}

}
