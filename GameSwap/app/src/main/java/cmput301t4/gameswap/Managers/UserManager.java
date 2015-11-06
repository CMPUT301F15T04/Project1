package cmput301t4.gameswap.Managers;

import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.Models.User;

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
            trader = new User("", "", "", "");
        }

        return trader;
    }//end getTrader

    //=====In-Work Notifty=====//

    public void pseduoConstructor(){
        trader.pseduoConstructor();
    }

    public void IncreaseNotifiyAmount(Integer type){
        trader.IncreaseNotifiyAmount(type);
    }

    public void DisplayNotify(Integer type){
        trader.DisplayNotify(type);
    }

    //=====End of Test Notifty related Code=====//

    static public void createUser(String username, String email, String city, String phoneNumber) {
        trader = new User(username, email, city, phoneNumber);
    }

    static public void editUserName(String username){
        trader.setUserName(username);
    }

    static public void editPhoneNumber(String phoneNumber){
        trader.setUserPhoneNumber(phoneNumber);
    }

    public void editEmail(String email){
        trader.setUserEmail(email);
    }

    static public void editCity(String city){
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
