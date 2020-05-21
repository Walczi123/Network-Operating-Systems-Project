package central.app.backend.centralapp.forms;

import central.app.backend.centralapp.models.Booking;

import java.time.LocalDate;

public class BookingForm extends Booking {
    private String username;

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username;}

    public int getId() {
        return super.getId();
    }

    public void setId(int id) { super.setId(id); }

    public int getOwner() { return super.getOwner(); }

    public void setOwner(int owner) { super.setOwner(owner); }

    public LocalDate getStartDateTime() { return super.getStartDateTime();}

    public void setStartDateTime(LocalDate startDateTime) { super.setStartDateTime(startDateTime); }

    public LocalDate getEndDateTime() { return super.getEndDateTime(); }

    public void setEndDateTime(LocalDate endDateTime) { super.setEndDateTime(endDateTime); }

    public double getCostPerDay() { return super.getCostPerDay(); }

    public void setCostPerDay(double costPerDay) { super.setCostPerDay(costPerDay); }

    public String getPostCode() { return super.getPostCode(); }

    public void setPostCode(String postCode) { super.setPostCode(postCode); }

    public String getCity() { return super.getCity(); }

    public void setCity(String city) { super.setCity(city); }

    public String getStreet() { return super.getStreet(); }

    public void setStreet(String street) { super.setStreet(street); }

    public String getDetails() { return super.getDetails(); }

    public void setDetails(String details) { super.setDetails(details); }

    BookingForm(){}

    public BookingForm(Booking booking, String username){
        super(booking);
        this.username = username;
    }

}
