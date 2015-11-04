package cmput301t4.gameswap.Managers;

import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;

/**
 * Created by dren on 11/4/15.
 */
public class SearchManager {

    public boolean searchFriendTrade(TradeList tradelistFriend, Trade trade){//returns true if trade exists in friend tradelist
        return tradelistFriend.hasTrade(trade);
    }

}
