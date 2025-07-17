package com.xiaoniu.bean;


public class School {
    private Integer id;
    private String name;

    public School() {
    }

    public School(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public School(Integer id) {
        this.id = id;
    }

    public School(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
