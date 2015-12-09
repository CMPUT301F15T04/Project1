package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.FriendList;

/**
 * FriendManager handles all the function related to the friends
 * @author  Kynan Ly, Preyanshu Kumar, Rupehra Chouhan , Daniel Ren, Blake Sakaluk,
 * @version Part 4
 */
public class FriendManager {
    /** Singleton code */
    static public FriendList getFriendlist(){
        return UserManager.getFriendlist();
    }

    /**
     * Adds a friend
     * @param friend friend name
     */
    static public void addFriend(String friend){
        getFriendlist().addFriend(friend);
    }

    /**
     * This deletes the friend
     * @param position index of friend in the friend list
     */
    static public void delFriend(int position) {getFriendlist().delFriend(position);
    }

    /**
     * Sets the friens list
     * @param friends user friends
     */
    public void setFriendList(ArrayList<String> friends)
    {getFriendlist().setFriendList(friends);}

    /**
     * Retrieve friend at index
     * @param index index which the friend is in the friendlist
     * @return user
     */
    static public String getUser(int index){return getFriendlist().getFriend(index);}//End getUser

    /**
     * Gets all friends
     * @return all friends
     */
    static public ArrayList<String> getAllUsers() {             //getting error in this one so commented it out
       return getFriendlist().getAllFriends();
    }

    /**
     *See if friendList contains friend
     * @param trader
     * @return boolean value depending on whether the trade is a friend or not
     */
    static public boolean hasFriend(String trader) {
        return getFriendlist().hasFriend(trader);
    }

    /**
     * Clears the friendlist
     */
    static public void clearFriendlist(){
        getFriendlist().clearFriendList();
    }

    /**
     * Gets the index of the friend
     * @param friendName friend name
     * @return index
     */
    static public int getFriendIndex(String friendName){
        return getFriendlist().getFriendIndex(friendName);
    }

}
