package cmput301t4.gameswap.Managers;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.Models.User;

/**
 * functions needed to include: editUserName(), EditPhoneNumber(),EditEmail()
 * EditCity(), CreateTrader() ?, DeleteTrader() ?, AddFriend(), DeleteFriend()
 */
public class UserManager {

    private static User trader = null;
    private static User friend = null;

    /**
     * Used to get the app-wide singleton of <code>User</code>
     *
     * @return The last <code>User</code> that was logged in if they didn't log out or a blank <code>User</code>.
     */
    static public User getTrader() {
        //TODO: Add call to CacheManager to see if there is a previous login on the phone

        if(trader == null){
            trader = new User("", "", "", "");
        }

        return trader;
    }

    static public User getFriend(){

        if(friend == null){
            friend = new User("", "", "", "");
        }
        return friend;
    }

    public static void setFriend(User user) { friend = user; }

    public static void setTrader(User user){
        trader = user;
    }
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

    //=====In-Work Trade Notifty=====//

    // index 0: new Trade 1: Counter Trade 2: Trade Cancel
    public String findBorrowerFriend(String BorrowerName){
        for(int i = 0; i < trader.getFriendList().getFriendlistSize(); i++){
            if (trader.getFriendList().getFriend(i).equals(BorrowerName)){
                return trader.getFriendList().getFriend(i);
            } else {
                return null;
            }
        }//end for looop
        return null;
    }//end findBorrower

    //Increment the friend's notify
    public void SendNewTradeNotify(User friend){
        friend.IncreaseNotifiyAmount(0);
    }//end SendnewTradeNotify

    //=====End In-Work Trade Notifty=====//

    /**
     * Calls the default constructor of <code>User</code> to create a profile in the app
     *
     * @param username The desired username
     * @param email The email of the user
     * @param city The city of the user
     * @param phoneNumber The phone number of the user
     * @param context The <code>Context</code> of the running app
     */
    static public void createUser(String username, String email, String city, String phoneNumber, Context context) {
        //TODO: Add call to server to see if username is available
        trader = new User(username, email, city, phoneNumber);
        saveUserLocally(context);
    }

    /**
     * Changes the name of the singleton <code>User</code>
     *
     * @param username A new name for the user
     */
    static public void editUserName(String username){
        //TODO: Add call to server to see if username is available
        trader.setUserName(username);
    }

    /**
     * Changes the phone number of the singleton <code>User</code>
     *
     * @param phoneNumber A new phone number for the user
     */
    static public void editPhoneNumber(String phoneNumber){
        trader.setUserPhoneNumber(phoneNumber);
    }

    /**
     * Saves the singleton <code>User</code> using the <code>Context</code> of the app
     *
     * @param context The <code>Context</code> of the running app
     */
    static public void saveUserLocally(Context context){
        //TODO: Move this into CacheManager

        try {
            FileOutputStream fos = context.openFileOutput("user.sav", 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(trader, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }//end try catch block
    }

    /**
     * Pulls the information on the last user to login
     *
     * @param context The <code>Context</code> of the running app
     */
    static public void loadUserLocally(Context context){
        //TODO: Move to CacheManager

        try {
            FileInputStream fis = context.openFileInput("user.sav");
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            trader = gson.fromJson(in, User.class);
        } catch (FileNotFoundException e) {
            //do nothing
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Changes the email of the singleton <code>User</code>
     *
     * @param email A new email for the user
     */
    static public void editEmail(String email){
        trader.setUserEmail(email);
    }

    /**
     * Changes the city of the singleton <code>User</code>
     *
     * @param city A new city for the user
     */
    static public void editCity(String city){
        trader.setUserCity(city);
    }

    /**
     * Changes the <code>Inventory</code> of the singleton <code>User</code>
     *
     * @param inventory The replacement <code>Inventory</code>
     */
    static public void setInventory(Inventory inventory){
        trader.setInventory(inventory);
    }

    /**
     * Changes the <code>FriendList</code> of the singleton <code>User</code>
     *
     * @param friendlist The replacement <code>FriendList</code>
     */
    static public void setFriendlist(FriendList friendlist){
        trader.setFriendList(friendlist);
    }

    /**
     * Changes the pending <code>TradeList</code> of the singleton <code>User</code>
     *
     * @param pendinglist The replacement <code>TradeList</code>
     */
    static public void setPendinglist(TradeList pendinglist){
        trader.setPendingTrades(pendinglist);
    }

    /**
     * Changes the past <code>TradeList</code> of the singleton <code>User</code>
     *
     * @param pastlist the replacement <code>TradeList</code>
     */
    static public void setPastlist(TradeList pastlist){
        trader.setPastTrades(pastlist);
    }

    /**
     * Retrieves the <code>Inventory</code> of the singleton <code>User</code>
     *
     * @return The <code>Inventory</code> of the current user
     */
    static public Inventory getInventory(){
        return trader.getInventory();
    }

    /**
     * Retrieves the <code>FriendList</code> of the singleton <code>User</code>
     *
     * @return The <code>FriendList</code> of the current user
     */
    static public FriendList getFriendlist(){
        return trader.getFriendList();
    }

    /**
     * Retrieves the pending <code>TradeList</code> of the singleton <code>User</code>
     *
     * @return The pending <code>TradeList</code> of the current user
     */
    static public TradeList getPendingList(){
        return trader.getPendingTrades();
    }

    /**
     * Retrieves the past <code>TradeList</code> of the singleton <code>User</code>
     *
     * @return The past <code>TradeList</code> of the current user
     */
    public TradeList getPastList(){
        return trader.getPastTrades();
    }

}
