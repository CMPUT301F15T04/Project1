package cmput301t4.gameswap.Models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Holds all offline data until a connection to the server can be established again.
 */
public class Cache {
    /** The Items that were created offline that will be sent to the server */
    ArrayList<Item> itemsToBePushed = new ArrayList<Item>();
    /** The Trades that were created offline that will be sent to the server */
    ArrayList<Trade> tradesToBePushed = new ArrayList<Trade>();
    int accountID; //Not yet impkemented (Server stuff)


    /**
     * Generic method to add a Collection of Items to the Cache
     *
     * @param items A Collection of offline Items created by the user
     */
    public void addItemsToCache(Collection<? extends Item> items) {
        itemsToBePushed.addAll(items);
    }

    /**
     * Generic method to add an Item to the Cache
     *
     * @param item An Item that was created offline by the user
     */
    public void addItemToCache(Item item) {
        itemsToBePushed.add(item);
    }

    /**
     * Retrieves all Items that are waiting to be pushed to the server
     *
     * @return The Collection of Items waiting to be pushed to the server
     */
	public Collection<? extends Item> getPendingItems() {
		return itemsToBePushed;
	}

    /**
     * Generic method to add a Collection of Trades to the Cache
     *
     * @param trades A Collection of offline Trades created by the user
     */
    public void addTradesToCache(Collection<? extends Trade> trades) {
        tradesToBePushed.addAll(trades);
    }

    /**
     * Generic method to add a Trade to the Cache
     *
     * @param trade A Trade that was created offline by the user
     */
    public void addTradeToCache(Trade trade) {
        tradesToBePushed.add(trade);
    }

    /**
     * Retrieves all Trades that are waiting to be pushed to the server
     *
     * @return The Collection of Trades waiting to be pushed to the server
     */
	public Collection<? extends Trade> getPendingTrades() {
		return tradesToBePushed;
	}

    /**
     * Saves the data of the most recently viewed friend in the Cache
     *
     * @param friend The User that was most recently viewed by the user
     */
    public void storeFriend(User friend) {
		//TODO: Pull friend from server
		//TODO: Move this method to CacheManager
    }
}
