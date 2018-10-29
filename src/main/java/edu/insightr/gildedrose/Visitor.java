package edu.insightr.gildedrose;

public class Visitor {

    private final String Vest;
    private final String Brie;
    private final String Sulfuras;
    private final String ETC;
    private final String Elixir;
    private final String ManaBun;

    {
        Elixir = "Elixir of the Mongoose";
        ETC = "Backstage passes to a TAFKAL80ETC concert";
        Sulfuras = "Sulfuras, Hand of Ragnaros";
        Brie = "Aged Brie";
        Vest = "+5 Dexterity Vest";
        ManaBun = "Conjured Mana Cake";
    }

    void updateQuality(Item myItem){
        myItem.setQuality(0);
    }
}
