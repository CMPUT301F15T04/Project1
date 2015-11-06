package cmput301t4.gameswap.Models;

import java.util.ArrayList;

/**
 * Created by rupehra on 11/1/15.
 *
 *
 * think friendlist is a singleton
 *
 *
 *
 */

public class FriendList {
    private ArrayList<User> friendList = new ArrayList<User>();

    //======Basic Function=====//
    public void addFriend(User trader) {friendList.add(trader);}//end add to tradelist

    //This del the friend , most likely will
    //be changed later to take in position
    public void delFriend(int position) {friendList.remove(friendList.get(position));
    }//end del

    //Retrieve friend at index
    public User getFriend(int index){return friendList.get(index);}//End getUser

    //See if friendList contains friend
    public boolean hasFriend(User trader) {
        return friendList.contains(trader);
    }//end hasFriend

    //Wrote this in for testing, Not sure if we really need
    public void clearFriendlist(){
        friendList.clear();
    }//end clearFriendlist

    public boolean isEmpty(){
        return friendList.isEmpty();
    }

}//end TradeList

