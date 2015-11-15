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
import android.widget.Toast;

import java.util.ArrayList;

import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.R;

public class SearchFriendActivity extends Activity {

    private ArrayAdapter<User> adapter;
    private ListView friendListView;
    private ArrayList<User> friendList;
    private ArrayList<String> friendNameList;

    protected int friendListViewItemPosition;
    private EditText searchFriendText;
    private String friendName;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);

        friendListView = (ListView) findViewById(R.id.listView);
        friendList = FriendManager.getAllUsers();
        size = friendList.size();

        if (friendList.isEmpty()) {
            FriendManager.addFriend(new User("Mike", "me@2.ca", "Hometown", "5551234567"));
            FriendManager.addFriend(new User("Cory", "me@2.ca", "Hometown", "5551234567"));
            FriendManager.addFriend(new User("Terri", "me@2.ca", "Hometown", "5551234567"));
            adapter = new ArrayAdapter<User>(this, R.layout.listviewtext, FriendManager.getAllUsers());
            friendListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            size = FriendManager.getAllUsers().size();
            Toast.makeText(getBaseContext(),size , Toast.LENGTH_SHORT).show();

            //for (int i = 0; i < friendList.size(); i++) {
              //  friendNameList.add(friendList.get(i).getUserName().toString());
            //}

        }
        else{
            Toast.makeText(getBaseContext(),size , Toast.LENGTH_SHORT).show();

        }


        //http://stackoverflow.com/questions/21329132/android-custom-dropdown-popup-menu
        //http://stackoverflow.com/questions/7201159/is-using-menuitem-getitemid-valid-in-finding-which-menuitem-is-selected-by-use
        //http://stackoverflow.com/questions/4554435/how-to-get-the-index-and-string-of-the-selected-item-in-listview-in-android
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
                                Intent intent = new Intent(SearchFriendActivity.this,FriendProfileActivity.class);
                                startActivity(intent);

                            case R.id.tradeFriendMenuId:
                                //Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.removeFriendMenuId:
                               // Toast.makeText(getBaseContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                                AlertDialog.Builder alert = new AlertDialog.Builder(SearchFriendActivity.this);
                                alert.setMessage("Are you sure, you want to remove friend");

                                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {
                                        //Toast.makeText(SearchFriendActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                        FriendManager.delFriend(friendListViewItemPosition);
                                        resetAdapter();
                                    }
                                });

                                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_friend, menu);

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
        adapter = new ArrayAdapter<User>(this,R.layout.listviewtext, FriendManager.getAllUsers());
        friendListView.setAdapter(adapter);
    }

    public void searchFriendButton(View view){

        searchFriendText = (EditText) findViewById(R.id.searchFriendEditText);
        friendName = searchFriendText.getText().toString().trim();
        searchFriend(friendName);

    }

    public void searchFriend(String friend){
        friendList = FriendManager.getAllUsers();
        for(int i=0; i< friendList.size();i++){

            if (friend.toLowerCase().equals(friendList.get(i).getUserName().toString().toLowerCase()) ){
                Toast.makeText(getBaseContext(), friend, Toast.LENGTH_SHORT).show();
            }
        }

    }




}
