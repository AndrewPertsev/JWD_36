package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;


public class Oven extends Device implements Serializable {

    private int id;
    private double powerConsumption;
    private double weight;
    private double capacity;
    private double depth;
    private double height;
    private double width;
    private Map<SearchCriteria.OVEN, Object> map;

    public Oven() {
    }

    public Oven(int id, double powerConsumption, double weight, double capacity, double depth, double height, double width) {
        this.id = id;
        this.powerConsumption = powerConsumption;
        this.weight = weight;
        this.capacity = capacity;
        this.depth = depth;
        this.height = height;
        this.width = width;

    }

    public Oven(Map<SearchCriteria.OVEN, Object> map) {
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setMap(Map<SearchCriteria.OVEN, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Oven)) return false;
        Oven oven = (Oven) o;
        return getId() == oven.getId() &&
                Double.compare(oven.getPowerConsumption(), getPowerConsumption()) == 0 &&
                Double.compare(oven.getWeight(), getWeight()) == 0 &&
                Double.compare(oven.getCapacity(), getCapacity()) == 0 &&
                Double.compare(oven.getDepth(), getDepth()) == 0 &&
                Double.compare(oven.getHeight(), getHeight()) == 0 &&
                Double.compare(oven.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPowerConsumption(), getWeight(), getCapacity(), getDepth(), getHeight(), getWidth());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " " + map;
    }

    @Override
    public Map<SearchCriteria.OVEN, Object> getMap() {
        return map;
    }
}