import edu.princeton.cs.algs4.StdIn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataLayer {
    private Connection conn;
    private Statement myStatement;

    public UserDataLayer() throws Exception {
        Class.forName("org.sqlite.JDBC");
        conn = null;
        try {
            // Tengjast við database
            conn = DriverManager.getConnection("jdbc:sqlite:team4d.db");
            /*
            //Ef við viljum eyða töflunni, búa til nýja og fylla inn í hana
            /////////////////////////////////////////////////////////////////
            // Eyða töflu ef er til (Ekki gert í loka verkefninu)
            myStatement = conn.createStatement();
            myStatement.executeUpdate("DROP TABLE IF EXISTS Users");

            // Br til töfluna

            myStatement = conn.createStatement();
            myStatement.executeUpdate("Drop Table User");
            myStatement.executeUpdate("CREATE TABLE User(userName TEXT PRIMARY KEY, id INTEGER UNIQUE)");
            */
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void printAll() throws Exception{
        String stmnt = "SELECT * from User";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("userName"));
        }
    }

    int createUser(String userName) throws Exception{
        // ? er breyta, setstring setur gildi
        String stmnt = "SELECT * FROM User WHERE userName == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        ResultSet rs = p.executeQuery();
        if (rs.next()){
            System.out.println("User already exists\nLogging in...");
            return -1;
        }
        System.out.println("Creating new user...");
        stmnt = "INSERT INTO User(userName) VALUES(?)";
        p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.executeUpdate();
        stmnt = "SELECT id FROM User WHERE userName == ?";
        p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        rs = p.executeQuery();
        rs.next();
        int id = rs.getInt("id");
        return id;
    }

    ResultSet getUser(String userName) throws Exception{
        String stmnt = "SELECT * from User WHERE userName == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
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
}