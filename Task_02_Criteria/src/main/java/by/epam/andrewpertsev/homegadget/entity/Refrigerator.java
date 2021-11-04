package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Refrigerator extends Device implements Serializable {

    private int id;
    private double powerConsumption;
    private double weight;
    private double height;
    private double width;
    private double freezerCapacity;
    private double overallCapacity;
    private Map<SearchCriteria.REFRIGERATOR, Object> map;

    public Refrigerator() {
    }

    public Refrigerator(int id, double powerConsumption, double weight, double freezerCapacity, double overallCapacity, double height, double width) {
        this.id = id;
        this.powerConsumption = powerConsumption;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.freezerCapacity = freezerCapacity;
        this.overallCapacity = overallCapacity;
    }

    public Refrigerator(Map<SearchCriteria.REFRIGERATOR, Object> map) {
        this.map = map;
    }

    public void setMap(Map<SearchCriteria.REFRIGERATOR, Object> map) {
        this.map = map;
    }

    @Override
    public Map<SearchCriteria.REFRIGERATOR, Object> getMap() {
        return map;
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

    public double getFreezerCapacity() {
        return freezerCapacity;
    }

    public void setFreezerCapacity(double freezerCapacity) {
        this.freezerCapacity = freezerCapacity;
    }

    public double getOverallCapacity() {
        return overallCapacity;
    }

    public void setOverallCapacity(double overallCapacity) {
        this.overallCapacity = overallCapacity;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Refrigerator)) return false;
        Refrigerator that = (Refrigerator) o;
        return getId() == that.getId() &&
                Double.compare(that.getPowerConsumption(), getPowerConsumption()) == 0 &&
                Double.compare(that.getWeight(), getWeight()) == 0 &&
                Double.compare(that.getHeight(), getHeight()) == 0 &&
                Double.compare(that.getWidth(), getWidth()) == 0 &&
                Double.compare(that.getFreezerCapacity(), getFreezerCapacity()) == 0 &&
                Double.compare(that.getOverallCapacity(), getOverallCapacity()) == 0;

    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPowerConsumption(), getWeight(), getHeight(), getWidth(), getFreezerCapacity(), getOverallCapacity());
    }

}
