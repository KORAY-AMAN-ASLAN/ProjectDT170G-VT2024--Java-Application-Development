package se.miun.dt170g.projektdt170g.beans;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import se.miun.dt170g.projektdt170g.API.EventAPI;
import se.miun.dt170g.projektdt170g.items.Event;
import se.miun.dt170g.projektdt170g.models.EventsEntity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;


@Named
@SessionScoped

public class EventsBean  implements Serializable{

    @Inject
    private EventAPI eventAPI;

    private List<Event> eventsEntities;
    private EventsEntity events;
    private static final Logger LOGGER = Logger.getLogger(EventsBean.class.getName());

    @PostConstruct
    public void init() {
        fetchEvents();
    }

    private void fetchEvents() {
        try {
            this.eventsEntities = eventAPI.getThreeNextEvents();
            LOGGER.info("Fetched events: " + eventsEntities);
        } catch (Exception e) {
            LOGGER.severe("Error fetching events: " + e.getMessage());
        }
    }

    // The rest of your getters and setters for the bean properties.
    // ...

    public List<Event> getEventsEntities() {
        return eventsEntities;
    }

    public void setEventsEntities(List<Event> eventsEntities) {
        this.eventsEntities = eventsEntities;
    }



    // Getters and Setters

    public List<Event> getEvents() {
        return eventsEntities;
    }




    public String getName() {
        return events.getName();
    }

    public void setName(String name) {
        events.setName(name);
    }

    public LocalDate getDate() {
        if (this.events != null) {
            return events.getDate();
        } else {
            // Handle the case where events is null, possibly by logging or throwing a more descriptive error
            return null; // or throw new IllegalStateException("Events is not initialized.");
        }
    }


    public String fetchEventImageAsBase64(int id) throws IOException {
        byte[] imageData = eventAPI.getEventImage(id);
        return Base64.getEncoder().encodeToString(imageData);
    }

    public void setDate(LocalDate date) {
        events.setDate(date);
    }

    public String getFormattedDate() {
        return events.getFormattedDate();
    }

    public int getPrice() {
        return events.getPrice();
    }

    public void setPrice(int price) {
        events.setPrice(price);
    }

    public String getDescription() {
        return events.getDescription();
    }

    public void setDescription(String description) {
        events.setDescription(description);
    }



}



