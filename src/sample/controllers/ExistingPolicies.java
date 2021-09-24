package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sample.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ExistingPolicies {
    @FXML
    ListView listOfExistingPolicies;

    Controller controller;

    public void initialize(){
        String content = readExistingPolicies();
        List<String> policies = Arrays.asList(content.split("\n"));
        listOfExistingPolicies.getItems().setAll(policies);
    }

    private String readExistingPolicies() {
        Path filePath = Paths.get("src/sample/existing.txt");
        try {
            return new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
