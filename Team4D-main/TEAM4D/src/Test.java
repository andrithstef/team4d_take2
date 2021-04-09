import java.sql.ResultSet;

public class Test {
    public static void main(String[] args) throws Exception {
        TripDataLayer a = new TripDataLayer();
        ResultSet rs = a.sort();
        while (rs.next()){
            System.out.println(rs.getString("name") + " : " + rs.getInt("price") + " : " + rs.getInt("id"));
        }
    }
}
