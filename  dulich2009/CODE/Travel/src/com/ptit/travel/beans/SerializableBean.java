/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.beans;

import java.io.Serializable;

/**
 *
 * @author D05CNPM
 */
public interface SerializableBean extends Serializable{

    /**
     * combine all the attributes of Object into a string message
     * @return
     */
    public String toMsg();    
}
