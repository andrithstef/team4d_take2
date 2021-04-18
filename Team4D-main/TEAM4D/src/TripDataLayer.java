import edu.princeton.cs.algs4.StdIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripDataLayer {
    private Connection conn;
    private Statement myStatement;

    public TripDataLayer() throws Exception {
        Class.forName("org.sqlite.JDBC");
        conn = null;
        try {
            // Tengjast við database
            conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");


            //Ef við viljum eyða töflunni, búa til nýja og fylla inn í hana
            /////////////////////////////////////////////////////////////////
            // Eyða töflu ef er til (Ekki gert í loka verkefninu)
            myStatement = conn.createStatement();
            /*
            myStatement.executeUpdate("DROP TABLE IF EXISTS Trips");

            // Br til töfluna
            myStatement.executeUpdate("CREATE TABLE Trips(name INTEGER, price INTEGER, id INTEGER PRIMARY KEY)");

            // Fylla inn í töfluna
            String stmnt = "INSERT INTO Trips(name,price) VALUES(?,?)";
            PreparedStatement p = conn.prepareStatement(stmnt);
            while (!StdIn.isEmpty()) {
                String name = StdIn.readString();
                int price = StdIn.readInt();
                p.setString(1,name);
                p.setString(2,Integer.toString(price));
                p.execute();
            }
            /////////////////////////////////////////////////////////////////
            */
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    ResultSet search(String querie) throws Exception{
        String stmnt = "SELECT * from Trip WHERE NAME LIKE ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1,"%"+querie+"%");
        ResultSet rs = p.executeQuery();
        return rs;
    }

    ResultSet search() throws Exception{
        String stmnt = "SELECT * from Trip";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        return rs;
    }

    ResultSet getTrip(int ID) throws Exception{
        String stmnt = "SELECT * from Trip WHERE id == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setInt(1,ID);
        ResultSet rs = p.executeQuery();
        return rs;
    }

    ResultSet sort() throws Exception{
        String stmnt = "SELECT * FROM Trip ORDER BY price ASC, name ASC";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        return rs;
    }

    Connection getConn() throws Exception{
        return conn;
    }

    void close() throws Exception{
        conn.close();
    }

    public void connect() throws Exception{
        conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");
    }

    public void printAll() throws Exception{
        String stmnt = "SELECT * FROM Trip";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("name"));
        }
    }

    public void removeSeat(int id, int seats) throws Exception{
        String stmnt = "Update trip set(availableSeats) == ? WHERE id == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1,Integer.toString(seats));
        p.setString(2,Integer.toString(id));
        p.executeUpdate();
    }

    public void printSeats() throws Exception{
        String stmnt = "SELECT name, availableSeats, id FROM Trip";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        System.out.println("name : seats : id");
        while (rs.next()){
            System.out.println(rs.getString("name") + " : " + rs.getString("availableSeats") + " : " + rs.getString("id"));
        }
    }

    public void addSeat(int id, int seats) throws Exception{
        String stmnt = "Update trip set(availableSeats) == ? WHERE id == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1,Integer.toString(seats));
        p.setString(2,Integer.toString(id));
        p.executeUpdate();
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