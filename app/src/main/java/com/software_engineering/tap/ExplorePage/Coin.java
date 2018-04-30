package com.software_engineering.tap.ExplorePage;

public class Coin {
    private String name;
    private String rate;

    public Coin(){

    }
    public Coin(String name, String rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public String getRate() {
        return rate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
