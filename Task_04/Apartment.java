package by.epam.ap.hotelapp.model.impl;

import by.epam.ap.hotelapp.model.Entity;

import java.util.Objects;

public class Apartment extends Entity {
    private int id;
    private String category;
    private int price;
    private int capacity;
    private String description;
    private String pathToPicture;

    public Apartment(){}

    public Apartment(int id, String category, int price, int capacity, String description, String pathToPicture) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.capacity = capacity;
        this.description = description;
        this.pathToPicture = pathToPicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        if (!(o instanceof Apartment)) return false;
        Apartment apartment = (Apartment) o;
        return getId() == apartment.getId() &&
                Double.compare(apartment.getPrice(), getPrice()) == 0 &&
                getCapacity() == apartment.getCapacity() &&
                getCategory().equals(apartment.getCategory()) &&
                getDescription().equals(apartment.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCategory(), getPrice(), getCapacity(), getDescription());
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", capacity=" + capacity +
                ", description='" + description + '\'' +
                ", pathToPicture='" + pathToPicture + '\'' +
                '}';
    }
}
