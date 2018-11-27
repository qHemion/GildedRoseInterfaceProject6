package edu.insightr.gildedrose;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileReader {


    List<Item> readInventory(File file)
    {
        List<Item> newItems = new ArrayList<Item>();
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader (file));

            JSONObject jsonObject = (JSONObject) obj;



            JSONArray JsonInventory = (JSONArray) jsonObject.get("inventory");
            Iterator<JSONObject> itemIterator = JsonInventory.iterator();
            while (itemIterator.hasNext()) {
                JSONObject currentItem = itemIterator.next();
                String name = (String) currentItem.get("name");
                long sellIn = (long) currentItem.get("sellIn");
                long quality = (long) currentItem.get("quality");
                newItems.add(new Item(name,(int) sellIn, (int) quality));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newItems;
    }
}
