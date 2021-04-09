public class UserCollection{

    private User[] userList;
    private int index = 0;
    //private UserDataLayer userData;
    //private User user;

    public UserCollection(){
        userList = new User[2];
    }

    public User[] getUserList() {
        return userList;
    }

    public void createNewUser(String userName, String email, int id){
        if (index >= userList.length-1){
            User[] temp = new User[2*userList.length];
            for (int i = 0; i <= index; i++){
                temp[i] = userList[i];
            }
            userList = temp;
            temp = null;
        }
        User u = new User(userName,email,id);
        userList[index++] = u;
    }
}