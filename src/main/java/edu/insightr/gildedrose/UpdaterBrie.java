package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;
import static edu.insightr.gildedrose.Inventory.ManaBun;

public class UpdaterBrie extends Updater {
    @Override
    public boolean Update(Item myItem)
    {
        if (myItem.getQuality() < 50) {
            myItem.setQuality(myItem.getQuality() + 1);
        }
        myItem.setSellIn(myItem.getSellIn() - 1);

        if (myItem.getSellIn() < 0 && myItem.getQuality() < 50) {
            myItem.setQuality(myItem.getQuality() + 1);
        }
        return true;
    }
}
