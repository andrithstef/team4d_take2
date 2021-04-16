import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController{

    private User login = null;
    private UserDataLayer dl;

    public UserController() throws Exception{
        dl = new UserDataLayer();
    }

    public void login(String userName, String email) throws Exception{
        ResultSet rs = dl.getUser(userName,email);
        if (rs.next()){
            System.out.println("logging in...");
            login = new User(userName, email, rs.getInt("id"));
        }
        else{
            System.out.println("no such user exists");
        }
    }

    public void printAll() throws Exception{
        System.out.println("All existing users");
        dl.printAll();
        System.out.println("*********************");
    }

    public void createUser(String userName, String email) throws Exception{
        int id = dl.createUser(userName, email);
        login = new User(userName, email, id);
    }

    public void close() throws Exception{
        dl.close();
    }

    public void connect() throws Exception{
        dl.connect();
    }

    public User getUser() {
        return login;
    }

    public boolean isLogin(){
        return login != null;
    }

    public static void main(String[] args) throws Exception{
        UserController uc = new UserController();
        uc.login("temp", "basicEmal123");
        User logged = uc.getUser();
        uc.printAll();
        if (uc.isLogin()) {
            System.out.println("logged in user: ");
            System.out.println(logged.getName());
            uc.close();
        }

    }
}