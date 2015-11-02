package cmput301t4.gameswap;


import java.util.ArrayList;

/**
 * Created by rupehra on 11/1/15.
 *
 * Since we might not have an account we might need to shift Account Manager's
 * functionality to Trader Manager
 *
 * friendList is just a list of other traders

 */

public class Trader {
    private String userName;
    private String userEmail;
    private String userCity;
    private String userPhoneNumber;
    private Inventory inventory;
    private ArrayList<Trader> friendList;
    private TradeList pendingTrades;
    private TradeList pastTrades;


    public Trader(String userName, String userEmail, String userCity, String userPhoneNumber) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCity = userCity;
        this.userPhoneNumber = userPhoneNumber;
        this.friendList = new ArrayList<Trader>();
        this.pendingTrades = new TradeList();
        this.pastTrades = new TradeList();
        this.inventory = new Inventory();

    }

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

    public Inventory getInventory() {
        return inventory;
    }


    public ArrayList<Trader> getFriendList() {
        return friendList;
    }

    public TradeList getPendingTrades() {
        return pendingTrades;
    }

    public TradeList getPastTrades() {
        return pastTrades;
    }




}


