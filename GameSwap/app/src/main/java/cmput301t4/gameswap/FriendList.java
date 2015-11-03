package cmput301t4.gameswap;

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

    private ArrayList<Trader> friendList = new ArrayList<Trader>();

    //======Basic Function=====//
    public void addFriend(Trader trader) {friendList.add(trader);}//end add to tradelist

    //This del the friend , most likely will
    //be changed later to take in position
    public void delFriend(int position) {friendList.remove(friendList.get(position));
    }//end del

    //Retrieve friend at index
    public Trader getTrader(int index){return friendList.get(index);}//End getTrade

    //See if friendList contains friend
    public boolean hasFriend(Trader trader) {
        return friendList.contains(trader);
    }//end hasTrade

    //Wrote this in for testing, Not sure if we really need
    public void clearTradelist(){
        friendList.clear();
    }//end clearTradelist

}//end TradeList

