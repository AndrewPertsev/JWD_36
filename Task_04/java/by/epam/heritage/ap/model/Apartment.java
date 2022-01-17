package by.epam.heritage.ap.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Apartment extends Entity  implements Serializable {
    private int apartmentId;
    private int category;
    private int capacity;
    private String description;
    private String pathToPicture;

    public Apartment(){}

    public Apartment(int apartmentId, int category,  int capacity, String description, String pathToPicture) {
        this.apartmentId = apartmentId;
        this.category = category;
        this.capacity = capacity;
        this.description = description;
        this.pathToPicture = pathToPicture;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int categoryId) {
        this.category = categoryId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToPicture() {
        return pathToPicture;
    }

    public void setPathToPicture(String pathToPicture) {
        this.pathToPicture = pathToPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return apartmentId == apartment.apartmentId && category == apartment.category && capacity == apartment.capacity && Objects.equals(description, apartment.description) && Objects.equals(pathToPicture, apartment.pathToPicture);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apartmentId, category, capacity, description, pathToPicture);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + apartmentId +
                ", categoryId='" + category + '\'' +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", pathToPicture='" + pathToPicture + '\'' +
                '}';
    }
}
