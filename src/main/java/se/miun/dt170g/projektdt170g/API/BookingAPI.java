package se.miun.dt170g.projektdt170g.API;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.Booking;
import se.miun.dt170g.projektdt170g.models.BookingEntity;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Path("/bookings")
@Stateless
public class BookingAPI {
    @PersistenceContext
    private EntityManager entityManager;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Booking> getBookingsByDate(@QueryParam("date") String dateString)  {
        List<BookingEntity> bookingEntities;
        List<Booking> bookings;
        LocalDate date = LocalDate.parse(dateString);

        bookingEntities = entityManager.createNamedQuery(BookingEntity.findByDate, BookingEntity.class)
                .setParameter("date", date)
                .getResultList();
        bookings = bookingEntities.stream().map(Booking::new).collect(Collectors.toList());
        return bookings;

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(Booking booking) {
        if (booking == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Booking information must be provided").build();
        }
        try {
            entityManager.persist(new BookingEntity(booking));
            return Response.status(Response.Status.CREATED).entity(booking).build();
        } catch (Exception e) {

            return Response.status(Response.Status.BAD_REQUEST).entity("Error creating the booking").build();
        }
    }




}
