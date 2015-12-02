package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.TradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class OfferTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    private CreateTradeManager CTM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_trade);
        CTM = new CreateTradeManager();
        myItems = CTM.getOwnerSide().getItemsNames();
        friendItems = CTM.getFriendSide().getItemsNames();
        myInventoryItemsListView = (ListView) findViewById(R.id.itemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.itemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.myselecteditemstext, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.friendselecteditemstext, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);


    }

    private void resetAdapter(){

        myItems = CreateTradeManager.getOwnerSide().getItemsNames();
        friendItems = CreateTradeManager.getFriendSide().getItemsNames();
        myadapter.notifyDataSetChanged();
        friendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        super.onResume();
        resetAdapter();
    }

    public void friendInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, SelectFromFriendInventoryActivity.class);
        startActivity(intent);
    }

    public void myInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, MineInventoryActivity.class);
        startActivity(intent);
    }

    public void offerTradeClicked(View v){
        TradeManager.createTrade(UserManager.getTrader().getUserName(), UserManager.getFriend().getUserName(), CreateTradeManager.getOwnerSide(), CreateTradeManager.getFriendSide());

        CTM.clearFriendSide();
        CTM.clearOwnerSide();
        //Toast.makeText(getBaseContext(), "Offering Trade", Toast.LENGTH_SHORT).show();
        ServerManager.saveUserOnline(UserManager.getTrader());
        Intent intent = new Intent(OfferTradeActivity.this, TradesActivity.class);
        ServerManager.notifyTrade(0);
        startActivity(intent);
        finish();

    }


    public void addItemsToMyList(ArrayList<String> myItems) {

        myInventoryItemsListView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();
    }

    public void addItemsToFriendList(ArrayList<String> friendItems) {

        friendInventoryItemsListView.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPause(){
        super.onPause();
    }

}
