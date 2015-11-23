package cmput301t4.gameswap.Managers;

import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Models.UsersList;

/**
 * Created by Rupehra on 2015-11-21.
 */
public class UserListManager {

    private static UsersList usersList = null;
    // singleton Code //
    static public UsersList getInstance(){
        if(usersList == null){
            usersList = new UsersList();
        }
        return usersList;
    }

    static public void addUser(User user){getInstance().addUser(user);}

    static public String getUserName(String user){return getInstance().getUserName(user);}

    //static public String getUserCity(String user){return getInstance().getUserCity(user)}
    static public int getUserListSize(){return getInstance().getUserListSize();}

    static public boolean hasUserName(String username){
        if(getInstance().hasUserName(username)){
            return true;
        }
        return false;
    }
}
