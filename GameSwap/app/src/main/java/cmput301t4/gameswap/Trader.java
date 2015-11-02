package cmput301t4.gameswap;

import java.util.ArrayList;

/**
 * Created by rupehra on 11/1/15.
 *
 *
 *
 * Since we might not have an account we might need to shift Account Manager's
 * functionality to Trader Manager
 */
public class Trader {

    private String username;
    private String userEmail;
    private String userCity;
    private ArrayList<String> userPhoneNumber;
    private Inventory inventory;
    private FriendList friendList;
    private TradeList pendingTrades;
    private TradeList pastTrades;


    public Trader(String username, String userEmail, String userCity, ArrayList<String> userPhoneNumber){
        this.username = username;
        this.userEmail = userEmail;
        this.userCity = userCity;
        this.userPhoneNumber = userPhoneNumber;
        this.inventory = new Inventory();
        this.friendList = new FriendList();
        this.pendingTrades = new TradeList();
        this.pastTrades = new TradeList();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public void setUserPhoneNumber(ArrayList<String> userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }






    /*public DeleteTrader(){
    // TODO: implement this later

    }*/













}
