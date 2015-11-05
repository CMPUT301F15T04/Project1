package cmput301t4.gameswap.Models;


import java.util.ArrayList;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.TradeList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by rupehra on 11/1/15.
 *
 * Since we might not have an account we might need to shift Account Manager's
 * functionality to User Manager
 *
 * friendList is just a list of other traders

 */

public class User {
    //=====Attribute=====//
    private String userName;
    private String userEmail;
    private String userCity;
    private String userPhoneNumber;
    private Inventory inventory;
    private FriendList friendList;
    private TradeList pendingTrades;
    private TradeList pastTrades;

    //=====In-Work Notifty=====//
    private ArrayList<String> notification = new ArrayList<String>();
    // index 0: new Trade 1: Counter Trade 2: Trade Cancel
    private ArrayList<Integer> notificationAmount = new ArrayList<Integer>(Collections.nCopies(3,0));

    public void pseduoConstructor(){
        this.notification.add("You have ");
        this.notification.add(" New Trade");
        this.notification.add(" New Counter Offer");
        this.notification.add(" Trade Cancellation");
    }

    public void IncreaseNotifiyAmount(Integer type){
        this.notificationAmount.set(type, notificationAmount.get(type) + 1);
    }

    public void clearNotificationAmount(){
        this.notificationAmount.set(0,0);
        this.notificationAmount.set(1,0);
        this.notificationAmount.set(2,0);
    }

    public void IfNotify(){
        for(int i = 0; i < notificationAmount.size(); i++){
            if (notificationAmount.get(i) != 0){
                DisplayNotify(i);
            }
        }
    }//end IfNotify

    public void DisplayNotify(Integer type){
        System.out.println(notification.get(0) + notificationAmount.get(type) + notification.get(type+1));
        ClearNotify(type);
    }

    public void ClearNotify(Integer type){
        this.notificationAmount.set(type, 0);
    }

    //=====End of Test Notifty related Code=====//

    public User() {
        this.userName = "Enter Desired Username";
        this.userEmail = "Enter Email";
        this.userCity = "Enter City";
        this.userPhoneNumber = "Enter Phone Number";
    }//end Trader constuctor

//=====Setters and Getters=====//

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }

    public void setPendingTrades(TradeList pendingTrades) {
        this.pendingTrades = pendingTrades;
    }

    public void setPastTrades(TradeList pastTrades) {
        this.pastTrades = pastTrades;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public FriendList getFriendList() {
        return friendList;
    }

    public TradeList getPendingTrades() {
        return pendingTrades;
    }

    public TradeList getPastTrades() {
        return pastTrades;
    }




}//end Trader


