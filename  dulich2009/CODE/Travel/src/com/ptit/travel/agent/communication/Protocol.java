package com.ptit.travel.agent.communication;
/**
 * Protocol for Hotel
 * 
 * @author D05CNPM
 *Reference: OpenTravel_MessageUsersGuide_2009A
 */
public class Protocol {

	public final static String HOTEL_AVAIL = "HotelAvail";
	/**
	 *  AVAIL_GET provides the ability for a booking source to obtain 
	 *	availability status from one or more specified hotel properties.
	 *Coming message:
	 *	<DateRange Start="2009-03-01" End="2009-05-31"/>
	 *	Room type: STD, DLX
	 *                   
	 */
	public final static String HOTEL_AVAIL_GET = "HotelAvailGet";
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
	public final static String HOTEL_RES = "HotelRes";
	public final static String HOTEL_CANCEL = "Cancel";
	public final static String HOTEL_RES_NOTIF = "HotelResNotif";
	public final static String HOTEL_GET_MSG = "GetMsg";
	public final static String HOTEL_GET_MSG_INFO = "GetMsgInfo";
	/**
	 * common hotel information
	 */
	public final static String HOTEL_COMMNOTIF = "CommNotif";
	/**
	 * stay information
	 */
	public final static String HOTEL_STAY_INFO_NOTIF = "StayInfoNotif";
	/**
	 * statistics notify
	 */
	public final static String HOTEL_STATISTICS_NOTIF = "StatisticsNotif";
	/**
	 * statistic
	 */
	public final static String HOTEL_STATISTICS = "Statistics";
}
