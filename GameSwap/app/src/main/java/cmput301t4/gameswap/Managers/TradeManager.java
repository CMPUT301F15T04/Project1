package cmput301t4.gameswap.Managers;

import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Item;
import cmput301t4.gameswap.Trade;
import cmput301t4.gameswap.TradeList;

/**
 * Created by kynan on 11/1/15.
 */
public class TradeManager {

    private static TradeList currentList = null;
    private static TradeList pastList =null;

    //Singleton Code for currentList
    static public TradeList getCurrent(){
        if (currentList == null){
            currentList = new TradeList();
        }
        return currentList;
    }//end getInstance

    //Singleton Code for pastList
    static public TradeList getPast(){
        if (pastList == null){
            pastList = new TradeList();
        }
        return pastList;
    }//end getInstance

    //=====Basic Function=====//

    //Create a new trade
    public void createTrade(String OwnerName, String BorrowerName, ArrayList<Item> OwnerItems, ArrayList<Item> BorrowerItems){
        //String OwnerName, String BorrowerName, ArrayList<Item> OwnerItems, ArrayList<Item> BorrowerItems
        //Trade trade = new Trade(OwnerName, BorrowerName, OwnerItems, BorrowerItems);
        getCurrent().add(new Trade(OwnerName, BorrowerName, OwnerItems, BorrowerItems));
    }//end createTrade

    //Del trade from current Trade (most likely only used offline)
    public void delTrade(int position){
        getCurrent().del(position);
    }//end deltrade

    //Get trade from list (1 = Current, else = Past)
    public Trade getTrade(int position, int list){
        if(list == 1){
            return getCurrent().getTrade(position);
        } else {
            return getPast().getTrade(position);
        }
    }//end getTrade

    //Move trade from current to past
    public void moveTrade(int position){
        getPast().add(getCurrent().getTrade(position));
        getCurrent().del(position);
    }//end moveTrade

    //check if they have trade object
    /*
    Used more see the friend tradelist has the trade object
    (situtation : counter trade offer)
     */
    public boolean hasTrade(Trade trade){
        return getCurrent().hasTrade(trade);
    }//end hasItem

    //Wrote this in for testing, not sure if we really need
    public void clearTradelist(){
        getCurrent().clearTradelist();
        getPast().clearTradelist();
    }//end clearTradelist


    //==More Advance Function==//
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

}//end TradeManager
