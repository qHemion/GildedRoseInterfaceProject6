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
import java.util.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    Inventory inventory = new Inventory();

    Item selectedItem = inventory.getItems().get(0);

    final FileChooser fileChooser = new FileChooser();

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
    public ListView list;

    @FXML
    public BarChart barDate;

    @FXML
    public BarChart barSellIn;


    @FXML
    private void initialize()
    {
        pie.setTitle("Pie chart");
        pie.setLabelLineLength(10);
        pie.setLegendSide(Side.LEFT);
        pie.setLegendVisible(true);
        reBuildList();
        day.setText(String.valueOf(currentDay));
        barDate.setLegendVisible(false);
        barSellIn.setLegendVisible(false);
        barDate.setAnimated(false);
        barSellIn.setAnimated(false);


    }

    private void PieChartUpdate()
    {
        List<PieChart.Data> listPie = new ArrayList<PieChart.Data>();
        List<String> listElement = new ArrayList<String>();
        List<Integer> listCount = new ArrayList<Integer>();

        for(int i=0;i<inventory.getItems().size();i++){
            if(!listElement.contains(inventory.getItems().get(i).getName()))
            {
                listElement.add(inventory.getItems().get(i).getName());
                listCount.add(1);
            }
            else{
                int index = listElement.indexOf(inventory.getItems().get(i).getName());
                listCount.set(index, listCount.get(index)+1);
            }
        }

        for (int i=0;i<listElement.size();i++){
            listPie.add(new PieChart.Data(listElement.get(i), listCount.get(i)));
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableList(listPie);

        pie.setData(pieChartData);
    }

    private void UpdateSelectedItem()
    {
        itemName.setText(selectedItem.getName());
        sellIn.setText(String.valueOf(selectedItem.getSellIn()));
        quality.setText(String.valueOf(selectedItem.getQuality()));
    }

    @FXML
    private void UpdateSelection()
    {
        String selection = (String) list.getSelectionModel().getSelectedItem();
        int indiceItem = Integer.parseInt(selection.split(" ")[0]);
        selectedItem = inventory.getItems().get(indiceItem-1);
        UpdateSelectedItem();
    }

    private void reBuildList()
    {
        list.getItems().clear();
        for(int i=0;i<inventory.getItems().size();i++){
            list.getItems().add(String.valueOf(i + 1) + " " + inventory.getItems().get(i).getName());

        }
        selectedItem = inventory.getItems().get(0);
        PieChartUpdate();
        updateDateBarChart();
        BarSellInUpdate();
    }

    public void updateDateBarChart()
    {
        barDate.getData().clear();
        XYChart.Series dataSeries1 = new XYChart.Series();
        List<Integer> listDays = new ArrayList<Integer>();
        List<Integer> listCount = new ArrayList<Integer>();

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
        barDate.setBarGap(0);


    }

    private void BarSellInUpdate(){

        barSellIn.getData().clear();
        barSellIn.getXAxis().setLabel("Sell in Days");
        barSellIn.getYAxis().setLabel("Number of items");
        XYChart.Series series1 = new XYChart.Series();


        List<Integer> listCount = new ArrayList<Integer>();

        for(int i = 0;i < inventory.getItems().size(); i++){
            listCount.add(inventory.getItems().get(i).getSellIn());
        }



        int value = 0;
        int count = 0;
        do{
            for(int i = 0;i < listCount.size(); i++){
                if(listCount.get(i) == value){
                    count ++;
                }
            }
            if(count > 0){
                series1.getData().add(new XYChart.Data(value + "", count));
                //System.out.println("adding new chart " + count + " " + value);
            }

            value++;
            count = 0;
        }
        while (value < 1100);


        barSellIn.getData().addAll(series1);
        barSellIn.setBarGap(0);
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
