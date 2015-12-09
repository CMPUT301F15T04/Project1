package cmput301t4.gameswap.Models;


import android.location.Location;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.Collections;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;

/**
 * User model contains information about the trades, for example, their name, contact information,
 * their friends, trades made, current trades and their notifications
 *
 * friendList is just a list of other traders
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class User {
    /** user name  */
    private String userName;
    /** user email  */
    private String userEmail;
    /** user city  */
    private String userCity;
    /** user phone number  */
    private String userPhoneNumber;
    /** user inventory  */
    private Inventory inventory;
    /** user location  */
    private Location defaultLocation;
    /** Number of items that a user has */
    private int itemCounter;
    /** User friend list*/
    private FriendList friendList;
    /** User pending trades */
    private TradeList pendingTrades;
    /** User past trades */
    private TradeList pastTrades;
    /** User notification amount*/
    private ArrayList<Integer> notificationAmount;
    /** User notification strings */
    private ArrayList<String> notification;

    @Override
    public String toString() {
        return userName;
    }

    /**
     * This is the contructor for the User model which is called when the user registers for an account
     *
     * @param username:
     * @param email
     * @param city
     * @param phoneNumber
     */
    public User(String username, String email, String city, String phoneNumber) {
        this.userName = username;
        this.userEmail = email;
        this.userCity = city;
        this.userPhoneNumber = phoneNumber;
        this.itemCounter=0;
        NotificationConstructor();
        ManagerConstructor();
    }

    /**
     * Notification Constructor
     * NotficationAmount Index meaning:
     *  0: new Trade
     *  1: Counter Trade
     *  2: Trade Cancel
     *
     *  Notification Index meaning:
     *  1: new Trade
     *  2: Counter Trade
     *  3: Trade Cancel
     */
    public void NotificationConstructor(){
        this.notification = new ArrayList<String>();
        this.notification.add("You have ");
        this.notification.add(" New Trade");
        this.notification.add(" New Counter Offer");
        this.notification.add(" Trade Cancellation");
        this.notification.add(" Trade Accepted");
        this.notificationAmount = new ArrayList<Integer>(Collections.nCopies(4,0));
    }

    /**
     * Function to increase the notification amount
     * @param type: type could be new Trade, counter trade or cancel trade
     */
    public void IncreaseNotifiyAmount(Integer type){
        this.notificationAmount.set(type, notificationAmount.get(type) + 1);
    }

    /**
     * Function to increment the counter
     */
    public void incrementCounter(){
        itemCounter+=1;
    }

    /**
     * @return counter
     */
    public int getCounter(){
        return itemCounter;
    }

    /** index 0: new Trade 1: Counter Trade 2: Trade Cancel */
    public void clearNotificationAmount(){
        this.notificationAmount.set(0,0);
        this.notificationAmount.set(1,0);
        this.notificationAmount.set(2,0);
        this.notificationAmount.set(3,0);
    }

    /**
     *
     * @param i
     * @return notification message if there are notifications
     *              else  let the user know that they have no notifications
     */
    public String IfNotify(int i){

        if (notificationAmount.get(i) != 0){
            String message = DisplayNotify(i);
            return message;
        }else {
            return "You have no " + notification.get(i + 1);
        }
    }

    /**
     * Display the notification for one category
     * @param type: new trade, counter trade or cancel trade
     * @return message depending on the type
     */
    public String DisplayNotify(Integer type){
        System.out.println(notification.get(0) + notificationAmount.get(type) + notification.get(type + 1));
        String message = notification.get(0) + notificationAmount.get(type) + notification.get(type+1);
        ClearNotify(type);
        return message;
    }

    /**
     * just clear noitify (you seen the update)
     * @param type :new trade, counter trade or cancel trade
     */
    public void ClearNotify(Integer type){
        this.notificationAmount.set(type, 0);
        ServerManager.saveUserOnline(UserManager.getTrader());
    }

    public ArrayList<Integer> getNotificationAmount() {
        return notificationAmount;
    }

    /** =====End of Notification Code===== */

    /** =====Manager related code===== */

    public void ManagerConstructor(){
        this.setPastTrades(getPastTrades());
        this.setPendingTrades(getPendingTrades());
        this.setFriendList(getFriendList());
        this.setInventory(getInventory());
    }


    /** =====End Manager related code===== */

    /**
     * @return username
     */

    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName: name of the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user e-mail
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Setter for user e-mail
     * @param userEmail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * @return user city
     */
    public String getUserCity() {
        return userCity;
    }

    /**
     * Setter for user city
     * @param userCity
     */
    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    /**
     * @return user phone number
     */
    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    /**
     * Setter for user phone number
     * @param userPhoneNumber
     */
    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    /**
     * Setter for inventory
     * @param inventory
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Setter for friend list
     * @param friendList
     */
    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }

    /**
     * Setter for pending trades
     * @param pendingTrades
     */
    public void setPendingTrades(TradeList pendingTrades) {
        this.pendingTrades = pendingTrades;
    }

    /**
     * Setter for past trades
     * @param pastTrades
     */
    public void setPastTrades(TradeList pastTrades) {
        this.pastTrades = pastTrades;
    }

    /**
     * @return user inventory of items
     */
    public Inventory getInventory() {
        if(inventory == null) {
            inventory = new Inventory();
        }

        return inventory;
    }

    /**
     * @return user friends in a list
     */
    public FriendList getFriendList() {
        if(friendList == null) {
            friendList = new FriendList();
        }

        return friendList;
    }

    /**
     * @return pending trades
     */
    public TradeList getPendingTrades() {
        if(pendingTrades == null) {
            pendingTrades = new TradeList();
        }

        return pendingTrades;
    }

    /**
     * @return past trades
     */
    public TradeList getPastTrades() {
        if(pastTrades == null) {
            pastTrades = new TradeList();
        }

        return pastTrades;
    }

    /**
     * Sets user location
     * @param location
     */
    public void setDefaultLocation(Location location) {
        defaultLocation = location;
    }

    /**
     * @return user location
     */
    public Location getDefaultLocation() {

       if (defaultLocation == null){
           defaultLocation = new Location(LocationManager.GPS_PROVIDER);
           defaultLocation.setLatitude(10);
           defaultLocation.setLongitude(10);
       }

        return defaultLocation;
    }

}


