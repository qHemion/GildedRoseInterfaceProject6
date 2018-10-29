package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class ItemFactory {

    Item createItem(String ItemType)
    {
        Item newItem = null;
        if(Brie.equals(ItemType)){
            newItem = new Item(Brie, 20, 20);
        }
        if(Vest.equals(ItemType)){
        newItem = new Item(Vest, 20, 20);
        }
        if(Sulfuras.equals(ItemType)){
        newItem = new Item(Sulfuras, 20, 20);
        }
        if(Elixir.equals(ItemType)){
        newItem = new Item(Elixir, 20, 20);
        }
        if(ETC.equals(ItemType)){
        newItem = new Item(ETC, 20, 20);
        }
        if(ManaBun.equals(ItemType)){
        newItem = new Item(ManaBun, 20, 20);
        }
        return newItem;
    }
}
