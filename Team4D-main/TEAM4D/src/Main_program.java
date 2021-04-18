import com.sun.tools.javac.Main;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.*;
import java.awt.print.Book;





public class Main_program {
    UserController uc;
    TripController tc;
    BookingController bc;
    ReviewController rc;

    public Main_program() throws Exception{
        uc = new UserController();
        tc = new TripController();
        bc = new BookingController();
        rc = new ReviewController();
    }

    public void search() throws Exception{
        tc.connect();
        System.out.println("Please enter a keyword");
        String inp = StdIn.readString();
        tc.search(inp);
        Trip[] results = tc.getTripList();
        System.out.println("Search results:\n");
        for (int i = 0; i<results.length; i++){
            if (results[i]== null){
                break;
            }
            System.out.println(results[i].getName() + " : " + results[i].getPrice() + " : " + results[i].getId());
        }
        tc.close();
    }

    public void sort() throws Exception{
        tc.connect();
        tc.sort();
        Trip[] results = tc.getTripList();
        System.out.println("trips sorted by price:\n");
        for (int i = 0; i<results.length; i++){
            if (results[i]== null){
                break;
            }
            System.out.println(results[i].getName() + " : " + results[i].getPrice() + " : " + results[i].getId());
        }
        tc.close();
    }

    public void change() throws Exception{
        uc.connect();
        System.out.println("please enter username:");
        String userName = StdIn.readString();
        uc.createUser(userName);
        System.out.println("Currently logged in as:");
        System.out.println(uc.getUser().getName() + "\n");
        uc.close();
    }

    public void book() throws Exception{
        tc.connect();
        System.out.println("Please enter the trip ID:");
        int id = StdIn.readInt();
        System.out.println("Please enter number of seats you would like to book:");
        int numberSeats = StdIn.readInt();
        Trip desired = tc.getTrip(id);
        tc.close();
        if (desired.getAvailableSeats() <= numberSeats){
            System.out.println("insufficient seats");
        }
        else {
            bc.connect();
            bc.createBooking(uc.getUser(), desired, numberSeats);
            bc.printAll();
            bc.close();
            tc.connect();
            tc.removeSeats(desired, numberSeats);
            tc.close();
        }
    }

    public void info(User user) throws Exception{
        bc.connect();
        Booking[] bookings = bc.getBookings(user);
        bc.close();
        System.out.println("reviews");
        for (int i = 0; i<bookings.length;i++){
            if (bookings[i] == null){
                break;
            }
            System.out.println(bookings[i].getTripName() + " : "+ bookings[i].getTripId());
        }
    }
    public void review(User user) throws Exception {
        System.out.println("Select ID of trip to review");
        int id = StdIn.readInt();
        tc.connect();
        Trip trip = tc.getTrip(id);
        tc.close();
        rc.connect();
        if (trip == null){
            return;
        }
        System.out.println("Please enter score\n0-5");
        Scanner sc= new Scanner(System.in);
        String temp = sc.nextLine();
        try{
            int tripScore = Integer.parseInt(temp);
            if ((0 > tripScore) || (tripScore > 5)){
                System.out.println("score must be between 0 and 5");
                review(user);
                return;
            }
            System.out.println("Please enter text");
            String reviewText = sc.nextLine();
            rc.createReview(user, id, tripScore, reviewText);
            rc.close();
        }
        catch (Exception e){
            System.out.println("invalid input");
            return;
        }
    }

    public void seeReview() throws Exception{
        System.out.println("Select ID of trip to see review of");
        String id = StdIn.readString();
        try{
            tc.connect();
            Trip trip = tc.getTrip(Integer.parseInt(id));
            tc.close();

            rc.connect();
            Review[] r = rc.getReviewList(trip);
            rc.close();
            float score = 0;
            float amt = 0;
            for (int i = 0; i<r.length; i++){
                if (r[i] == null){
                    break;
                }
                System.out.println(r[i].getBody());
                score += r[i].getScore();
                amt += 1;
            }
            if (amt == 0){
                System.out.println("this trip has no reviews");
            }
            else {
                float rating = score/amt;
                System.out.println("trip rating: " + rating);
            }
        }
        catch (Exception e){
            System.out.println("this trip does not exist");
        }

    }



    //DEBUG MODE
    public void debug() throws Exception{
        System.out.println("u: show all users\nb: show all bookings\ns: print seats");
        String inp = StdIn.readString();
        if (inp.equals("u")){
            uc.connect();
            uc.printAll();
            uc.close();
        }
        else if(inp.equals("b")){
            bc.connect();
            bc.printAll();
            bc.close();
        }
        else if(inp.equals("s")){
            tc.connect();
            tc.printSeats();
            tc.close();
        }
        else{
            System.out.println("invalid input");
        }
    }

    public void run() throws Exception{
        boolean cont = true;
        System.out.println("please enter username:");
        String userName = StdIn.readString();
        uc.createUser(userName);
        if (uc.isLogin()){
            uc.close();
            System.out.println("Currently logged in as:");
            System.out.println(uc.getUser().getName() + "\n");
            tc.search();
            tc.close();
            Trip[] allTrips = tc.getTripList();
            System.out.println("All availible trips:\nname:price:id");
            for (int i = 0; i<allTrips.length; i++){
                if (allTrips[i] == null){
                    break;
                }
                System.out.println(allTrips[i].getName() + " : " + allTrips[i].getPrice() + " : " +allTrips[i].getId());
            }
            while (cont) {
                System.out.println("What would you like to do?\ns: search \np :sort based on price \nb: book \nr: review trip\nsr: see review \nu: user info\nc: change user \nq: quit");
                String input = StdIn.readString();
                if (input.equals("q")){
                    System.out.println("Exiting program");
                    cont = false;
                    break;
                }
                if (input.equals("s")){
                    search();
                }
                else if (input.equals("p")){
                    sort();
                }
                else if (input.equals("b")){
                    book();
                }
                else if (input.equals("c")){
                    change();
                }
                else if (input.equals("r")){
                    review(uc.getUser());
                }
                else if (input.equals("u")){
                    info(uc.getUser());
                }
                //Debugging not in final project
                else if (input.equals("d")){
                    debug();
                }
                else if (input.equals("sr")){
                    seeReview();
                }
                else{
                    System.out.println("invalid input");
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main_program mc = new Main_program();
        mc.run();
    }
}
