package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class UpdaterVest extends Updater {

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
