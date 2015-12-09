package cmput301t4.gameswap.Managers;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;

/**
 * TradeManager handles all the information related to trades
 * @author Kynan Ly, Blake Sakaluk, Preyanshu Kumar, Daniel Ren, Rupehra Chouhan
 * @version Part 4
 */
public class TradeManager {

    /** Singleton Code for currentList */
    static public TradeList getCurrent(){
        return UserManager.getPendingList();
    }

    /**Singleton Code for pastList */
    static public TradeList getPast(){
        return UserManager.getPastList();
    }

    /**
     * This creates a new trade
     * @param OwnerName owner of the item
     * @param BorrowerName borrower of the item
     * @param OwnerItems owner items to trade
     * @param BorrowerItems borrower items to trade
     */
    public static void createTrade(String OwnerName, String BorrowerName, Inventory OwnerItems, Inventory BorrowerItems){
        //String OwnerName, String BorrowerName, ArrayList<Item> OwnerItems, ArrayList<Item> BorrowerItems
        //Trade trade = new Trade(OwnerName, BorrowerName, OwnerItems, BorrowerItems);
        getCurrent().add(new Trade(OwnerName, BorrowerName, OwnerItems, BorrowerItems));

    }

    /**
     *Del trade from current Trade (most likely only used offline)
     * @param position position at which the item resides that needs to be deleted
     */
    public static void delTrade(int position){
        getCurrent().del(position);
    }//end deltrade

    /**
     * Gets trade from list (1 = Current, else = Past)
     * @param position position of the trade in list
     * @param list list of trades
     * @return trade
     */
    public Trade getTrade(int position, int list){
        if(list == 1){
            return getCurrent().getTrade(position);
        } else {
            return getPast().getTrade(position);
        }
    }

    /**
     * Gets the most recent trade
     * @return most recent trade
     */
    public static Trade getMostRecentTrade(){
        int lastPosition = getCurrent().getSize() - 1;
        return getCurrent().getTrade(lastPosition);
    }

    /**
     * Gets the name of trades in progress
     * @param trade trade
     * @return name of trades in progress
     */
    static public ArrayList<String> getCurrentNames(Boolean trade){
        if (trade == Boolean.TRUE){
            return getCurrent().getNames();
        } else {
            return getPast().getNames();
        }
    }

    /**
     * Move trade from current to past
     * @param position
     */
    static public void moveTrade(int position){
        getPast().add(getCurrent().getTrade(position));
        getCurrent().del(position);
    }

    /**
     * checks if they have trade object
     * @param trade trade to be checked if it exists
     * @return true if the trade exits, false otherwise
     */
    public boolean hasTrade(Trade trade){
        return getCurrent().hasTrade(trade);
    }//end hasItem


    /**
     * Clears the trade list
     */
    public void clearTradelist(){
        getCurrent().clearTradelist();
        getPast().clearTradelist();
    }


    //==More Advance Function (NOT IMPLEMENTED)==//
    /*
    These will be added once we implment server
    */
    //Del trade from both side (Not yet implemented)
    /*public void TradeCancel(Trade trade){
        find the trade object
        go find the other trader account
        go find the other trader current tradelist
        del from other trader current tradelist
        del from current trader current tradelist
    }//end TradeCancel
    */

    //Create a counter trade
    /*
    public void createCounterTrade(Trade trade, ArrayList<Item> OwnerItem, ArrayList<Item> BorrowerItem){
        createTrade(trade.getOwnername(),trade.getBorrowerName(), OwnerItem, BorrowerItem);
        //TradeCancel(trade);
    }//end createCounterTrade
    */

}
