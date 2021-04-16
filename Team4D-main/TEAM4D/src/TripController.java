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
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"));
        }
    }

    public void search(String querie) throws Exception{
        tripList = new Trip[20];
        ResultSet rs = dl.search(querie);
        int index = 0;
        while (rs.next()){
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"));
        }
    }

    public Trip getTrip(int ID) throws Exception{
        ResultSet rs = dl.getTrip(ID);
        rs.next();
        Trip result = new Trip(rs.getString("name"),rs.getInt("price"),rs.getInt("id"));
        return result;
    }

    public void printAll() throws Exception{
        dl.printAll();
    }

    public void sort() throws Exception{
        tripList = new Trip[20];
        ResultSet rs = dl.sort();
        int index = 0;
        while (rs.next()){
            tripList[index++] = new Trip(rs.getString("name"), rs.getInt("id"), rs.getInt("price"));
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