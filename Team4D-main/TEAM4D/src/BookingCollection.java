public class BookingCollection{

    private Booking[] bookingList;
    //BookingDataLayer bookingData;
    private int index = 0;

    public BookingCollection(){
        bookingList = new Booking[10];
        //booking = new BookingDataLayer();
    }

    public Booking[] getBookingList() {
        return bookingList;
    }

    public void createBooking(User user, Trip trip) {
        if (index >= bookingList.length-1){
            Booking[] temp = new Booking[2*bookingList.length];
            for (int i = 0; i <= index; i++){
                temp[i] = bookingList[i];
            }
            bookingList = temp;
            temp = null;
        }
        Booking b = new Booking(user, trip);
        bookingList[index++] = b;
    }
    public void cancelBooking(Booking booking) {
        return;
    }
    public void changeBooking(Booking booking){
        return;
    }
}

