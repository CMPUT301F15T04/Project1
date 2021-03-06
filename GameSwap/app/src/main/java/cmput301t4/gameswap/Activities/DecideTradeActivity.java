
package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

/**
 * * This activity allows the user to decide what they want to do
 * with a offered trade. They have three options to choose from
 * Reject, Counter Offer, Accept

 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class DecideTradeActivity extends Activity {

    /** ListView for user inventory */
    private ListView myInventoryItemsListView;
    /** ListView for friend inventory */
    private ListView friendInventoryItemsListView;
    /** ArrayAdapter for user items */
    private ArrayAdapter<String> myadapter;
    /** ArrayAdapter for friend items */
    private ArrayAdapter<String> friendAdapter;
    /** ArrayList for user items */
    private ArrayList<String> myItems;
    /** ArrayList for friend items */
    private ArrayList<String> friendItems;
    /** TextView for friend invetory title */
    private TextView friendInventoryTitle;
    int index;

    /**
     * User decides if they want to accept, reject, or
     * make a counter trade
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decide_trade);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            index = b.getInt("index");
            myItems = UserManager.getPendingList().getTrade(index).getBorrowerItems().getItemsNames();
            friendItems = UserManager.getPendingList().getTrade(index).getOwnerItems().getItemsNames();
        }


        //Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();
        myInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.decidemyitemstextlistview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.decidefrienditemlisttextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
        friendInventoryTitle = (TextView) findViewById(R.id.decidefriendofferinventory);
        friendInventoryTitle.setText(UserManager.getPendingList().getTrade(index).getOwnername() + "'s Inventory");

    }

    /**
     * This is called when the user attempts to make
     * a counter trade
     * @param v: counter trade button view
     */
    public void counterTradeButton(View v){
        Intent intent = new Intent(DecideTradeActivity.this,OfferTradeActivity.class);
        intent.putExtra("index", index);
        ServerManager.getFriendOnline(UserManager.getPendingList().getTrade(index).getOwnername());
        CreateTradeManager.setOwnerSide(UserManager.getPendingList().getTrade(index).getBorrowerItems());
        CreateTradeManager.setFriendSide(UserManager.getPendingList().getTrade(index).getOwnerItems());
        CreateTradeManager.updateOwnerSideName();
        CreateTradeManager.updateFriendSiderName();
        startActivity(intent);
        finish();
    }

    /**
     * This is called when the user attempts to reject the
     * offered trade
     * @param v: reject trade button view
     */
    public void rejectTradeButtonClicked(View v){
        ServerManager.getFriendOnline(TradeManager.getCurrent().getTrade(index).getOwnername());
        UserManager.getFriend().getPendingTrades().delete(TradeManager.getCurrent().getTrade(index));
        TradeManager.getCurrent().del(index);
        ServerManager.saveUserOnline(UserManager.getTrader());
        ServerManager.saveUserOnline(UserManager.getFriend());
        ServerManager.notifyTrade(2);
        finish();
    }

    /**
     * This is called when the user attempts to accept the
     * trade
     * @param v: accept trade button view
     */
    public void acceptTradeButtonClicked(View v){

        TradeManager.getPast().add(TradeManager.getCurrent().getTrade(index));  //addes trade to past list current user
        ServerManager.getFriendOnline(TradeManager.getCurrent().getTrade(index).getOwnername()); //loads most up to date version of frient

        int ownerTradesize = TradeManager.getCurrent().getTrade(index).getOwnerItems().size();
        int BorrowerTradesize = TradeManager.getCurrent().getTrade(index).getBorrowerItems().size();

        //deletes items from respective inventories
        UserManager.getTrader().getInventory().deleteItemAfterTrade(TradeManager.getCurrent().getTrade(index).getBorrowerItems());
        UserManager.getFriend().getInventory().deleteItemAfterTrade(TradeManager.getCurrent().getTrade(index).getOwnerItems());
        //adds items to respective inventories
        UserManager.getTrader().getInventory().addItemAfterTrade(TradeManager.getCurrent().getTrade(index).getOwnerItems(), UserManager.getTrader());
        UserManager.getFriend().getInventory().addItemAfterTrade(TradeManager.getCurrent().getTrade(index).getBorrowerItems(), UserManager.getFriend());


        UserManager.getFriend().getPendingTrades().delete(TradeManager.getCurrent().getTrade(index));//deleting trade from friend
        UserManager.getFriend().getPastTrades().add(TradeManager.getCurrent().getTrade(index));
        TradeManager.getCurrent().del(index);//deletes trade from users list of active trades
        //updating info serverside
        ServerManager.saveUserOnline(UserManager.getTrader());
        ServerManager.saveUserOnline(UserManager.getFriend());
        ServerManager.notifyTrade(3);
        this.finish();
    }


}
