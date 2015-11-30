package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.zip.CheckedInputStream;

import cmput301t4.gameswap.Managers.CreateTradeManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.Inventory;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class MineInventoryActivity extends Activity  {

    private ListView listView;



    //Adapter.ItemHolder.ListViewAdapter adapter;
    //private ArrayList<Item> inventory;
    private ArrayList<Item> inventory;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> nameOfItemsList;
    private ArrayList<String> itemsSelected;
    private int size;
    private CreateTradeManager CTM = new CreateTradeManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_inventory);
        listView = (ListView) findViewById(R.id.mineInventoryListView);
        nameOfItemsList = InventoryManager.getItemsNames();
        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext,nameOfItemsList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(CTM.OwnerSideContian(InventoryManager.getItem(position)) == Boolean.FALSE){
                    Toast.makeText(getBaseContext(), InventoryManager.getItem(position).getName() + " Added to Trade", Toast.LENGTH_SHORT).show();
                    CTM.addOwnerSide(InventoryManager.getItem(position));
                } else  {
                    Toast.makeText(getBaseContext(), "Already Added to list", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void doneButtonClicked(View v){
        finish();
    }


}

