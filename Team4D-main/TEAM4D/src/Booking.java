public class Booking{

    private User user;
    private Trip trip;
    private int bookingId;

    public Booking(User user, Trip trip){
        this.user = user;
        this.trip = trip;
        this.bookingId = user.getId()+trip.getId();
    }
    public User getUser(){
        return user;
    }
    public Trip getTrip(){
        return trip;
    }
    public int getBookingId(){
        return bookingId;
    }

}