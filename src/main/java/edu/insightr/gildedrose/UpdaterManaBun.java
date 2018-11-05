package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class UpdaterManaBun extends Updater {
    @Override
    public boolean Update(Item myItem)
    {
            if (myItem.getQuality() > 0) {

                    myItem.setQuality(myItem.getQuality() - 2);

            }


            myItem.setSellIn(myItem.getSellIn() - 1);


        if (myItem.getSellIn() < 0 && (myItem.getQuality() > 0)) {

            myItem.setQuality(myItem.getQuality() - 2);

        }
        return true;
    }
}
