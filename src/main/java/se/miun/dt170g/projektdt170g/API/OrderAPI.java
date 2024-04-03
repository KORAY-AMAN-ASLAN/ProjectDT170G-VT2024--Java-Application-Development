package se.miun.dt170g.projektdt170g.API;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;
import se.miun.dt170g.projektdt170g.items.Drink;
import se.miun.dt170g.projektdt170g.items.OrderDTO;
import se.miun.dt170g.projektdt170g.models.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * REST API endpoint class for managing a la carte menu items.
 * Allows retrieval of dinner menu items filtered by their type.
 */
@Path("/order")
@Stateless
public class OrderAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "jdbc/database")
    private DataSource dataSource;;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDTO getOrder(@QueryParam("orderID") int orderID) {

        entityManager.clear();
        entityManager.flush();

        //check orderID if not given, error or just everything today

        OrderDTO order_return = new OrderDTO();
        RestaurantOrderEntity test = entityManager.find(RestaurantOrderEntity.class,orderID);

        entityManager.refresh(test);

        order_return.setOrder_ID(test.getRestaurantOrderId());
        order_return.setStatusAppetizer(test.getStatusAppetizer());
        order_return.setStatusMain(test.getStatusMain());
        order_return.setStatusDessert(test.getStatusDessert());
        order_return.setComment(test.getComment());
        order_return.setOrderStatus(test.getOrderStatus());
        order_return.setRestaurantTableId(test.getRestaurantTableId());

        for ( PurchasedALaCarteEntity purchasedALaCarte : test.getPurchasedALaCartesByRestaurantOrderId()){
            ALaCarteMenuEntity food = entityManager.find(ALaCarteMenuEntity.class,purchasedALaCarte.getaLaCarteId());
            order_return.addFood(new ALaCarteItem(food));
        }
        for (PurchasedDrinksEntity purchasedDrinks : test.getPurchasedDrinksByRestaurantOrderId() ){
            DrinksEntity drink = entityManager.find(DrinksEntity.class, purchasedDrinks.getDrinkId());
            order_return.addDrink(new Drink(drink));
        }
        return order_return;
    }
    @GET
    @Path("/activeOrders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTO> getactiveOrders() {

        entityManager.clear();
        entityManager.flush();

        //check orderID if not given, error or just everything today

        List<OrderDTO> returnOrders = new ArrayList<>();
        List<RestaurantOrderEntity> activeOrders = new ArrayList<>();

        activeOrders = entityManager.createNamedQuery(RestaurantOrderEntity.allActiveOrders,RestaurantOrderEntity.class).getResultList();

        for (RestaurantOrderEntity currentOrder : activeOrders) {
            OrderDTO orderReturn = new OrderDTO();
            entityManager.refresh(currentOrder);

            orderReturn.setOrder_ID(currentOrder.getRestaurantOrderId());
            orderReturn.setStatusAppetizer(currentOrder.getStatusAppetizer());
            orderReturn.setStatusMain(currentOrder.getStatusMain());
            orderReturn.setRestaurantTableId(currentOrder.getRestaurantTableId());
            orderReturn.setStatusDessert(currentOrder.getStatusDessert());
            orderReturn.setOrderStatus(currentOrder.getOrderStatus());
            orderReturn.setComment(currentOrder.getComment());

            for (PurchasedALaCarteEntity purchasedALaCarte : currentOrder.getPurchasedALaCartesByRestaurantOrderId()) {
                ALaCarteMenuEntity food = entityManager.find(ALaCarteMenuEntity.class, purchasedALaCarte.getaLaCarteId());
                orderReturn.addFood(new ALaCarteItem(food));
            }
            for (PurchasedDrinksEntity purchasedDrinks : currentOrder.getPurchasedDrinksByRestaurantOrderId()) {
                DrinksEntity drink = entityManager.find(DrinksEntity.class, purchasedDrinks.getDrinkId());
                orderReturn.addDrink(new Drink(drink));
            }
            returnOrders.add(orderReturn);
        }
        return returnOrders;
    }


    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addOrder(OrderDTO orderDTO) {
        try (Connection connection = dataSource.getConnection()) {
            String insertOrderSQL = "INSERT INTO restaurant_order (status_appetizer, status_main, status_dessert, restaurant_table_id, comment, order_status) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement orderStatement = connection.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
                orderStatement.setString(1, orderDTO.getStatusAppetizer());
                orderStatement.setString(2, orderDTO.getStatusMain());
                orderStatement.setString(3, orderDTO.getStatusDessert());
                orderStatement.setInt(4, orderDTO.getRestaurantTableId());
                orderStatement.setString(5, orderDTO.getComment());

                if(orderDTO.getOrderStatus()){
                    orderStatement.setInt(6, 1);
                }else{
                    orderStatement.setInt(6,0);
                }

                int affectedRows = orderStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating order failed, no rows affected.");
                }

                try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int orderId = generatedKeys.getInt(1);

                        // Insert the purchased a la carte items
                        String insertPurchasedItemSQL = "INSERT INTO purchased_a_la_carte (order_id, a_la_carte_id) VALUES (?, ?)";
                        try (PreparedStatement itemStatement = connection.prepareStatement(insertPurchasedItemSQL)) {
                            for (ALaCarteItem item : orderDTO.getFoods()) {
                                itemStatement.setInt(1, orderId);
                                itemStatement.setInt(2, item.getaLaCarteID());
                                itemStatement.executeUpdate();
                            }
                        }

                        // Insert the purchased drinks items
                        String insertPurchasedDrinkSQL = "INSERT INTO purchased_drinks (order_id, drink_id) VALUES (?, ?)";
                        try (PreparedStatement drinkStatement = connection.prepareStatement(insertPurchasedDrinkSQL)) {
                            for (Drink drink : orderDTO.getDrinks()) {
                                drinkStatement.setInt(1, orderId);
                                drinkStatement.setInt(2, drink.getDrinkID());
                                drinkStatement.executeUpdate();
                            }
                        }
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing order: " + e.getMessage()).build();
        }
        return Response.ok().build();
    }
    @PUT
    @Path("/{orderId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrder(@PathParam("orderId") int orderId, OrderDTO orderDTO) {
        if (orderId != orderDTO.getOrder_ID()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Order ID in path and body do not match.").build();
        }

        try (Connection connection = dataSource.getConnection()) {
            // Update the order details
            String updateOrderSQL = "UPDATE restaurant_order SET status_appetizer = ?, status_main = ?, status_dessert = ?, restaurant_table_id = ?, comment = ?, order_status = ? WHERE restaurant_order_id = ?";
            try (PreparedStatement orderStatement = connection.prepareStatement(updateOrderSQL)) {
                orderStatement.setString(1, orderDTO.getStatusAppetizer());
                orderStatement.setString(2, orderDTO.getStatusMain());
                orderStatement.setString(3, orderDTO.getStatusDessert());
                orderStatement.setInt(4, orderDTO.getRestaurantTableId());
                orderStatement.setString(5, orderDTO.getComment());
                if(orderDTO.getOrderStatus()){
                    orderStatement.setInt(6, 1);
                }else{
                    orderStatement.setInt(6,0);
                }
                orderStatement.setInt(7, orderId);

                int affectedRows = orderStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating order failed, no rows affected.");
                }
            }

            /*
            the following is a bit inefficient but, it deletes all purchased links and reinserts them,
            I consider this acceptable cause an order wont ever contain a huge amount of links, and it is easier
            than to make  more complex logic for what should happen if there are multiple links between the same order
            and same item, it would require to count amount of links to the same item in the database and compare it to
            the amount in the orderDto
             */
            String deletePurchasedItemsSQL = "DELETE FROM purchased_a_la_carte WHERE order_id = ?";
            try (PreparedStatement deleteItemsStatement = connection.prepareStatement(deletePurchasedItemsSQL)) {
                deleteItemsStatement.setInt(1, orderId);
                deleteItemsStatement.executeUpdate();
            }

            String deletePurchasedDrinksSQL = "DELETE FROM purchased_drinks WHERE order_id = ?";
            try (PreparedStatement deleteDrinksStatement = connection.prepareStatement(deletePurchasedDrinksSQL)) {
                deleteDrinksStatement.setInt(1, orderId);
                deleteDrinksStatement.executeUpdate();
            }

            // Re-insert the a la carte items and drinks as in the POST method
            // Insert the purchased a la carte items
            String insertPurchasedItemSQL = "INSERT INTO purchased_a_la_carte (order_id, a_la_carte_id) VALUES (?, ?)";
            try (PreparedStatement itemStatement = connection.prepareStatement(insertPurchasedItemSQL)) {
                for (ALaCarteItem item : orderDTO.getFoods()) {
                    itemStatement.setInt(1, orderId);
                    itemStatement.setInt(2, item.getaLaCarteID());
                    itemStatement.executeUpdate();
                }
            }

            // Insert the purchased drinks items
            String insertPurchasedDrinkSQL = "INSERT INTO purchased_drinks (order_id, drink_id) VALUES (?, ?)";
            try (PreparedStatement drinkStatement = connection.prepareStatement(insertPurchasedDrinkSQL)) {
                for (Drink drink : orderDTO.getDrinks()) {
                    drinkStatement.setInt(1, orderId);
                    drinkStatement.setInt(2, drink.getDrinkID());
                    drinkStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating order: " + e.getMessage()).build();
        }

        return Response.ok().build();
    }

}