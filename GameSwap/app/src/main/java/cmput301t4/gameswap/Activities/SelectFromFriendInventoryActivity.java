package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class SelectFromFriendInventoryActivity extends Activity {

    private ArrayList<String> itemNamesList;
    private ArrayList<Item> inventory;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private CreateTradeManager CTM = new CreateTradeManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_from_friend_inventory);
        inventory = UserManager.getFriend().getInventory().getItems();
        itemNamesList = UserManager.getFriend().getInventory().getItemsNames();
        listView = (ListView) findViewById(R.id.friendInventoryListView);
        adapter = new ArrayAdapter<String>(this,R.layout.friendinventorylistviewtext,itemNamesList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(CTM.FriendSideContain(inventory.get(position)) == Boolean.FALSE){
                    Toast.makeText(getBaseContext(),inventory.get(position).getName() + " Added to Trade", Toast.LENGTH_SHORT).show();
                    CTM.addFriendSide(inventory.get(position));
                } else  {
                    Toast.makeText(getBaseContext(), "Already Added to list", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    public void frienddoneButtonClicked(View v){
        finish();
    }

}
