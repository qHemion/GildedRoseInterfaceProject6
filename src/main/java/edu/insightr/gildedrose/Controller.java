package edu.insightr.gildedrose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller {

    Inventory inventory = new Inventory();

    Item selectedItem = inventory.getItems()[0];

    @FXML
    public Label itemName;

    @FXML
    public Label sellIn;

    @FXML
    public Label quality;

    @FXML
    public ListView list;

    @FXML
    private void initialize()
    {
        for(int i=0;i<inventory.getItems().length;i++){
            list.getItems().add(String.valueOf(i + 1) + " " + inventory.getItems()[i].getName());
        }
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
        selectedItem = inventory.getItems()[indiceItem-1];
        UpdateSelectedItem();
    }

    public void UpdateInventory(ActionEvent actionEvent) {
        inventory.updateQuality();
        UpdateSelectedItem();

    }
}
