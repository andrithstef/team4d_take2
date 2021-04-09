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


            //Ef við viljum eyða töflunni, búa til nýja og fylla inn í hana
            /////////////////////////////////////////////////////////////////
            // Eyða töflu ef er til (Ekki gert í loka verkefninu)
            myStatement = conn.createStatement();
            myStatement.executeUpdate("DROP TABLE IF EXISTS Users");

            // Br til töfluna
            myStatement.executeUpdate("CREATE TABLE Users(userName TEXT PRIMARY KEY, email TEXT, id INTEGER UNIQUE)");

            // Fylla inn í töfluna
            String stmnt = "INSERT INTO Users(userName, email) VALUES(?,?)";
            PreparedStatement p = conn.prepareStatement(stmnt);
            while (!StdIn.isEmpty()) {
                String userName = StdIn.readString();
                String email = StdIn.readString();
                p.setString(1,userName);
                p.setString(2,email);
                p.execute();
            }
            /////////////////////////////////////////////////////////////////

        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    void printAll() throws Exception{
        String stmnt = "SELECT * from Users";
        PreparedStatement p = conn.prepareStatement(stmnt);
        ResultSet rs = p.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString("userName") + " : " + rs.getString("email"));
        }
    }

    ResultSet getUser(String userName, String email) throws Exception{
        String stmnt = "SELECT * from Users WHERE userName == ? AND email == ?";
        PreparedStatement p = conn.prepareStatement(stmnt);
        p.setString(1, userName);
        p.setString(2, email);
        ResultSet rs = p.executeQuery();
        return rs;
    }

    Connection getConn() throws Exception{
        return conn;
    }

    void close() throws Exception{
        conn.close();
    }

    public static void main(String[] args) throws Exception {
        UserDataLayer a = null;
        try{
            a = new UserDataLayer();
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