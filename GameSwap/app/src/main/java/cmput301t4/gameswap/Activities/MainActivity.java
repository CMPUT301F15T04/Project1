package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void clickedLogin(View view){
        UserManager.loadUserLocally(this);
        EditText username = (EditText) findViewById(R.id.textView);

        if(UserManager.getTrader().getUserName().equals(username.getText().toString())){
            Intent intent = new Intent(MainActivity.this, selectTaskActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getBaseContext(), "Wrong username, please make an account", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickedRegister(View view){
        Intent intent = new Intent(MainActivity.this, CreateProfileActivity.class);
        startActivity(intent);
    }

    class SaveThread extends Thread {
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
    }

}
