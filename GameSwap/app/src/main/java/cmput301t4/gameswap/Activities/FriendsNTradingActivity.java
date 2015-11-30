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
    public void onPause(){
        super.onPause();
        this.finish();
    }

    public void searchPeopleButton(View view){
        Intent intent = new Intent(FriendsNTradingActivity.this,SearchPeopleActivity.class);
        startActivity(intent);
    }
    public void searchFriendButton(View view){
        Intent intent = new Intent(FriendsNTradingActivity.this,SearchFriendActivity.class);
        startActivity(intent);
    }

}
