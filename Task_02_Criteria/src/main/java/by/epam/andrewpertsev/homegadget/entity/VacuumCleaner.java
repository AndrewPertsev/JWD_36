package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class VacuumCleaner extends Device implements Serializable {

    private int id;
    private int powerConsumption;
    private String filterType;
    private String bagType;
    private String wandType;
    private int motorSpeedRegulation;
    private int cleaningWidth;
    private Map<SearchCriteria.VACUUMCLEANER, Object> map;

    public VacuumCleaner() {
    }

    public VacuumCleaner(Map<SearchCriteria.VACUUMCLEANER, Object> map) {
        this.map = map;
    }

    public VacuumCleaner(int id, int powerConsumption, String filterType, String bagType, String wandType, int motorSpeedRegulation, int cleaningWidth) {
        this.id = id;
        this.powerConsumption = powerConsumption;
        this.filterType = filterType;
        this.bagType = bagType;
        this.wandType = wandType;
        this.motorSpeedRegulation = motorSpeedRegulation;
        this.cleaningWidth = cleaningWidth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getBagType() {
        return bagType;
    }

    public void setBagType(String bagType) {
        this.bagType = bagType;
    }

    public String getWandType() {
        return wandType;
    }

    public void setWandType(String wandType) {
        this.wandType = wandType;
    }

    public int getMotorSpeedRegulation() {
        return motorSpeedRegulation;
    }

    public void setMotorSpeedRegulation(int motorSpeedRegulation) {
        this.motorSpeedRegulation = motorSpeedRegulation;
    }

    public int getCleaningWidth() {
        return cleaningWidth;
    }

    public void setCleaningWidth(int cleaningWidth) {
        this.cleaningWidth = cleaningWidth;
    }

    @Override
    public Map<SearchCriteria.VACUUMCLEANER, Object> getMap() {
        return map;
    }

    public void setMap(Map<SearchCriteria.VACUUMCLEANER, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VacuumCleaner)) return false;
        VacuumCleaner that = (VacuumCleaner) o;
        return getId() == that.getId() &&
                getPowerConsumption() == that.getPowerConsumption() &&
                getMotorSpeedRegulation() == that.getMotorSpeedRegulation() &&
                getCleaningWidth() == that.getCleaningWidth() &&
                getFilterType().equals(that.getFilterType()) &&
                getBagType().equals(that.getBagType()) &&
                getWandType().equals(that.getWandType()) &&
                getMap().equals(that.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPowerConsumption(), getFilterType(), getBagType(), getWandType(), getMotorSpeedRegulation(), getCleaningWidth(), getMap());
    }

    @Override
    public String toString() {
        return "VacuumCleaner{" +
                "map=" + map +
                '}';
    }
}


