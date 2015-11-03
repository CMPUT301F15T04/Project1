package cmput301t4.gameswap;


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


    public User() {
        this.userName = "Enter Desired Username";
        this.userEmail = "Enter Email";
        this.userCity = "Enter City";
        this.userPhoneNumber = "Enter Phone number";

        FriendManager FM = new FriendManager();
        TradeManager TM = new TradeManager();
        InventoryManager IM = new InventoryManager();
        this.inventory = IM.getInstance();
        this.pendingTrades = TM.getCurrent();
        this.pastTrades = TM.getPast();
        this.friendList = FM.getFriendlist();

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


