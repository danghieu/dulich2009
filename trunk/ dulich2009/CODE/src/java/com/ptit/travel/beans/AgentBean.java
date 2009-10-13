/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.beans;

/**
 *
 * @author D05CNPM
 */
public class AgentBean {

    private String name;
    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
}
