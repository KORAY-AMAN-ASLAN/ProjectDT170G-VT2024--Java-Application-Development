package se.miun.dt170g.projektdt170g.admin;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class AdminBean {

    // This method could be called when the "Event" button is pressed
    public String goToMainAdmin(){
        return "admin?faces-redirect=true";
    }
    public String goToEventAdmin() {
        // Preprocess or prepare data here, such as loading event lists, checking permissions, etc.

        return "eventAdmin?faces-redirect=true"; // Navigate to the eventAdmin.xhtml page
    }
    public String goToLunchAdmin() {
        // Preprocess or prepare data here, such as loading event lists, checking permissions, etc.

        return "lunchAdmin?faces-redirect=true"; // Navigate to the eventAdmin.xhtml page
    }
    public String goToALaCarteAdmin(){
        return "alacarteAdmin?faces-redirect=true";
    }
    public String goToDrinkAdmin(){
        return "drinkAdmin?faces-redirect=true";
    }

    public String goToBookingAdmin() {
        return "bookingsAdmin?faces-redirect=true";
    }

    // You can have additional methods for "insert new event", "update event", and "delete event" actions
}
