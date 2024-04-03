
package se.miun.dt170g.projektdt170g.API;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.io.Serializable;

@Stateless
@Path("/lunch")
public class LunchAPI implements Serializable {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LunchMenuEntity> getLunch(@QueryParam("week") boolean week,
                             @QueryParam("today") boolean today,
                             @QueryParam("afterToday") boolean afterToday) {
        LocalDate todayDate = LocalDate.now();
        List<LunchMenuEntity> lunchMenus;

        if (today) {
            lunchMenus = entityManager.createNamedQuery("LunchMenuEntity.findByDate", LunchMenuEntity.class)
                    .setParameter("date", todayDate)
                    .getResultList();
        } else if (week) {
            LocalDate startOfWeek = todayDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = todayDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            lunchMenus = entityManager.createNamedQuery("LunchMenuEntity.findBetweenDates", LunchMenuEntity.class)
                    .setParameter("startDate", startOfWeek)
                    .setParameter("endDate", endOfWeek)
                    .getResultList();
        } else if (afterToday) {
            lunchMenus = entityManager.createNamedQuery(LunchMenuEntity.findAfterToday, LunchMenuEntity.class).setParameter("date",todayDate).getResultList();
        } else {
            // Define your fallback logic here, such as returning an empty list or all records
            lunchMenus = entityManager.createNamedQuery("LunchMenuEntity.findAll", LunchMenuEntity.class)
                    .getResultList(); // Assuming you have a named query to fetch all records
        }

        // Use the Response builder to return the list with proper status code
        return lunchMenus;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public LunchMenuEntity getLunch(@PathParam("id") int id){
        return entityManager.find(LunchMenuEntity.class,id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/previous")
    public List<LunchMenuEntity> getPreviousLunches()
    {
        return entityManager.createNamedQuery("LunchMenuEntity.findAll", LunchMenuEntity.class).setMaxResults(14)
                .getResultList(); // Assuming you have a named query to fetch all records
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLunch(LunchMenuEntity lunchMenu) {
        try {
            entityManager.persist(lunchMenu);
            return Response.status(Response.Status.CREATED).entity(lunchMenu).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error saving lunch menu: " + e.getMessage()).build();
        }
    }
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLunch(@PathParam("id") int id, LunchMenuEntity updateEntity) {
        try {
            LunchMenuEntity lunchMenu = entityManager.find(LunchMenuEntity.class, id);
            if (lunchMenu == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Lunch menu item not found").build();
            }
            lunchMenu.setDate(updateEntity.getDate());
            lunchMenu.setName(updateEntity.getName());
            lunchMenu.setDescription(updateEntity.getDescription());
            lunchMenu.setPrice(updateEntity.getPrice());
            entityManager.merge(lunchMenu);
            return Response.ok(lunchMenu).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating lunch menu: " + e.getMessage()).build();
        }
    }
    @DELETE
    @Path("/{id}")
    public Response deleteLunch(@PathParam("id") int id) {

        try {
            LunchMenuEntity lunchMenu = entityManager.find(LunchMenuEntity.class, id);
            if (lunchMenu == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Lunch menu item not found").build();
            }
            entityManager.remove(lunchMenu);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error deleting lunch menu: " + e.getMessage()).build();
        }
    }

}



