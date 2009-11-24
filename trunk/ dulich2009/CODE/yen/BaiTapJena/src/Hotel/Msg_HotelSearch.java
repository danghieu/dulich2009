package Hotel;
import java.util.*;
public class Msg_HotelSearch {

private String Destination;
private Date checkIn;
private Date checkOut;

public String getDestination() {
	return Destination;
}
public void setDestination(String destination) {
	Destination = destination;
}
public Date getCheckIn() {
	return checkIn;
}
public void setCheckIn(Date checkIn) {
	this.checkIn = checkIn;
}
public Date getCheckOut() {
	return checkOut;
}
public void setCheckOut(Date checkOut) {
	this.checkOut = checkOut;
}

public Msg_HotelSearch(String destination, Date checkIn, Date checkOut) {
	
	Destination = destination;
	this.checkIn = checkIn;
	this.checkOut = checkOut;
}
public Msg_HotelSearch(){
	
}
public Date converDate(int a, int b, int c){
	Date date = new Date();
	if(a>0&&a<=31)
	{
		date.setDate(a);
		
	if(b>0&&b<=12)
	{
		date.setMonth(b);
		{
			if(c>date.getYear())
				date.setYear(c);
			
		}
	}
	return date;
	}
	System.out.println("Ngay ko ton tai");
	return null;
	
}
}
