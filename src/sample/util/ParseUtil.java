package sample.util;

import sample.data.CustomItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ParseUtil {
    public static List<CustomItem> getItemsFromContent(String content) {
        List<String> stringItems = parseContent(content);
        List<CustomItem> items = new LinkedList<>();

        stringItems.forEach(s -> {
            items.add(convertToCustomItemObject(s));
        });

        return items;
    }

    public static List<String> parseContent(String content) {
        List<String> customItemsContent = new ArrayList<>();

        String foundItem = "";

        for (String tag : content.split("\\s+")) {
            if (tag.equals("<custom_item>")) {
                foundItem = "";
            } else if (tag.equals("</custom_item>")) {
                customItemsContent.add(foundItem);
            } else {
                foundItem = foundItem.concat(" " + tag);
            }
        }

        return customItemsContent;
    }

    public static CustomItem convertToCustomItemObject(String element) {
        CustomItem item = new CustomItem();

        List<String> fields = Arrays.asList("type", "description", "info", "solution", "see_also", "value_type",
                "value_data", "reg_key", "reg_item", "reg_option", "reference", "PolicySettingName", "check_type", "right_type");

        String currentField = "";
        String currentValue = "";

        element = element.replaceFirst("Policy Setting Name", "PolicySettingName");

        boolean firstType = true;

        for (String word : element.split("\\s+")) {
            if (fields.contains(word)) {
                String info = currentValue.replaceFirst(":", "").replaceAll("\"", "")
                        .trim().replaceAll(" +", " ");
                switch (currentField) {
                    case "type": {
                        if (firstType) {
                            item.setType(info);
                            firstType = false;
                        }
                        break;
                    }
                    case "description": {
                        item.setDescription(info);
                        break;
                    }
                    case "info": {
                        item.setInfo(info);
                        break;
                    }
                    case "solution": {
                        item.setSolution(info);
                        break;
                    }
                    case "see_also": {
                        item.setSeeAlso(info);
                        break;
                    }
                    case "value_type": {
                        item.setValueType(info);
                        break;
                    }
                    case "value_data": {
                        item.setValueData(info);
                        break;
                    }
                    case "reg_key": {
                        item.setRegKey(info);
                        break;
                    }
                    case "reg_item": {
                        item.setRegItem(info);
                        break;
                    }
                    case "reg_option": {
                        item.setRegOption(info);
                        break;
                    }
                    case "reference": {
                        item.setReference(info);
                        break;
                    }
                    case "PolicySettingName": {
                        item.setPolicySettingName(info);
                        break;
                    }
                    case "check_type":{
                        break;
                    }
                    case "right_type":{
                        break;
                    }
                }
                currentValue = "";
                currentField = word;
            } else {
                currentValue = currentValue.concat(" " + word);
            }
        }

        return item;
    }
}
