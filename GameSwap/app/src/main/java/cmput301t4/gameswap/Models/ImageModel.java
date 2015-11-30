package cmput301t4.gameswap.Models;

import android.graphics.Bitmap;

/**
 * Created by preyansh on 11/29/15.
 */
public class ImageModel {
    private int itemId;
    private String userName;
    private Bitmap itemPicture;

    public ImageModel(int itemId, String userName, Bitmap itemPicture) {
        this.itemId = itemId;
        this.userName = userName;
        this.itemPicture = itemPicture;
    }

    public int getImageItemId(){return itemId;}

    public String getImageuserName(){return userName;}

    public Bitmap getImage(){return itemPicture;}
}
