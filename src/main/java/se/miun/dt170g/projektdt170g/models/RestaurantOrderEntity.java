package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;
import se.miun.dt170g.projektdt170g.items.OrderDTO;

import java.util.ArrayList;
import java.util.Collection;

@NamedQueries({
        @NamedQuery(
                name = "RestaurantOrderEntity.activeOrders",
                query = "SELECT l FROM RestaurantOrderEntity l WHERE l.orderStatus = true"
        ),
        /*@NamedQuery(
                name = "RestaurantOrderEntity.orderId",
                query = "SELECT l FROM RestaurantOrderEntity l WHERE l.orderStatus = true"
        )*/
})


@Entity
@Table(name = "restaurant_order", schema = "dt170gprojekt", catalog = "")
public class RestaurantOrderEntity {
    public static  final String allActiveOrders = "RestaurantOrderEntity.activeOrders";
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurant_order_id", nullable = false)
    private int restaurantOrderId;
    @Basic
    @Column(name = "status_appetizer", nullable = false, length = 255)
    private String statusAppetizer;
    @Basic
    @Column(name = "status_main", nullable = false, length = 255)
    private String statusMain;
    @Basic
    @Column(name = "status_dessert", nullable = false, length = 255)
    private String statusDessert;
    @Basic
    @Column(name = "restaurant_table_id", nullable = false)
    private int restaurantTableId;
    @Basic
    @Column(name = "comment", nullable = false, length = 255)
    private String comment;

    @Basic
    @Column(name = "order_status", nullable = false )
    private boolean orderStatus;

    @OneToMany(mappedBy = "restaurantOrderByOrderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<PurchasedALaCarteEntity> purchasedALaCartesByRestaurantOrderId;
    @OneToMany(mappedBy = "restaurantOrderByOrderId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<PurchasedDrinksEntity> purchasedDrinksByRestaurantOrderId;



    public RestaurantOrderEntity(OrderDTO orderDTO) {
        this.statusAppetizer = orderDTO.getStatusAppetizer();
        this.statusMain = orderDTO.getStatusMain();
        this.statusDessert = orderDTO.getStatusDessert();
        this.restaurantTableId = orderDTO.getRestaurantTableId();
        this.comment = orderDTO.getComment();
        this.purchasedALaCartesByRestaurantOrderId = new ArrayList<>();
        this.purchasedDrinksByRestaurantOrderId = new ArrayList<>();
    }

    public RestaurantOrderEntity() {
        this.purchasedALaCartesByRestaurantOrderId = new ArrayList<>();
        this.purchasedDrinksByRestaurantOrderId = new ArrayList<>();
    }

    public int getRestaurantOrderId() {
        return restaurantOrderId;
    }

    public void setRestaurantOrderId(int restaurantOrderId) {
        this.restaurantOrderId = restaurantOrderId;
    }

    public String getStatusAppetizer() {
        return statusAppetizer;
    }

    public void setStatusAppetizer(String statusAppetizer) {
        this.statusAppetizer = statusAppetizer;
    }

    public String getStatusMain() {
        return statusMain;
    }

    public void setStatusMain(String statusMain) {
        this.statusMain = statusMain;
    }

    public String getStatusDessert() {
        return statusDessert;
    }

    public void setStatusDessert(String statusDessert) {
        this.statusDessert = statusDessert;
    }

    public int getRestaurantTableId() {
        return restaurantTableId;
    }

    public void setRestaurantTableId(int restaurantTableId) {
        this.restaurantTableId = restaurantTableId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Collection<PurchasedALaCarteEntity> getPurchasedALaCartesByRestaurantOrderId() {
        return purchasedALaCartesByRestaurantOrderId;
    }

    public void setPurchasedALaCartesByRestaurantOrderId(Collection<PurchasedALaCarteEntity> purchasedALaCartesByRestaurantOrderId) {
        this.purchasedALaCartesByRestaurantOrderId = purchasedALaCartesByRestaurantOrderId;
    }

    public Collection<PurchasedDrinksEntity> getPurchasedDrinksByRestaurantOrderId() {
        return purchasedDrinksByRestaurantOrderId;
    }

    public void setPurchasedDrinksByRestaurantOrderId(Collection<PurchasedDrinksEntity> purchasedDrinksByRestaurantOrderId) {
        this.purchasedDrinksByRestaurantOrderId = purchasedDrinksByRestaurantOrderId;
    }
    public void addPurchasedALaCarte(PurchasedALaCarteEntity aLaCarteEntity){
        purchasedALaCartesByRestaurantOrderId.add(aLaCarteEntity);
        aLaCarteEntity.setRestaurantOrderByOrderId(this);
    }

    public boolean getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}