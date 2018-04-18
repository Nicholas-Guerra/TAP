package com.software_engineering.tap.ExplorePage;

public class Coin {
    private String name;
    private double rate;

    public Coin(){

    }
    public Coin(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
