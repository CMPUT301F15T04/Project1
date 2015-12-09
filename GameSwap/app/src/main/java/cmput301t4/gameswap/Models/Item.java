package cmput301t4.gameswap.Models;

import android.location.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cmput301t4.gameswap.Exceptions.DateFormatException;
import cmput301t4.gameswap.Exceptions.NameTooLongException;
import cmput301t4.gameswap.Managers.UserManager;

/**
 * Stores the information about a given game
 * @author Kynan Ly, Rupehra Chouhan , Daniel Ren, Blake Sakaluk, Preyanshu Kumar
 * @version Part 4
 */
public class Item {

    /** The name of the Item */
    private String Name;
    /** A description of the Item */
    private String Description;
    /** The date when the game was released for purchase */
    private Date ReleaseDate;
    /**a unique item id that can be used to grab images*/
    private int itemid;
    /** A boolean that is true if the Item is only visible to the current user */
    private Boolean isPrivate;
    /** A numeric representation of the quality of the Item */
    private Integer Quality;
    /** The intended platform of the game mapped to a number */
    private Integer Platform;
    /** Variable to specify if the item has an image associated with it*/
    private Integer hasPicture;
    private Location location;

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
        this.location = UserManager.getDefaultLocation();
        this.itemid = UserManager.getTrader().getCounter();
        UserManager.getTrader().incrementCounter();
        this.hasPicture = 0;
    }


    /**
     * Method to ensure Date is in the correct format
     * @param Date date that the user entered for item
     * @throws DateFormatException
     */
    public void checkDate(String Date) throws DateFormatException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            ReleaseDate = formatter.parse(Date);
        } catch (ParseException e) {
            throw new DateFormatException();
        }
    }

    /**
     * Method to ensure String is within desired length
     * @param text name of the item
     * @throws NameTooLongException: name of the item can be no longer than 140 characters
     */
    public void setNameText(String text) throws NameTooLongException {
        if (text.length() <= 140) {
            this.Name = text;
        } else {
            throw new NameTooLongException();
        }
    }

    /**
     * Sets public status of the item
     */
    public void setPublic() {
        this.isPrivate = false;
    }

    /**
     * Sets private status of the item
     */
    public void setPrivate() {
        this.isPrivate = true;
    }

    /**
     * Sets to 1 if the item has a picture
     */
    public void setHasPicture(){this.hasPicture = 1;}

    /**
     * Sets to 0 if the items does not have a picture
     */
    public void setNoPicture(){this.hasPicture = 0;}

    /**
     * Gets 1 0r 0 depending on if the item has a picture
     * @return 1 or 0
     */
    public int gethasPicture(){return this.hasPicture;}

    //Note: Setting IsPrivate true/false method is not in this section

    /**
     * Gets the item name
     * @return name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the item name
     * @param name name of the items
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * Sets ID for the item
     * @param num: unique ID of the item
     */
    public void setItemid(int num){itemid = num;}

    /**
     * Gets item ID
     * @return ID
     */
    public int getItemid(){return itemid;}

    /**
     * Gets item description
     * @return  item description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets item description
     * @param description item description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Gets item release date
     * @return  item release date
     */
    public String getReleaseDate() {
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
        return format1.format(ReleaseDate);
    }

    /**
     * Sets item item release date
     * @param releaseDate
     */
    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    /**
     * Gets private/public status
     * @return true/false
     */
    public Boolean getIsPrivate() {
        return isPrivate;
    }

    /**
     * Gets the item quality
     * @return quality
     */
    public Integer getQuality() {
        return Quality;
    }

    /**
     * Sets item quality
     * @param quality
     */
    public void setQuality(Integer quality) {
        Quality = quality;
    }

    /**
     * Gets platform that the item requires
     * @return item platform
     */
    public Integer getPlatform() {
        return Platform;
    }

    /**
     * Sets item platform
     * @param platform
     */
    public void setPlatform(Integer platform) {
        Platform = platform;
    }

    /**
     * .equals Override
     * @param item
     * @return
     */
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

    /**
     * Gets item location
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets item location
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}