package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.Brie;
import static edu.insightr.gildedrose.Inventory.ETC;
import static edu.insightr.gildedrose.Inventory.Sulfuras;

public class Visitor {



    void updateQuality(Item myItem){
        if (myItem.getName() != Brie
                && myItem.getName() != ETC) {
            if (myItem.getQuality() > 0) {
                if (myItem.getName() != Sulfuras) {
                    myItem.setQuality(myItem.getQuality() - 1);
                }
            }
        } else {
            if (myItem.getQuality() < 50) {
                myItem.setQuality(myItem.getQuality() + 1);

                if (myItem.getName() == ETC) {
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

        if (myItem.getName() != Sulfuras) {
            myItem.setSellIn(myItem.getSellIn() - 1);
        }

        if (myItem.getSellIn() < 0) {
            if (myItem.getName() != Brie) {
                if (myItem.getName() != ETC) {
                    if (myItem.getQuality() > 0) {
                        if (myItem.getName() != Sulfuras) {
                            myItem.setQuality(myItem.getQuality() - 1);
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
    }
}
