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
    private TextView description;
    private TextView platform;
    private TextView date;
    private Boolean status;
    private String statusDisplay;
    private TextView statusView;
    private ImageView imageView;
    private TextView location;
    private ArrayList<String> platformList;
    private String platformString;
    private Integer platformIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        name  = (TextView) findViewById(R.id.viewItemName);
        quality = (TextView) findViewById(R.id.viewItemQuality);
        description = (TextView) findViewById(R.id.viewItemDesciption);
        platform = (TextView) findViewById(R.id.viewItemPlatform);
        date = (TextView) findViewById(R.id.viewItemDate);
        name = (TextView) findViewById(R.id.viewItemName);
        statusView = (TextView) findViewById(R.id.viewStatus);
        location =(TextView) findViewById(R.id.locationDescription);
        imageView = (ImageView) findViewById(R.id.gameImageView);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            description.setText(b.getString("description"));

            status = b.getBoolean("private");
            if (status == Boolean.TRUE){
                statusDisplay = "Private";
            }
            else {
                statusDisplay = "Public";
            }

            name.setText(b.getString("name"));
            date.setText(b.getString("releaseDate"));
            quality.setText(b.getString("quality"));
            if (quality.getText().equals(0));{
                quality.setText("Perfect Condition/Unopened/Download Code");
            }
            if (quality.getText().equals(1));{
                quality.setText("Opened No Scratches/Damage");
            }
            if (quality.getText().equals(2));{
                quality.setText("Light Scratches/Damage");
            }
            if (quality.getText().equals(3));{
                quality.setText("Decent Scratches/Damage");
            }
            if (quality.getText().equals(4));{
                quality.setText("Heavy Scratches/Damage");
            }
            /**do the same for all the different possibilities for platforms*/

            platform.setText(b.getString("platform"));

            if (platform.getText().equals(0));{
                platform.setText("Playstation 4");
            }
            if (platform.getText().equals(1));{
                platform.setText("Xbox ONE");
            }
            if (platform.getText().equals(2));{
                platform.setText("PC");
            }
            if (platform.getText().equals(3));{
                platform.setText("Wii U");
            }
            if (platform.getText().equals(4));{
                platform.setText("Nintendo 3DS");
            }
            if (platform.getText().equals(5));{
                platform.setText("Playstation 3");
            }
            if (platform.getText().equals(6));{
                platform.setText("Playstation Vita<");
            }
            if (platform.getText().equals(7));{
                platform.setText("Xbox 360");
            }
            if (platform.getText().equals(8));{
                platform.setText("Nintendo Wii");
            }
            if (platform.getText().equals(9));{
                platform.setText("Nintendo DS");
            }
            if (platform.getText().equals(10));{
                platform.setText("PlayStation 2");
            }
            if (platform.getText().equals(11));{
                platform.setText("Xbox");
            }
            if (platform.getText().equals(12));{
                platform.setText("Nintendo Gamecube");
            }

            if (platform.getText().equals(13));{
                platform.setText("Game Boy Advanced");
            }

            if (platform.getText().equals(14));{
                platform.setText("Playstation Portable");
            }
            if (platform.getText().equals(15));{
                platform.setText("PlayStation");
            }
            if (platform.getText().equals(16));{
                platform.setText("Nintendo 64");
            }
            if (platform.getText().equals(17));{
                platform.setText("Game Boy");
            }
            if (platform.getText().equals(18));{
                platform.setText("SNES");
            }
            if (platform.getText().equals(19));{
                platform.setText("NES");
            }


            statusView.setText(statusDisplay.toUpperCase());
            location.setText("Latitude: " + b.getDouble("Latitude") + ", Longitude: " + b.getDouble("Longitude"));


            //statusView.setText(status);
            //ServerManager.blakeLoadItemdImage(b.getInt("itemId"));
            ServerManager.loadImage(b.getInt("itemId"));

            statusView.setText(statusDisplay.toUpperCase());
            location.setText("Latitude: " + b.getDouble("Latitude") + ", Longitude: " + b.getDouble("Longitude"));

            if(UserManager.imageRdy == 1) {
                byte[] byteArray = UserManager.getImageModel().getImage();
                Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                //imageView.setImageBitmap(UserManager.getImage());
                imageView.setImageBitmap(image);
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
        return description;
    }

    public TextView getPlatformText() {
        return platform;
    }

    public TextView getDateText() {
        return date;
    }

    public String getStatusText() {
        return statusDisplay;
    }

    //=====End function needed for Testcases=====//


}
