package by.epam.andrewpertsev.homegadget.entity;

import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Speakers extends Device implements Serializable {

    private int id;
    private int powerConsumption;
    private int numberOfSpeakers;
    private String frequencyRange;
    private double cordLength;
    private Map<SearchCriteria.SPEAKERS, Object> map;

    public Speakers() {
    }

    public Speakers(int id, int powerConsumption, int numberOfSpeakers, String frequencyRange, double cordLength) {
        this.id = id;
        this.powerConsumption = powerConsumption;
        this.numberOfSpeakers = numberOfSpeakers;
        this.frequencyRange = frequencyRange;
        this.cordLength = cordLength;
    }

    public Speakers(Map<SearchCriteria.SPEAKERS, Object> map) {
        this.map = map;
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

    public int getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    public void setNumberOfSpeakers(int numberOfSpeakers) {
        this.numberOfSpeakers = numberOfSpeakers;
    }

    public String getFrequencyRange() {
        return frequencyRange;
    }

    public void setFrequencyRange(String frequencyRange) {
        this.frequencyRange = frequencyRange;
    }

    public double getCordLength() {
        return cordLength;
    }

    public void setCordLength(double cordLength) {
        this.cordLength = cordLength;
    }

    @Override
    public Map<SearchCriteria.SPEAKERS, Object> getMap() {
        return map;
    }

    public void setMap(Map<SearchCriteria.SPEAKERS, Object> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Speakers)) return false;
        Speakers speakers = (Speakers) o;
        return getId() == speakers.getId() &&
                getPowerConsumption() == speakers.getPowerConsumption() &&
                getNumberOfSpeakers() == speakers.getNumberOfSpeakers() &&
                Double.compare(speakers.getCordLength(), getCordLength()) == 0 &&
                getFrequencyRange().equals(speakers.getFrequencyRange()) &&
                getMap().equals(speakers.getMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPowerConsumption(), getNumberOfSpeakers(), getFrequencyRange(), getCordLength(), getMap());
    }

    @Override
    public String toString() {
        return "Speakers{" +
                "map=" + map +
                '}';
    }
}

