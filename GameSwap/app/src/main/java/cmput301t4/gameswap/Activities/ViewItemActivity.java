package cmput301t4.gameswap.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.ServerManager;
import cmput301t4.gameswap.Managers.UserManager;
import cmput301t4.gameswap.R;

public class ViewItemActivity extends Activity {

    /*Declaring all the listViews here*/
    private TextView name;
    private TextView quality;
    private TextView descrition;
    private TextView platform;
    private TextView date;
    private String status;
    private TextView statusView;
    private ImageView imageView;
    private ArrayList<String> platformList;
    private String platformString;
    private Integer platformIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        name  = (TextView) findViewById(R.id.viewItemName);
        quality = (TextView) findViewById(R.id.viewItemQuality);
        descrition = (TextView) findViewById(R.id.viewItemDesciption);
        platform = (TextView) findViewById(R.id.viewItemPlatform);
        date = (TextView) findViewById(R.id.viewItemDate);
        name = (TextView) findViewById(R.id.viewItemName);
        statusView = (TextView) findViewById(R.id.viewStatus);
        imageView = (ImageView) findViewById(R.id.gameImageView);
       /** platformList.add("Playstation 4");
        platformList.add("Xbox ONE");
        platformList.add("PC");
        platformList.add("Wii U");
        platformList.add("Nintendo 3DS");
        platformList.add("Playstation 3");
        platformList.add("Playstation Vita");
        platformList.add("Xbox 360");
        platformList.add("Nintendo Wii");
        platformList.add("Nintendo DS");
        platformList.add("Playstation 2");
        platformList.add("Xbox");
        platformList.add("Nintendo Gamecube");
        platformList.add("Game Boy Advanced");
        platformList.add("Playstation Portable");
        platformList.add("Playstation");
        platformList.add("Nintendo 64");
        platformList.add("Game Boy");
        platformList.add("NES");*/

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            descrition.setText(b.getString("description"));
            status = b.getString("private");
            if (status.equals(true)){
                status = "Private";
            }
            else {
                status = "Public";
            }

            name.setText(b.getString("name"));
            date.setText(b.getString("releaseDate"));
            quality.setText(b.getString("quality"));
            platform.setText(b.getString("platform"));
            statusView.setText(status.toUpperCase());
            ServerManager.blakeLoadItemdImage(b.getInt("itemId"));
            //ServerManager.loadImage(b.getInt("itemId"));
            if(UserManager.imageRdy == 1) {
                //byte[] byteArray = UserManager.getImage().getImage();
               // Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imageView.setImageBitmap(UserManager.getImage());
            }

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


    //=====Function needed for Testcases=====//

    public TextView getNameText() {
        return name;
    }

    public TextView getQualityText() {
        return quality;
    }

    public TextView getDescritionText() {
        return descrition;
    }

    public TextView getPlatformText() {
        return platform;
    }

    public TextView getDateText() {
        return date;
    }

    public String getStatusText() {
        return status;
    }

    //=====End function needed for Testcases=====//


}
