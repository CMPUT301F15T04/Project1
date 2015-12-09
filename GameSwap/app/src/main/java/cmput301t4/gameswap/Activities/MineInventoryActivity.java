package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

/**
 * Allows user to select items from their inventory in order to trade
 *
 * @author Preyanshu Kumar, Kynan Ly, Daniel Ren, Rupehra Chouhan, Blake Sakaluk
 * @version Part 4
 */
public class MineInventoryActivity extends Activity  {

    /** ListView for inventory */
    private ListView listView;
    //Adapter.ItemHolder.ListViewAdapter adapter;
    private ArrayList<Item> inventory;
    /*Adapter for the inventory */
    private ArrayAdapter<String> adapter;
    /*ArrayList of the item names */
    private ArrayList<String> nameOfItemsList;
    /** ArrayList of items that were selected by the user */
    private ArrayList<String> itemsSelected;
    private int size;


    /**
     * Grabs infromation about user inventory and displays it in this activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_inventory);
        listView = (ListView) findViewById(R.id.mineInventoryListView);
        nameOfItemsList = InventoryManager.getItemsNames();
        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext,nameOfItemsList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(CreateTradeManager.OwnerSideContian(InventoryManager.getItem(position)) == Boolean.FALSE){
                    Toast.makeText(getBaseContext(), InventoryManager.getItem(position).getName() + " Added to Trade", Toast.LENGTH_SHORT).show();
                    CreateTradeManager.addOwnerSide(InventoryManager.getItem(position));
                    CreateTradeManager.updateOwnerSideName();
                } else  {
                    Toast.makeText(getBaseContext(), "Already Added to list", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /**
     * Called when user is done selecting items from their inventory by clicking on
     * done button
     * @param v: done button view
     */
    public void doneButtonClicked(View v){
        finish();
    }


}

