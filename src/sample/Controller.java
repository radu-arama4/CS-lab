package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.controllers.Dialog;
import sample.data.CustomItem;
import sample.util.ParseUtil;
import sample.util.SerializationDeserializationUtil;
import sample.util.registry.Registry2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Controller {
    Stage stage;

    @FXML
    public ListView listView;

    public TextArea type;
    public TextArea description;
    public TextArea info;
    public TextArea solution;
    public TextArea policy;
    public TextArea seeAlso;
    public TextArea valueType;
    public TextArea valueData;
    public TextArea regKey;
    public TextArea regItem;
    public TextArea regOption;
    public TextArea reference;

    public TextField searchedItem;

    public TextField searchedPolicy;

    static List<CustomItem> items = new LinkedList<>();

    List<String> selected = new LinkedList<>();

    public void setupStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(String item) {
                BooleanProperty observable = new SimpleBooleanProperty();
                observable.addListener((obs, wasSelected, isNowSelected) ->
                        selected.add(item)
                );
                return observable ;
            }
        }));

        stage = Main.getStage();
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void clearItems(){
        listView.getItems().clear();
    }

    private void loadItems(List<CustomItem> items) {
        List<String> names = new LinkedList<>();
        items.forEach(item -> {
            names.add(item.getDescription());
        });

        listView.getItems().addAll(names);
        this.items = items;
    }

    public void addNewFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));
        //Adding action on the menu item
        //Opening a dialog box
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getAbsoluteFile());

        String content = readContent(file.getAbsolutePath());

        loadItems(ParseUtil.getItemsFromContent(content));
    }

    public static String readContent(String path) throws IOException {
        Path filePath = Paths.get(path);
        return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
    }

    private void loadItemInfo(CustomItem item){
        type.setText(item.getType());
        description.setText(item.getDescription());
        info.setText(item.getInfo());
        solution.setText(item.getSolution());
        policy.setText(item.getPolicySettingName());
        seeAlso.setText(item.getSeeAlso());
        valueType.setText(item.getValueType());
        valueData.setText(item.getValueData());
        regKey.setText(item.getRegKey());
        regItem.setText(item.getRegItem());
        regOption.setText(item.getRegOption());
        reference.setText(item.getReference());
    }

    public void handleSelectedItem(MouseEvent event) {
        String description = (String) listView.getSelectionModel().getSelectedItem();
        CustomItem item = findByDescription(description);

        System.out.println(item.toString());

        loadItemInfo(item);

    }

    private CustomItem findByDescription(String description) {
        for (CustomItem item : items) {
            if (item.getDescription().equals(description)) {
                return item;
            }
        }
        return null;
    }

    public void callSaveItems(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Dialog.setItems(items);
    }

    public void showExistingPolicies(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("existingPolicies.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void openExistingPolicy(){
        items = SerializationDeserializationUtil.deserialize(searchedPolicy.getText());
        clearItems();
        loadItems(items);
    }

    public void callSaveSelectedItems(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myDialog.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

        List<CustomItem> selectedItems = new LinkedList<>();
        items.forEach(item -> {
            if (selected.contains(item.getDescription())){
                selectedItems.add(item);
            }
        });

        Dialog.setItems(selectedItems);
    }

    public void searchByName(){
        List<String> foundItems = new LinkedList<>();
        items.forEach(item -> {
            if (item.getDescription().startsWith(searchedItem.getText())){
                foundItems.add(item.getDescription());
            }
        });
        listView.getItems().setAll(foundItems);
    }

    public void performAudit(){
        for (CustomItem item:items){
            String path = item.getRegKey();

            if(path!=null){
                path = path.replaceFirst("HKLM", "HKEY_LOCAL_MACHINE");

                String data = Registry2.readRegistry(path, item.getRegItem());

                System.out.println(data);
            }

        }

    }
}
