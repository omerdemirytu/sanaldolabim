package com.student.sanaldolabim.model;

import java.util.Date;

public class SDClothes {

    public enum ClotheType {
        HAT, PANTS, TSHIRT, COAT, SHOE
    }

    public enum ClotheColor {
        RED, GREEN, BLUE, BLACK
    }

    public enum ClothePattern {
        STRAIGHT, STRIPED
    }

    private Long id;
    private String name;
    private ClotheType clotheType;
    private ClotheColor color;
    private ClothePattern pattern;
    private Date purchaseDate;
    private Double price;
    private String photo;
    private SDDrawer sdDrawer;

    public SDClothes() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClotheType getClotheType() {
        return clotheType;
    }

    public void setClotheType(ClotheType clotheType) {
        this.clotheType = clotheType;
    }

    public ClotheColor getColor() {
        return color;
    }

    public void setColor(ClotheColor color) {
        this.color = color;
    }

    public ClothePattern getPattern() {
        return pattern;
    }

    public void setPattern(ClothePattern pattern) {
        this.pattern = pattern;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public SDDrawer getSdDrawer() {
        return sdDrawer;
    }

    public void setSdDrawer(SDDrawer sdDrawer) {
        this.sdDrawer = sdDrawer;
    }

    public static String[] getClothTypes() {
        String[] values = new String[ClotheType.values().length];
        for (int i = 0; i < ClotheType.values().length; i++) {
            values[i] = ClotheType.values()[i].toString();
        }
        return values;
    }

    public static String[] getColors() {
        String[] values = new String[ClotheColor.values().length];
        for (int i = 0; i < ClotheColor.values().length; i++) {
            values[i] = ClotheColor.values()[i].toString();
        }
        return values;
    }

    public static String[] getPatterns() {
        String[] values = new String[ClothePattern.values().length];
        for (int i = 0; i < ClothePattern.values().length; i++) {
            values[i] = ClothePattern.values()[i].toString();
        }
        return values;
    }
}
