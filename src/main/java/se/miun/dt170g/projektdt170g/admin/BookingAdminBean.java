

package se.miun.dt170g.projektdt170g.admin;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.ViewHandler;
import jakarta.faces.component.UIViewRoot;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.ALaCarteAPI;
import se.miun.dt170g.projektdt170g.API.BookingAPI;
import se.miun.dt170g.projektdt170g.API.TablesAPI;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.net.URI;
import java.util.stream.Collectors;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TemporalType;
import se.miun.dt170g.projektdt170g.items.Booking;
import se.miun.dt170g.projektdt170g.models.LunchMenuEntity;


@Named
@ViewScoped
public class BookingAdminBean implements Serializable {



    @Inject
    private BookingAPI bookingAPI;

    @Inject
    private TablesAPI tablesAPI;

    private String action; // Define the action property

    private String message = "";

    List<Booking> bookings;
    private Map<Integer, BigDecimal> tableAssignments = new HashMap<>();

    @PostConstruct
    public void init() {

        this.bookings = bookingAPI.getBookingsByDate(LocalDate.now().toString());
        setAction("begin");


        };



    // Other properties and methods


    public  Map<Integer, BigDecimal> getTableAssignments() {
        return tableAssignments;
    }

    public void setTableAssignments( Map<Integer, BigDecimal> tableAssignments) {
        this.tableAssignments = tableAssignments;

    }

    // Method to process table assignments
    public void assignTables() {

        tablesAPI.bookMultipleTables(new ArrayList<>(tableAssignments.values().stream()
                .map(BigDecimal::intValue) // Converts BigDecimal to int, note: this truncates decimal parts without rounding
                .collect(Collectors.toList())));

        setMessage("Dagens bokningar, tilldelade bord");
        setAction("done");
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    // Temporary here for update a la carte




    public String getAction() {
        return action;
    }

    public void setAction(String action) {


        this.action = action;
    }








}
