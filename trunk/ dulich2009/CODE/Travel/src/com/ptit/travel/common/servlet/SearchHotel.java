package com.ptit.travel.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ptit.travel.agent.user.UserAgent;
import com.ptit.travel.common.AgentManager;
import com.ptit.travel.common.CallAgent;
/**
 * Servlet implementation class SearchHotel
 */
public class SearchHotel extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private CallAgent callAgent = new CallAgent();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHotel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//process(request,response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		
//		String stateid = request.getParameter("stateid");
//		String beginDate = request.getParameter("beginDate");;
//		String endDate = request.getParameter("endDate");
//		String numberStar = request.getParameter("numberStar");
//		String price = request.getParameter("price");
		String msgId = "Hotel" + System.currentTimeMillis();
		String msg = msgId + "@@@";// +stateid + "@@@" +beginDate+ "@@@" +endDate + "@@@" +numberStar+ "@@@" + price;
				
//		if(true){
//			String host = "localhost";
//			String port = "1099";
//			String nickName = "Guest" + System.currentTimeMillis();
//			String className = "com.ptit.travel.agent.user.UserAgent";
//			try {
//				AgentManager.startAgent(host, port, nickName, className);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//		}
		String result = "";
		try {
			callAgent.callTheAgentViaXmlRpc("search", msg);
			result = callAgent.callTheAgentViaXmlRpc("getSearchResults", msgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		PrintWriter out = response.getWriter();
		out.print(result);
	}
}
