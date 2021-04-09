import java.sql.ResultSet;

public class UserController{

    private User login;
    private UserDataLayer dl;

    public UserController() throws Exception{
        dl = new UserDataLayer();
    }

    public void login() throws Exception{
        String userName = "andri";
        String email = "ats21";
        int id = -1;
        ResultSet rs = dl.getUser(userName, email);
        while (rs.next()){
            id = rs.getInt("id");
        }
        if (id == -1){
            System.out.println("User does not exist");
            return;
        }
        login = new User(userName, email, id);
    }

    public void createUser(String userName, String email) throws Exception{

    }

    public User getUser() {
        return login;
    }

    public static void main(String[] args) throws Exception{
        UserController uc = new UserController();
        uc.login();
        User logged = uc.getUser();
        System.out.println(logged.getName());
    }
}