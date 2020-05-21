package central.app.backend.centralapp.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "booking")
@EntityListeners(AuditingEntityListener.class)
public class Booking implements Serializable {

    // columns
    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "owner", nullable = false)
    private int owner;

    @Column(name = "start_date_time", nullable = false)
    private LocalDate startDateTime;

    @Column(name = "end_date_time", nullable = false)
    private LocalDate endDateTime;

    @Column(name = "cost_per_day", nullable = false)
    private double costPerDay;

    @Column(name = "post_code", nullable = false)
    private String postCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "details", nullable = false)
    private String details;

    public Booking() {
    }

    public Booking(Booking booking) {
        this.id = booking.id;
        this.owner = booking.owner;
        this.startDateTime = booking.startDateTime;
        this.endDateTime = booking.endDateTime;
        this.costPerDay = booking.costPerDay;
        this.postCode = booking.postCode;
        this.city = booking.city;
        this.street = booking.street;
        this.details = booking.details;
    }

    public Booking(int owner, LocalDate startDateTime, LocalDate endDateTime, double costPerDay, String postCode, String city, String street, String details) {
        this.owner = owner;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.costPerDay = costPerDay;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.details = details;
    }

    public Booking(int id, int owner, LocalDate startDateTime, LocalDate endDateTime, double costPerDay, String postCode, String city, String street, String details) {
        this.id = id;
        this.owner = owner;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.costPerDay = costPerDay;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.details = details;
    }

    // setters and getters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwner() { return this.owner; }

    public void setOwner(int owner) { this.owner = owner; }

    public LocalDate getStartDateTime() { return this.startDateTime; }

    public void setStartDateTime(LocalDate startDateTime) { this.startDateTime = startDateTime; }

    public LocalDate getEndDateTime() { return this.endDateTime; }

    public void setEndDateTime(LocalDate endDateTime) { this.endDateTime = endDateTime; }

    public double getCostPerDay() { return this.costPerDay; }

    public void setCostPerDay(double costPerDay) { this.costPerDay = costPerDay; }

    public String getPostCode() { return this.postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getCity() { return this.city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return this.street; }

    public void setStreet(String street) { this.street = street; }

    public String getDetails() { return this.details; }

    public void setDetails(String details) { this.details = details; }

    public void setAll(int owner, LocalDate startDateTime, LocalDate endDateTime, double costPerDay, String postCode, String city, String street, String details) {
        this.owner = owner;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.costPerDay = costPerDay;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.details = details;
    }

    public void setAll(int id, int owner, LocalDate startDateTime, LocalDate endDateTime, double costPerDay, String postCode, String city, String street, String details) {
        this.id = id;
        this.owner = owner;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.costPerDay = costPerDay;
        this.postCode = postCode;
        this.city = city;
        this.street = street;
        this.details = details;
    }

    public void setAll(Booking booking) {
        this.owner = booking.owner;
        this.startDateTime = booking.startDateTime;
        this.endDateTime = booking.endDateTime;
        this.costPerDay = booking.costPerDay;
        this.postCode = booking.postCode;
        this.city = booking.city;
        this.street = booking.street;
        this.details = booking.details;
    }

    @Override
    public String toString() {
        return "{" +",\n"+
                "\"owner\":" + this.owner + ",\n"+
                "\"startDateTime\":" + this.startDateTime + ",\n"+
                "\"endDateTime\":" + this.endDateTime + ",\n"+
                "\"costPerDay\":" + this.costPerDay +",\n"+
                "\"postCode\":" + this.postCode +",\n"+
                "\"city\":" + this.city +",\n"+
                "\"street\":" + this.street +",\n"+
                "\"details\":" + this.details +",\n"+
                "}";
    }
}
