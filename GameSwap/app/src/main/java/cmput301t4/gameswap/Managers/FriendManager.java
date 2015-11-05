package cmput301t4.gameswap.Managers;

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

    public void addFriend(User friend){
        getFriendlist().addFriend(friend);
    }//end addFriend

    //This del the friend , most likely will
    //be changed later to take in position
    public void delFriend(int position) {getFriendlist().delFriend(position);
    }//end del

    //Retrieve friend at index
    public User getUser(int index){return getFriendlist().getFriend(index);}//End getUser

    //See if friendList contains friend
    public boolean hasFriend(User trader) {
        return getFriendlist().hasFriend(trader);
    }//end hasFriend

    //Wrote this in for testing, Not sure if we really need
    public void clearFriendlist(){
        getFriendlist().clearFriendlist();
    }//end clearFriendlist

}//end FriendManager
