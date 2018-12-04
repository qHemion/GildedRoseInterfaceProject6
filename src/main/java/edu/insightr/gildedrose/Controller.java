package edu.insightr.gildedrose;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    Inventory inventory = new Inventory();

    Inventory inventorySup = new Inventory();

    Item selectedItem = null;

    Item selectedItemSup = null;

    final FileChooser fileChooser = new FileChooser();

    List<Transaction> transactions;


    int currentDay=0;

    @FXML
    public Label itemName;

    @FXML
    public Label sellIn;

    @FXML
    public Label day;

    @FXML
    public PieChart pie;

    @FXML
    public Label quality;

    @FXML
    public Label itemNameSup;

    @FXML
    public Label sellInSup;

    @FXML
    public Label qualitySup;

    @FXML
    public ListView list;

    @FXML
    public ListView listSup;

    @FXML
    public BarChart barDate;

    @FXML
    public BarChart barSellIn;

    @FXML
    public BarChart barSold;


    @FXML
    private void initialize()
    {
        pie.setTitle("Pie chart");
        pie.setLabelLineLength(10);
        pie.setLegendSide(Side.LEFT);
        pie.setLegendVisible(true);
        UpdateSelectedItem();
        UpdateSelectedItemSup();
        reBuildList();
        reBuildListSup();
        day.setText(String.valueOf(currentDay));
        barDate.setLegendVisible(false);
        barSellIn.setLegendVisible(false);
        barSold.setLegendVisible(true);
        barDate.setAnimated(false);
        barSellIn.setAnimated(false);
        barSold.setAnimated(false);
        transactions = new ArrayList<Transaction>();
    }

    /*private void printLog(String S)
    {
        if(printer!=null)
        {
            printer.print(S);
        }
    }*/

    private void PieChartUpdate()
    {
        List<PieChart.Data> listPie = new ArrayList<PieChart.Data>();
        List<String> listElement = new ArrayList<String>();
        List<Integer> listCount = new ArrayList<Integer>();

        if(inventory.getItems().size()!=0) {

            for (int i = 0; i < inventory.getItems().size(); i++) {
                if (!listElement.contains(inventory.getItems().get(i).getName())) {
                    listElement.add(inventory.getItems().get(i).getName());
                    listCount.add(1);
                } else {
                    int index = listElement.indexOf(inventory.getItems().get(i).getName());
                    listCount.set(index, listCount.get(index) + 1);
                }
            }

            for (int i = 0; i < listElement.size(); i++) {
                listPie.add(new PieChart.Data(listElement.get(i), listCount.get(i)));
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(listPie);

            pie.setData(pieChartData);
        }
    }

    private void UpdateSelectedItem()
    {
        if(selectedItem==null)
        {
            itemName.setText("no Item");
            sellIn.setText("no Item");
            quality.setText("no Item");
        }
        else{
            itemName.setText(selectedItem.getName());
            sellIn.setText(String.valueOf(selectedItem.getSellIn()));
            quality.setText(String.valueOf(selectedItem.getQuality()));
        }

    }

    private void UpdateSelectedItemSup()
    {
        if(selectedItemSup==null)
        {
            itemNameSup.setText("no Item");
            sellInSup.setText("no Item");
            qualitySup.setText("no Item");
        }
        else{
            itemNameSup.setText(selectedItemSup.getName());
            sellInSup.setText(String.valueOf(selectedItemSup.getSellIn()));
            qualitySup.setText(String.valueOf(selectedItemSup.getQuality()));
        }

    }

    @FXML
    private void UpdateSelection()
    {
        if(list.getSelectionModel().getSelectedItem()!=null)
        {
            String selection = (String) list.getSelectionModel().getSelectedItem();
            int indiceItem = Integer.parseInt(selection.split(" ")[0]);
            selectedItem = inventory.getItems().get(indiceItem-1);

        }else selectedItem= null;

        UpdateSelectedItem();

    }


    @FXML
    private void UpdateSelectionSup()
    {
        if(listSup.getSelectionModel().getSelectedItem()!=null) {
            String selection = (String) listSup.getSelectionModel().getSelectedItem();
            int indiceItem = Integer.parseInt(selection.split(" ")[0]);
            selectedItemSup = inventorySup.getItems().get(indiceItem - 1);

        }else selectedItemSup= null;

        UpdateSelectedItemSup();
    }

    @FXML
    private void BuySelection()
    {
        if(selectedItemSup!=null)
        {
            inventory.getItems().add(new Item(selectedItemSup, currentDay));
            transactions.add(new Transaction(currentDay, false));
            reBuildList();
            updateSoldBarChart();
        }
    }

    @FXML
    private void SellSelection()
    {
        if(selectedItem!=null)
        {
            inventory.getItems().remove(selectedItem);
            reBuildList();
            transactions.add(new Transaction(currentDay, true));
            selectedItem=null;
            UpdateSelectedItem();
            updateSoldBarChart();
        }
    }


    private void reBuildList()
    {
        list.getItems().clear();
        if(inventory.getItems().size()>0)
        {

            for(int i=0;i<inventory.getItems().size();i++){
                list.getItems().add(String.valueOf(i + 1) + " " + inventory.getItems().get(i).getName());

            }
            if(inventory.getItems().size()>=0) selectedItem = inventory.getItems().get(0);
            else selectedItem = null;
            PieChartUpdate();
            updateDateBarChart();
            BarSellInUpdate();
        }
        else selectedItem = null;

    }

    private void reBuildListSup()
    {
        listSup.getItems().clear();
        if(inventorySup.getItems().size()>0) {

            for (int i = 0; i < inventorySup.getItems().size(); i++) {
                listSup.getItems().add(String.valueOf(i + 1) + " " + inventorySup.getItems().get(i).getName());

            }
            if (inventorySup.getItems().size() >= 0) selectedItemSup = inventorySup.getItems().get(0);
            else selectedItemSup = null;
        }
    }

    public void updateDateBarChart()
    {
        XYChart.Series dataSeries1 = new XYChart.Series();
        List<Integer> listDays = new ArrayList<Integer>();
        List<Integer> listCount = new ArrayList<Integer>();

        if(inventory.getItems().size()!=0)
        {
            barDate.getData().clear();
            for(int i=0;i<inventory.getItems().size();i++){
                if(!listDays.contains(inventory.getItems().get(i).getDateAdded()))
                {
                    listDays.add(inventory.getItems().get(i).getDateAdded());
                    listCount.add(1);
                }
                else{
                    int index = listDays.indexOf(inventory.getItems().get(i).getDateAdded());
                    listCount.set(index, listCount.get(index)+1);
                }
            }

            for (int i=0;i<listDays.size();i++){

                dataSeries1.getData().add(new XYChart.Data("Day " + listDays.get(i), listCount.get(i)));
                dataSeries1.setName("Arrival each day");
            }

            barDate.getData().add(dataSeries1);
            barDate.setBarGap(0);        }




    }

    public void updateSoldBarChart()
    {
        XYChart.Series Bought = new XYChart.Series();
        XYChart.Series Sold = new XYChart.Series();
        Bought.setName("# of items bought");
        Sold.setName("# of items sold");
        List<Integer> listDays = new ArrayList<Integer>();
        List<Integer> listCountB = new ArrayList<Integer>();
        List<Integer> listCountS = new ArrayList<Integer>();


        if(transactions.size()!=0)
        {
            barSold.getData().clear();

            for(int i=0;i<transactions.size();i++){
                if(!listDays.contains(transactions.get(i).getDay()))
                {
                    listDays.add(transactions.get(i).getDay());
                    if(transactions.get(i).isSold)
                    {


                        listCountS.add(1);
                        listCountB.add(0);
                    }else{
                        listCountS.add(0);
                        listCountB.add(1);
                    }

                }
                else{
                    int index = listDays.indexOf(transactions.get(i).getDay());

                    if(transactions.get(i).isSold) listCountS.set(index, listCountS.get(index)+1);
                    else listCountB.set(index, listCountB.get(index)+1);
                }
            }

            for (int i=0;i<listDays.size();i++){

                Bought.getData().add(new XYChart.Data("Day " + listDays.get(i), listCountB.get(i)));
                Sold.getData().add(new XYChart.Data("Day " + listDays.get(i), listCountS.get(i)));

            }


            barSold.getData().add(Bought);
            barSold.getData().add(Sold);
            barSold.setBarGap(0);        }




    }


    private void BarSellInUpdate(){

        barSellIn.getData().clear();
        barSellIn.getXAxis().setLabel("Sell in Days");
        barSellIn.getYAxis().setLabel("Number of items");
        XYChart.Series series1 = new XYChart.Series();

        if(inventory.getItems().size()!=0) {

            barSellIn.getData().clear();



            List<Integer> listCount = new ArrayList<Integer>();

                for (int i = 0; i < inventory.getItems().size(); i++) {
                    listCount.add(inventory.getItems().get(i).getSellIn());
                }


                int value = 0;
                int count = 0;
                do {
                    for (int i = 0; i < listCount.size(); i++) {
                        if (listCount.get(i) == value) {
                            count++;
                        }
                    }
                    if (count > 0) {
                        series1.getData().add(new XYChart.Data(value + "", count));
                        //System.out.println("adding new chart " + count + " " + value);
                    }

                    value++;
                    count = 0;
                } while (value < 1100);


                barSellIn.getData().addAll(series1);
                barSellIn.setBarGap(0);
        }
    }


    public void UpdateInventory(ActionEvent actionEvent) {
        if(inventory.updateQuality()) //Si il y a eut des changements
        {
            reBuildList();
        }
        UpdateSelectedItem();
        BarSellInUpdate();
        currentDay++;
        day.setText(String.valueOf(currentDay));

    }

    /*public void ChooseFileLog(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                fileWriter = new FileWriter(file, true);
                printer = new PrintWriter(fileWriter);
                printer.printf("%s" + "%n", "test");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }*/

    public void ChooseFile(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                inventory = new Inventory(file, currentDay);
                reBuildList();
                PieChartUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void ChooseFileSup(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                inventorySup = new Inventory(file, currentDay);
                reBuildListSup();
                PieChartUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void AddItem(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                inventory.fusion(new Inventory(file, currentDay));
                reBuildList();
                PieChartUpdate();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
