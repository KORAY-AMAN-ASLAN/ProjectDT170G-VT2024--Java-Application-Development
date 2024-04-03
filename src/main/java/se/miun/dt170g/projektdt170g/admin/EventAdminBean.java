
package se.miun.dt170g.projektdt170g.admin;

//import jakarta.enterprise.context.RequestScoped;
//import jakarta.faces.annotation.View;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import se.miun.dt170g.projektdt170g.API.ALaCarteAPI;
import se.miun.dt170g.projektdt170g.API.EventAPI;
import se.miun.dt170g.projektdt170g.items.ALaCarteItem;
import se.miun.dt170g.projektdt170g.items.Event;
import jakarta.servlet.http.Part;
import jakarta.ws.rs.core.Response;

@Named
@ViewScoped
public class EventAdminBean implements Serializable {

    @Context
    private ServletContext context;


    private Event event = new Event();

    UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    private String temporaryFileName;

    private String message = "";

    private int selectedEventId;

    private int eventIdToDelete;
    private String action; // Define the action property

    @Inject
    private EventAPI eventAPI;
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        if("add".equals(action) || "update".equals(action) || "delete".equals(action)) {
            event = new Event();
            setMessage("");
        }

        this.action = action;
    }

    public String getEventName() {
        return event.getName();
    }

    public void setEventName(String eventName) {
        this.event.setName(eventName);
    }

    public String getEventDescription() {
        return event.getDescription();
    }

    public void setEventDescription(String eventDescription) {
        this.event.setDescription(eventDescription);
    }

    public int getEventPrice() {
        return event.getPrice();
    }

    public void setEventPrice(int eventPrice) {
        this.event.setPrice(eventPrice);
    }

    public LocalDate getEventDate() {
        return event.getDate();
    }
    public void setEventDate(LocalDate eventDate) {
        this.event.setDate(eventDate);
    }

    public int getSelectedEventId() {
        return selectedEventId;
    }

    public void setSelectedEventId(int selectedEventId) {
        this.selectedEventId = selectedEventId;
    }

    public int getEventIdToDelete() {
        return eventIdToDelete;
    }

    public void setEventIdToDelete(int getIdToDelete) {
        this.eventIdToDelete = getIdToDelete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Event> getAllEvents() {
        return eventAPI.getComingEvents();
    }

    public LocalTime getEventTime(){
        return event.getTime();
    }
    public void setEventTime(LocalTime time){
        event.setTime(time);
    }


    public void addEvent(){
        setMessage("Tillagd");
        setAction("none");
        eventAPI.addEvent(this.event);
        dummyAction(eventAPI.getLatestEventId());

    }

    public void deleteEvent() {
        setMessage("Borttagen");
        setAction("none");
        Response response = eventAPI.deleteEvent(this.eventIdToDelete);

    }

    public void updateEvent() {
        setMessage("Uppdaterad");
        setAction("none");
        Response response = eventAPI.updateEvent(selectedEventId,event);
        dummyAction(selectedEventId);
    }







    public void loadSelectedEvent() {
        this.event = eventAPI.getEventById(selectedEventId);
       ;

    }




    public void dummyAction(int id) {
        String directoryPath = context.getInitParameter("eventImagesDirectory");

        // Generate a unique file name. This could be based on a database ID, timestamp, etc.
        // For this example, I'll use a simple timestamp approach.
        String fileName = id + ".jpeg"; // Consider a more robust approach for production.

        File outputFile = new File(directoryPath, fileName);

        try (InputStream input = file.getInputStream()) {
            // Ensure directory exists.
            new File(directoryPath).mkdirs();

            // Copy file to the target directory.
            Files.copy(input, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Log success or further process the file as needed.
            System.out.println("Uploaded file successfully saved as " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            // Handle exceptions (e.g., logging, throwing a runtime exception)
            e.printStackTrace();
            throw new WebApplicationException("Error saving uploaded file", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    // Getter and setter for the action property

}
