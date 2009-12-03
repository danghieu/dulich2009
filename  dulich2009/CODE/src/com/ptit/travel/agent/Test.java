package com.ptit.travel.agent.user;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import org.apache.xmlrpc.XmlRpcClientLite;
import org.apache.xmlrpc.XmlRpcException;

import com.ptit.travel.agent.communication.Message;

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
		String s = "_aaaNONEbbb_";
		Test test = new Test();
//		while(((arr = test.callTheAgentViaXmlRpc("getSearchResults", msgId)).equals("_aaaNONEbbb_"))){
//
//		}
		s = test.callTheAgentViaXmlRpc("getSearchResults", "102");
		System.out.println(s);
		ArrayList<String> arr = new Test().split(s);
		System.out.println(arr);
	}

	public ArrayList<String> split(String input){
		ArrayList<String> arr = new ArrayList<String>();
		String sSaparate = Message.SEPARATE;
		int beginIndex = 0;
		int endIndex = input.indexOf(sSaparate);
		int maxIndex = input.lastIndexOf(sSaparate);
		String s = "";
		if(endIndex == -1){
			arr.add(input);
			return arr;
		}
		while(beginIndex <= maxIndex){
			endIndex = input.indexOf(sSaparate, beginIndex+1);
			if(endIndex != -1){
				s = input.substring(beginIndex, endIndex); 
				
				s = s.replaceAll(sSaparate, "");
				System.out.println(s);
				arr.add(s);
			}
			else{
				s = input.substring(beginIndex, input.length());
				s = s.replaceAll(sSaparate, "");
				arr.add(s);
				break;
			}
			
			beginIndex = endIndex;
		}
		return arr;
	}
}
