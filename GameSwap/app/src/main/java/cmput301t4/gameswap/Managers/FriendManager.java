package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.User;

/**
 * Created by kynan on 11/2/15.
 */
public class FriendManager {

    private static FriendList friendList = null;
    //=====Singleton Code=====//
    static public FriendList getFriendlist(){
        if (friendList == null){
            friendList = new FriendList();
        }
        return friendList;
    }//end getFriendList

    static public void addFriend(User friend){
        getFriendlist().addFriend(friend);
    }//end addFriend

    //This del the friend , most likely will
    //be changed later to take in position
    static public void delFriend(int position) {getFriendlist().delFriend(position);
    }//end del

    //Retrieve friend at index
    static public User getUser(int index){return getFriendlist().getFriend(index);}//End getUser

    static public ArrayList<User> getAllUsers() {
        return getFriendlist().getAllFriends();
    }

    //See if friendList contains friend
    static public boolean hasFriend(User trader) {
        return getFriendlist().hasFriend(trader);
    }//end hasFriend

    //Wrote this in for testing, Not sure if we really need
    static public void clearFriendlist(){
        getFriendlist().clearFriendList();
    }//end clearFriendList

}//end FriendManager
