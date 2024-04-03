package se.miun.dt170g.projektdt170g.API;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.models.DrinksEntity;
import se.miun.dt170g.projektdt170g.models.RestaurantOrderEntity;

/**
 * REST API endpoint class for managing orders.
 * Allows inserting new orders to the API->DB.
 */
@Path("/orders")
public class RestaurantOrderAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addOrder(RestaurantOrderEntity restaurantOrderEntity) {
        if (restaurantOrderEntity != null) {
            entityManager.persist(restaurantOrderEntity);
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
