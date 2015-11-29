package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import org.apache.commons.logging.Log;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserListManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.FriendList;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.Models.UsersList;
import cmput301t4.gameswap.R;

public class SearchPeopleActivity extends Activity {

    Button sButton;
    EditText traderEditText;
    String traderName;
    private SearchView search;
    private FriendList friendList;

    private SearchPeopleActivity activity = this;


    private User user_1;
    private User user_2;
    private User user_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);

        friendList = UserManager.getTrader().getFriendList();
        /**
         * Code from - http://sampleprogramz.com/android/searchview.php
         */
        search = (SearchView) findViewById(R.id.searchView);
        search.setQueryHint("Search Trader");
        search.setIconifiedByDefault(false);

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(getBaseContext(), query, Toast.LENGTH_SHORT).show();

                findTrader(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void findTrader(final String trader){
        traderName = trader;


        traderName = search.getQuery().toString().toLowerCase();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ServerManager.searchForUser(traderName);

            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
        search.setQuery("",false);
        Toast.makeText(getBaseContext(), traderName, Toast.LENGTH_SHORT).show();
        Toast.makeText(getBaseContext(), "Here", Toast.LENGTH_SHORT).show();

        //Boolean a = UserManager.getTrader().getFriendList().hasFriend(trader);
        String user  = UserManager.getTrader().getUserName();
        //Toast.makeText(getBaseContext(), name, Toast.LENGTH_SHORT).show();
        int size = UserManager.getTrader().getFriendList().getFriendlistSize();
        String sizeString  = Integer.toString(size);
        android.util.Log.e("size",sizeString);

        android.util.Log.e("name",user);
        int i =0;
        if(UserManager.getTrader().getFriendList().hasFriend(trader)){

      // if (UserManager.getTrader().getFriendList().hasFriend(trader)) {
            Intent intent = new Intent(SearchPeopleActivity.this, FriendProfileActivity.class);
            ServerManager.getFriendOnline(traderName);
            //intent.putExtra("name", traderName.toLowerCase());
            activity.finish();
            startActivity(intent);

        } /**else if(ServerManager.checkResult()) {
                Intent intent2 = new Intent(SearchPeopleActivity.this, AddFriendActivity.class);
                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ServerManager.getFriendOnline(traderName);
                    }
                });
                thread2.start();
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                //intent2.putExtra("name",traderName.toLowerCase());
                search.clearChildFocus(search);
                activity.finish();
                startActivity(intent2);

        } else {
            Toast.makeText(getBaseContext(), "No user exist", Toast.LENGTH_SHORT).show();
        }

*/

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_people, menu);
        return true;
    }

    @Override
    public void onPause(){
        super.onPause();
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
}
