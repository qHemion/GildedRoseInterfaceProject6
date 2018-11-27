package edu.insightr.gildedrose;

import java.io.File;
import java.net.URL;
import java.util.List;

public class Inventory {

    public static final String Vest = "+5 Dexterity Vest";
    public static final String Brie  = "Aged Brie";
    public static final String Sulfuras = "Sulfuras, Hand of Ragnaros";
    public static final String ETC = "Backstage passes to a TAFKAL80ETC concert";
    public  static final String Elixir = "Elixir of the Mongoose";
    public static final String ManaBun = "Conjured Mana Cake";

    private List<Item> items;

    public Inventory(List<Item> items) {
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

    public Inventory(File file, int day) {
        super();
        JsonFileReader fileReader = new JsonFileReader();
        items = fileReader.readInventory(file);
        for(int i=0; i<items.size(); i++)
        {
            items.get(i).setDateAdded(day);
        }
    }

    public void fusion(Inventory I2)
    {
        for(int i=0; i<I2.getItems().size(); i++)
        {
            items.add(I2.getItems().get(i));
        }
    }

    public List<Item>  getItems() {
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

    public boolean updateQuality() {
        boolean Changed = false;
        for (int i = 0; i < items.size(); i++) {
            items.get(i).update();
        }
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getSellIn()<0)
            {
                items.remove(i);
                i--;
                Changed=true;
            }
        }
        return Changed;
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        for (int i = 0; i < 10; i++) {
            inventory.updateQuality();
            inventory.printInventory();
        }
    }
}
