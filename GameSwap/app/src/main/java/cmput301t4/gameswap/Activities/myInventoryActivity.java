package cmput301t4.gameswap.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

public class myInventoryActivity extends Activity{

    private ListView myInventoryListView;
    private ArrayAdapter<Item> adapter;
    private ArrayList<Item> inventory;
    protected int myInventoryListViewPosition;
    private InventoryManager im;

    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private Date ReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_inventory);

        //TODO: Fix this hacky nonsense
        //im.addItem("Duck Hunt", "02-01-1972", Boolean.FALSE, 1, 1, "Soooo cool");
        //im.addItem("Halo", "15-10-2001", Boolean.FALSE, 1, 1, "Even cooler");
        inventory = im.getItems();


        myInventoryListView = (ListView) findViewById(R.id.myInventoryListView);
        adapter = new ArrayAdapter<Item>(this,R.layout.myinventorylistviewtext, inventory);
        myInventoryListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        myInventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, final int position, long id) {
                final PopupMenu popupMenu = new PopupMenu(myInventoryActivity.this,childView);
                popupMenu.getMenuInflater().inflate(R.menu.myinventoryitempopup,popupMenu.getMenu());

                myInventoryListViewPosition = position;

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.editItemMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                Name = inventory.get(position).getName().toString();
                                Description = inventory.get(position).getDescription().toString();
                                ReleaseDate = inventory.get(position).getReleaseDate();

                                final Intent intent = new Intent(myInventoryActivity.this, EditItemActivity.class);
                                intent.putExtra("name",Name);
                                intent.putExtra("description",Description);
                                intent.putExtra("releaseDate",ReleaseDate);
                                intent.putExtra("index",myInventoryListViewPosition);

                                startActivity(intent);

                                return true;
                            case R.id.deleteItemMenuId:

                                final AlertDialog.Builder alert = new AlertDialog.Builder(myInventoryActivity.this);
                                alert.setMessage("Are you sure, you want to delete this item");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        inventory.remove(position);
                                        resetAdapter();

                                    }

                                });

                                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       // finish();
                                        dialog.dismiss();
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
        this.finish();
    }


    private void resetAdapter(){
        adapter = new ArrayAdapter<Item>(this,R.layout.myinventorylistviewtext, inventory);
        myInventoryListView.setAdapter(adapter);
    }
}
