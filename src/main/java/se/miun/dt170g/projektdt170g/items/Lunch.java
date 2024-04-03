package se.miun.dt170g.projektdt170g.items;

import jakarta.enterprise.inject.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * this class represent a lunch item
 */

public class Lunch  {
    private int lunch_id;
    private String name;
    private String description;
    private Date date;
    private int price;

    public Lunch(int lunch_id, String name, String description, Date date, int price) {
        this.lunch_id = lunch_id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public int getLunch_id() {
        return lunch_id;
    }

    public void setLunch_id(int lunch_id) {
        this.lunch_id = lunch_id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
