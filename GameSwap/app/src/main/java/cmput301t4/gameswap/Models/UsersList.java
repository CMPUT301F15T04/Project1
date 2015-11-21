package cmput301t4.gameswap.Models;

import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Rupehra on 2015-11-21.
 */
public class UsersList {
    private ArrayList<User> userList = new ArrayList<User>();

    /**
     * Stores all the users of the App
     */
    public void addUser(User user){userList.add(user);}

    /**
     * Removes a user from the userlist
     */
    public void delUser(int position){userList.remove(position);}

    /**
     * Getter to return the username of the user
     */
    public String getUser(String user){
        if (hasUser(user)){
            return userList.get(userList.indexOf(user)).getUserName().toString();
        }
        else
            return "null";
    }
    /**
     * Checks if the user exists
     */
    public boolean hasUser(String user){
        if (userList.contains(user)){
            return true;
        }
        else
            return false;
    }
    /**
     * Get the size of the user list
     */
    public int getUserListSize(){
        return userList.size();
    }
}