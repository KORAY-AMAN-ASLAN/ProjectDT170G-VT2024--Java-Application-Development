package se.miun.dt170g.projektdt170g.API;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.items.Employee;
import se.miun.dt170g.projektdt170g.models.EmployeeEntity;


import java.util.ArrayList;
import java.util.List;

@Path("/employee")
public class EmployeeAPI {
    @PersistenceContext
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee() {

        List<EmployeeEntity> employeeEntities;
        List<Employee> employees = new ArrayList<>();
        employeeEntities = entityManager.createNamedQuery("EmployeeEntity.findAll", EmployeeEntity.class).getResultList();
        for (EmployeeEntity employee : employeeEntities){
            employees.add(new Employee(employee));
        }
        return Response.ok(employees).build();
    }
}
