package edu.insightr.gildedrose;


public class Transaction {
    int day;
    boolean isSold;

    public Transaction(int Day, boolean IsSold)
    {
        day = Day;
        isSold = IsSold;
    }

    public int getDay()
    {
        return day;
    }

    public boolean getIsSold()
    {
        return isSold;
    }
}
