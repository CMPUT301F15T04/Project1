package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class OfferTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_trade);
        myItems = CreateTradeManager.getOwnerSide().getItemsNames();
        friendItems = CreateTradeManager.getFriendSide().getItemsNames();
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
        myadapter = new ArrayAdapter<String>(this, R.layout.myselecteditemstext, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.friendselecteditemstext, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
        myadapter.notifyDataSetChanged();
        friendAdapter.notifyDataSetChanged();
        //saveToFile();
    }

    @Override
    public void onResume(){
        super.onResume();
        resetAdapter();
    }

    public void friendInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, FriendInventoryActivity.class);
        startActivity(intent);
        //finish();
    }

    public void myInventoryButtonClicked(View v) {
        Intent intent = new Intent(OfferTradeActivity.this, MineInventoryActivity.class);
        startActivity(intent);
        //rfinish();
    }


    public void addItemsToMyList(ArrayList<String> myItems) {

        myInventoryItemsListView.setAdapter(myadapter);
        myadapter.notifyDataSetChanged();

    }

    public void addItemsToFriendList(ArrayList<String> friendItems) {

        friendInventoryItemsListView.setAdapter(friendAdapter);
        friendAdapter.notifyDataSetChanged();


    }
}
