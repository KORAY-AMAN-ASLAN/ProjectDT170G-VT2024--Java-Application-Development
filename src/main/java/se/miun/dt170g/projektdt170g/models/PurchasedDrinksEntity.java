package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;

@Entity
@Table(name = "purchased_drinks", schema = "dt170gprojekt", catalog = "")
public class PurchasedDrinksEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchased_ID", nullable = false)
    private int purchasedId;
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "restaurant_order_id", nullable = false)
    private RestaurantOrderEntity restaurantOrderByOrderId;
    @ManyToOne
    @JoinColumn(name = "drink_id", referencedColumnName = "drink_id", nullable = false)
    private DrinksEntity drinksByDrinkId;

    public int getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(int purchasedId) {
        this.purchasedId = purchasedId;
    }

    public int getOrderId() {
        return restaurantOrderByOrderId.getRestaurantOrderId();
    }



    public int getDrinkId() {
        return drinksByDrinkId.getDrinkId();
    }





    public RestaurantOrderEntity getRestaurantOrderByOrderId() {
        return restaurantOrderByOrderId;
    }

    public void setRestaurantOrderByOrderId(RestaurantOrderEntity restaurantOrderByOrderId) {
        this.restaurantOrderByOrderId = restaurantOrderByOrderId;
    }

    public DrinksEntity getDrinksByDrinkId() {
        return drinksByDrinkId;
    }

    public void setDrinksByDrinkId(DrinksEntity drinksByDrinkId) {
        this.drinksByDrinkId = drinksByDrinkId;
    }
}
