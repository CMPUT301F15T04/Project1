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
import java.lang.reflect.Type;
import java.util.ArrayList;

import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.Trade;
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

    static public void createUser(String username, String email, String city, String phoneNumber, Context context) {
        trader = new User(username, email, city, phoneNumber);
        saveUserLocally(context);
    }//end Create User

    static public void editUserName(String username){
        trader.setUserName(username);
    }//end editUserName

    static public void editPhoneNumber(String phoneNumber){
        trader.setUserPhoneNumber(phoneNumber);
    }//end EditPhoneNumber
    static public void saveUserLocally(Context context){
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
    }//end save
    static public void loadUserLocally(Context context){
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

    public void editEmail(String email){
        trader.setUserEmail(email);
    }//end editEmail

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
