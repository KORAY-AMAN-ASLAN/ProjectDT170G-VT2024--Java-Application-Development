package se.miun.dt170g.projektdt170g.items;

import se.miun.dt170g.projektdt170g.models.DrinksEntity;

public class Drink {
    private int drinkID;

    private String name;
    private String description;
    private int price;

    public Drink(int drink_id, String name, String description, int price) {
        this.drinkID = drink_id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    public Drink(DrinksEntity drink){
        this.drinkID = drink.getDrinkId();
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.price = drink.getPrice();
    }
    public Drink(){

    }

    public int getDrinkID() {
        return drinkID;
    }

    public void setDrinkID(int drinkID) {
        this.drinkID = drinkID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
