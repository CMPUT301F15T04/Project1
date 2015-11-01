package cmput301t4.gameswap;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by kynan on 11/1/15.
 */
public class Trade {
    private String Ownername;
    private String BorrowerName;
    private ArrayList<Item> OwnerItems;
    private ArrayList<Item> BorrowerItems;
    private Date DateTransaction;

    public Trade(String OwnerName, String BorrowerName, ArrayList<Item> OwnerItems, ArrayList<Item> BorrowerItems, Date DateTransaction){
        this.Ownername = OwnerName;
        this.BorrowerName = BorrowerName;
        this.OwnerItems = OwnerItems;
        this.BorrowerItems = BorrowerItems;
        this.DateTransaction = new Date();
    }//End Trade Constructor

}//end Trade Class
