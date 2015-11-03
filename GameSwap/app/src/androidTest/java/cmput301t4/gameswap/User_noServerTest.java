package cmput301t4.gameswap;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by kynan on 11/2/15.
 */
public class User_noServerTest extends TestCase {

    //=====Basic Test (Without Controller)=====//

    //Test create a user
    public void testCreateUser(){
        User user = new User();
        assertTrue(user.getUserName().equals("Enter Desired Username"));
        assertTrue(user.getUserEmail().equals("Enter Email"));
        assertTrue(user.getUserCity().equals("Enter City"));
        assertTrue(user.getUserPhoneNumber().equals("Enter Phone Number"));
        assertNull(user.getFriendList());
        assertNull(user.getInventory());
        assertNull(user.getPastTrades());
        assertNull(user.getPendingTrades());
    }//end testCreateUser

    //test edit attribute of user
    public void testEditUserInfo(){
        User user = new User();
        assertTrue(user.getUserName().equals("Enter Desired Username"));
        assertTrue(user.getUserEmail().equals("Enter Email"));
        assertTrue(user.getUserCity().equals("Enter City"));
        assertTrue(user.getUserPhoneNumber().equals("Enter Phone Number"));
        user.setUserCity("Hawii");
        assertTrue(user.getUserCity().equals("Hawii"));
    }//end testEditUserInfo

    //=====Basic Test Friend (With Controller)=====//

    //test add a friend for user
    public void testC_AddFriendForUser(){
        User friend = new User();
        friend.setUserCity("Hawii");
        UserManager UM = new UserManager();
        FriendManager FM = new FriendManager();
        assertNull(UM.getTrader().getFriendList());
        UM.setFriendlist(FM.getFriendlist());
        assertNotNull(UM.getTrader().getFriendList());
        UM.getFriendlist().addFriend(friend);
        assertTrue(UM.getFriendlist().getFriend(0).equals(friend));
    }//end testC_AddFriendForUser

    //test del a friend for user
    public void testC_DelFriend(){
        User friend = new User();
        friend.setUserCity("Hawii");
        UserManager UM = new UserManager();
        FriendManager FM = new FriendManager();
        UM.getFriendlist().clearFriendlist();
        assertTrue(UM.getTrader().getFriendList().isEmpty());
        UM.setFriendlist(FM.getFriendlist());
        assertNotNull(UM.getTrader().getFriendList());
        UM.getFriendlist().addFriend(friend);
        assertTrue(UM.getFriendlist().getFriend(0).equals(friend));
        UM.getFriendlist().delFriend(0);
        assertFalse(UM.getFriendlist().hasFriend(friend));
    }//end testC_DelFriend

    //test if we can find the inventory of friend
    /*
    Did not account for public and private
     */
    public void testC_FindFriendInventory(){
        User friend = new User();
        friend.setUserCity("Hawii");
        UserManager UM = new UserManager();
        FriendManager FM = new FriendManager();
        UM.getFriendlist().clearFriendlist();
        assertTrue(UM.getTrader().getFriendList().isEmpty());
        UM.setFriendlist(FM.getFriendlist());
        assertNotNull(UM.getTrader().getFriendList());
        UM.getFriendlist().addFriend(friend);
        assertTrue(UM.getFriendlist().getFriend(0).equals(friend));
        assertNull(UM.getFriendlist().getFriend(0).getInventory());
        Inventory inv = new Inventory();
        friend.setInventory(inv);
        assertNotNull(UM.getFriendlist().getFriend(0).getInventory());
        assertTrue(UM.getFriendlist().getFriend(0).getInventory().isEmpty());
    }//end testC_FindFriendInventory

    //=====Basic Test Trade (With Controller)=====//

    //test if we can find pending trade of user
    public void testC_FindPendingTrade(){
        User friend = new User();
        friend.setUserCity("Hawii");
        UserManager UM = new UserManager();
        TradeManager TM = new TradeManager();
        TM.getCurrent().clearTradelist();
        UM.setPendinglist(TM.getCurrent());
        assertTrue(UM.getTrader().getPendingTrades().isEmpty());
        assertNotNull(UM.getTrader().getPendingTrades());
        TradeList tradeList = new TradeList();
        ArrayList<Item> o_tradeitem = new ArrayList<Item>();
        ArrayList<Item> b_tradeitem = new ArrayList<Item>();
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        Item item_2 = new Item("Call of Doom", "02-02-1000", true, 2, 5, "It's better than Okay");
        o_tradeitem.add(item_1);
        b_tradeitem.add(item_2);
        TM.createTrade("Owner", "Borrower", o_tradeitem, b_tradeitem);
        Trade trade = new Trade("Owner", "Borrower", o_tradeitem, b_tradeitem);
        assertTrue(TM.getTrade(0,1).equals(trade));
    }//end testC_FindPendingTrade

    //test if we can find past trade of user
    /*
    Also see if move trade works as intended
     */
    public void testC_FindPastTrade(){
        User friend = new User();
        friend.setUserCity("Hawii");
        UserManager UM = new UserManager();
        TradeManager TM = new TradeManager();
        TM.getPast().clearTradelist();
        TM.getCurrent().clearTradelist();
        UM.setPastlist(TM.getPast());
        assertTrue(UM.getTrader().getPastTrades().isEmpty());
        assertNotNull(UM.getTrader().getPastTrades());
        TradeList tradeList = new TradeList();
        ArrayList<Item> o_tradeitem = new ArrayList<Item>();
        ArrayList<Item> b_tradeitem = new ArrayList<Item>();
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        Item item_2 = new Item("Call of Doom", "02-02-1000", true, 2, 5, "It's better than Okay");
        o_tradeitem.add(item_1);
        b_tradeitem.add(item_2);
        TM.createTrade("Owner", "Borrower", o_tradeitem, b_tradeitem);
        TM.moveTrade(0);
        Trade trade = new Trade("Owner", "Borrower", o_tradeitem, b_tradeitem);
        assertTrue(TM.getTrade(0,0).equals(trade));
    }//end testC_FindPendingTrade

    //=====Basic Test Inventory (With Controller)=====//

    public void testC_AddItemUserInventory(){
        UserManager UM = new UserManager();
        InventoryManager IM = new InventoryManager();
        UM.setInventory(IM.getInstance());
        UM.getInventory().clearInventory();
        assertTrue(UM.getTrader().getInventory().isEmpty());
        assertNotNull(UM.getTrader().getFriendList());
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        IM.addItem("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        assertTrue(UM.getInventory().getItem(0).equals(item_1));
    }//end testC_AddItemUserInventory

    public void testC_DelItemUserInventory(){
        UserManager UM = new UserManager();
        InventoryManager IM = new InventoryManager();
        UM.setInventory(IM.getInstance());
        UM.getInventory().clearInventory();
        assertTrue(UM.getTrader().getInventory().isEmpty());
        assertNotNull(UM.getTrader().getFriendList());
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        IM.addItem("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        assertTrue(UM.getInventory().getItem(0).equals(item_1));
        IM.delItem(0);
        assertTrue(UM.getInventory().isEmpty());
        assertFalse(UM.getInventory().hasItem(item_1));
    }//end testC_DelItemUserInventory

}//end User_noServerTest
