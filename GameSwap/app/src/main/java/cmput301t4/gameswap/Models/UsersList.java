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
    public String getUserName(String user) {
        String name="";
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i).getUserName().toLowerCase().equals(user)) {
               name =userList.get(i).getUserName().toLowerCase();
            }
        return name;
    }
    /**
     * Checks if the user exists
     */
    public boolean hasUserName(String username) {
        int answer = 0;

        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().toLowerCase().equals(username)) {
                answer = 1;
                break;
            }
        }
        if(1 == answer)
            return true;
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
