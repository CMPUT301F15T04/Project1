package cmput301t4.gameswap.Models;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Trade;

/**
 * Created by kynan on 11/1/15.
 */
public class TradeList {
    private ArrayList<Trade> tradelist = new ArrayList<Trade>();

    //======Basic Function=====//
    public void add(Trade trade) {
        tradelist.add(trade);
    }//end add to tradelist

    //This del the trade if you have trade object already, most likely will
    //be changed later to take in position
    public void del(int position) {
        tradelist.remove(tradelist.get(position));
    }//end del

    //Retrieve Trade at index
    public Trade getTrade(int index){
        return tradelist.get(index);
    }//End getTrade

    //See if tradelist contain trade
    public boolean hasTrade(Trade trade) {
        return tradelist.contains(trade);
    }//end hasTrade

    //Wrote this in for testing, Not sure if we really need
    public void clearTradelist(){
        tradelist.clear();
    }//end clearTradelist

    public boolean isEmpty(){return tradelist.isEmpty();}//end isEmpty

    public int getIndex(Trade trade){
        return tradelist.indexOf(trade);
    }//end getIndex

}//end TradeList
