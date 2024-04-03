package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;
import se.miun.dt170g.projektdt170g.items.Drink;

import java.util.Collection;
@NamedQuery(
        name = "DrinksEntity.findAll",
        query = "SELECT l FROM DrinksEntity l"
)
@Entity
@Table(name = "drinks", schema = "dt170gprojekt", catalog = "")
public class DrinksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "drink_id", nullable = false)
    private int drinkId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Basic
    @Column(name = "price", nullable = false)
    private int price;
    @OneToMany(mappedBy = "drinksByDrinkId")
    private Collection<PurchasedDrinksEntity> purchasedDrinksByDrinkId;

    public DrinksEntity(){}
    public DrinksEntity(Drink drink){
        this.drinkId = drink.getDrinkID();
        this.name = drink.getName();
        this.price = drink.getPrice();
        this.description = drink.getDescription();
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
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

    public Collection<PurchasedDrinksEntity> getPurchasedDrinksByDrinkId() {
        return purchasedDrinksByDrinkId;
    }

    public void setPurchasedDrinksByDrinkId(Collection<PurchasedDrinksEntity> purchasedDrinksByDrinkId) {
        this.purchasedDrinksByDrinkId = purchasedDrinksByDrinkId;
    }
}
