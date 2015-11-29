package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301t4.gameswap.Managers.InventoryManager;
import cmput301t4.gameswap.Models.Item;
import cmput301t4.gameswap.R;

/**
 * Created by Rupehra on 2015-11-29.
 */


public class ListViewAdapter  extends ArrayAdapter<Item> {

    //boolean[] checkBoxStat;
    Context context;
    private ArrayList<Item> inventory;
    private LayoutInflater inflater;
    ItemHolder itemholder;

    public ListViewAdapter(Context context, int textViewResourceId, ArrayList<Item> inventory) {
        super(context, R.layout.mineinventorylistviewtext, inventory);
        this.inventory = InventoryManager.getInstance().getItems();
        this.context = context;
        //checkBoxStat = new boolean[inventory.size()];


    }

    private class ItemHolder {
        public TextView itemName;
        public CheckBox checkBox;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {

            inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mineinventorylistviewtext, null);
            itemholder = new ItemHolder();
            itemholder.itemName = (TextView) convertView.findViewById(R.id.nameOfItem);
            itemholder.checkBox = (CheckBox) convertView.findViewById(R.id.myInventoryCheckbox);

            convertView.setTag(itemholder);

        } else {
            itemholder = (ItemHolder) convertView.getTag();
        }
        itemholder.itemName.setText(InventoryManager.getInstance().getItem(0).getName().toString());


        return convertView;
    }

}
