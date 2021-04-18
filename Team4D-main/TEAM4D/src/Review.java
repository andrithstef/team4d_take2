public class Review {
    private String userNameReview;  //Hefur nafn usersins ekki userinn sjálfann
    private int tripId;             //Hefur ID af trippinu en ekki trippið sjálft
    private int score;
    private String body;

    public Review(String userNameReview, int tripId, int score, String body) {
        this.userNameReview = userNameReview;
        this.tripId = tripId;
        this.score = score;
        this.body = body;
    }

    public String getUser() {
        return userNameReview;
    }

    public int getTripId() {
        return tripId;
    }

    public int getScore() {
        return score;
    }

    public String getBody() {
        return body;
    }
}
