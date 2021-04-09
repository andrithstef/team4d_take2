public class User {

    private String userName;
    private String email;
    private int id;

    public User(String userName, String email, int id){
        this.userName = userName;
        this.email = email;
        this.id = id;
    }

    public String getName(){
        return userName;
    }

    public String getEmail(){
        return email;
    }

    public int getId(){
        return id;
    }

}
