package com.ptit.travel.agent.communication;

/**
 * Protocol for Hotel
 * 
 * @author D05CNPM
 *Reference: OpenTravel_MessageUsersGuide_2009A
 */
public class Protocol {

    
    public final static String SUFFIX_SEARCH = "_SEARCH";
    public final static String SUFFIX_BOOK = "_BOOK";
    public final static String PREFIX_HOTEL = "HOTEL_";
    public final static String HOTEL_AVAIL = PREFIX_HOTEL + "Avail" + SUFFIX_SEARCH;
    
    /**
     *  AVAIL_GET provides the ability for a booking source to obtain 
     *	availability status from one or more specified hotel properties.
     *Coming message:
     *	<DateRange Start="2009-03-01" End="2009-05-31"/>
     *	Room type: STD, DLX
     *                   
     */
    public final static String HOTEL_AVAIL_GET = PREFIX_HOTEL + "AvailGet" + SUFFIX_SEARCH;
    /**
     *  HOTEL_RES provides the ability for a booking source to obtain 
     *	availability status from one or more specified hotel properties.
     *Coming message:
     *	  <StatusApplication Start="2006-05-20" End="2006-05-23" InvTypeCode="N2DRAC"/>
     *			<GuestCounts> 
     *                <GuestCount AgeQualifyingCode="10" Count="1"/> 
     *                <!--AgeQualifyingCode 10=Adult--> 
     *                <GuestCount AgeQualifyingCode="8" Count="1"/> 
     *                <!--AgeQualifyingCode 8=Child--> 
     *         </GuestCounts>  
     *         
     *                   
     */
    public final static String HOTEL_RES = PREFIX_HOTEL + "Res" + SUFFIX_BOOK;
    public final static String HOTEL_CANCEL = PREFIX_HOTEL + "Cancel";
    public final static String HOTEL_RES_NOTIF = PREFIX_HOTEL + "ResNotif" + SUFFIX_SEARCH;
    public final static String HOTEL_GET_MSG = PREFIX_HOTEL + "GetMsg";
    public final static String HOTEL_GET_MSG_INFO = PREFIX_HOTEL + "GetMsgInfo" + SUFFIX_SEARCH;
    /**
     * common hotel information
     */
    public final static String HOTEL_COMMNOTIF = PREFIX_HOTEL + "CommNotif";
    /**
     * stay information
     */
    public final static String HOTEL_STAY_INFO_NOTIF = PREFIX_HOTEL + "StayInfoNotif" + SUFFIX_SEARCH;
    /**
     * statistics notify
     */
    public final static String HOTEL_STATISTICS_NOTIF = PREFIX_HOTEL + "StatisticsNotif" + SUFFIX_SEARCH;
    /**
     * statistic
     */
    public final static String HOTEL_STATISTICS = PREFIX_HOTEL + "Statistics" + SUFFIX_SEARCH;
    // FLIGHT
    public final static String PREFIX_FLIGHT = "FLIGHT_";
    public final static String FLIGHT_AVAIL = PREFIX_FLIGHT + "Avail" + SUFFIX_SEARCH;
    public final static String FLIGHT_RES = PREFIX_FLIGHT + "Res" + SUFFIX_BOOK;
    // CAR
    public final static String PREFIX_CAR = "CAR_";
    public final static String CAR_AVAIL = PREFIX_CAR + "Avail" + SUFFIX_SEARCH;  
    public final static String CAR_RES = PREFIX_CAR + "Res" + SUFFIX_BOOK;
    // TRAIN
    public final static String PREFIX_TRAIN = "TRAIN_";
    public final static String TRAIN_AVAIL = PREFIX_TRAIN + "Avail" + SUFFIX_SEARCH;
    public final static String TRAIN_RES = PREFIX_TRAIN + "Res" + SUFFIX_BOOK;
    // TOUR SERVICE
    public final static String PREFIX_TOURSERVICE = "TOURSERVICE_";
    public final static String TOURSERVICE_AVAIL = PREFIX_TOURSERVICE + "Avail" + SUFFIX_SEARCH;
    public final static String TOURSERVICE_RES = PREFIX_TOURSERVICE + "Res" + SUFFIX_BOOK;

}
