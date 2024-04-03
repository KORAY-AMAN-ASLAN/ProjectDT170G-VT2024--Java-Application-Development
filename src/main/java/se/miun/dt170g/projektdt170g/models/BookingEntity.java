package se.miun.dt170g.projektdt170g.models;

import jakarta.persistence.*;
import se.miun.dt170g.projektdt170g.items.Booking;

import java.time.LocalDate;
import java.time.LocalTime;
@NamedQueries({
        @NamedQuery(
                name = "BookingEntity.findByDate",
                query = "SELECT l FROM BookingEntity l WHERE l.date = :date"
        )
})
@Entity
@Table(name = "booking", schema = "dt170gprojekt", catalog = "")
public class BookingEntity {
    public static final String findByDate = "BookingEntity.findByDate";
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "booking_id", nullable = false)
    private int bookingId;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "telephone", nullable = false, length = 12)
    private String telephone;
    @Basic
    @Column(name = "amount", nullable = false)
    private int amount;
    @Basic
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Basic
    @Column(name = "time", nullable = false)
    private LocalTime time;

    public BookingEntity(Booking booking) {
        this.bookingId = booking.getBookingId();
        this.name = booking.getName();
        this.date = booking.getDate();
        this.time = booking.getTime();
        this.amount = booking.getAmount();
        this.telephone = booking.getTelephone();
    }
    public BookingEntity(){}

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
}
