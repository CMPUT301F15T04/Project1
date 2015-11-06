package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import cmput301t4.gameswap.R;

public class FriendsNTradingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_ntrading);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends_ntrading, menu);
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

    public void searchPeopleButton(View view){
        Intent intent = new Intent(FriendsNTradingActivity.this,SearchPeopleActivity.class);
        startActivity(intent);
    }
    public void searchFriendButton(View view){
        Intent intent = new Intent(FriendsNTradingActivity.this,SearchFriendActivity.class);
        startActivity(intent);
    }
    public void viewFriendInventoryButton(View view){
        Intent intent = new Intent(FriendsNTradingActivity.this,ViewFriendInventoryActivity.class);
        startActivity(intent);
    }


}