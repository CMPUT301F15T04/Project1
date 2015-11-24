package cmput301t4.gameswap.Models;


import java.util.ArrayList;
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

    @Override
    public String toString() {
        return userName;
    }

    //=====In-Work Notifty=====//
    private ArrayList<String> notification = new ArrayList<String>();
    // index 0: new Trade 1: Counter Trade 2: Trade Cancel
    private ArrayList<Integer> notificationAmount = new ArrayList<Integer>(Collections.nCopies(3,0));

    public ArrayList<Integer> getNotificationAmount() {
        return notificationAmount;
    }

    /*
    The pseduo constructor will be added if we ddem that the notify code
    works when we implement server
     */
    public void pseduoConstructor(){
        this.notification.add("You have ");
        this.notification.add(" New Trade");
        this.notification.add(" New Counter Offer");
        this.notification.add(" Trade Cancellation");
    }//end pseduoContructor

    public void IncreaseNotifiyAmount(Integer type){
        this.notificationAmount.set(type, notificationAmount.get(type) + 1);
    }//end IncreaseNotifiyAmount

    // index 0: new Trade 1: Counter Trade 2: Trade Cancel
    public void clearNotificationAmount(){
        this.notificationAmount.set(0,0);
        this.notificationAmount.set(1,0);
        this.notificationAmount.set(2,0);
    }//end clearNotification

    public void IfNotify(){
        for(int i = 0; i < notificationAmount.size(); i++){
            if (notificationAmount.get(i) != 0){
                DisplayNotify(i);
            }//end If
        }//end For loop
    }//end IfNotify

    //Display the notification for one category
    public void DisplayNotify(Integer type){
        System.out.println(notification.get(0) + notificationAmount.get(type) + notification.get(type+1));
        ClearNotify(type);
    }//end DisplayNotify

    //Just clear noitify (you seen the update)
    public void ClearNotify(Integer type){
        this.notificationAmount.set(type, 0);
    }

    //=====End of Test Notify related Code=====//

    public User(String username, String email, String city, String phoneNumber,FriendList friendList) {
        this.userName = username;
        this.userEmail = email;
        this.userCity = city;
        this.userPhoneNumber = phoneNumber;
        this.friendList = friendList;
    }//end Trader constructor

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


