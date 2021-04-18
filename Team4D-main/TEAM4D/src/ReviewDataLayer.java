import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;

public class ReviewDataLayer {
    private Connection conn;
    private Statement myStatement;

    public ReviewDataLayer() throws Exception {
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
            myStatement.executeUpdate("DROP TABLE IF EXISTS Reviews");

            // Br til töfluna
            myStatement.executeUpdate("CREATE TABLE Reviews(user TEXT, tripId INTEGER, score INTEGER, title TEXT, body TEXT, PRIMARY KEY(user, tripId))");
            */
            /////////////////////////////////////////////////////////////////


        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //Skilar öllum trips í db
    void printAll() throws Exception{
        String stmnt = "SELECT * from Reviews";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("user") + " : " + rs.getString("tripId")
                    + " : " + rs.getString("score") + " : " + rs.getString("title")
                    + " : " + rs.getString("body"));
        }
    }

    //Býr til nýtt review
    void createNewReview(String user, int id, int score, String title, String body) throws Exception{
        String stmnt = "SELECT * FROM Reviews WHERE user==? AND tripId==?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, user);
        p.setInt(2, id);
        ResultSet rs = p.executeQuery();
        if (rs.next()){
            System.out.println("Review already exists");
            return;
        }
        stmnt = "INSERT INTO Reviews(user, tripId, score, title, body) VALUES(?,?,?,?,?)";
        p = conn.prepareStatement(stmnt);
        p.setString(1, user);
        p.setInt(2,id);
        p.setInt(3,score);
        p.setString(4,title);
        p.setString(5,body);
        p.executeUpdate();
    }

    //Eyðir review
    void deleteReview(String userName, int id) throws Exception {
        String stmnt = "DELETE FROM Reviews WHERE user=? AND tripId=?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.setInt(2,id);
        p.executeUpdate();
    }

    //Skilar öllum reviews fyrir ákveðið trip
    ResultSet getReviews(Trip trip) throws Exception {
        int id = trip.getId();
        String stmnt = "SELECT * from Reviews WHERE tripId == "+id;
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

    void connect() throws Exception{
        conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");
    }

    //Test
    public static void main(String[] args) throws Exception {
        ReviewDataLayer a = null;
        try{
            a = new ReviewDataLayer();
            a.printAll();
            a.deleteReview("Biggi", 2);
            StdOut.println();
            a.printAll();
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
