package cmput301t4.gameswap.Models;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import cmput301t4.gameswap.Exceptions.DateFormatException;
import cmput301t4.gameswap.Exceptions.NameTooLongException;
import cmput301t4.gameswap.Managers.UserManager;

/**
 * Stores the information about a given game
 */
public class Item {

    /** The name of the Item */
    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private Date ReleaseDate;
    /** The image of the item*/
    private Bitmap image;
    /**a unique item id that can be used to grab images*/
    private int itemid;
    /** A boolean that is true if the Item is only visible to the current user */
    private Boolean isPrivate;
    /** A numeric representation of the quality of the Item */
    private Integer Quality;
    /** The intended platform of the game mapped to a number */
    private Integer Platform;

    @Override
    public String toString() {
        return Name + ReleaseDate.toString();
    }

    /**
     * Creates a new Item without a picture
     *
     * @param Name The name of the Item
     * @param ReleaseDate The date when the game was released for purchase
     * @param isPrivate A boolean that is true if the Item is only visible to the current user
     * @param Quaility A numeric  representation of the quality of the Item
     * @param Platform The intended platform of the game mapped to a number
     * @param Description A description of the Item
     * @throws NameTooLongException This occurs if the provided Item name is more than 140 characters
     */
    public Item(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description) throws NameTooLongException {
        this.setNameText(Name);
        this.checkDate(ReleaseDate);
        this.isPrivate = isPrivate;
        this.Quality = Quaility;
        this.Platform = Platform;
        this.Description = Description;
        this.itemid= UserManager.getTrader().getCounter();
        UserManager.getTrader().incrementCounter();
    }

    //Contructor for Item with Image (Not Yet Implemented)
    /*
    public Item(String Name, String ReleaseDate, boolean isPrivate, Integer Quaility, Integer Platform, String Description) throws NameTooLongException {
        this.setNameText(Name);
        this.checkDate(ReleaseDate);
        this.isPrivate = isPrivate;
        this.Quality = Quaility;
        this.Platform = Platform;
        this.Description = Description;
    }//End Item without image constructor
    */

    //Method to ensure Date is in the correct format
    //Not sure how we want to do date.
    //There this method is TEMP
    public void checkDate(String Date) throws DateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ReleaseDate = formatter.parse(Date);
        } catch (ParseException e) {
            throw new DateFormatException();
        }
    }//End checkDate

    //Method to ensure String is within desired length
    //Not sure how we want to do date.
    //There this method is TEMP
    public void setNameText(String text) throws NameTooLongException {
        if (text.length() <= 140) {
            this.Name = text;
        } else {
            throw new NameTooLongException();
        }
    }//End setNameText

    //=====Setting public and private function=====//
    public void setPublic() {
        this.isPrivate = true;
    }//end setPublic

    public void setPrivate() {
        this.isPrivate = false;
    }//end setPrivate

    //=====Getters and Setters=====//
    //Note: Setting IsPrivate true/false method is not in this section

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public Integer getQuality() {
        return Quality;
    }

    public void setQuality(Integer quality) {
        Quality = quality;
    }

    public Integer getPlatform() {
        return Platform;
    }

    public void setPlatform(Integer platform) {
        Platform = platform;
    }

    //===== .equals Override=====//
    @Override
    public boolean equals(Object item){
        Item item1 = (Item) item;
        if(this.Name.equals(item1.getName())){
            if(this.Description.equals(item1.getDescription())){
                if(this.ReleaseDate.equals(item1.getReleaseDate())){
                    if(this.isPrivate.equals(item1.getIsPrivate())){
                        if(this.Quality.equals(item1.getQuality())){
                            if(this.Platform.equals(item1.getPlatform())){
                                return true;
                            }//end check platform
                        }//end check quality
                    }//end check isPrivate
                }//end check ReleaseDate
            }//end check Description
        }//end check Name
        return false;
    }//end item equals override

}//end Item Class