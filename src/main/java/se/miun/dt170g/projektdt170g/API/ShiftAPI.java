package se.miun.dt170g.projektdt170g.API;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.Shift;
import se.miun.dt170g.projektdt170g.models.ShiftEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * REST API endpoint class for managing shift items.
 * Allows retrieval of shift items filtered by their type.
 */

@Path("/shift")
public class ShiftAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShift() {

        List<ShiftEntity> shiftEntities;
        List<Shift> shifts = new ArrayList<>();
        shiftEntities = entityManager.createNamedQuery("ShiftEntity.findAll", ShiftEntity.class).getResultList();
        for (ShiftEntity shift : shiftEntities){
            shifts.add(new Shift(shift));
        }
        return Response.ok(shifts).build();
    }

    @GET
    @Path("/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShiftByDate(@PathParam("date") String date) {

        List<ShiftEntity> shifts = new ArrayList<>();
        shifts = entityManager.createNamedQuery("ShiftEntity.findByDate", ShiftEntity.class).setParameter("date", date).getResultList();

        return Response.ok(shifts).build();
    }

}
