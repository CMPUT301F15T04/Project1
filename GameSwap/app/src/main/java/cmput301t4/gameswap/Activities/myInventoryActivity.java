package cmput301t4.gameswap.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayList<Item> items;
    private ArrayAdapter<Item> adapter;
    protected int myInventoryListViewPosition;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        //TODO: Fix this hacky nonsense
        InventoryManager.addItem("Duck Hunt", "02-01-1972", Boolean.FALSE, 1, 1, "Soooo cool");
        InventoryManager.addItem("Halo", "15-10-2001", Boolean.FALSE, 1, 1, "Even cooler");
        items = InventoryManager.getItems();


        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        adapter = new ArrayAdapter<Item>(this,R.layout.myinventorylistviewtext, items);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myInventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {
                PopupMenu popupMenu = new PopupMenu(myInventoryActivity.this,childView);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup,popupMenu.getMenu());

                myInventoryListViewPosition = position;
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.editItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(myInventoryActivity.this, EditItemActivity.class);
                                startActivity(intent);

                                return true;
                            case R.id.deleteItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                //Intent intent1 = new Intent(myInventoryActivity.this,DeleteItemActivity.class);
                                //startActivity(intent1);
                                //public void open(View view){

                                AlertDialog.Builder alert = new AlertDialog.Builder(myInventoryActivity.this);
                                alert.setMessage("Are you sure, you want to delete this item");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        Toast.makeText(myInventoryActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                    }
                                });

                                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });

                                AlertDialog alertDialog = alert.create();
                                alertDialog.show();

                                return true;

                            default:;

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
        Intent intent = new Intent(myInventoryActivity.this,AddItemActivity.class);
        startActivity(intent);
    }
}
