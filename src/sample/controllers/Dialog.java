package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.data.CustomItem;
import sample.util.SerializationDeserializationUtil;

import java.util.List;

public class Dialog {
    @FXML
    TextField fileName;

    private static List<CustomItem> currentItems;

    public static void setItems(List<CustomItem> items){
        currentItems = items;
    }

    public void saveFile(){
        SerializationDeserializationUtil.serialize(currentItems, fileName.getText());
    }
}
