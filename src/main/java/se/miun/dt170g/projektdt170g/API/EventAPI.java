package se.miun.dt170g.projektdt170g.API;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;
import se.miun.dt170g.projektdt170g.items.Booking;
import se.miun.dt170g.projektdt170g.items.Event;
import se.miun.dt170g.projektdt170g.models.ALaCarteMenuEntity;
import se.miun.dt170g.projektdt170g.models.EventsEntity;
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Path("/events")
@Stateless
public class EventAPI {
    @PersistenceContext
    private EntityManager entityManager;
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Event getEventById(@PathParam("id") int id){
        return new Event(entityManager.find(EventsEntity.class,id));
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getThreeNextEvents() {
        LocalDate today = LocalDate.now();


        List<EventsEntity> eventsEntities = entityManager.createNamedQuery(EventsEntity.findAfterDate, EventsEntity.class)
                .setMaxResults(3)
                .setParameter("date", today)
                .getResultList();

        return eventsEntities.stream().map(Event::new).collect(Collectors.toList());
    }
    @GET
    @Path("/allEvents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getComingEvents(){
        LocalDate today = LocalDate.now();
        List<EventsEntity> eventsEntities = entityManager.createNamedQuery(EventsEntity.findAfterDate, EventsEntity.class)
                .setParameter("date", today)
                .getResultList();

        return eventsEntities.stream().map(Event::new).collect(Collectors.toList());
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int addEvent(Event event) {
        EventsEntity eventToAdd = new EventsEntity(event);
        entityManager.persist(eventToAdd);
        entityManager.flush();
        return eventToAdd.getEventId();
    }

    // this is real stupid but my transactions are wierldy handled
    // and my teacher didnt see why either so this is my current solution
    public int getLatestEventId() {
        try {
            int latestId = entityManager.createQuery("SELECT MAX(e.eventId) FROM EventsEntity e", int.class)
                    .getSingleResult();
            return latestId != 0 ? latestId : -1; // Return -1 or any indicator for "not found"
        } catch (NoResultException e) {
            return -1; // Handle the case where there are no entries yet
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvent(@PathParam("id") int id, Event updateEvent){
        EventsEntity eventsEntity = entityManager.find(EventsEntity.class, id);
        eventsEntity.setDate(updateEvent.getDate());
        eventsEntity.setDescription(updateEvent.getDescription());
        eventsEntity.setName(updateEvent.getName());
        eventsEntity.setTime(updateEvent.getTime());
        eventsEntity.setPrice(updateEvent.getPrice());
        entityManager.merge(eventsEntity);
        return Response.ok(eventsEntity).build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteEvent(@PathParam("id") int id) {
        EventsEntity eventsEntity = entityManager.find(EventsEntity.class,id);
        entityManager.remove(eventsEntity);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/eventImage/{id}")
    @Produces("image/jpeg")
    public byte[] getEventImage(@PathParam("id") long id) { // Consider changing `int` to `long` if IDs can be large
        String directoryPath = context.getInitParameter("eventImagesDirectory");
        File imageFile = new File(directoryPath, id + ".jpeg"); // Construct file name directly from ID

        if (imageFile.exists() && !imageFile.isDirectory()) { // Check if the file exists and is not a directory
            try {
                FileInputStream fis = new FileInputStream(imageFile);
                byte[] imageData = new byte[(int) imageFile.length()];
                fis.read(imageData);
                fis.close();
                return imageData;
            } catch (IOException e) {
                // Log error or handle it appropriately
                throw new WebApplicationException("Error reading image file", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } else {
            // If the file doesn't exist, you might want to return a 404 Not Found
            throw new WebApplicationException("Image not found", Response.Status.NOT_FOUND);
        }
    }

}
