package edu.insightr.gildedrose;

import java.io.File;
import java.net.URL;

public class Inventory {

    public static final String Vest = "+5 Dexterity Vest";
    public static final String Brie  = "Aged Brie";
    public static final String Sulfuras = "Sulfuras, Hand of Ragnaros";
    public static final String ETC = "Backstage passes to a TAFKAL80ETC concert";
    public  static final String Elixir = "Elixir of the Mongoose";
    public static final String ManaBun = "Conjured Mana Cake";

    private Item[] items;

    public Inventory(Item[] items) {
        super();
        this.items = items;
    }

    public Inventory() {
        super();
        JsonFileReader fileReader = new JsonFileReader();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("Inventory.json").getFile());
        items = fileReader.readInventory(file);
        /*items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        };*/


    }

    public Inventory(File file) {
        super();
        JsonFileReader fileReader = new JsonFileReader();
        items = fileReader.readInventory(file);
    }

    public Item[] getItems() {
        return items;
    }

    public void printInventory() {
        System.out.println("***************");
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.println("***************");
        System.out.println("\n");
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            items[i].update();
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
