# flight_booking



# Backend Documentation

## Database Backend Documentation by Biruk Solomon

### Typical Use Cases

### Starting Client Session

// entry point is SessionManager Class
// exception thrown if there is no username and password matching the one specified
UserSession userSession = SessionManager.createUserSession(String username, String Password) throws NoMatchingRows 

// Also able to create user by sending the values for the columns as an ArrayList<String> which will be used to insert row into table and return a user object which can be used to create session (and also to modify the column values)
// execption thrown in case if repeated primary key
User user = SessionManager.createUser(ArrayList<String> credentials) throws InvalidEntry
// to create UserSession
UserSession userSession = SessionManager.createUserSession(user)

// You can also create an unsigned user session if the user is going to sign up after they choose a flight
// you need to supply uniqueID used to hold seat
UnsignedUserSession unsignedUserSession = SessionManager.createUnsignedUserSession(String UniqueID)

### Starting Admin Session

// Similar with User session creation. Username and password is hardcoded to ‘root’
//throws if supplied username and password are not ‘root’
AdminSession adminSession = SessionManager.createAdminSession(String username, String password)

### Performing Client Operations

All these operations can be called from either UserSession or UnsignedUserSession
Find Flights with arrivalDate, departureDate, arrivalCity, and departureCity
// returned is an ArrayList of Flight details each stored as a hashmap with keys being the column names and values being the values in those columns 
// on a side note the classes User (for passenger), Aircraft, Location, and Flight have a method called getAttributes() that returns a hashmap of the same style
ArrayList<HashMap<String, String>> flightsListed = findFlights(Timestamp arrivalDate, Timestamp departureDate, String arrivalCity, String departureCity) 

### Find booked seats for flights
//returned is an ArrayList of Seat numbers 
ArrayList<String> bookedSeats = findBookedSeats(String flightID)

Check if session is still alive

// returns a Boolean that is true is session is still on and false if session is expired
Boolean isAlive = isSessionAlive()

### Hold seat for passenger

// reserves a seat for passenger
// throws overbooking error if passenger has reserved another seat without paying
holdSeat(String flightID) throws OverbookingError

UnsignedUserSession operations 
 addCredentials(ArrayList<String> creds) throws InvalidEntry
if this method executes successfully then Unsigned user can make the same calls as user session

### UserSession operations
// UserSession throws an OverBooking exception on holdSeat method if User has another Flight at that time
Modify User fields 
User user = UserSession.getUser()
//user class is discussed later

### Register baggage 
// an ArrayList of ArrayList containing 2 elements: BaggageTag and Weight (in that order)
registerBaggage(ArrayList<ArrayList<String>> info, String flightID)
  
### CheckIn
//Checks In user
checkIn(String flightID)
### Book flight
reserveFlight(String flightID, String ticketClass, String seatNumber) throws OverbookingError, SessionExpired

### Admin Session operations
    public Flight findFlight(String flightID) throws NoMatchingRow {
    public Location findLocation(String locationID) throws NoMatchingRow {
    public Aircraft findAircraft(String aircraftID) throws NoMatchingRow {
    public void addLocation(ArrayList<String> creds) throws InvalidEntry {
    public void addAircraft(ArrayList<String> creds) throws InvalidEntry {
    public void addFlight(ArrayList<String> creds) throws InvalidEntry{

User Flight Location and Aircraft Classes implement DataManger Interface which has the following methods
    // refresh the values of the object by syncing with database
    void sync();
    
    // saves the changes in object to the database
    void update() throws InvalidEntry;
    
    // returns a HashMap whose keys are the attributes and values are the 
    // values of those attributes
    HashMap<String, String> getAttributes();
    
    // generate object by reading a row of the database
    // doesn't move ResultSet to next entry
    void readRow(ResultSet row);

other than that each class has a set and get method for each of the attribute that you can change then call the update method to update the database

