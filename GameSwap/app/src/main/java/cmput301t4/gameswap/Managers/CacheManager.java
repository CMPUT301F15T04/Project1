package cmput301t4.gameswap.Managers;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import cmput301t4.gameswap.Models.Cache;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.Trade;

/**
 * Created by Blake on 2015-11-06.
 */
public class CacheManager {

    final static String ITEMS_SAVE_FILE = "gameswap_local_items.sav";
    final static String TRADES_SAVE_FILE = "gameswap_local_trades.sav";
    private static Cache cache = null;

    static public Cache getInstance() {
        if(cache == null) {
            cache = new Cache();
        }

        return cache;
    }

    public void saveToFile(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(ITEMS_SAVE_FILE, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(pullItemCache(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fos = context.openFileOutput(TRADES_SAVE_FILE, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(pullTradeCache(), out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

        //TODO: store saved friend to file
    }

    private void loadFromFile(Context context) {
        try {
            FileInputStream fis = context.openFileInput(ITEMS_SAVE_FILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Item> pulledItems = gson.fromJson(in, arraylistType);
            cacheItems(pulledItems);
        } catch (FileNotFoundException e) {
            ArrayList<Item> pulledItems = new ArrayList<Item>();
            cacheItems(pulledItems);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            FileInputStream fis = context.openFileInput(TRADES_SAVE_FILE);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ArrayList<Item>>() {}.getType();
            ArrayList<Trade> pulledTrades = gson.fromJson(in, arraylistType);
            cacheTrades(pulledTrades);
        } catch (FileNotFoundException e) {
            ArrayList<Trade> pulledTrades = new ArrayList<Trade>();
            cacheTrades(pulledTrades);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //TODO: load friend from save file
    }

    public void cacheItem(Item item) {
        getInstance().addItemToCache(item);
    }

    public void cacheItems(Collection<? extends Item> items) {
        getInstance().addItemsToCache(items);
    }

    public Collection<? extends Item> pullItemCache() {
        return getInstance().getPendingItems();
    }

    public void cacheTrade(Trade trade) {
        getInstance().addTradeToCache(trade);
    }

    public void cacheTrades(Collection<? extends Trade> trades) {
        getInstance().addTradesToCache(trades);
    }

    public Collection<? extends Trade> pullTradeCache() {
        return getInstance().getPendingTrades();
    }
}
