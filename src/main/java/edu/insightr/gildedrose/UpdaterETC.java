package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class UpdaterETC extends Updater {
    @Override
    public boolean Update(Item myItem)
    {

            if (myItem.getQuality() < 50) {
                myItem.setQuality(myItem.getQuality() + 1);


                if (myItem.getSellIn() < 11 && myItem.getQuality()<50) {
                        myItem.setQuality(myItem.getQuality() + 1);
                        if (myItem.getSellIn() < 6 && myItem.getQuality()<50 ){
                            myItem.setQuality(myItem.getQuality() + 1);
                        }
                }

            }
            myItem.setSellIn(myItem.getSellIn() - 1);


        if (myItem.getSellIn() < 0) {

            myItem.setQuality(0);
        }
        return true;
    }
}
