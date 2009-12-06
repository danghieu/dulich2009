/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.beans.hotel;

import com.ptit.travel.beans.SerializableBean;
import java.io.Serializable;

/**
 *
 * @author D05CNPM
 */
public class HotelAvailability implements SerializableBean{

    private String id;
    private String city;
    private String numberStar;
    private String beginDate;
    private String endDate;
    private int quantity;
    
    public HotelAvailability(){}
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumberStar() {
        return numberStar;
    }

    public void setNumberStar(String numberStar) {
        this.numberStar = numberStar;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public HotelAvailability(String msg){
        //TODO
    }
    public String toMsg(){
        String msg = "";
        //@TODO
        return msg;
        
    }
}
