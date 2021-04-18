import java.awt.print.Book;
import java.sql.ResultSet;

public class BookingController{

    private Booking[] bookingList;
    BookingDataLayer dl;
    private int index = 0;

    public BookingController() throws Exception{
        bookingList = new Booking[10];
        dl = new BookingDataLayer();
    }

    public Booking[] getBookingList() {
        return bookingList;
    }

    public void printAll() throws Exception{
        dl.printAll();
    }

    public Booking[] getBookings(User user) throws Exception{
        ResultSet rs = dl.getBookings(user.getName());
        bookingList = new Booking[20];
        int index = 0;
        while (rs.next()){
            bookingList[index++] = new Booking(user, rs.getString("tripName"), rs.getInt("tripId"),rs.getInt("bookingId"));
        }
        return bookingList;
    }

    public void connect() throws Exception{
        dl.connect();
    }

    public void close() throws Exception{
        dl.close();
    }


    public void createBooking(User user, Trip trip, int numberSeats) throws Exception{
        String userName = user.getName();
        int tripId = trip.getId();
        String tripName = trip.getName();
        dl.createBooking(userName, tripName, tripId, numberSeats);
    }
    public void cancelBooking(Booking booking) {
        return;
    }
    public void changeBooking(Booking booking){
        return;
    }

}