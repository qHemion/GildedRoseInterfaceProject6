package edu.insightr.gildedrose;

import static edu.insightr.gildedrose.Inventory.*;

public class Item {

    private String name;
    private int sellIn;
    private int dateAdded;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        super();
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
        this.dateAdded = 0;
    }

    public Item(Item I, int dayOfCopy)
    {
        super();
        this.name = I.name;
        this.sellIn = I.sellIn;
        this.quality = I.quality;
        this.dateAdded = dayOfCopy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getDateAdded() {return dateAdded;}

    public void setDateAdded(int dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setSellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public boolean update()
    {
        Updater updater;
        if(Brie.equals(this.getName()))
        {
            updater = new  UpdaterBrie();
        }
        else if(ManaBun.equals(this.getName()))
        {
            updater = new UpdaterManaBun();
        }
        else if(Vest.equals(this.getName()))
        {
            updater = new UpdaterVest();
        }
        else if(Sulfuras.equals(this.getName()))
        {
            updater = new UpdaterSulfuras();
        }
        else if(Elixir.equals(this.getName()))
        {
            updater = new UpdaterElixir();
        }
        else if(ETC.equals(this.getName()))
        {
            updater = new UpdaterETC();
        }
        else updater = new Updater();
        return updater.Update(this);
    }

    public int price(boolean fromProducer)
    {
        int priceMultiplier = 0;
        if(Brie.equals(this.getName()))
        {
            priceMultiplier = 1;
        }
        else if(ManaBun.equals(this.getName()))
        {
            priceMultiplier = 1;
        }
        else if(Vest.equals(this.getName()))
        {
            priceMultiplier = 5;
        }
        else if(Sulfuras.equals(this.getName()))
        {
            priceMultiplier = 1000;
        }
        else if(Elixir.equals(this.getName()))
        {
            priceMultiplier = 8;
        }
        else if(ETC.equals(this.getName()))
        {
            priceMultiplier = 12;
        }
        int prix = priceMultiplier * quality;
        if(fromProducer) prix = (prix * 4)/5;
        return prix;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}