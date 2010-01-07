/* CVS $Id: $ */
package com.ptit.travel.jane.train; 
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.ontology.*;
 
/**
 * Vocabulary definitions from file:/C:/Program%20Files/Protege_3.4/schemagen-temp.owl 
 * @author Auto-generated by schemagen on 07 Jan 2010 21:59 
 */
public class Train {
    /** <p>The ontology model that holds the vocabulary terms</p> */
    private static OntModel m_model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM, null );
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://www.owl-ontologies.com/Train.owl#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final ObjectProperty HasName = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#HasName" );
    
    public static final ObjectProperty contactInfo = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#contactInfo" );
    
    public static final ObjectProperty description = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#description" );
    
    public static final ObjectProperty getTicketAddress = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#getTicketAddress" );
    
    public static final ObjectProperty hasAddress = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasAddress" );
    
    public static final ObjectProperty hasAvailabilityPeriod = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasAvailabilityPeriod" );
    
    public static final ObjectProperty hasBegin = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasBegin" );
    
    public static final ObjectProperty hasBeginPoint = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasBeginPoint" );
    
    public static final ObjectProperty hasBeginTime = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasBeginTime" );
    
    public static final ObjectProperty hasCatering = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasCatering" );
    
    public static final ObjectProperty hasChanges = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasChanges" );
    
    public static final ObjectProperty hasCustomerData = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasCustomerData" );
    
    public static final ObjectProperty hasEnd = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasEnd" );
    
    public static final ObjectProperty hasEndPoint = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasEndPoint" );
    
    public static final ObjectProperty hasEndTime = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasEndTime" );
    
    public static final ObjectProperty hasFacilities = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasFacilities" );
    
    public static final ObjectProperty hasMeansOfTransport = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasMeansOfTransport" );
    
    public static final ObjectProperty hasNumberOfUnits = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasNumberOfUnits" );
    
    public static final ObjectProperty hasParkingPlace = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasParkingPlace" );
    
    public static final ObjectProperty hasPaymentMethod = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasPaymentMethod" );
    
    public static final ObjectProperty hasPeriodOfTime = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasPeriodOfTime" );
    
    public static final ObjectProperty hasPieceOfLuggage = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasPieceOfLuggage" );
    
    public static final ObjectProperty hasPrice = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasPrice" );
    
    public static final ObjectProperty hasSeatUnit = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasSeatUnit" );
    
    public static final ObjectProperty hasTrainJourney = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasTrainJourney" );
    
    public static final ObjectProperty hasTrainTicket = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasTrainTicket" );
    
    public static final ObjectProperty hasTransport = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasTransport" );
    
    public static final ObjectProperty hasTransportSeat = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#hasTransportSeat" );
    
    public static final ObjectProperty isAdressOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isAdressOf" );
    
    public static final ObjectProperty isAvailabilityPeriodOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isAvailabilityPeriodOf" );
    
    public static final ObjectProperty isBeginOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isBeginOf" );
    
    public static final ObjectProperty isBeginPointOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isBeginPointOf" );
    
    public static final ObjectProperty isBeginTimeOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isBeginTimeOf" );
    
    public static final ObjectProperty isCateringOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isCateringOf" );
    
    public static final ObjectProperty isChangeOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isChangeOf" );
    
    public static final ObjectProperty isEndOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isEndOf" );
    
    public static final ObjectProperty isEndPointOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isEndPointOf" );
    
    public static final ObjectProperty isEndTimeOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isEndTimeOf" );
    
    public static final ObjectProperty isFacilitiesOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isFacilitiesOf" );
    
    public static final ObjectProperty isMeansOfTransportOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isMeansOfTransportOf" );
    
    public static final ObjectProperty isNumberOfUnitsOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isNumberOfUnitsOf" );
    
    public static final ObjectProperty isParkingPlaceOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isParkingPlaceOf" );
    
    public static final ObjectProperty isPeriodOfTimeOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isPeriodOfTimeOf" );
    
    public static final ObjectProperty isPieceOfLuggageOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isPieceOfLuggageOf" );
    
    public static final ObjectProperty isSeatUnitOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isSeatUnitOf" );
    
    public static final ObjectProperty isTransportOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isTransportOf" );
    
    public static final ObjectProperty isTransportSeatOf = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#isTransportSeatOf" );
    
    public static final ObjectProperty paymentMethod = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#paymentMethod" );
    
    public static final ObjectProperty totalCost = m_model.createObjectProperty( "http://www.owl-ontologies.com/Train.owl#totalCost" );
    
    /** <p>Luong tien te (CarRental)</p> */
    public static final DatatypeProperty Amount = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#Amount" );
    
    public static final DatatypeProperty ArrivalRailway = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#ArrivalRailway" );
    
    public static final DatatypeProperty BookTrainID = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#BookTrainID" );
    
    public static final DatatypeProperty CompanyName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#CompanyName" );
    
    public static final DatatypeProperty CurrencyCode = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#CurrencyCode" );
    
    public static final DatatypeProperty DepartureDate = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#DepartureDate" );
    
    public static final DatatypeProperty DepartureRailway = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#DepartureRailway" );
    
    public static final DatatypeProperty Email = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#Email" );
    
    public static final DatatypeProperty GivenName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#GivenName" );
    
    public static final DatatypeProperty NamePrefix = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#NamePrefix" );
    
    public static final DatatypeProperty PhoneNumer = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#PhoneNumer" );
    
    public static final DatatypeProperty Sumame = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#Sumame" );
    
    public static final DatatypeProperty TrainCode = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#TrainCode" );
    
    public static final DatatypeProperty age = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#age" );
    
    public static final DatatypeProperty availability = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#availability" );
    
    public static final DatatypeProperty availabilityNumber = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#availabilityNumber" );
    
    public static final DatatypeProperty beginTime = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#beginTime" );
    
    public static final DatatypeProperty bookingFee = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#bookingFee" );
    
    public static final DatatypeProperty cancelBookingFee = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#cancelBookingFee" );
    
    public static final DatatypeProperty cateringType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#cateringType" );
    
    public static final DatatypeProperty city = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#city" );
    
    public static final DatatypeProperty companyName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#companyName" );
    
    public static final DatatypeProperty couchetteBedPosition = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#couchetteBedPosition" );
    
    public static final DatatypeProperty country = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#country" );
    
    public static final DatatypeProperty cuisineType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#cuisineType" );
    
    public static final DatatypeProperty customerType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#customerType" );
    
    public static final DatatypeProperty date = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#date" );
    
    public static final DatatypeProperty discount = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#discount" );
    
    public static final DatatypeProperty domain = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#domain" );
    
    public static final DatatypeProperty duration = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#duration" );
    
    public static final DatatypeProperty endTime = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#endTime" );
    
    public static final DatatypeProperty expirationDate = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#expirationDate" );
    
    public static final DatatypeProperty getTicketDate = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#getTicketDate" );
    
    public static final DatatypeProperty houseNumber = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#houseNumber" );
    
    public static final DatatypeProperty language = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#language" );
    
    public static final DatatypeProperty linkType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#linkType" );
    
    public static final DatatypeProperty luggageType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#luggageType" );
    
    public static final DatatypeProperty mealType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#mealType" );
    
    public static final DatatypeProperty name = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#name" );
    
    public static final DatatypeProperty numberOfPersons = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#numberOfPersons" );
    
    public static final DatatypeProperty numberOfSeat = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#numberOfSeat" );
    
    public static final DatatypeProperty numberOfTickets = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#numberOfTickets" );
    
    public static final DatatypeProperty numberTickets = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#numberTickets" );
    
    public static final DatatypeProperty numberbookedTicket = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#numberbookedTicket" );
    
    public static final DatatypeProperty owner = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#owner" );
    
    public static final DatatypeProperty password = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#password" );
    
    public static final DatatypeProperty personType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#personType" );
    
    public static final DatatypeProperty result = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#result" );
    
    public static final DatatypeProperty seatLocation = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#seatLocation" );
    
    public static final DatatypeProperty securityCode = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#securityCode" );
    
    public static final DatatypeProperty serviceID = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#serviceID" );
    
    public static final DatatypeProperty serviceName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#serviceName" );
    
    public static final DatatypeProperty serviceState = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#serviceState" );
    
    public static final DatatypeProperty sleeperCompartmentType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#sleeperCompartmentType" );
    
    public static final DatatypeProperty smoker = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#smoker" );
    
    public static final DatatypeProperty street = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#street" );
    
    public static final DatatypeProperty targetGroup = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#targetGroup" );
    
    public static final DatatypeProperty ticketID = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#ticketID" );
    
    public static final DatatypeProperty time = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#time" );
    
    public static final DatatypeProperty touristNumber = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#touristNumber" );
    
    public static final DatatypeProperty trainCompany = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainCompany" );
    
    public static final DatatypeProperty trainEquipment = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainEquipment" );
    
    public static final DatatypeProperty trainJourneyClass = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainJourneyClass" );
    
    public static final DatatypeProperty trainJourneyCode = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainJourneyCode" );
    
    public static final DatatypeProperty trainName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainName" );
    
    public static final DatatypeProperty trainType = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#trainType" );
    
    public static final DatatypeProperty unit = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#unit" );
    
    public static final DatatypeProperty userName = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#userName" );
    
    public static final DatatypeProperty vehicleSize = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#vehicleSize" );
    
    public static final DatatypeProperty zipCode = m_model.createDatatypeProperty( "http://www.owl-ontologies.com/Train.owl#zipCode" );
    
    public static final OntClass Address = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Address" );
    
    public static final OntClass AvailabilityPeriod = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#AvailabilityPeriod" );
    
    public static final OntClass Catering = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Catering" );
    
    public static final OntClass Change = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Change" );
    
    public static final OntClass Company = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Company" );
    
    public static final OntClass ContactInfo = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#ContactInfo" );
    
    public static final OntClass CustomerData = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#CustomerData" );
    
    public static final OntClass Facilities = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Facilities" );
    
    public static final OntClass Inference = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Inference" );
    
    public static final OntClass LinkType = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#LinkType" );
    
    /** <p></p> */
    public static final OntClass MeanOfTransport = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#MeanOfTransport" );
    
    public static final OntClass Msg_BookTrainRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_BookTrainRQ" );
    
    public static final OntClass Msg_BookTrainRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_BookTrainRS" );
    
    public static final OntClass Msg_CancelBookTrainRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_CancelBookTrainRQ" );
    
    public static final OntClass Msg_CancelBookTrainRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_CancelBookTrainRS" );
    
    public static final OntClass Msg_PublicTrainJourneyRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_PublicTrainJourneyRQ" );
    
    public static final OntClass Msg_PublicTrainJourneyRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_PublicTrainJourneyRS" );
    
    public static final OntClass Msg_RegisterTrainRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_RegisterTrainRQ" );
    
    public static final OntClass Msg_RegisterTrainRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_RegisterTrainRS" );
    
    public static final OntClass Msg_TrainAvailRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_TrainAvailRQ" );
    
    public static final OntClass Msg_TrainAvailRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_TrainAvailRS" );
    
    public static final OntClass Msg_TrainDescriptionRQ = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_TrainDescriptionRQ" );
    
    public static final OntClass Msg_TrainDescriptionRS = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Msg_TrainDescriptionRS" );
    
    public static final OntClass Name = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Name" );
    
    public static final OntClass NumberOfUnits = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#NumberOfUnits" );
    
    public static final OntClass ParkingPlace = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#ParkingPlace" );
    
    public static final OntClass Period = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Period" );
    
    public static final OntClass PieceOfLuggage = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#PieceOfLuggage" );
    
    public static final OntClass PointOfTime = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#PointOfTime" );
    
    public static final OntClass Policy = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Policy" );
    
    public static final OntClass Price = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Price" );
    
    public static final OntClass RailwayStation = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#RailwayStation" );
    
    public static final OntClass Seat = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Seat" );
    
    public static final OntClass SeatUnit = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#SeatUnit" );
    
    /** <p>các d?ch v? du l?ch (c? lo?i c? b?n và k?t h?p). TourismService cung c?p m?t 
     *  giao di?n chung cho t?t c? các d?ch v? du l?ch, giao di?n này ??a ra các thu?c 
     *  tính c?n thi?t cho vi?c t?o m?t d?ch v? du l?ch</p>
     */
    public static final OntClass TourismService = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TourismService" );
    
    public static final OntClass Train = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Train" );
    
    public static final OntClass TrainJourney = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TrainJourney" );
    
    public static final OntClass TrainJourneyFacilities = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TrainJourneyFacilities" );
    
    public static final OntClass TrainJourneySeat = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TrainJourneySeat" );
    
    public static final OntClass TrainMessage = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TrainMessage" );
    
    public static final OntClass TrainTickets = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TrainTickets" );
    
    public static final OntClass TranportSeat = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TranportSeat" );
    
    public static final OntClass Transport = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Transport" );
    
    public static final OntClass TransportSeatUnit = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#TransportSeatUnit" );
    
    public static final OntClass Transportation = m_model.createClass( "http://www.owl-ontologies.com/Train.owl#Transportation" );
    
    public static final Individual BH_SG_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#BH_SG_SE1", Change );
    
    public static final Individual Bien_Hoa = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Bien_Hoa", RailwayStation );
    
    public static final Individual Change1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Change1", Change );
    
    public static final Individual Change2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Change2", Change );
    
    public static final Individual DHa_Hue_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#DHa_Hue_SE1", Change );
    
    public static final Individual DHoi_DongHa_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#DHoi_DongHa_SE1", Change );
    
    public static final Individual Da_Nang = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Da_Nang", RailwayStation );
    
    public static final Individual DieuTri_TuyHoa_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#DieuTri_TuyHoa_SE1", Change );
    
    public static final Individual Dieu_Tri = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Dieu_Tri", RailwayStation );
    
    public static final Individual Dn_TamKy_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Dn_TamKy_SE1", Change );
    
    public static final Individual Dong_Ha = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Dong_Ha", RailwayStation );
    
    public static final Individual Dong_Hoi = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Dong_Hoi", RailwayStation );
    
    public static final Individual HN_ND_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#HN_ND_SE1", Change );
    
    public static final Individual HN_PL_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#HN_PL_SE1", Change );
    
    public static final Individual Ha_Noi = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Ha_Noi", RailwayStation );
    
    public static final Individual Hue = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Hue", RailwayStation );
    
    public static final Individual Hue_DN_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Hue_DN_SE1", Change );
    
    public static final Individual MM_BH_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#MM_BH_SE1", Change );
    
    public static final Individual Muong_Man = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Muong_Man", RailwayStation );
    
    public static final Individual NB_TH_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#NB_TH_SE1", Change );
    
    public static final Individual ND_NB_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#ND_NB_SE1", Change );
    
    public static final Individual NT_TC_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#NT_TC_SE1", Change );
    
    public static final Individual Nam_Dinh = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Nam_Dinh", RailwayStation );
    
    public static final Individual Nha_Trang = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Nha_Trang", RailwayStation );
    
    public static final Individual Ninh_Binh = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Ninh_Binh", RailwayStation );
    
    public static final Individual NumberOfUnits1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#NumberOfUnits1", NumberOfUnits );
    
    public static final Individual NumberOfUnits2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#NumberOfUnits2", NumberOfUnits );
    
    public static final Individual PL_ND_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PL_ND_SE1", Change );
    
    public static final Individual Period1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Period1", Period );
    
    public static final Individual Period2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Period2", Period );
    
    public static final Individual Phu_Ly = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Phu_Ly", RailwayStation );
    
    public static final Individual PieceOfLuggage1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PieceOfLuggage1", PieceOfLuggage );
    
    public static final Individual PointOfTime1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PointOfTime1", PointOfTime );
    
    /** <p>= pointOfT1 + 16</p> */
    public static final Individual PointOfTime2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PointOfTime2", PointOfTime );
    
    public static final Individual PointOfTime3 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PointOfTime3", PointOfTime );
    
    /** <p>= POT3 + 18</p> */
    public static final Individual PointOfTime4 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#PointOfTime4", PointOfTime );
    
    public static final Individual Price1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price1", Price );
    
    public static final Individual Price_12k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_12k", Price );
    
    public static final Individual Price_14k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_14k", Price );
    
    public static final Individual Price_22k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_22k", Price );
    
    public static final Individual Price_24k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_24k", Price );
    
    public static final Individual Price_26k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_26k", Price );
    
    public static final Individual Price_38k = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_38k", Price );
    
    public static final Individual Price_catering = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Price_catering", Price );
    
    public static final Individual QN_DieuTri_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#QN_DieuTri_SE1", Change );
    
    public static final Individual QuangTrungPham = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#QuangTrungPham", Name );
    
    public static final Individual Quang_Ngai = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Quang_Ngai", RailwayStation );
    
    public static final Individual SE1_Den_BH = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_BH", PointOfTime );
    
    public static final Individual SE1_Den_DN = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_DN", PointOfTime );
    
    public static final Individual SE1_Den_DieuTri = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_DieuTri", PointOfTime );
    
    public static final Individual SE1_Den_DongHa = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_DongHa", PointOfTime );
    
    public static final Individual SE1_Den_DongHoi = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_DongHoi", PointOfTime );
    
    public static final Individual SE1_Den_Hue = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_Hue", PointOfTime );
    
    public static final Individual SE1_Den_MuongMan = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_MuongMan", PointOfTime );
    
    public static final Individual SE1_Den_NB = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_NB", PointOfTime );
    
    public static final Individual SE1_Den_ND = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_ND", PointOfTime );
    
    public static final Individual SE1_Den_NT = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_NT", PointOfTime );
    
    public static final Individual SE1_Den_PL = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_PL", PointOfTime );
    
    public static final Individual SE1_Den_QN = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_QN", PointOfTime );
    
    public static final Individual SE1_Den_SG = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_SG", PointOfTime );
    
    public static final Individual SE1_Den_TH = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_TH", PointOfTime );
    
    public static final Individual SE1_Den_TamKy = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_TamKy", PointOfTime );
    
    public static final Individual SE1_Den_ThapCham = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_ThapCham", PointOfTime );
    
    public static final Individual SE1_Den_TuyHoa = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_TuyHoa", PointOfTime );
    
    public static final Individual SE1_Den_Vinh = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_Den_Vinh", PointOfTime );
    
    public static final Individual SE1_HN_SG = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#SE1_HN_SG", TrainJourney );
    
    public static final Individual Sai_Gon = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Sai_Gon", RailwayStation );
    
    public static final Individual Se1_Tu_HN = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Se1_Tu_HN", PointOfTime );
    
    public static final Individual TC_MuongMan_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TC_MuongMan_SE1", Change );
    
    public static final Individual TH_Vinh_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TH_Vinh_SE1", Change );
    
    public static final Individual TamKy_QN_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TamKy_QN_SE1", Change );
    
    public static final Individual Tam_Ky = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Tam_Ky", RailwayStation );
    
    public static final Individual Thanh_Hoa = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Thanh_Hoa", RailwayStation );
    
    public static final Individual Thap_Cham = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Thap_Cham", RailwayStation );
    
    public static final Individual Train1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Train1", Train );
    
    public static final Individual TrainJourneyFacilities1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainJourneyFacilities1", TrainJourneyFacilities );
    
    public static final Individual TrainJourneySeat1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainJourneySeat1", TrainJourneySeat );
    
    public static final Individual TrainJourney_22 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainJourney_22", TrainJourney );
    
    public static final Individual TrainTickets_HN_ND_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_HN_ND_SE1", TrainTickets );
    
    public static final Individual TrainTickets_HN_PL_SE1_1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_HN_PL_SE1_1", TrainTickets );
    
    public static final Individual TrainTickets_HN_PL_SE1_2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_HN_PL_SE1_2", TrainTickets );
    
    public static final Individual TrainTickets_NB_TH_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_NB_TH_SE1", TrainTickets );
    
    public static final Individual TrainTickets_ND_NB_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_ND_NB_SE1", TrainTickets );
    
    public static final Individual TrainTickets_PL_ND_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TrainTickets_PL_ND_SE1", TrainTickets );
    
    /** <p>ch?a nh?p h?t các thu?c tín</p> */
    public static final Individual Train_DN_HCM = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Train_DN_HCM", TrainJourney );
    
    /** <p>ch?a nh?p h?t các thu?c tín</p> */
    public static final Individual Train_HN_DN = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Train_HN_DN", TrainJourney );
    
    public static final Individual Transport1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Transport1", Transport );
    
    public static final Individual Transport2 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Transport2", Transport );
    
    public static final Individual TransportSeatUnit1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TransportSeatUnit1", TransportSeatUnit );
    
    public static final Individual TuyHoa_NhaTrang_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#TuyHoa_NhaTrang_SE1", Change );
    
    public static final Individual Tuy_Hoa = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Tuy_Hoa", RailwayStation );
    
    public static final Individual Vinh = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Vinh", RailwayStation );
    
    public static final Individual Vinh_DongHoi_SE1 = m_model.createIndividual( "http://www.owl-ontologies.com/Train.owl#Vinh_DongHoi_SE1", Change );
    
}
