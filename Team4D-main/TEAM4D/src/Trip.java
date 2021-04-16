public class Trip {

    private String name;
    private int id;
    private int price;
    private String location;
    private int availableSeats;
    private boolean accessibility;
    private boolean meals;
    private boolean vegan;
    private String activityType;
    private double popularity;
    private double discount;
    private String time;
    private double rating;


    public Trip(String name, int id, int price, String location, int availableSeats, boolean accessibility, boolean meals, boolean vegan, String activityType, double popularity, double discount, String time, double rating) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.location = location;
        this.availableSeats = availableSeats;
        this.accessibility = accessibility;
        this.meals = meals;
        this.vegan = vegan;
        this.activityType = activityType;
        this.popularity = popularity;
        this.discount = discount;
        this.time = time;
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public boolean isMeals() {
        return meals;
    }

    public boolean isVegan() {
        return vegan;
    }

    public String getActivityType() {
        return activityType;
    }

    public double getPopularity() {
        return popularity;
    }

    public double getDiscount() {
        return discount;
    }

    public String getTime() {
        return time;
    }

    public double getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}