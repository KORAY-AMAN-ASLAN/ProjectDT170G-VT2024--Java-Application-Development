package se.miun.dt170g.projektdt170g.API;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.models.TableEntity;

import java.util.List;


/**
 * REST API endpoint class for managing tables.
 */


@Path("/tables")
@Stateless
public class TablesAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTables() {

        List<TableEntity> tables;
        tables = entityManager.createNamedQuery("TableSessionEntity.findAll", TableEntity.class).getResultList();
        return Response.ok(tables).build();
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateTable(@PathParam("id") int session_id, TableEntity newTable) {
        // Find the existing table in the database
        TableEntity existingTable = entityManager.find(TableEntity.class, session_id);

        //SELECT TABLE FROM TABLES WHERE id = session_id

        if (existingTable == null)
            return Response.status(Response.Status.NOT_FOUND).build(); // Table not found, return a 404

        // Update the existing table with the new information
        existingTable.setStatus(newTable.getStatus());

        return Response.ok().build();
    }

    @PUT
    @Path("/bookTables")
    public Response bookMultipleTables(List<Integer> tableAssignments){
        for ( Integer entry : tableAssignments) {
            TableEntity table = entityManager.find(TableEntity.class, entry);
            table.setStatus("Reserved");
            entityManager.merge(table);
        }
        return Response.ok().build();
    }

}