package se.miun.dt170g.projektdt170g.items;

import se.miun.dt170g.projektdt170g.models.EventsEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private int eventID;
    private String name;


    private LocalDate date;
    private LocalTime time;
    private int price;
    private String description;

    public Event() {}

    public Event(EventsEntity eventsEntity){
        this.eventID = eventsEntity.getEventId();
        this.name = eventsEntity.getName();
        this.price = eventsEntity.getPrice();
        this.date = eventsEntity.getDate();
        this.time = eventsEntity.getTime();
        this.description = eventsEntity.getDescription();
    }
    public String getFormattedDate() {
        // Combine date and time into a LocalDateTime object
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // Define a formatter with the desired format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm");

        // Format the dateTime object and return it as a string
        return dateTime.format(formatter);
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
