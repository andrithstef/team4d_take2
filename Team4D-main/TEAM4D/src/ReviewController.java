import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReviewController {

    private Review[] reviewList;
    private ReviewDataLayer rdl;
    //Dummy Users og Trips fyrir testing
    //private static User userReview = new User("Biggi", "biggi@gmail.com", 12);
    //private static User userReview2 = new User("Boggo", "boggo@gmail.com", 13);
    //private static Trip tripReview = new Trip("Skidi", 2,12000);
    //private static Trip tripReview2 = new Trip("Skautar", 3,3000);

    public ReviewController() throws Exception {
        reviewList = new Review[10];
        rdl = new ReviewDataLayer();
    }


    //Skilar Öllum reviews fyrir ákveðið trip
    public Review[] getReviewList(Trip trip) throws Exception {
        reviewList = new Review[20];
        ResultSet rs = rdl.getReviews(trip);
        int index = 0;
        while (rs.next()){
            reviewList[index++] = new Review(rs.getString("user"),
                    rs.getInt("tripId"),
                    rs.getInt("score"),
                    rs.getString("body"));
        }
        return reviewList;
    }

    //Skilar öllum trips í db
    public void printAll() throws Exception{

        System.out.println("All existing reviews");
        rdl.printAll();
        System.out.println("*********************");
    }

    //Eyðir review
    public void deleteReview(User user, Trip trip) throws Exception {
        rdl.deleteReview(user.getName(), trip.getId());
    }

    //Býr til nýtt review
    public void createReview(User user, int tripId, int score,  String body) throws Exception {

        rdl.createNewReview(user.getName(), tripId, score, body);
    }

    public void close() throws Exception{
        rdl.close();
    }

    public void connect() throws Exception{
        rdl.connect();
    }

    //Test
    public static void main(String[] args) throws Exception{
        ReviewController rc = new ReviewController();
        /*
        rc.createReview(userReview, tripReview,4, "Skidi eru god", "Va ogedslega gaman");
        rc.createReview(userReview2, tripReview,3, "Skidi eru vond", "Va ogedslega leidinlegt");
        rc.createReview(userReview, tripReview2,1, "Skautar ojjj", "drepleidinleg ferd");
        rc.printAll();
        Review[] listi = rc.getReviewList(tripReview);
        for (int i = 0; i<10;i++) {
            if (listi[i] == null) {
                break;
            }
            System.out.println(listi[i].getUser() + " : " + listi[i].getTripId()
                    + " : " + listi[i].getScore()
                    + " : " + listi[i].getTitle()
                    + " : " + listi[i].getBody());
        }

         */
    }
}
