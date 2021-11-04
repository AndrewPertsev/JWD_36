package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Laptop extends Device implements Serializable {

    private int id;
    private double batteryCapacity;
    private String os;
    private int memoryRom;
    private int systemMemory;
    private double cpu;
    private double displayInches;
    private Map<SearchCriteria.LAPTOP, Object> map;

    public Laptop() {
    }

    public Laptop(int id, double batteryCapacity, String os, int memoryRom, int systemMemory, double cpu, double displayInches) {
        this.id = id;
        this.batteryCapacity = batteryCapacity;
        this.os = os;
        this.memoryRom = memoryRom;
        this.systemMemory = systemMemory;
        this.cpu = cpu;
        this.displayInches = displayInches;
    }

    public Laptop(Map<SearchCriteria.LAPTOP, Object> map) {
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

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(int memoryRom) {
        this.memoryRom = memoryRom;
    }

    public int getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(int systemMemory) {
        this.systemMemory = systemMemory;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(double displayInches) {
        this.displayInches = displayInches;
    }

    @Override
    public Map<SearchCriteria.LAPTOP, Object> getMap() {
        return map;
    }

    public void setMap(Map<SearchCriteria.LAPTOP, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return getId() == laptop.getId() &&
                Double.compare(laptop.getBatteryCapacity(), getBatteryCapacity()) == 0 &&
                Double.compare(laptop.getMemoryRom(), getMemoryRom()) == 0 &&
                getSystemMemory() == laptop.getSystemMemory() &&
                Double.compare(laptop.getCpu(), getCpu()) == 0 &&
                Double.compare(laptop.getDisplayInches(), getDisplayInches()) == 0 &&
                Objects.equals(getOs(), laptop.getOs()) &&
                Objects.equals(getMap(), laptop.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBatteryCapacity(), getOs(), getMemoryRom(), getSystemMemory(), getCpu(), getDisplayInches(), getMap());
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "map=" + map +
                '}';
    }
}
