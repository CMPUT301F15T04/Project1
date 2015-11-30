package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cmput301t4.gameswap.Exceptions.ServerDownException;
import cmput301t4.gameswap.Managers.FriendManager;
import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void clickedLogin(View view) {
        //UserManager.loadUserLocally(this);
        EditText username = (EditText) findViewById(R.id.textView);
        final String user = username.getText().toString();

        Thread loginThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    ServerManager.searchForUser(user);
                    ServerManager.serverNotDown();
                }catch(ServerDownException e){
                    ServerManager.serverIsDown();
                }
            }
        });


        loginThread.start();

            try {
                loginThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            if(ServerManager.checkResult()) {
                Thread LoadUser = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ServerManager.getUserOnline(user);
                    }
                });
                LoadUser.start();
                try {
                    LoadUser.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
                FriendManager.setFriendManager(UserManager.getTrader());
                InventoryManager.setInventoryManager(UserManager.getTrader());
                //ServerManager.getUserOnline(username.getText().toString());
                Intent intent = new Intent(this, selectTaskActivity.class);

                startActivity(intent);
            } else {
                if (ServerManager.checkServerStatus() == Boolean.TRUE){
                    Toast.makeText(getBaseContext(), "ServerDown", Toast.LENGTH_SHORT).show();
                    ServerManager.serverIsDown();

                } else {
                Toast.makeText(getBaseContext(), "User not found", Toast.LENGTH_SHORT).show();}
                ServerManager.serverNotDown();
            }

    }//end click Login

    public void clickedRegister(View view){
        Intent intent = new Intent(MainActivity.this, CreateProfileActivity.class);
        startActivity(intent);
    }

    //=======Code to help run Test Cases======//

    public Button getRegisterButton(){
        Button button = (Button) findViewById(R.id.registerButton);
        return button;
    }//end getRegisterButton

    public Button getLoginButton(){
        Button button = (Button) findViewById(R.id.loginButton);
        return button;
    }//end getLoginButton

    public EditText getUsernameField(){
        EditText text = (EditText) findViewById(R.id.textView);
        return text;
    }//end getUsernameField

    //=======End of code to run Test Cases =====//

    /*class SaveThread extends Thread {
        private User user;

        public SaveThread(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            ServerManager server = new ServerManager();
            server.saveUserOnline(user);
        }
    }

    class LoadThread extends Thread {
        private String username;

        public LoadThread(String user) {
            this.username = user;
        }

        @Override
        public void run() {
            ServerManager server = new ServerManager();
            server.getUserOnline(username);
        }
    }*/
    /**<!--<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:tools="http://schemas.android.com/tools" android:layout_width="match_parent"
     android:layout_height="match_parent"
     android:orientation="vertical"
     tools:context="cmput301t4.gameswap.Activities.ViewItemActivity"
     android:weightSum="1"
     android:id="@+id/viewIteamReleaseDate">

     <RelativeLayout
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_weight="0.03">

     <ImageView
     android:layout_width="150dp"
     android:layout_height="75dp"
     android:id="@+id/ItemImageView"
     android:layout_alignParentStart="true"
     />
     <TextView
     android:layout_width="100dp"
     android:layout_height="75dp"
     android:hint="Game"
     android:textSize="20sp"
     android:id="@+id/viewItemName"
     android:layout_weight="0.27"
     android:layout_alignParentTop="true"
     android:layout_alignParentEnd="true"
     android:layout_toEndOf="@+id/ItemImageView" />
     </RelativeLayout>

     <Space
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.03" />

     <TextView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:textAppearance="?android:attr/textAppearanceMedium"
     android:id="@+id/viewItemPlatform"
     android:hint="Platform"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.20" />

     <Space
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.03" />

     <TextView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:textAppearance="?android:attr/textAppearanceMedium"
     android:hint="Quality"
     android:id="@+id/viewItemQuality"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.21" />

     <Space
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.03" />

     <TextView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:textAppearance="?android:attr/textAppearanceMedium"
     android:hint="Release Date"
     android:id="@+id/viewItemDate"
     android:layout_weight="0.14" />

     <Space
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:layout_gravity="center_horizontal"
     android:layout_weight="0.03" />

     <TextView
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:textAppearance="?android:attr/textAppearanceMedium"
     android:hint="Description"
     android:id="@+id/viewItemDesciption"
     android:layout_weight="0.20" />

     </LinearLayout>
     --->*/

}
