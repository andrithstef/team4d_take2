public class Booking{

    private User user;
    private String tripName;
    private int tripId;
    private int bookingId;

    public Booking(User user, String tripName, int tripId, int bookingId){
        this.user = user;
        this.tripName = tripName;
        this.tripId = tripId;
        this.bookingId = bookingId;
    }
    public User getUser(){
        return user;
    }


    public String getTripName(){
        return tripName;
    }

    public int getTripId(){
        return tripId;
    }

    public int getBookingId(){
        return bookingId;
    }

}