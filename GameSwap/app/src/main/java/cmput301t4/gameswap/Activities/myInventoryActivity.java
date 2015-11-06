package cmput301t4.gameswap.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayList<Item> items;
    private ArrayAdapter<String> adapter;
    protected int myInventoryListViewPosition;
    private Item item;
    ArrayList<String> allItemsNamesNStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        allItemsNamesNStatus = new ArrayList<String>();
        items = new ArrayList<Item>();
        Item item_1 = new Item("Call of Duty", "01-01-2000", false, 5, 5, "It's Okay");
        Item item_2 = new Item("Call of Duty 2", "01-01-2000", false, 5, 5, "It's bad");

        //items contains all the items with all the attributes
        items.add(item_1);
        items.add(item_2);

        //allItemsNames contains the names of all the items
        allItemsNamesNStatus.add(items.get(0).getName().toString()+"           "+ items.get(0).getIsPrivate().toString());
        allItemsNamesNStatus.add(items.get(1).getName().toString()+"           "+ items.get(1).getIsPrivate().toString());


        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        adapter = new ArrayAdapter<String>(this,R.layout.myinventorylistviewtext,allItemsNamesNStatus);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myInventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(myInventoryActivity.this,childView);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup,popupMenu.getMenu());

                myInventoryListViewPosition = position;
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    public boolean onMenuItemClick(MenuItem item){

                        switch (item.getItemId()){

                            case R.id.editItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(myInventoryActivity.this,EditItemActivity.class);
                                startActivity(intent);

                                return true;
                            case R.id.deleteItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(myInventoryActivity.this,DeleteItemActivity.class);
                                startActivity(intent1);

                                return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_my_inventory, menu);
        return super.onCreateOptionsMenu(menu);
        // return true;
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
    public void addNewItem(View view){
            
    }
}
