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

import cmput301t4.gameswap.Models.User;
import cmput301t4.gameswap.R;

public class SearchPeopleActivity extends Activity {

    Button sButton;
    EditText traderEditText;
    String traderName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);


        //creating some dummy users
        User user_1 = new User("Kynan", "kynan@ualberta.ca", "Edmonton", "780-999-8888");
        User user_2 = new User("Daneil", "dren@ualberta.ca", "Edmonton", "780-999-8887");
        User user_3 = new User("Preyanshu", "pre@ualberta.ca", "Edmonton", "780-999-8886");



        sButton = (Button)findViewById(R.id.button7);
        traderEditText = (EditText) findViewById(R.id.searchFriendEditText);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                traderName = traderEditText.getText().toString();
                Toast.makeText(getBaseContext(), traderName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchPeopleActivity.this,FriendProfileActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_search_people, menu);
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
}
