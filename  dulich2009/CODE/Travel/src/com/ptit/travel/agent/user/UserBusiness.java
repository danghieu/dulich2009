/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ptit.travel.agent.user;

import com.ptit.travel.beans.SerializableBean;
import java.util.ArrayList;

/**
 * handle user businesses
 * @author D05CNPM
 */
public interface UserBusiness {

    /**
     * combine Hotels' services: if the hotel itself satifys user's request the number of rooms
     * we'll combine with other ones which are close to it.
     * 
     * @param beans
     */
    public void combineRooms(ArrayList<SerializableBean> beans);
   /**
     * combine Hotels' services: if the hotel itself satifys user's request the rooms and meeting
     * we'll combine with other ones which are close to it.
     * 
     * @param beans
     */
    public void combineRoomsMeeting(ArrayList<SerializableBean> beans);
    
}

