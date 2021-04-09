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

    public void createBooking(User user, Trip trip) throws Exception{
        String userName = user.getName();
        int tripId = trip.getId();
        dl.createBooking(userName,tripId);
    }
    public void cancelBooking(Booking booking) {
        return;
    }
    public void changeBooking(Booking booking){
        return;
    }

    public static void main(String[] args) throws Exception{
        UserController u = new UserController();
        TripController t = new TripController();
        BookingController b = new BookingController();
        u.printAll();
        u.createUser("andrith", "ats21");
        System.out.println("User online");
        t.search();
        System.out.println("searched");
        u.close();
        t.close();
        Trip[] trips = t.getTripList();
        for (int i = 0; i<trips.length; i++){
            if (trips[i] == null){
                break;
            }
            b.createBooking(u.getUser(), trips[i]);
            b.printAll();
        }
    }
}