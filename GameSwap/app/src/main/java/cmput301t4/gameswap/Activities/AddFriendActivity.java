/*


//WE DO NOT NEED THIS ACTIVITY ANYMORE
//BEEN REFACTORED TO JUST USE ONLY "FRIENDPROFILEACTIVITY"

package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class AddFriendActivity extends Activity {

    private Button addTrader;
    private String traderName;

    private AddFriendActivity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        addTrader = (Button) findViewById(R.id.addTraderButton);
        traderName = (UserManager.getFriend().getUserName());
        TextView username = (TextView) findViewById(R.id.userNameTextView);
        username.setText(traderName);
    }

    public void addTraderClicked(View v){
        System.out.println(UserManager.getTrader().getFriendList());
        FriendManager.addFriend(traderName);
        UserManager.saveUserLocally(this);
        ServerManager.saveUserOnline(UserManager.getTrader());
        Toast.makeText(getBaseContext(), "Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddFriendActivity.this,FriendProfileActivity.class);
        intent.putExtra("name", traderName);
        this.finish();
        startActivity(intent);
    }

}*/
