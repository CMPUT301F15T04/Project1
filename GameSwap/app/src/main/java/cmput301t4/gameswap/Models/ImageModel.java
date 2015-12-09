package cmput301t4.gameswap.Models;

/**
 * Image model contains images corresponding to the items along with their information
 * such as imahe size, user name and image ID
 * @author  Preyanshu Kumar, Kynan Ly, Rupehra Chouhan , Daniel Ren, Blake Sakaluk,
 * @version Part 4
 */

public class ImageModel {

    /** Integer for image ID */
    private int itemId;
    /** String for image username */
    private String userName;
    /** The image */
    private byte[] itemPicture;

    /**
     * Constructor for Image model
     * @param itemId : Id of the image
     * @param userName :username of the image
     * @param picReceived: size of the image
     */
    public ImageModel(int itemId, String userName, byte[] picReceived) {
        this.itemId = itemId;
        this.userName = userName;
        this.itemPicture = picReceived;
    }

    /**
     * Gets the image ID
     * @return image ID
     */
    public int getImageItemId(){return itemId;}

    /**
     * Gets the image user name
     * @return user name of the image
     */
    public String getImageuserName(){return userName;}

    /**
     * Gets the image
     * @return image
     */
    public byte[] getImage(){return itemPicture;}

    /**
     * Sets the image ID
     * @param num image ID
     */
    public void setImageItemId(int num){this.itemId = num;}

    /**
     * Sets image user
     * @param user
     */
    public void setImageUser(String user){this.userName = user;}
}
