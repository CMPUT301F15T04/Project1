package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Trade;
import cmput301t4.gameswap.Models.TradeList;
import cmput301t4.gameswap.R;

/**
 * Lets user cancel the trade that they made
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class CancelCreateTradeActivity extends Activity {

    /** ListView for inventory */
    private ListView myInventoryItemsListView;
    /** ListView for friend inventory */
    private ListView friendInventoryItemsListView;
    /** Adapter for user inventory items */
    private ArrayAdapter<String> myadapter;
    /** Adapter for friend inventory adapter */
    private ArrayAdapter<String> friendAdapter;
    /** ArrayList of user items */
    private ArrayList<String> myItems;
    /** ArrayList of friend items */
    private ArrayList<String> friendItems;
    /** trade orbject of class Trade*/
    private Trade trade;
    /** TM object of Controller TradeManager */
    private TradeManager TM;
    /** tradelist is an object of class TradeList */
    private TradeList tradeList;
    /** index for the item selected */
    private int index;

    /**
     * This is called when the activity is first created
     * It initializes all the list views, adapters, and variables
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_create_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        TM = new TradeManager();

        if(b!=null) {
            index = b.getInt("index");
            myItems = UserManager.getPendingList().getTrade(index).getOwnerItems().getItemsNames();
            friendItems = UserManager.getPendingList().getTrade(index).getBorrowerItems().getItemsNames();
        }

        //Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();
        myInventoryItemsListView = (ListView) findViewById(R.id.cancelitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.cancelitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.cancelmyinventorytextview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.cancelfriendinventorytextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
        myadapter.notifyDataSetChanged();
        friendAdapter.notifyDataSetChanged();

    }
    /**
     * This function deletes the trade that the user creates
     * @param v : cancel trade button view
     */
    public void cancelTradeClicked(View v){
        CreateTradeManager.clearOwnerSide();
        CreateTradeManager.clearFriendSide();
        tradeList  = TM.getCurrent();
        ServerManager.getFriendOnline(tradeList.getTrade(index).getBorrowerName());
        UserManager.getFriend().getPendingTrades().delete(tradeList.getTrade(index));
        tradeList.del(index);
        ServerManager.saveUserOnline(UserManager.getFriend());
        ServerManager.saveUserOnline(UserManager.getTrader());
        finish();
    }

    //==================Functions needed for Testcases==============//

    public Button getcancelButton(){
        return (Button) findViewById(R.id.friendInventoryButton);
    }

    //==================End Functino needed for Testcases===========//
}
