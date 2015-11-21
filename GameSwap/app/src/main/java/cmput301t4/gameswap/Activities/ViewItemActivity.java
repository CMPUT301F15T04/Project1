package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import cmput301t4.gameswap.R;

public class ViewItemActivity extends Activity {

    /*Declaring all the listViews here*/
    private TextView name;
    private TextView quality;
    private TextView descrition;
    private TextView platform;
    private TextView date;
    private TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        descrition  = (TextView) findViewById(R.id.viewItemName);
        quality = (TextView) findViewById(R.id.viewItemQuality);
        descrition = (TextView) findViewById(R.id.viewItemDesciption);
        status = (TextView) findViewById(R.id.viewItemStatus);
        platform = (TextView) findViewById(R.id.viewItemPlatform);
        date = (TextView) findViewById(R.id.viewItemDate);
        name = (TextView) findViewById(R.id.viewItemName);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            descrition.setText(b.getString("description"));
            name.setText(b.getString("name"));
            date.setText(b.getString("releaseDate"));
            quality.setText(b.getString("quality"));
            platform.setText(b.getString("platform"));
            status.setText(b.getString("releaseDate"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_item, menu);
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
