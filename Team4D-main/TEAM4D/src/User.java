public class User {

    private String userName;
    private int id;

    public User(String userName, int id){
        this.userName = userName;
        this.id = id;
    }

    public String getName(){
        return userName;
    }

    public int getId(){
        return id;
    }

}
