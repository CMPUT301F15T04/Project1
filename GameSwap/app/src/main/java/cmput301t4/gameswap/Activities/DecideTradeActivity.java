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


import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Trade;

import cmput301t4.gameswap.R;

public class DecideTradeActivity extends Activity {

    private ListView myInventoryItemsListView;
    private ListView friendInventoryItemsListView;
    private ArrayAdapter<String> myadapter;
    private ArrayAdapter<String> friendAdapter;
    private ArrayList<String> myItems;
    private ArrayList<String> friendItems;
    int index;
    //private CreateTradeManager CTM;


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

        Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();
        myInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.decidemyitemstextlistview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.decidefrienditemlisttextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);

    }

    public void counterTradeButton(View v){
        Intent intent = new Intent(DecideTradeActivity.this,CounterTradeActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);

    }

    public void rejectTradeButtonClicked(View v){
        //UserManager.getPendingList().

    }

    public void acceptTradeButtonClicked(View v){

    }


}
