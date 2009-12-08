/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.servlet;

import com.ptit.travel.agent.communication.Message;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author D05CNPM
 */
public class HandleRequest {

    private static Logger log = Logger.getLogger(HandleRequest.class.getName());
    /**
     * combine all parameters values into a string.
     * Firstly, parameters ordered according to alphabet, then executes combining 
     * @param request
     * @return ([) + SEPARATE + getParameter(param1) + ...]
     */
    public static String extract(HttpServletRequest request){
        String param = null;
        String msg = "";
        Enumeration paramList = request.getParameterNames();
        TreeSet<String> paramSet = new TreeSet<String>();

        while (paramList.hasMoreElements()) {

            param = (String) paramList.nextElement();
            if (!"protocol".equals(param) && !"submit".equals(param) && !"button".equals(param)) {
                paramSet.add(param);

            }
        }
        log.info("|| Parameter: " + paramSet);
        Object[] params = paramSet.toArray();
        int length = params.length;
        for (int i = 0; i < length; i++) {
            param = (String) params[i];
            msg += request.getParameter(param);
            if (i < length - 1) {
                msg += Message.FIELD_SEPARATE;

            }
        }
        log.info("|| " + msg);
        return msg;
    }
    
}
