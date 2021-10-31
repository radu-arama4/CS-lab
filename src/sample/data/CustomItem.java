package sample.data;

import java.io.Serializable;

public class CustomItem implements Serializable {
    private static final long serialVersionUID = 239847239321L;

    String type;
    String description;
    String info;
    String solution;
    String seeAlso;
    String valueType;
    String valueData;
    String regKey;
    String regItem;
    String regOption;
    String reference;

    public CustomItem() {
    }

    public CustomItem(String type, String description, String info, String solution,
                      String seeAlso, String valueType, String valueData, String regKey, String regItem,
                      String regOption, String reference) {
        this.type = type;
        this.description = description;
        this.info = info;
        this.solution = solution;
        this.seeAlso = seeAlso;
        this.valueType = valueType;
        this.valueData = valueData;
        this.regKey = regKey;
        this.regItem = regItem;
        this.regOption = regOption;
        this.reference = reference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(String seeAlso) {
        this.seeAlso = seeAlso;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getValueData() {
        return valueData;
    }

    public void setValueData(String valueData) {
        this.valueData = valueData;
    }

    public String getRegKey() {
        return regKey;
    }

    public void setRegKey(String regKey) {
        this.regKey = regKey;
    }

    public String getRegItem() {
        return regItem;
    }

    public void setRegItem(String regItem) {
        this.regItem = regItem;
    }

    public String getRegOption() {
        return regOption;
    }

    public void setRegOption(String regOption) {
        this.regOption = regOption;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "CustomItem{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", info='" + info + '\'' +
                ", solution='" + solution + '\'' +
                ", seeAlso='" + seeAlso + '\'' +
                ", valueType='" + valueType + '\'' +
                ", valueData='" + valueData + '\'' +
                ", regKey='" + regKey + '\'' +
                ", regItem='" + regItem + '\'' +
                ", regOption='" + regOption + '\'' +
                ", reference='" + reference + '\'' +
                '}';
    }
}
