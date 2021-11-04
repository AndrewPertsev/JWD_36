package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class TabletPC extends Device implements Serializable {

    private int id;
    private double batteryCapacity;
    private double displayInches;
    private double memoryRom;
    private double flashMemoryCapacity;
    private String color;
    private Map<SearchCriteria.TABLETPC, Object> map;

    public TabletPC() {
    }

    public TabletPC(int id, double batteryCapacity, double displayInches, double memoryRom, double flashMemoryCapacity, String COLOR) {
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.displayInches = displayInches;
        this.memoryRom = memoryRom;
        this.flashMemoryCapacity = flashMemoryCapacity;
        this.color = COLOR;
    }


    public TabletPC(Map<SearchCriteria.TABLETPC, Object> map) {
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(double displayInches) {
        this.displayInches = displayInches;
    }

    public double getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(double memoryRom) {
        this.memoryRom = memoryRom;
    }

    public double getFlashMemoryCapacity() {
        return flashMemoryCapacity;
    }

    public void setFlashMemoryCapacity(double flashMemoryCapacity) {
        this.flashMemoryCapacity = flashMemoryCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMap(Map<SearchCriteria.TABLETPC, Object> map) {
        this.map = map;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, batteryCapacity, displayInches, memoryRom, flashMemoryCapacity, color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TabletPC)) return false;
        TabletPC that = (TabletPC) o;
        return getId() == that.getId() &&
                Double.compare(that.getBatteryCapacity(), getBatteryCapacity()) == 0 &&
                Double.compare(that.getDisplayInches(), getDisplayInches()) == 0 &&
                getMemoryRom() == that.getMemoryRom() &&
                Double.compare(that.getFlashMemoryCapacity(), getFlashMemoryCapacity()) == 0 &&
                getColor().equals(that.getColor()) &&
                getMap().equals(that.getMap());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " " + map;
    }

    @Override
    public Map<SearchCriteria.TABLETPC, Object> getMap() {
        return map;
    }
}
