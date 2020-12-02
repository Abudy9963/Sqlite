package com.example.myapplication;

public class Car {
    private String name;
    private String model;
    private String number;

    public Car(String number) {
        this.number = number;
    }

    public Car(String name, String model, String number) {
        this.name = name;
        this.model = model;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
