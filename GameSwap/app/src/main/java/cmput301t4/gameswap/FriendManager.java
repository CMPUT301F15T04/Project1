package cmput301t4.gameswap;

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
    }//end addItem

    //This del the friend , most likely will
    //be changed later to take in position
    public void delFriend(int position) {getFriendlist().delFriend(position);
    }//end del

    //Retrieve friend at index
    public User getTrader(int index){return getFriendlist().getTrader(index);}//End getTrade

    //See if friendList contains friend
    public boolean hasFriend(User trader) {
        return getFriendlist().hasFriend(trader);
    }//end hasTrade

    //Wrote this in for testing, Not sure if we really need
    public void clearTradelist(){
        getFriendlist().clearFriendlist();
    }//end clearTradelist

}//end FriendManager
