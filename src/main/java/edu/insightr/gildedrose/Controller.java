package edu.insightr.gildedrose;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {

    Inventory inventory = new Inventory();

    Item selectedItem = inventory.getItems()[0];

    final FileChooser fileChooser = new FileChooser();

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

    public void ChooseFile(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            try {
                inventory = new Inventory(file);

                list.getItems().clear();
                for (int i = 0; i < inventory.getItems().length; i++) {
                    list.getItems().add(String.valueOf(i + 1) + " " + inventory.getItems()[i].getName());
                }
                selectedItem = inventory.getItems()[0];
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
