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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", sellIn=" + sellIn +
                ", quality=" + quality +
                '}';
    }
}