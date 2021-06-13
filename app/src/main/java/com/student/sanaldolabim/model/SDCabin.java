package com.student.sanaldolabim.model;

import java.util.ArrayList;
import java.util.List;

public class SDCabin {

    private Long id;
    private String name;
    private SDClothes sectionOne;
    private SDClothes sectionTwo;
    private SDClothes sectionThree;
    private SDClothes sectionFour;
    private SDClothes sectionFive;

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

    public SDClothes getSectionOne() {
        return sectionOne;
    }

    public void setSectionOne(SDClothes sectionOne) {
        this.sectionOne = sectionOne;
    }

    public SDClothes getSectionTwo() {
        return sectionTwo;
    }

    public void setSectionTwo(SDClothes sectionTwo) {
        this.sectionTwo = sectionTwo;
    }

    public SDClothes getSectionThree() {
        return sectionThree;
    }

    public void setSectionThree(SDClothes sectionThree) {
        this.sectionThree = sectionThree;
    }

    public SDClothes getSectionFour() {
        return sectionFour;
    }

    public void setSectionFour(SDClothes sectionFour) {
        this.sectionFour = sectionFour;
    }

    public SDClothes getSectionFive() {
        return sectionFive;
    }

    public void setSectionFive(SDClothes sectionFive) {
        this.sectionFive = sectionFive;
    }

    public List<String> getFilePaths() {
        List<String> paths = new ArrayList<>();

        if (getSectionOne() != null) {
            paths.add(getSectionOne().getPhoto());
        }
        if (getSectionTwo() != null) {
            paths.add(getSectionTwo().getPhoto());
        }
        if (getSectionThree() != null) {
            paths.add(getSectionThree().getPhoto());
        }
        if (getSectionFour() != null) {
            paths.add(getSectionFour().getPhoto());
        }
        if (getSectionFive() != null) {
            paths.add(getSectionFive().getPhoto());
        }
        return paths;
    }
}
