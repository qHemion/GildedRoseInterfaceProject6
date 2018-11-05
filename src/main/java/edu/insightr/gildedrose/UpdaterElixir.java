package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;
import static edu.insightr.gildedrose.Inventory.ManaBun;

public class UpdaterElixir extends Updater {
    @Override
    public boolean Update(Item myItem)
    {
        if (myItem.getQuality() > 0) {
            myItem.setQuality(myItem.getQuality() - 1);
        }
        myItem.setSellIn(myItem.getSellIn() - 1);
        return true;
    }
}
