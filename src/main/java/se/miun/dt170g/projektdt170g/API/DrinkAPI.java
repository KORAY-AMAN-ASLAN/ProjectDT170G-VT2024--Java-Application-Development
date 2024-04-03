package se.miun.dt170g.projektdt170g.API;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.Drink;
import se.miun.dt170g.projektdt170g.models.DrinksEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * REST API endpoint class for managing a la carte menu items.
 * Allows retrieval of dinner menu items filtered by their type.
 */
@Path("/drinks")
@Stateless
public class DrinkAPI {
    @PersistenceContext
    private EntityManager entityManager;



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Drink getDrinkByID( @PathParam("id") int id) {
        return new Drink(entityManager.find(DrinksEntity.class, id));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Drink> getAllDrinks() {

        List<DrinksEntity> drinksEntities;
        List<Drink> drinks = new ArrayList<>();
        drinksEntities = entityManager.createNamedQuery("DrinksEntity.findAll", DrinksEntity.class).getResultList();
        for (DrinksEntity drink : drinksEntities){
            drinks.add(new Drink(drink));
        }
        return drinks;
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDrink(@PathParam("id") int id, Drink drinkUpdate) {
        try {
            DrinksEntity drink = entityManager.find(DrinksEntity.class, id);
            if (drink == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Drink not found").build();
            }
            drink.setName(drinkUpdate.getName());
            drink.setDescription(drinkUpdate.getDescription());
            drink.setPrice(drinkUpdate.getPrice());
            entityManager.merge(drink);
            return Response.ok(new Drink(drink)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating drink: " + e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deleteDrink(@PathParam("id") int id) {
        try {
            DrinksEntity drink = entityManager.find(DrinksEntity.class, id);
            if (drink == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Drink not found").build();
            }
            entityManager.remove(drink);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting drink: " + e.getMessage()).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDrink(Drink drink) {
        if (drink == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Drink information must be provided").build();
        }
        try {
            DrinksEntity drinksEntity = new DrinksEntity(drink);
            entityManager.persist(drinksEntity);
            entityManager.flush(); // Ensure the drink ID is generated
            return Response.status(Response.Status.CREATED).entity(new Drink(drinksEntity)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating drink: " + e.getMessage()).build();
        }
    }
}

