import java.sql.ResultSet;
import java.sql.SQLException;

public class TripController {

    private Trip[] tripList;
    private int index = 0;
    private TripDataLayer dl;

    public TripController() throws Exception{
        tripList = new Trip[10];
        dl = new TripDataLayer();
    }
    public Trip[] getTripList() {
        return tripList;
    }

    public void search() throws Exception{
        tripList = new Trip[20];
        ResultSet rs = dl.search();
        int index = 0;
        while (rs.next()){
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"), rs.getString("location"), rs.getInt("availableSeats"),
                    rs.getBoolean("accessibility"), rs.getBoolean("meals"), rs.getBoolean("vegan"), rs.getString("activityType"), rs.getDouble("popularity"),
                    rs.getDouble("discount"), rs.getString("time"), rs.getDouble("rating"));
        }
    }

    public void search(String querie) throws Exception{
        tripList = new Trip[20];
        ResultSet rs = dl.search(querie);
        int index = 0;
        while (rs.next()){
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"), rs.getString("location"), rs.getInt("availableSeats"),
                    rs.getBoolean("accessibility"), rs.getBoolean("meals"), rs.getBoolean("vegan"), rs.getString("activityType"), rs.getDouble("popularity"),
                    rs.getDouble("discount"), rs.getString("time"), rs.getDouble("rating"));
        }
    }

    public Trip getTrip(int ID) throws Exception{
        ResultSet rs = dl.getTrip(ID);
        if (rs.next()) {
            Trip result = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"), rs.getString("location"), rs.getInt("availableSeats"),
                    rs.getBoolean("accessibility"), rs.getBoolean("meals"), rs.getBoolean("vegan"), rs.getString("activityType"), rs.getDouble("popularity"),
                    rs.getDouble("discount"), rs.getString("time"), rs.getDouble("rating"));
            return result;
        }
        else{
            System.out.println("This trip does not exist");
            return null;
        }
    }

    public void printSeats() throws Exception{
        dl.printSeats();
    }

    public void removeSeats(Trip trip, int numberSeats) throws Exception{
        int id = trip.getId();
        int seats = trip.getAvailableSeats()-numberSeats;
        dl.removeSeat(id, seats);
    }

    public void addSeat(Trip trip, int numSeats) throws Exception {
        int id = trip.getId();
        int seats = trip.getAvailableSeats()+numSeats;
        dl.addSeat(id, seats);
    }


    public void printAll() throws Exception{
        dl.printAll();
    }

    public void sort() throws Exception{
        tripList = new Trip[20];
        ResultSet rs = dl.sort();
        int index = 0;
        while (rs.next()){
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"), rs.getString("location"), rs.getInt("availableSeats"),
                    rs.getBoolean("accessibility"), rs.getBoolean("meals"), rs.getBoolean("vegan"), rs.getString("activityType"), rs.getDouble("popularity"),
                    rs.getDouble("discount"), rs.getString("time"), rs.getDouble("rating"));
        }
    }

    public void connect() throws Exception{
        dl.connect();
    }

    public void close() throws Exception{
        dl.close();
    }

    public static void main(String[] args) throws Exception {
        try {
            TripController tc;
            tc = new TripController();
            tc.sort();
            Trip[] listi = tc.getTripList();
            for (int i = 0; i < 10; i++) {
                if (listi[i] == null) {
                    break;
                }
                System.out.println(listi[i].getName() + " : " + listi[i].getPrice());
            }
            tc.close();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}