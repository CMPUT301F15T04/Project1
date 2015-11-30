package cmput301t4.gameswap.Activities;

//commented out the adapter line

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.FriendList;

import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.R;

public class SearchFriendActivity extends Activity {

    private ArrayAdapter<String> adapter;
    private ListView friendListView;
    private FriendList friendList;

    protected int friendListViewItemPosition;
    private EditText searchFriendText;
    private String friendName;
    private int size;

    private SearchView search;
    private static final String FILENAME = "friends.sav"; // model


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);

        friendListView = (ListView) findViewById(R.id.friendlistView);
        friendList = UserManager.getTrader().getFriendList();

        // FriendManager.addFriend("Cory");
        //FriendManager.addFriend("Terri");
        adapter = new ArrayAdapter<String>(this, R.layout.listviewtext, friendList.getAllFriends());
        friendListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //size = FriendManager.getAllUsers().size();

        //code referenced from http://stackoverflow.com/questions/21329132/android-custom-dropdown-popup-menu
        //code referenced from http://stackoverflow.com/questions/7201159/is-using-menuitem-getitemid-valid-in-finding-which-menuitem-is-selected-by-use
        //code referenced from http://stackoverflow.com/questions/4554435/how-to-get-the-index-and-string-of-the-selected-item-in-listview-in-android
        friendListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View childView, int position, long id) {

                PopupMenu popupMenu = new PopupMenu(SearchFriendActivity.this, childView);
                popupMenu.getMenuInflater().inflate(R.menu.friend_popup, popupMenu.getMenu());

                friendListViewItemPosition = position;


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    // @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.viewFriendProfileMenuId:

                                Thread thread = new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        ServerManager.getFriendOnline(FriendManager.getFriendlist().getFriend(friendListViewItemPosition));
                                    }
                                });
                                thread.start();
                                try {
                                    thread.join();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException();
                                }

                                Intent intent = new Intent(SearchFriendActivity.this,FriendProfileActivity.class);
                                //intent.putExtra("name",FriendManager.getUser(friendListViewItemPosition));
                                startActivity(intent);

                            case R.id.tradeFriendMenuId:
                                Intent intent1 = new Intent(SearchFriendActivity.this,OfferTradeActivity.class);
                                startActivity(intent1);
                                return true;
                            case R.id.removeFriendMenuId:
                               // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                AlertDialog.Builder alert = new AlertDialog.Builder(SearchFriendActivity.this);
                                alert.setMessage("Are you sure, you want to remove friend");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        //Toast.makeText(SearchFriendActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                        UserManager.getFriendlist().delFriend(friendListViewItemPosition);
                                        ServerManager.saveUserOnline(UserManager.getTrader());
                                        resetAdapter();
                                    }
                                });

                                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        //finish();
                                    }
                                });

                                AlertDialog alertDialog = alert.create();
                                alertDialog.show();

                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
                // onPrepareOptionsMenu(popupMenu.getMenu());

            }

        });

        search = (SearchView) findViewById(R.id.searchViewFriend);
        search.setQueryHint("Search Friend");
        search.setIconifiedByDefault(false);
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Toast.makeText(getBaseContext(), "searching...", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query,
                //Toast.LENGTH_SHORT).show();
                searchFriend(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_friend, menu);

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

    private void resetAdapter(){
        adapter = new ArrayAdapter<String>(this,R.layout.listviewtext, friendList.getAllFriends());
        friendListView.setAdapter(adapter);
    }

    public void searchFriend(View view){

        friendName = search.getQuery().toString();
        //Toast.makeText(getBaseContext(), traderName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SearchFriendActivity.this,FriendProfileActivity.class);
        startActivity(intent);
        this.finish();

    }

    public void searchFriend(String friend){
        friendList = UserManager.getTrader().getFriendList();
        for(int i=0; i< friendList.getFriendlistSize();i++){

            if (friend.toLowerCase().equals(friendList.getFriend(i).toString().toLowerCase()) ){
                Toast.makeText(getBaseContext(), friend, Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(SearchFriendActivity.this, FriendProfileActivity.class);
                startActivity(intent);
                finish();
            }
        }

    }


    public boolean searchFriendOnline(final String friend){
        if(friend.equals(UserManager.getTrader().getUserName())){
           return false;
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerManager.searchForUser(friend);
                if(ServerManager.checkResult()){
                    ServerManager.getFriendOnline(friend);
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        if(ServerManager.checkResult()){
            Intent intent =  new Intent(SearchFriendActivity.this, FriendProfileActivity.class);
        }
        return true;
    }

    @Override
    protected void onStart() {

        super.onStart();
        //loadFromFile();
        friendListView = (ListView) findViewById(R.id.friendlistView);
//        friendList = UserManager.getTrader().getFriendList().getAllFriends();
        friendList = UserManager.getTrader().getFriendList();
        adapter = new ArrayAdapter<String>(this, R.layout.listviewtext, friendList.getAllFriends());
        friendListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void loadFromFile(){

        try {

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            //code referenced from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html, 2015-09-23
            Type arraylistType = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> friends = gson.fromJson(in, arraylistType);
            FriendManager.getFriendlist().setFriendList(friends);
            } catch (FileNotFoundException e) {
            ArrayList<String> friends = FriendManager.getFriendlist().getAllFriends();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void saveToFile() {

        try {
            ArrayList<String> friends = FriendManager.getFriendlist().getAllFriends();
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(friends, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }

    }

}
