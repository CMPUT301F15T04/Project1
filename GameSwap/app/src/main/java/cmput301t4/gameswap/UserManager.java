package cmput301t4.gameswap;

/**
 * Created by rupehra on 11/1/15.
 *
 *
 * functions needed to include: editUserName(), EditPhoneNumber(),EditEmail()
 * EditCity(), CreateTrader() ?, DeleteTrader() ?, AddFriend(), DeleteFriend()
 *
 *
 */
public class UserManager {

    private static User trader = null;

    //=====Singleton Code=====//

    static public User getTrader(){
        if(trader == null){
            trader = new User();
        }
        return trader;
    }//end getTrader

    public void editUserName(String username){
        trader.setUserName(username);
    }

    public void editPhoneNumber(String phoneNumber){
        trader.setUserPhoneNumber(phoneNumber);
    }

    public void editEmail(String email){
        trader.setUserEmail(email);
    }

    public void editCity(String city){
        trader.setUserCity(city);
    }

    //=====Setter for Arraylist of User=====//
    public void setInventory(Inventory inventory){
        trader.setInventory(inventory);
    }//end set inventory

    public void setFriendlist(FriendList friendlist){
        trader.setFriendList(friendlist);
    }//end set friendlist

    public void setPendinglist(TradeList pendinglist){
        trader.setPendingTrades(pendinglist);
    }//end setPendingList

    public void setPastlist(TradeList pastlist){
        trader.setPastTrades(pastlist);
    }//end setPastlist

    //=====Getter for Arraylist of User=====//
    public Inventory getInventory(){
        return trader.getInventory();
    }//end getInventory

    public FriendList getFriendlist(){
        return trader.getFriendList();
    }//end getFriendlist

    public TradeList getPendingList(){
        return trader.getPendingTrades();
    }//end getPendingList

    public TradeList getPastList(){
        return trader.getPastTrades();
    }//end getPastlist

}//end UserManager
