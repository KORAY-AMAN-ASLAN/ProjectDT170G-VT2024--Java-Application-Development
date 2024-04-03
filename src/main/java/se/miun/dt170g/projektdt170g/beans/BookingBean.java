package se.miun.dt170g.projektdt170g.beans;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import se.miun.dt170g.projektdt170g.API.BookingAPI;
import se.miun.dt170g.projektdt170g.items.Booking;
import se.miun.dt170g.projektdt170g.models.BookingEntity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Calendar;
import java.util.List;


@Named
@SessionScoped

public class BookingBean implements Serializable {
    @Inject
    private BookingAPI bookingAPI;
    private Booking bookingItem = new Booking();
    private String minBookingDate = getCurrentDate();
    private List<Booking> bookingList;


    public String getName() {
        return bookingItem.getName();
    }

    public void setName(String name) {
        bookingItem.setName(name);
    }

    public String getTelephone() {
        return bookingItem.getTelephone();
    }

    public void setTelephone(String telephone) {
        bookingItem.setTelephone(telephone);
    }

    public int getAmount() {
        return bookingItem.getAmount();
    }

    public void setAmount(int amount) {
        bookingItem.setAmount(amount);
    }

    public LocalDate getDate() {
        return bookingItem.getDate();
    }

    public void setDate(LocalDate date) {
        bookingItem.setDate(date);
    }

    public LocalTime getTime() {
        return bookingItem.getTime();
    }

    public void setTime(LocalTime time) {
        bookingItem.setTime(time);
    }


    public String getMinBookingDate() {
        return minBookingDate;
    }

    public String getCurrentDate() {
        // Create a Calendar instance
        Calendar calendar = Calendar.getInstance();

        // Get the Date object corresponding to the adjusted date
        Date currentDate = calendar.getTime();

        // Format the date using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(currentDate);
    }

    public void submit() {
        bookingAPI.createBooking(bookingItem);
    }

}

