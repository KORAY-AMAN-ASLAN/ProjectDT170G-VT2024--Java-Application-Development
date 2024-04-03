package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;

@Entity
@Table(name = "purchased_a_la_carte", schema = "dt170gprojekt", catalog = "")
public class PurchasedALaCarteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchased_ID", nullable = false)
    private int purchasedId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "restaurant_order_id", nullable = false)
    private RestaurantOrderEntity restaurantOrderByOrderId;
    @ManyToOne
    @JoinColumn(name = "a_la_carte_id", referencedColumnName = "a_la_carte_id", nullable = false )
    private ALaCarteMenuEntity aLaCarteMenuByALaCarteId;



    public int getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(int purchasedId) {
        this.purchasedId = purchasedId;
    }

    public int getOrderId() {
        return restaurantOrderByOrderId.getRestaurantOrderId();
    }


    public int getaLaCarteId() {
        return aLaCarteMenuByALaCarteId.getaLaCarteId();
    }



    public RestaurantOrderEntity getRestaurantOrderByOrderId() {
        return restaurantOrderByOrderId;
    }

    public void setRestaurantOrderByOrderId(RestaurantOrderEntity restaurantOrderByOrderId) {
        this.restaurantOrderByOrderId = restaurantOrderByOrderId;
    }

    public ALaCarteMenuEntity getaLaCarteMenuByALaCarteId() {
        return aLaCarteMenuByALaCarteId;
    }

    public void setaLaCarteMenuByALaCarteId(ALaCarteMenuEntity aLaCarteMenuByALaCarteId) {
        this.aLaCarteMenuByALaCarteId = aLaCarteMenuByALaCarteId;
    }
}
