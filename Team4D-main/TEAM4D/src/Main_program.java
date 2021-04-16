import com.sun.tools.javac.Main;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

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

    public void book() throws Exception{
        tc.connect();
        System.out.println("Please enter the trip ID:");
        int id = StdIn.readInt();
        Trip desired = tc.getTrip(id);
        tc.close();
        if (desired.getAvailableSeats() <= 0){
            System.out.println("No seats availible");
        }
        else {
            bc.createBooking(uc.getUser(), desired);
            System.out.println("ID");
            System.out.println(desired.getId());
            System.out.println(desired.getPrice());
            bc.printAll();
        }
    }


    public void run() throws Exception{
        boolean cont = true;
        System.out.println("please enter username and email:");
        String userName = StdIn.readString();
        String email = StdIn.readString();
        uc.createUser(userName,email);
        if (uc.isLogin()){
            uc.close();
            System.out.println("Currently logged in as:");
            System.out.println(uc.getUser().getName() + "\n");
            tc.search();
            Trip[] allTrips = tc.getTripList();
            System.out.println("All availible trips:\nname:price:id");
            for (int i = 0; i<allTrips.length; i++){
                if (allTrips[i] == null){
                    break;
                }
                System.out.println(allTrips[i].getName() + " : " + allTrips[i].getPrice() + " : " +allTrips[i].getId());
            }
            while (cont) {
                System.out.println("What would you like to do?\ns: search \np:sort based on price \nb: book \nr: review trip\nq: quit");
                String input = StdIn.readString();
                if (input.equals("q")){
                    System.out.println("Exiting program");
                    cont = false;
                    break;
                }
                if (!input.equals("s") && !input.equals("b") && !input.equals("r")){
                    System.out.println("invalid input");
                }
                else if (input.equals("s")){
                    search();
                }
                else if (input.equals("p")){
                    sort();
                }
                else if (input.equals("b")){
                    book();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Main_program mc = new Main_program();
        mc.run();
    }
}
