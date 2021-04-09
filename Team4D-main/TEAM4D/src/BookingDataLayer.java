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
            myStatement = conn.createStatement();
            myStatement.executeUpdate("DROP TABLE IF EXISTS Bookings");

            // Br til töfluna
            myStatement.executeUpdate("CREATE TABLE Booking(userName TEXT, tripid INTEGER, bookingID INTEGER UNIQUE, PRIMARY KEY(userName,tripid))");

            /////////////////////////////////////////////////////////////////

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    
    Connection getConn() throws Exception{
        return conn;
    }

    void close() throws Exception{
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        TripDataLayer a = null;
        try{
            a = new TripDataLayer();
            System.out.println("TEST SORT");
            ResultSet rs = a.sort();
            while (rs.next()){
                System.out.println(rs.getString("name") + " : " + rs.getInt("price"));
            }
            System.out.println("---------------------------------");
            System.out.println("TEST SEARCH EMPTY");
            rs = a.search();
            while (rs.next()){
                System.out.println(rs.getString("name") + " : " + rs.getInt("price"));
            }
            System.out.println("---------------------------------");
            System.out.println("TEST SEARCH KAYAK");
            rs = a.search("kayak");
            while (rs.next()){
                System.out.println(rs.getString("name") + " : " + rs.getInt("price"));
            }

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        finally {
            try {
                if (a != null) {
                    if (a.getConn() != null)
                        a.close();
                }
            }
            catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}