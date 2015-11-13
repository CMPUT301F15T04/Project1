package cmput301t4.gameswap.Models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Stores the list of Users that the current user has added as friends
 */
public class FriendList {
    private ArrayList<User> friendList = new ArrayList<User>();

    //=====Function for Trade Notify=====//

    public Integer getFriendlistSize(){
        return friendList.size();
    }

    //=====Function for Trade Notify=====//

    /**
     * Stores a User as a friend
     *
     * @param trader The User that the user has added as a friend
     */
    public void addFriend(User trader) {
        friendList.add(trader);
    }

    /**
     * Removes a friend based on the position in the list
     *
     * @param position The position in the list of the friend
     */
    public void delFriend(int position) {
        friendList.remove(position);
    }

    /**
     * Simple getter to retrieve a friend based on their position in the list
     *
     * @param index The position in the list of the friend
     * @return The User at the provided position
     */
    public User getFriend(int index){
        return friendList.get(index);
    }

    /**
     * Generic getter to retrieve all friends of a user
     *
     * @return The Collection of all friends of that user
     */
    public Collection<? extends User> getAllFriends() {
        return friendList;
    }

    /**
     * Check if a User is a friend of the current user
     *
     * @param trader The User to be checked
     * @return A boolean that is true if the provided User is a friend of the current user
     */
    public boolean hasFriend(User trader) {
        return friendList.contains(trader);
    }

    /**
     * Removes all of the current user's friends
     */
    public void clearFriendList(){
        friendList.clear();
    }

    /**
     * Checks if the current user has any friends :(
     * @return
     */
    public boolean isEmpty(){
        return friendList.isEmpty();
    }
}

