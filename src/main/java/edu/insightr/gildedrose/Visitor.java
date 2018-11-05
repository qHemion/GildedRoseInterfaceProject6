package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class Visitor {



    boolean updateQuality(Item myItem){
        if (!Brie.equals(myItem.getName())
                && !ETC.equals( myItem.getName())){
            if (myItem.getQuality() > 0) {
                if (!Sulfuras.equals(myItem.getName())) {
                    myItem.setQuality(myItem.getQuality() - 1);
                    if(ManaBun.equals(myItem.getName())) myItem.setQuality(myItem.getQuality() - 1);
                }
            }
        } else {
            if (myItem.getQuality() < 50) {
                myItem.setQuality(myItem.getQuality() + 1);

                if (ETC.equals(myItem.getName())) {
                    if (myItem.getSellIn() < 11) {
                        if (myItem.getQuality() < 50) {
                            myItem.setQuality(myItem.getQuality() + 1);
                        }
                    }

                    if (myItem.getSellIn() < 6) {
                        if (myItem.getQuality() < 50) {
                            myItem.setQuality(myItem.getQuality() + 1);
                        }
                    }
                }
            }
        }

        if (!Sulfuras.equals(myItem.getName())) {
            myItem.setSellIn(myItem.getSellIn() - 1);
        }

        if (myItem.getSellIn() < 0) {
            if (!Brie.equals(myItem.getName())) {
                if (!ETC.equals(myItem.getName())) {
                    if (myItem.getQuality() > 0) {
                        if (Sulfuras.equals(myItem.getName())) {
                            myItem.setQuality(myItem.getQuality() - 1);
                            if(ManaBun.equals(myItem.getName())) myItem.setQuality(myItem.getQuality() - 1);
                        }
                    }
                } else {
                    myItem.setQuality(0);
                }
            } else {
                if (myItem.getQuality() < 50) {
                    myItem.setQuality(myItem.getQuality() + 1);
                }
            }
        }
        return true;
    }
}
