package com.student.sanaldolabim.model;

public class SDDrawer {

    private Long id;
    private String name;

    public SDDrawer() {

    }

    public SDDrawer(String name) {
        this.name = name;
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
}
