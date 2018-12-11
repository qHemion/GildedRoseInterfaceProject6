package edu.insightr.gildedrose;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteFile {

    private static String path = "logs.txt";

    public static void ClearFile(){
        FileWriter write = null;
        try{
            write =  new FileWriter(path, false);
            write.write("");
            write.close();
        }
        catch (IOException ex){

        }
    }

    static public void WriteToFile(String content){
        FileWriter write = null;
        try{
            write =  new FileWriter(path, true);
            write.write(content +"\n");
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
