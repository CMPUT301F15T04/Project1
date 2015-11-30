package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.R;

public class CancelCreateTradeActivity extends Activity {

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
        setContentView(R.layout.activity_decide_trade);
        CTM = new CreateTradeManager();
        myItems = CTM.getOwnerSide().getItemsNames();
        friendItems = CTM.getFriendSide().getItemsNames();
        myInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromMyInventory);
        friendInventoryItemsListView = (ListView) findViewById(R.id.decideitemsFromFriendInventory);
        myadapter = new ArrayAdapter<String>(this, R.layout.decidemyitemstextlistview, myItems);
        friendAdapter = new ArrayAdapter<String>(this, R.layout.decidefrienditemlisttextview, friendItems);
        myInventoryItemsListView.setAdapter(myadapter);
        friendInventoryItemsListView.setAdapter(friendAdapter);
        myadapter.notifyDataSetChanged();
        friendAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decide_trade, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cancelTradeClicked(View v){
        CTM.clearFriendSide();
        CTM.clearOwnerSide();


    }
}