package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class ItemFactory {

    Item createItem(String ItemType)
    {
        Item newItem = null;
        if(Brie.equals(ItemType)){
            newItem = new Item(Brie, 2, 0);
        }
        if(Vest.equals(ItemType)){
        newItem = new Item(Vest, 10, 20);
        }
        if(Sulfuras.equals(ItemType)){
        newItem = new Item(Sulfuras, 0, 80);
        }
        if(Elixir.equals(ItemType)){
        newItem = new Item(Elixir, 5, 7);
        }
        if(ETC.equals(ItemType)){
        newItem = new Item(ETC, 15, 20);
        }
        if(ManaBun.equals(ItemType)){
        newItem = new Item(ManaBun, 3, 6);
        }
        return newItem;
    }
}
