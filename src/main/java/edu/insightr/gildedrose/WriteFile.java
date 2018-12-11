package edu.insightr.gildedrose;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

    private static String path = "logs";

    public WriteFile(String path)
    {
        this.path = path;
    }

    static public void WriteToFile(String content){
        FileWriter write = null;
        try{
            write =  new FileWriter(path, true);
            write.write(content);
            write.close();
        }
        catch (IOException ex){

        }

    }
    public void WriteToFile(String path, String content) {
        this.path = path;
        WriteToFile(content);
    }
}
