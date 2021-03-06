import edu.princeton.cs.algs4.StdIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingDataLayer {
    private Connection conn;
    private Statement myStatement;

    public BookingDataLayer() throws Exception {
        Class.forName("org.sqlite.JDBC");
        conn = null;
        try {
            // Tengjast við database
            conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");


            //Ef við viljum eyða töflunni, búa til nýja og fylla inn í hana
            /////////////////////////////////////////////////////////////////
            // Eyða töflu ef er til (Ekki gert í loka verkefninu)
            /*
            myStatement = conn.createStatement();
            // Br til töfluna
            myStatement.executeUpdate("DROP table IF EXISTS Booking");
            myStatement.executeUpdate("CREATE TABLE Booking(userName TEXT, tripName TEXT, tripId INTEGER, bookingID INTEGER UNIQUE, numberSeats INTEGER, PRIMARY KEY(userName,tripid))");
            */


            /////////////////////////////////////////////////////////////////

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public int deleteBooking(String userName, int tripId) throws Exception {
        String stmnt = "SELECT numberSeats FROM Booking where userName == ? AND tripID == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.setInt(2,tripId);
        ResultSet rs = p.executeQuery();
        try {
            rs.next();
            int numSeats = 0;
            numSeats = rs.getInt("numberSeats");
            if (numSeats == 0) {
                return 0;
            }
            stmnt = "DELETE FROM Booking WHERE userName==? AND tripId==?";
            p = conn.prepareStatement(stmnt);
            p.setString(1, userName);
            p.setInt(2, tripId);
            p.executeUpdate();
            return numSeats;
        }
        catch (Exception e){
            System.out.println("This booking does not exist");
            return 0;
        }
    }

    public void createBooking(String userName, String tripName, int tripId, int numberSeats) throws Exception{
        String stmnt = "SELECT * FROM Booking WHERE userName == ? AND tripId == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.setString(2, Integer.toString(tripId));
        ResultSet rs = p.executeQuery();
        if (rs.next()){
            System.out.println("This booking already exists");
            return;
        }
        stmnt = "INSERT INTO Booking(userName, tripName, TripId, numberSeats) VALUES (?,?,?,?)";
        p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.setString(2,tripName);
        p.setString(3, Integer.toString(tripId));
        p.setString(4, Integer.toString(numberSeats));
        p.executeUpdate();
        System.out.println("Booking created");
    }

    public ResultSet getBookings(String userName) throws Exception{
        String stmnt = "SELECT * FROM Booking WHERE userName == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1,userName);
        ResultSet rs = p.executeQuery();
        return rs;
    }

    public void printAll() throws Exception{
        String stmnt = "SELECT * FROM Booking";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        System.out.println("All bookings");
        while(rs.next()){
            System.out.println(rs.getString("userName") + " : " + rs.getString("tripName") + "  : " + rs.getString("tripId"));
        }
        System.out.println("**********************");
    }
    
    Connection getConn() throws Exception{
        return conn;
    }

    void connect() throws Exception{
        conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");
    }

    void close() throws Exception{
        conn.close();
    }
}